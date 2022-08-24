package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.celi.system.crypto.BCryptPasswordEncoder;
import com.celi.system.dto.ResponseDTO;
import com.celi.system.entity.*;
import com.celi.system.enums.PermissionTypeEnum;
import com.celi.system.enums.ServiceCode;
import com.celi.system.exception.AuthenticationException;
import com.celi.system.utils.DateUtils;
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
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private AppPermissionRoleService appPermissionRoleService;
    @Resource
    private PermissionService permissionService;

    public void userLogin(UserLoginEntity userLoginEntity) {
        User userInfo = userService.findUserByLoginName(userLoginEntity.getUserName());
        if (null == userInfo) {
            throw new AuthenticationException(ServiceCode.UNKNOWN_USER.getMessage());
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isPasswordCorrect = encoder.matches(userLoginEntity.getPassword(), userInfo.getPassword());
        if (!isPasswordCorrect) {
            throw new AuthenticationException(ServiceCode.CHECK_INPUT_PASSWORD.getMessage());
        }
        userInfo.setLastLoginDate(DateUtils.now());
        userService.save(userInfo);
        StpUtil.login(userInfo.getUserId());
    }

    public User queryUserInfo(Integer userId) {
        User userInfo = userService.findByUserId(userId);
        JSONArray permissionJSONArray = JSONUtil.parseArray(listPermissionsByUserId(userId));
        Set permissions = permissionJSONArray.stream().collect(Collectors.toSet());
        userInfo.setPermissionCode(permissions);
        return userInfo;
    }

    public String listPermissionsByUserId(Integer userId) {
        // 首先判断是否是平台管理员
        User user = userService.findByUserId(userId);
        if (user == null) {
            return "";
        }

        if (user.getIsPlatformAdmin()) {
            List<String> permissions = userService.getPermissionsByUsernameAndAppCode(user.getLoginName(), null);
            return JSONUtil.toJsonStr(permissions);
        } else {
            // 1. 根据用户id查询角色id集合
            List<Integer> roleIds = userRoleService.findRoleIds(userId).stream().map(UserRole::getRoleId).collect(Collectors.toList());

            // 2. 根据角色集合查询权限集合(去重)
            List<Integer> permissionList = appPermissionRoleService.listPermissionsByRoleIds(roleIds);
            if (permissionList == null || permissionList.size() == 0) {
                return null;
            }

            List<String> PermCodeList = permissionService.listPermissionsByPermissionList(permissionList);
            return JSONUtil.toJsonStr(PermCodeList);
        }
    }

    public List<PermissionGroup> listAppMenusByUserId(Integer tenantId, Integer userId, String appCode) {
        // 1. 查询 appId
//        App app = AppService.getAppByCode(appCode);
//        if (app == null) {
//            throw new ServiceException("未找到该应用, 应用编码:"+appCode);
//        }
//        return listPermissionsByAppId(tenantId, app.getAppId());

        // 首先判断是否是平台管理员
        User user = userService.findByUserId(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        List<PermissionGroup> groups = new ArrayList<>();
        List<Permission> permissions;

        if (user.getIsPlatformAdmin()) {
            // 如果是平台管理员，则获取所有菜单权限
            permissions = permissionService.listAllAppMenus(appCode);
        } else {
            permissions = permissionService.listAppMenusByUserId(tenantId, userId, appCode);
        }

        Map<Integer, List<Permission>> collect = permissions.stream().collect(Collectors.groupingBy(Permission::getGroupId));
        collect.forEach((k, v) -> {
            PermissionGroup group = new PermissionGroup();
            group.setGroupId(k);
            if (CollectionUtil.isNotEmpty(v)) {
                group.setGroupName(v.get(0).getGroupName());
                group.setCreateDate(v.get(0).getCreateDate());
                group.setGroupIcon(v.get(0).getGroupIcon());
                group.setGroupSort(v.get(0).getGroupSort());
            }
            group.setPermissionList(v);
            groups.add(group);
        });

        return groups.stream().sorted(Comparator.comparing(PermissionGroup::getGroupSort)).collect(Collectors.toList());
    }


}
