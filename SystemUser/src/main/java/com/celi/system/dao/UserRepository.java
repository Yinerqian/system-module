package com.celi.system.dao;

import com.celi.system.entity.User;
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
public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findAllByLoginNameContainingOrNickNameContaining(String loginName, String nickName, Pageable pageable);

    Page<User> findAll(Pageable pageable);

    void deleteByUserId(String userId);

    User findUserByLoginName(String loginName);

    User findByUserId(String userId);

    List<User> findAllByUserIdIn(List<String> userIds);
}
