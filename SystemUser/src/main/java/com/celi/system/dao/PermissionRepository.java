package com.celi.system.dao;

import com.celi.system.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 * @desc 权限管理
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    List<Permission> findAllByPermissionNameContainingOrGroupNameContaining(String permissionName, String groupName);

    List<Permission> findByPermissionCode(String permissionCode);

    Permission findPermissionByPermissionId(String permissionId);

    Permission findByGroupId(String groupId);

    void deleteByPermissionId(String permissionId);

    List<Permission> findByPermissionIdIn(List<String> permissionIds);
}
