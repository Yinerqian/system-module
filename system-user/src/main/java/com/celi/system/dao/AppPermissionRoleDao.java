package com.celi.system.dao;

import com.celi.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppPermissionRoleDao extends JpaRepository<User, String> {
    @Query(nativeQuery = true, value = "SELECT\n" +
            "            DISTINCT sapr.permission_id\n" +
            "        FROM sys_app_permission_role sapr\n" +
            "        JOIN sys_role_app sra ON sra.app_id = sapr.app_id AND sra.tenant_id = sapr.tenant_id AND sra.role_id = sapr.role_id\n" +
            "        WHERE sapr.role_id in (:roleIds)")
    List<Integer> listPermissionsByRoleIds(@Param("roleIds") List<Integer> roleIds);

}
