package com.celi.system.service;

import cn.hutool.core.util.IdUtil;
import com.celi.system.dao.PermissionGroupRepository;
import com.celi.system.entity.PermissionGroup;
import com.celi.system.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionGroupService {

    private PermissionGroupRepository permissionGroupRepository;

    @Autowired
    public PermissionGroupService(PermissionGroupRepository scmpAlarmRepository) {
        this.permissionGroupRepository = scmpAlarmRepository;
    }

    public PermissionGroup findPermissionByGroupId(String groupId) {
        return permissionGroupRepository.findPermissionByGroupId(groupId);
    }

    public List<PermissionGroup> findAll(String groupName) {
        List<PermissionGroup> allByGroupNameContaining = null;
        if (groupName != null) {
            allByGroupNameContaining = permissionGroupRepository.findAllByGroupNameContaining(groupName);
            return allByGroupNameContaining;
        }
        allByGroupNameContaining = permissionGroupRepository.findAll();
        return allByGroupNameContaining;
    }

    public List<PermissionGroup> findAll() {
        return permissionGroupRepository.findAll();
    }

    public int savePermissionGroupInfo(PermissionGroup sysPermissionGroup, String userId) {
        sysPermissionGroup.setGroupId(IdUtil.simpleUUID());
        sysPermissionGroup.setCreatedBy(userId);
        sysPermissionGroup.setUpdatedBy(userId);
        sysPermissionGroup.setUpdatedTime(DateUtils.now());
        sysPermissionGroup.setCreatedTime(DateUtils.now());
        PermissionGroup save = permissionGroupRepository.save(sysPermissionGroup);
        if (save != null) {
            return 1;
        }
        return 0;
    }

    public int updatePermissionGroupInfo(PermissionGroup sysPermissionGroup, String userId) {
        sysPermissionGroup.setUpdatedBy(userId);
        sysPermissionGroup.setUpdatedTime(DateUtils.now());
        PermissionGroup save = permissionGroupRepository.save(sysPermissionGroup);
        if (save != null) {
            return 1;
        }
        return 0;
    }

    @Transactional
    public int deleteByPermissionGroupId(String groupId) {
        // 先删除分组
        int value = permissionGroupRepository.deleteByGroupId(groupId);
        return value;
    }

}
