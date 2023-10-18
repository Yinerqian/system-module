package com.celi.system.dao;

import com.celi.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Changaowen
 * @Date: 2021/11/29 16:30
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findUserByLoginName(String loginName);

    User findByUserId(Integer userId);

}
