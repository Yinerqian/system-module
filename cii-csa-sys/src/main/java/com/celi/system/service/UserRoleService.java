package com.celi.system.service;

import com.celi.system.dao.UserRoleRepository;
import com.celi.system.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository AlarmRepository) {
        this.userRoleRepository = AlarmRepository;
    }

    /**
     * 根据用户id 查询该用户下 所有的角色id
     *
     * @param userId
     * @return
     */
    public List<UserRole> findRoleIds(Integer userId) {
        return userRoleRepository.findAllByUserId(userId);
    }


    public List<String> listAllPermissionsByAppCode(String appCode) {
        return userRoleRepository.listAllPermissionsByAppCode(appCode);
    }

    public List<String> getPermissionsByUsernameAndAppCode(String name, String appCode) {
        return userRoleRepository.getPermissionsByUsernameAndAppCode(name, appCode);
    }
}
