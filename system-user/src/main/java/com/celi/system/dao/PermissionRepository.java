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

    @Query(nativeQuery = true, value = "SELECT\n" +
            "            sp.permission_code\n" +
            "        FROM sys_permission sp\n" +
            "        WHERE sp.del_flag = 0\n" +
            "        AND sp.permission_id in (:list)")
    List<String> listPermissionsByPermissionList(@Param("list") List<Integer> list);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "            sp.*\n" +
            "            , spg.group_name\n" +
            "            , spg.group_icon\n" +
            "            , spg.group_sort\n" +
            "        FROM sys_permission sp\n" +
            "        JOIN sys_app sa ON sp.app_id = sa.app_id and sa.app_code = :appCode\n" +
            "        JOIN sys_permission_group spg ON sp.group_id = spg.group_id\n" +
            "        WHERE sp.PERMISSION_TYPE = 'MENU'\n" +
            "        and sa.DEL_FLAG = 0\n" +
            "        and sp.DEL_FLAG = 0\n" +
            "        and spg.DEL_FLAG = 0\n" +
            "        ORDER BY sp.CREATE_DATE")
    List<Permission> listAllAppMenus(@Param("appCode") String appCode);

    @Query(nativeQuery = true, value = "SELECT\n" +
            "            sp.MENU_ICON_CLASS menuIconClass\n" +
            "            , sp.PLATFORM_ADMIN_FLAG platformAdminFlag\n" +
            "            , sp.PERMISSION_TYPE permissionType\n" +
            "            , sp.APP_ID appId\n" +
            "            , sp.GROUP_ID groupId\n" +
            "            , sp.PERMISSION_ID permissionId\n" +
            "            , sp.DEL_FLAG delFlag\n" +
            "            , sp.PARENT_PERMISSION_ID parentPermissionId\n" +
            "            , sp.MENU_URL menuUrl\n" +
            "            , sp.CREATE_DATE createDate\n" +
            "            , sp.CREATE_BY createBy\n" +
            "            , sp.PERMISSION_CODE permissionCode\n" +
            "            , sp.UPDATE_BY updateBy\n" +
            "            , sp.SORT sort\n" +
            "            , sp.UPDATE_DATE updateDate\n" +
            "            , sp.PERMISSION_NAME permissionName\n" +
            "            , spg.group_name groupName\n" +
            "            , spg.group_icon groupIcon\n" +
            "            , spg.group_sort groupSort\n" +
            "        FROM sys_permission sp\n" +
            "        JOIN sys_app_permission_role sapr ON sp.PERMISSION_ID = sapr.PERMISSION_ID\n" +
            "        JOIN sys_app sa ON sapr.app_id = sa.app_id and sa.app_code = :appCode\n" +
            "        JOIN sys_user_role sur ON sapr.role_id = sur.role_id and sur.user_id = :userId and sur.tenant_id = :tenantId\n" +
            "        JOIN sys_permission_group spg ON sp.group_id = spg.group_id\n" +
            "        JOIN sys_role_app sra ON sra.app_id = sa.app_id AND sra.role_id = sapr.role_id\n" +
            "        WHERE sp.PERMISSION_TYPE = 'MENU'\n" +
            "        and sa.DEL_FLAG = 0\n" +
            "        and sp.DEL_FLAG = 0\n" +
            "        and spg.DEL_FLAG = 0\n" +
            "        ORDER BY sp.CREATE_DATE ASC")
    List<Map<String, Object>> listAppMenusByUserId(@Param("tenantId") Integer tenantId, @Param("userId") Integer userId, @Param("appCode") String appCode);
}
