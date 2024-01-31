package com.celi.system.dao;

import com.celi.system.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 16:24
 * @Description 描述
 */
@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, String> {

    List<PermissionGroup> findAllByGroupNameContaining(String permissionName);

    @Query(value = "select * from sys_permission_group t order by t.sort", nativeQuery = true)
    List<PermissionGroup> findAll();

    PermissionGroup findPermissionByGroupId(String permissionId);

    int deleteByGroupId(String groupId);
}
