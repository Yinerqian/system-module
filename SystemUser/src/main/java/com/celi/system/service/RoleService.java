package com.celi.system.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.system.dao.RoleRepository;
import com.celi.system.dto.CiiPageRequest;
import com.celi.system.dto.PageInfo;
import com.celi.system.entity.Permission;
import com.celi.system.entity.Role;
import com.celi.system.entity.RolePermission;
import com.celi.system.entity.UserRole;
import com.celi.system.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author ce-li
 * @date 2021/11/10
 */
@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    public RoleService(RoleRepository AlarmRepository) {
        this.roleRepository = AlarmRepository;
    }

    /**
     * 保存角色信息
     * @param role
     */
    public int saveRoleInfo(Role role, String userId) {
        int result = insertIntoRole(role, StpUtil.getLoginIdAsString());
        // 维护角色-用户关系
        updateUserRole(role, userId);
        return result;
    }

    /**
     * 分页查询角色信息
     * @param pageSize
     * @param pageNum
     * @return
     */
    public PageInfo<Role> queryByPage(int pageNum, int pageSize, String orderByName, String orderByDirection, String roleName) {
        Page<Role> result = null;
        String sort = null;
        if (StrUtil.isNotEmpty(orderByName) && StrUtil.isNotEmpty(orderByDirection)){
            sort = String.format("%s,%s", orderByName, orderByDirection);
        }
        if (StringUtils.isBlank(roleName)){
            result = roleRepository.findAll(CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }else {
            result = roleRepository.findAllByRoleNameContaining(roleName ,CiiPageRequest.formatPagination(pageNum, pageSize,sort));
        }
        return new PageInfo(result);
    }

    /**
     * 删除角色信息
     * @param roleId 用户id
     */
    @Transactional
    public void deleteByRoleId(String roleId) {
        roleRepository.deleteByRoleId(roleId);
        rolePermissionService.deleteByRoleId(roleId);
    }

    /**
     * 修改角色信息
     * @param Role
     */
    public int updateByRoleId(Role Role, String userId){
        Role save = roleRepository.save(Role);
        // 维护角色-用户关系
        updateUserRole(Role, userId);
        if (save!=null){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查询角色信息
     * @param roleId
     * @return
     */
    public Role findAllByRoleId(String roleId) {
        Role role = roleRepository.findAllByRoleId(roleId);
        // 查询角色下的的 权限
        List<RolePermission> permissionIds = rolePermissionService.findPermissionIdByRoleId(roleId);
        //取出权限id
        List<String> permissionIdList = permissionIds.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        //查询权限id对应的权限
        List<Permission> permissionList = permissionService.queryPermissionsByIds(permissionIdList);

        role.setPermissionList(permissionList);
        return role;
    }

        public int insertIntoRole(Role role, String userId) {
        role.setRoleId(UUID.randomUUID().toString());
        role.setCreatedBy(userId);
        role.setCreatedTime(DateUtils.now());
        role.setUpdatedBy(userId);
        role.setUpdatedTime(DateUtils.now());
        Role save = roleRepository.save(role);
        if (save != null){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 查询用户有哪些角色
     * @param userId
     * @return
     */
    public List<Role> findAllByRoleIdIn(String userId) {
        List<UserRole> roleIds = userRoleService.findRoleIds(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        return roleRepository.findByRoleIdIn(roleIds.stream().map(UserRole::getRoleId).collect(Collectors.toList()));
    }

    @Transactional
    public void updateUserRole(Role role, String userId) {
        // 1. 先根据角色id删除用户角色关系
        rolePermissionService.deleteByRoleId(role.getRoleId());

        List<Permission> permissionList = role.getPermissionList();
        if (CollectionUtils.isEmpty(permissionList)) {
            return;
        }
        // 2. 新增用户-角色关系
        List<RolePermission> rolePermissionList = new ArrayList<>(permissionList.size());
        permissionList.stream().forEach(permission -> {
            RolePermission ur = new RolePermission(role.getRoleId(), permission.getPermissionId());
            rolePermissionList.add(ur);
        });
        rolePermissionService.saveAllRolePermission(rolePermissionList);
    }
}
