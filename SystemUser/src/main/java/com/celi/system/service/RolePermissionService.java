package com.celi.system.service;

import com.celi.system.dao.RolePermissionRepository;
import com.celi.system.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 */
@Service
public class RolePermissionService {

    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    public RolePermissionService(RolePermissionRepository AlarmRepository) {
        this.rolePermissionRepository = AlarmRepository;
    }


    /**
     * 保存信息
     * @param scmpRolePermissionList
     */
    public void saveAllRolePermission(List<RolePermission> scmpRolePermissionList) {
        rolePermissionRepository.saveAll(scmpRolePermissionList);
    }


    /**
     * 删除信息
     * @param roleId
     */
    @Transactional
    public void deleteByRoleId(String roleId) {
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    /**
     * 删除信息
     * @param permissionId
     */
    @Transactional
    public void deleteByPermissionId(String permissionId) {
        rolePermissionRepository.deleteByPermissionId(permissionId);
    }

    /**
     * 根据角色id 查询该角色下的所有权限
     * @param roleId
     * @return
     */
    public List<RolePermission> findPermissionIdByRoleId(String roleId){
        return rolePermissionRepository.findPermissionIdByRoleId(roleId);
    }

    /**
     * 根据角色ID查询关联的权限列表
     */
    public List<RolePermission> queryByRoleIds(List<String> roleIds) {
        return rolePermissionRepository.findByRoleIdIn(roleIds);
    }

}
