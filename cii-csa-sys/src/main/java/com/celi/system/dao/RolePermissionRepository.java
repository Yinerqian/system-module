package com.celi.system.dao;

import com.celi.system.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 16:16
 * @Description 描述
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {

    void deleteByPermissionId(String permissionId);

    void deleteByRoleId(String roleId);

    List<RolePermission> findPermissionIdByRoleId(String roleId);

    List<RolePermission> findByRoleIdIn(List<String> roleIds);

}

