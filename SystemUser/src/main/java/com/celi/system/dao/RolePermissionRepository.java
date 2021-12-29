package com.celi.system.dao;

import com.celi.system.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 * @desc 用户-角色关联
 */
@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, String> {


    void deleteByPermissionId(String permissionId);

    void deleteByRoleId(String roleId);

    List<RolePermission> findPermissionIdByRoleId(String roleId);

    List<RolePermission> findByRoleIdIn(List<String> roleIds);

}
