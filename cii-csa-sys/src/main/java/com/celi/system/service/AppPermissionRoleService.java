package com.celi.system.service;

import com.celi.system.dao.AppPermissionRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppPermissionRoleService {

    @Autowired
    private AppPermissionRoleDao appPermissionRoleDao;

    public List<Integer> listPermissionsByRoleIds(List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }

        return appPermissionRoleDao.listPermissionsByRoleIds(roleIds);
    }

}
