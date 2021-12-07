package com.celi.system.service;

import com.celi.system.dao.PermissionRepository;
import com.celi.system.entity.Permission;
import com.celi.system.entity.PermissionGroup;
import com.celi.system.enums.PermissionTypeEnum;
import com.celi.system.exception.ServiceException;
import com.celi.system.utils.DateUtils;
import com.celi.system.utils.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ce-li
 * @Date: 2021/11/15/11:36
 * @Description:
 */
@Service
public class PermissionService {

    private PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository AlarmRepository) {
        this.permissionRepository = AlarmRepository;
    }

    @Autowired
    private PermissionGroupService permissionGroupService;


    /**
     * 查询 权限
     * @param permissionId
     * @return
     */
    public Permission findPermissionByPermissionId(String permissionId) {
        return permissionRepository.findPermissionByPermissionId(permissionId);
    }


    public Permission findByGroupId(String groupId){
        return permissionRepository.findByGroupId(groupId);
    }

    public List<PermissionGroup> listPermissions(Permission filter) {
        // 查询所有分组
        PermissionGroup groupFilter = new PermissionGroup();
        if (StringUtils.isNotBlank(filter.getGroupName())) {
            groupFilter.setGroupName(filter.getGroupName());
        }
        List<PermissionGroup> groupList = permissionGroupService.findAll(filter.getGroupName());
        // 查询所有权限
        List<Permission> permissionList = null;
        if (filter.getPermissionName() != null || filter.getGroupName() != null){
            permissionList = permissionRepository.findAllByPermissionNameContainingOrGroupNameContaining(filter.getPermissionName(), filter.getGroupName());
        } else {
            permissionList = permissionRepository.findAll();
        }
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
        permission.setPermissionId(UUID.randomUUID().toString());
        permission.setCreatedBy(userId);
        permission.setUpdatedBy(userId);
        permission.setCreatedTime(DateUtils.now());
        permission.setUpdatedTime(DateUtils.now());

        Permission save = permissionRepository.save(permission);
        if (save != null) {
            return 1;
        }
        return 0;
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
            return 1;
        }
        return 0;
    }

    public List<Map> getPermissionTypeList() {
        return EnumUtils.listEnums(PermissionTypeEnum.class);
    }

    public List<Permission> queryPermissionsByIds(List<String> permissionIds) {
        return permissionRepository.findByPermissionIdIn(permissionIds);
    }
}
