package com.celi.system.service;

import com.celi.system.dao.RolePermissionRepository;
import com.celi.system.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 16:17
 * @Description 描述
 */
@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public void saveAllRolePermission(List<RolePermission> scmpRolePermissionList) {
        rolePermissionRepository.saveAll(scmpRolePermissionList);
    }

    @Transactional
    public void deleteByRoleId(String roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    @Transactional
    public void deleteByPermissionId(String permissionId) {
        rolePermissionRepository.deleteByPermissionId(permissionId);
    }

    public List<RolePermission> findPermissionIdByRoleId(String roleId) {
        return this.rolePermissionRepository.findPermissionIdByRoleId(roleId);
    }

    public List<RolePermission> queryByRoleIds(List<String> roleIds) {
        return this.rolePermissionRepository.findByRoleIdIn(roleIds);
    }
}
