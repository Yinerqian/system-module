package com.celi.system.dao;

import com.celi.system.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {

    List<Permission> findAllByPermissionNameContainingOrGroupNameContaining(String permissionName, String groupName);

    List<Permission> findAllByPermissionNameContainingOrGroupNameContainingAndPlatformAdminFlag(String permissionName, String groupName, Integer adminFlag);

    List<Permission> findAllByPlatformAdminFlag(Integer adminFlag);

    List<Permission> findAll();

    List<Permission> findByPermissionCode(String permissionCode);

    Permission findPermissionByPermissionId(String permissionId);

    Permission findByGroupId(String groupId);

    void deleteByPermissionId(String permissionId);

    List<Permission> findByPermissionIdIn(List<String> permissionIds);
}
