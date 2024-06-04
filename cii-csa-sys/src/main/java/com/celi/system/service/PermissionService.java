package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.celi.cii.common.exception.ServiceException;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.PermissionRepository;
import com.celi.system.entity.Permission;
import com.celi.system.entity.PermissionGroup;
import com.celi.system.entity.Role;
import com.celi.system.enums.PermissionTypeEnum;
import com.celi.system.utils.DateUtils;
import com.celi.system.utils.EnumUtils;
import com.celi.system.utils.RoleCodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository AlarmRepository) {
        this.permissionRepository = AlarmRepository;
    }

    @Autowired
    private PermissionGroupService permissionGroupService;

    @Autowired
    private RoleService roleService;


    /**
     * 查询 权限
     * @param permissionId
     * @return
     */
    public Permission findPermissionByPermissionId(String permissionId) {
        return permissionRepository.findPermissionByPermissionId(permissionId);
    }


    public List<Permission> findByGroupId(String groupId){
        return permissionRepository.findByGroupId(groupId);
    }

    public List<PermissionGroup> listPermissions(Permission filter) {
        // 查询所有分组
        PermissionGroup groupFilter = new PermissionGroup();
        if (StringUtils.isNotBlank(filter.getGroupName())) {
            groupFilter.setGroupName(filter.getGroupName());
        }
        List<PermissionGroup> groupList = permissionGroupService.findAll(filter.getGroupName());
        groupList.forEach(group -> log.info("name: {}, sort: {}",group.getGroupName(), group.getSort()));

        // 查询所有权限
        List<Permission> permissionList = null;
        if (filter.getPermissionName() != null || filter.getGroupName() != null){
            if (isTenantAdminRole()) {
                permissionList = permissionRepository.findAllByPermissionNameContainingOrGroupNameContaining(filter.getPermissionName(), filter.getGroupName());
            } else {
                permissionList = permissionRepository.findAllByPermissionNameContainingOrGroupNameContainingAndPlatformAdminFlag(filter.getPermissionName(), filter.getGroupName(), 0);
            }
        } else {
            if (isTenantAdminRole()) {
                permissionList = permissionRepository.findAll();
            } else {
                permissionList = permissionRepository.findAllByPlatformAdminFlag(0);
            }
        }

        log.info("========================================================================");
        // 按 sort 字段排序
        groupList = groupList.stream().sorted(Comparator.comparing(PermissionGroup::getSort)).collect(Collectors.toList());
        groupList.forEach(group -> log.info("name: {}, sort: {}",group.getGroupName(), group.getSort()));
        permissionList = permissionList.stream().sorted(Comparator.comparing(Permission::getSort)).collect(Collectors.toList());

        // 将权限添加到各分组中
        for (PermissionGroup group : groupList) {
            List<Permission> list = new ArrayList<>();
            for (Permission permission : permissionList) {
                if (permission.getGroupId().equals(group.getGroupId())) {
                    if (filter.isSelectFlag()) {
                        permission.setSelectFlag(true);
                    }
                    list.add(permission);
                }
            }
            group.setPermissionList(list);
        }

        return groupList;
    }

    @Transactional
    public void deleteByPermissionId(String permissionId) {
        permissionRepository.deleteByPermissionId(permissionId);
    }

    public int savePermissionInfo(Permission permission, String userId) {
        if (permission.getGroupId() == null) {
            throw new ServiceException("请先选择一个分组");
        }
        if (StringUtils.isBlank(permission.getPermissionCode())) {
            throw new ServiceException("请输入权限编码");
        }
        if (permission.getPermissionType() == null) {
            permission.setPermissionType(PermissionTypeEnum.OP);
        }
        // 验证唯一
        List<Permission> permissionList = permissionRepository.findByPermissionCode(permission.getPermissionCode());
        if (permissionList.size() != 0) {
            throw new ServiceException("权限编码已存在, 请输入新的权限编码");
        }
        permission.setPermissionId(IdUtil.simpleUUID());
        permission.setCreatedBy(userId);
        permission.setUpdatedBy(userId);
        permission.setCreatedTime(DateUtils.now());
        permission.setUpdatedTime(DateUtils.now());

        Permission save = permissionRepository.save(permission);
        if (save != null) {
            return SystemConstants.SUCCESS;
        }
        return SystemConstants.ERROR;
    }

    public int updatePermissionInfo(Permission permission, String userId) {
        if (permission.getGroupId() == null) {
            throw new ServiceException("请先选择一个分组");
        }
        if (permission.getPermissionId() == null) {
            throw new ServiceException("缺少权限ID");
        }
        if (StringUtils.isBlank(permission.getPermissionCode())) {
            throw new ServiceException("请输入权限编码");
        }
        if (permission.getPermissionType() == null) {
            permission.setPermissionType(PermissionTypeEnum.OP);
        }
        // 验证唯一
        Permission tempPermission = findPermissionByPermissionId(permission.getPermissionId());
        if (!permission.getPermissionCode().equals(tempPermission.getPermissionCode())) {
            List<Permission> permissionList = permissionRepository.findByPermissionCode(permission.getPermissionCode());
            if (permissionList.size() != 0) {
                throw new ServiceException("权限编码已存在, 请输入新的权限编码");
            }
        }
        permission.setUpdatedBy(userId);
        permission.setUpdatedTime(DateUtils.now());

        Permission save = permissionRepository.save(permission);
        if (save != null) {
            return SystemConstants.SUCCESS;
        }
        return SystemConstants.ERROR;
    }

    public List<Map> getPermissionTypeList() {
        return EnumUtils.listEnums(PermissionTypeEnum.class);
    }

    /**
     * 查看PermissionId对应的权限
     * @param permissionIds
     * @return
     */
    public List<Permission> queryPermissionsByIds(List<String> permissionIds) {
        return permissionRepository.findByPermissionIdIn(permissionIds);
    }

    /**
     * 判断当前登录用户是否为租户管理员工
     * @return
     */
    private boolean isTenantAdminRole() {
        String userId = StpUtil.getLoginIdAsString();
        List<Role> roles = roleService.findAllByRoleIdIn(userId);
        for (Role role : roles) {
            if(RoleCodeConstant.TENANT_ADMIN_CODE.equals(role.getRoleCode())) {
                return true;
            }
        }
        return false;
    }

}
