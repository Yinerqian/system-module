package com.celi.system.dao;

import com.celi.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 * @desc 用户-角色关联
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

    void deleteByUserId(String userId);

    void deleteByRoleId(String roleId);

    List<UserRole> findAllByUserId(String userId);

}
