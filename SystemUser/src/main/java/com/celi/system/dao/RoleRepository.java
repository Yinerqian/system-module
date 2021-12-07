package com.celi.system.dao;

import com.celi.system.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Changaowen
 * @Date: 2021/11/29 16:30
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Page<Role> findAll(Pageable pageable);

    Page<Role> findAllByRoleNameContaining(String roleName, Pageable pageable);

    void deleteByRoleId(String roleId);

    Role findAllByRoleId(String roleId);

    List<String> findByRoleId(String roleId);

    List<Role> findByRoleIdIn(List<String> roleIds);
}
