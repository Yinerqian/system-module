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
     * 保存角色信息
     * @param scmpUserRoleList
     */
    public void saveAllUserRole(List<UserRole> scmpUserRoleList) {
        userRoleRepository.saveAll(scmpUserRoleList);
    }


    /**
     * 删除信息
     * @param roleId 用户id
     */
    public void deleteByRoleId(String roleId) {
        userRoleRepository.deleteByRoleId(roleId);
    }

    /**
     * 删除信息
     * @param userId 用户id
     */
    public void deleteByUserId(String userId) {
        userRoleRepository.deleteByUserId(userId);
    }

    /**
     * 根据用户id 查询该用户下 所有的角色id
     * @param userId
     * @return
     */
    public List<UserRole> findRoleIds(String userId){
        return userRoleRepository.findAllByUserId(userId);
    }

}
