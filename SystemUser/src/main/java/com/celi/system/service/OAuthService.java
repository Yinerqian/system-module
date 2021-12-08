package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.UserRepository;
import com.celi.system.dto.PermissionGroupDTO;
import com.celi.system.entity.*;
import com.celi.system.enums.ServiceCode;
import com.celi.system.exception.AuthenticationException;
import com.celi.system.utils.DateUtils;
import com.celi.system.utils.PwdSecurityKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ce-li
 * @date 2021/11/11
 */
@Service
public class OAuthService {

    @Resource
    private UserRepository UserRepository;
    @Resource
    private UserRoleService UserRoleService;
    @Resource
    private PermissionService PermissionService;
    @Resource
    private PermissionGroupService permissionGroupService;
    @Resource
    private RolePermissionService RolePermissionService;

    public void userLogin(UserLoginEntity userLoginEntity) {
        User userInfo = UserRepository.findUserByLoginName(userLoginEntity.getUserName());
        if (null == userInfo) {
            throw new AuthenticationException(ServiceCode.UNKNOWN_USER.getMessage());
        }
        if (!StringUtils.equals(PwdSecurityKey.decryptPwd(userInfo.getPassword()), userLoginEntity.getPassword())) {
            throw new AuthenticationException(ServiceCode.CHECK_INPUT_PASSWORD.getMessage());
        }
        userInfo.setLastLoginDate(DateUtils.now());
        UserRepository.save(userInfo);
        StpUtil.login(userInfo.getUserId());
    }

    public User queryUserInfo(String userId) {
        User userInfo = UserRepository.findByUserId(userId);
        List<UserRole> userRoles = UserRoleService.findRoleIds(userId);
        return userInfo;
    }

    public List<PermissionGroupDTO> userPermissionByGroup() {
        String userId = StpUtil.getLoginIdAsString();
        List<UserRole> userRoles = UserRoleService.findRoleIds(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new AuthenticationException("用户未配置权限，请联系管理员");
        }
        // 该用户所有的角色ID
        List<String> userRoleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 根据角色ID查询所有的权限
        List<RolePermission> rolePermissions = RolePermissionService.queryByRoleIds(userRoleIds);
        if (CollectionUtils.isEmpty(rolePermissions)) {
            throw new AuthenticationException("用户未配置权限，请联系管理员");
        }
        // 查询权限分组
        List<PermissionGroup> allPermissionGroups = permissionGroupService.findAll();
        // 查询权限列表
        List<Permission> userPermissions = PermissionService.queryPermissionsByIds(rolePermissions.stream()
                .map(RolePermission::getPermissionId).collect(Collectors.toList()));
        Map<String, List<Permission>> userPermissionMap = userPermissions.stream().collect(Collectors.groupingBy(Permission::getGroupName));
        // 返回树状数组
        return generateGroupTree(allPermissionGroups, userPermissionMap);
    }

    /**
     * 生成权限树(利用指针指向内存空间的原理)
     *
     * @param allPermissionGroups 所有权限分组 List
     * @param userPermissionMap   用户具体权限
     * @return 权限树
     */
    private List<PermissionGroupDTO> generateGroupTree(List<PermissionGroup> allPermissionGroups, Map<String, List<Permission>> userPermissionMap) {
        // 生成 map 存储元素
        Map<String, PermissionGroupDTO> map = new HashMap<>();
        // 设置顶层节点
        PermissionGroupDTO parentNode = new PermissionGroupDTO();
        parentNode.setGroupId(SystemConstants.NO_PARENT_PERMISSION_GROUP);
        map.put(SystemConstants.NO_PARENT_PERMISSION_GROUP, parentNode);

        // 初始化 Map
        allPermissionGroups.forEach(permissionGroup -> map.put(permissionGroup.getGroupId(), convertPermissionGroup2DTO(permissionGroup)));

        // 通过 Map 生成树结构
        allPermissionGroups.forEach(permissionGroup -> {
            // 设置具体权限菜单
            List<Permission> permissions = userPermissionMap.get(permissionGroup.getGroupName());
            if (CollectionUtil.isNotEmpty(permissions)) {
                map.get(permissionGroup.getGroupId()).setPermissionList(permissions);
            }

            // 查找父元素，存在则将该元素插入到 children
            if (map.containsKey(permissionGroup.getParentGroupId())) {
                map.get(permissionGroup.getParentGroupId()).getChildren().add(map.get(permissionGroup.getGroupId()));
            }
        });
        return map.get(SystemConstants.NO_PARENT_PERMISSION_GROUP).getChildren();
    }

    private PermissionGroupDTO convertPermissionGroup2DTO(PermissionGroup permissionGroup) {
        PermissionGroupDTO permissionGroupDTO = new PermissionGroupDTO();
        BeanUtils.copyProperties(permissionGroup, permissionGroupDTO);
        return permissionGroupDTO;
    }


}
