package com.celi.system.dao;

import com.celi.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 * @desc 用户-角色关联
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    List<UserRole> findAllByUserId(Integer userId);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "        sp.PERMISSION_CODE\n" +
            "        FROM sys_permission sp\n" +
            "        JOIN sys_app sa ON sa.app_id = sp.app_id\n" +
            "            WHERE sa.APP_CODE = :appCode")
    List<String> listAllPermissionsByAppCode(@Param("appCode") String appCode);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "            sp.permission_code\n" +
            "        FROM sys_user su\n" +
            "        LEFT JOIN sys_user_role sur on sur.user_id = su.user_id\n" +
            "        LEFT JOIN sys_role sr on sr.role_id = sur.role_id\n" +
            "        LEFT JOIN sys_app_permission_role sapr on sapr.role_id = sr.role_id\n" +
            "        LEFT JOIN sys_app sa on sa.app_id = sapr.app_id\n" +
            "        LEFT JOIN sys_permission sp on sp.permission_id = sapr.permission_id\n" +
            "        JOIN sys_role_app sra ON sra.app_id = sapr.app_id AND sra.tenant_id = sapr.tenant_id AND sra.role_id = sapr.role_id\n" +
            "        WHERE su.login_name = :name\n" +
            "          AND sa.app_code = :appCode")
    List<String> getPermissionsByUsernameAndAppCode(@Param("name") String name, @Param("appCode") String appCode);
}
