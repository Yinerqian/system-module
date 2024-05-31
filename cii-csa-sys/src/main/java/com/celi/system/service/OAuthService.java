package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.cii.common.exception.AuthenticationException;
import com.celi.cii.common.exception.ServiceException;
import com.celi.system.constant.SystemConstants;
import com.celi.system.crypto.BCryptPasswordEncoder;
import com.celi.system.dao.UserRepository;
import com.celi.system.dto.PermissionGroupDTO;
import com.celi.system.entity.*;
import com.celi.system.enums.PermissionTypeEnum;
import com.celi.system.enums.ServiceCode;
import com.celi.system.enums.UserStatusEnum;
import com.celi.system.utils.DateUtils;
import com.celi.system.utils.PwdSecurityKey;
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
    private UserRepository userRepository;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private PermissionGroupService permissionGroupService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private RoleService roleService;

    public void userLogin(UserLoginEntity userLoginEntity) {
        User userInfo = userRepository.findUserByLoginName(userLoginEntity.getUserName());
        if (null == userInfo) {
            throw new ServiceException(ServiceCode.UNKNOWN_USER.getMessage());
        }

        if (userInfo.getDisabled() == UserStatusEnum.DISABLED) {
            throw new ServiceException("用户已被禁用，请联系管理员");
        }

        if (ObjectUtil.isNotNull(userInfo.getExpired())) {
            if (DateUtil.date().isAfter(userInfo.getExpired())) {
                throw new ServiceException("用户已过期，请联系管理员");
            }
        }
        String pwd = PwdSecurityKey.decryptPwd(userInfo.getPassword());
        boolean isPasswordCorrect = StrUtil.equals(pwd, userLoginEntity.getPassword());
        if (!isPasswordCorrect) {
            throw new ServiceException(ServiceCode.CHECK_INPUT_PASSWORD.getMessage());
        }
        userInfo.setLastLoginDate(DateUtils.now());
        userRepository.save(userInfo);
        StpUtil.login(userInfo.getUserId());
    }

    public User queryUserInfo(String userId) {
        User userInfo = userRepository.findByUserId(userId);
        userInfo.setRoleList(roleService.findAllByRoleIdIn(userId));

        List<String> userRoleIds = userInfo.getRoleList().stream().map(Role::getRoleId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userRoleIds)) {
            // 根据角色ID查询所有的权限
            List<RolePermission> userRolePermissions = rolePermissionService.queryByRoleIds(userRoleIds);
            List<String> permissionIds = userRolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
            List<Permission> permissionList = permissionService.queryPermissionsByIds(permissionIds);
            Set<String> permissionCodeSet = permissionList.stream().filter(permission -> {
                return permission.getPermissionType().ordinal() == PermissionTypeEnum.OP.ordinal();
            }).map(Permission::getPermissionCode).collect(Collectors.toSet());
            userInfo.setPermissionCode(permissionCodeSet);
        }
        return userInfo;

    }

    /**
     * @return 扁平权限树
     */
    public List<PermissionGroup> userPermissionFlatByGroup() {
        // 根据角色ID查询所有的权限
        List<RolePermission> rolePermissions = getRolePermissions();
        // 查询权限列表
        List<Permission> userPermissions = permissionService.queryPermissionsByIds(
                rolePermissions.stream()
                        .map(RolePermission::getPermissionId)
                        .collect(Collectors.toList()))
                .stream()
                .sorted(Comparator.comparingInt(Permission::getSort))
                .collect(Collectors.toList());
        Map<String, List<Permission>> userPermissionMap = new HashMap<>();

        List<PermissionGroup> permissionGroups = new ArrayList<>();

        userPermissions.forEach(userPermission -> {
            String groupName = userPermission.getGroupName();
            Optional<PermissionGroup> first = permissionGroups.stream()
                    .filter(group -> group.getGroupName().equals(groupName))
                    .findFirst();
            if (first.isPresent()) {
                PermissionGroup targetGroup = first.get();
                int i = permissionGroups.indexOf(targetGroup);
                targetGroup.getPermissionList().add(userPermission);
                permissionGroups.set(i, targetGroup);
            } else {
                PermissionGroup permissionGroup = new PermissionGroup();
                permissionGroup.setGroupName(groupName);
                List<Permission> permissions = new ArrayList<>();
                permissions.add(userPermission);
                permissionGroup.setPermissionList(permissions);
                permissionGroups.add(permissionGroup);
            }
        });
        return permissionGroups;
    }

    private List<RolePermission> getRolePermissions() {
        String userId = StpUtil.getLoginIdAsString();
        List<UserRole> userRoles = userRoleService.findRoleIds(userId);
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new ServiceException("用户未配置权限，请联系管理员");
        }
        // 该用户所有的角色ID
        List<String> userRoleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        // 根据角色ID查询所有的权限
        List<RolePermission> rolePermissions = rolePermissionService.queryByRoleIds(userRoleIds);
        if (CollectionUtils.isEmpty(rolePermissions)) {
            throw new ServiceException("用户未配置权限，请联系管理员");
        }
        return rolePermissions;
    }

    public List<PermissionGroupDTO> userPermissionByGroup() {
        // 根据角色ID查询所有的权限
        List<RolePermission> rolePermissions = getRolePermissions();
        // 查询权限分组
        List<PermissionGroup> allPermissionGroups = permissionGroupService.findAll();
        // 查询权限列表
        List<Permission> userPermissions = permissionService.queryPermissionsByIds(rolePermissions.stream()
                .map(RolePermission::getPermissionId).collect(Collectors.toList()));

        // 排序
        allPermissionGroups = allPermissionGroups.stream().sorted(Comparator.comparingInt(PermissionGroup::getSort)).collect(Collectors.toList());
        userPermissions = userPermissions.stream().sorted(Comparator.comparingInt(Permission::getSort)).collect(Collectors.toList());

        // 2023-3-13 由按分组NAME修改为按分组ID匹配
        Map<String, List<Permission>> userPermissionMap = userPermissions.stream()
                .filter(permission -> {
                    return permission.getPermissionType().ordinal() == PermissionTypeEnum.MENU.ordinal();
                }).collect(Collectors.groupingBy(Permission::getGroupId));
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
            List<Permission> permissions = userPermissionMap.get(permissionGroup.getGroupId());
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
