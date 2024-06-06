package com.celi.system.dao;

import com.celi.system.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Changaowen
 * @Date: 2021/11/29 16:30
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByLoginName(String loginName);

    User findByUserId(String userId);

    Page<User> findAllByLoginNameContainingOrNickNameContaining(String loginName, String nickName, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    void deleteByUserId(String userId);

    List<User> findAllByUserIdIn(List<String> userIds);

    @Query(value = "SELECT su.* FROM sys_user_role sur LEFT JOIN sys_user su ON sur.USER_ID = su.USER_ID WHERE sur.ROLE_ID = :roleId", nativeQuery = true)
    List<User> findUserByRoleId(@Param("roleId") String roleId);

}
