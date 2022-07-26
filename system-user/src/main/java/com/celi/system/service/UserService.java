package com.celi.system.service;

import com.celi.system.dao.UserRepository;
import com.celi.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserRoleService userRoleService;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }


    public User findUserByLoginName(String userName) {
        return userRepository.findUserByLoginName(userName);
    }


    public void save(User userInfo) {
        userRepository.save(userInfo);
    }


    public User findByUserId(Integer userId) {
        return userRepository.findByUserId(userId);
    }

    public List<String> getPermissionsByUsernameAndAppCode(String name, String appCode) {
        User user = userRepository.findUserByLoginName(name);

        List<String> codes;
        if (user.getIsPlatformAdmin()) {
            // 如果是平台管理员，则设置所有权限
            codes = userRoleService.listAllPermissionsByAppCode(appCode);
        } else {
            codes = userRoleService.getPermissionsByUsernameAndAppCode(name, appCode).stream()
                    .filter(Objects::nonNull).collect(Collectors.toList());
        }

        return codes;
    }
}
