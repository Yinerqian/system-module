package com.celi.system.service;

import cn.hutool.core.util.IdUtil;
import com.celi.system.constant.SystemConstants;
import com.celi.system.dao.PermissionGroupRepository;
import com.celi.system.entity.PermissionGroup;
import com.celi.system.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 16:25
 * @Description 描述
 */
@Service
public class PermissionGroupService {

    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    public PermissionGroupService(PermissionGroupRepository AlarmRepository) {
        this.permissionGroupRepository = AlarmRepository;
    }


    public PermissionGroup findPermissionByGroupId(String groupId) {
        return this.permissionGroupRepository.findPermissionByGroupId(groupId);
    }

    public List<PermissionGroup> findAll(String groupName) {
        List<PermissionGroup> allByGroupNameContaining = null;
        if (groupName != null) {
            allByGroupNameContaining = this.permissionGroupRepository.findAllByGroupNameContaining(groupName);
            return allByGroupNameContaining;
        } else {
            allByGroupNameContaining = this.permissionGroupRepository.findAll();
            return allByGroupNameContaining;
        }
    }

    public List<PermissionGroup> findAll() {
        return this.permissionGroupRepository.findAll();
    }

    public int savePermissionGroupInfo(PermissionGroup sysPermissionGroup, String userId) {
        sysPermissionGroup.setGroupId(IdUtil.simpleUUID());
        sysPermissionGroup.setCreatedBy(userId);
        sysPermissionGroup.setUpdatedBy(userId);
        sysPermissionGroup.setUpdatedTime(DateUtils.now());
        sysPermissionGroup.setCreatedTime(DateUtils.now());
        PermissionGroup save = permissionGroupRepository.save(sysPermissionGroup);
        if (save != null) {
            return SystemConstants.SUCCESS;
        }
        return SystemConstants.ERROR;
    }

    public int updatePermissionGroupInfo(PermissionGroup sysPermissionGroup, String userId) {
        sysPermissionGroup.setUpdatedBy(userId);
        sysPermissionGroup.setUpdatedTime(DateUtils.now());
        PermissionGroup save = permissionGroupRepository.save(sysPermissionGroup);
        if (save != null) {
            return SystemConstants.SUCCESS;
        }
        return SystemConstants.ERROR;
    }


    @Transactional
    public int deleteByPermissionGroupId(String groupId) {
        int value = this.permissionGroupRepository.deleteByGroupId(groupId);
        return value;
    }
}
