package com.celi.system.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.cii.common.dto.PageInfo;
import com.celi.cii.common.exception.ServiceException;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.UserRepository;
import com.celi.system.dto.CiiPageRequest;
import com.celi.system.entity.Role;
import com.celi.system.entity.User;
import com.celi.system.entity.UserRole;
import com.celi.system.enums.ServiceCode;
import com.celi.system.utils.DateUtils;
import com.celi.system.utils.PwdSecurityKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private DetailService detailService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    public UserService(UserRepository AlarmRepository) {
        this.userRepository = AlarmRepository;
    }

    /**
     * 保存用户信息
     * @param user
     */
    @Transactional
    public int saveUserInfo(User user) {
        user = encapUserData(user, StpUtil.getLoginIdAsString());
        user.setUserId(IdUtil.simpleUUID());
        user.setPassword(PwdSecurityKey.encryptionPwd(SaSecureUtil.md5(SaSecureUtil.md5("123456"))));
        User save = null;
        try {
            save = userRepository.save(user);
        } catch (DuplicateKeyException e) {
            throw new ServiceException(ServiceCode.SAME_LOGIN_NAME_USER.getMessage());
        }
        //用户-角色关系
        updateIntoUserRole(user);
        if (save != null){
            return SystemConstants.SUCCESS;
        }else {
            return SystemConstants.ERROR;
        }
    }

    /**
     * 分页查询yh信息
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo<User> queryByPage(int pageNum, int pageSize, String orderByName, String orderByDirection, String keyword) {
        Page<User> result = null;
        String sort = null;
        if (StrUtil.isNotEmpty(orderByName) && StrUtil.isNotEmpty(orderByDirection)){
            sort = String.format("%s,%s", orderByName, orderByDirection);
        }
        if (StringUtils.isBlank(keyword)){
            result = userRepository.findAll(CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }else {
            result = userRepository.findAllByLoginNameContainingOrNickNameContaining(keyword, keyword, CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }
        return new PageInfo(result);
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     */
    @Transactional
    public void deleteByUserId(String userId) {
        userRepository.deleteByUserId(userId);
        userRoleService.deleteByUserId(userId);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Transactional
    public int updateByUserId(User user){
        User save = userRepository.save(user);
        //用户-角色关系
        updateIntoUserRole(user);
        if (save != null){
            return SystemConstants.SUCCESS;
        }else {
            return SystemConstants.ERROR;
        }
    }

    /**
     * 查询用户有哪些权限
     * @param roles
     * @return
     */
    public List<User> findAllByUserIdIn(List<String> roles) {
        return userRepository.findAllByUserIdIn(roles);
    }

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    public User findByUserId(String userId) {
        User user = userRepository.findByUserId(userId);
        // 2. 查询用户角色
        List<Role> sysRoleList = roleService.findAllByRoleIdIn(userId);
        user.setRoleList(sysRoleList);
        return user;
    }

    private User encapUserData(User user, String userId) {
        user.setCreatedBy(userId);
        user.setCreatedTime(DateUtils.now());
        user.setUpdatedBy(userId);
        user.setUpdatedTime(DateUtils.now());
        String pass = detailService.getDefaultPassword();
        user.setPassword(pass);
        user.setInitPasswordFlag(0);
        user.setCanDelFlag(1);      // 是否能删除，默认1，可以删除
        user.setOnlineStatus(0);        // 在线状态: 1-在线，0 离线
        user.setDelFlag(0); //设置默认为0
        return user;
    }

    @Transactional
    public void updateIntoUserRole(User user) {
        List<Role> roleList = user.getRoleList();
        // 1. 先根据用户id删除用户角色关系
        userRoleService.deleteByUserId(user.getUserId());

        if (CollectionUtils.isEmpty(roleList)) {
            return;
        }

        // 2. 新增用户-角色关系
        List<UserRole> userRoleList = new ArrayList<>(roleList.size());
        roleList.stream().forEach(role -> {
            UserRole ur = new UserRole(user.getUserId(), role.getRoleId());
            userRoleList.add(ur);
        });
        userRoleService.saveAllUserRole(userRoleList);
    }

    //修改密码
    public int updatePassword(User user) {
        if (user.getUserId() == null || StringUtils.isBlank(user.getOldPassword()) || StringUtils.isBlank(user.getNewPassword())) {
            throw new ServiceException("修改密码失败");
        }
        User userInfo = userRepository.findByUserId(user.getUserId());
        int value = 0;
        String oldPwd = SaSecureUtil.md5(SaSecureUtil.md5(user.getOldPassword()));
        if (oldPwd.equals(PwdSecurityKey.decryptPwd(userInfo.getPassword()))) {
            userInfo.setPassword(PwdSecurityKey.encryptionPwd(SaSecureUtil.md5(SaSecureUtil.md5(user.getNewPassword()))));
            userInfo.setUpdatedBy(StpUtil.getLoginIdAsString());
            userInfo.setUpdatedTime(DateUtils.now());
            User save = userRepository.save(userInfo);
            if (save != null){
                value = SystemConstants.SUCCESS;
            } else {
                value = SystemConstants.ERROR;
            }
            return value;
        }
        return value;
    }

    //重置密码
    public void resetPassword(User user) {
        User userInfo = userRepository.findByUserId(user.getUserId());
        userInfo.setUpdatedBy(StpUtil.getLoginIdAsString());
        userInfo.setUpdatedTime(DateUtils.now());
        userInfo.setPassword(PwdSecurityKey.encryptionPwd(SaSecureUtil.md5(SaSecureUtil.md5("123456"))));
        userRepository.save(userInfo);
    }

}
