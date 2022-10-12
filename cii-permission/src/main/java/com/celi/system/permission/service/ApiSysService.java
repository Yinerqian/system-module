package com.celi.system.permission.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.Setting;
import com.celi.system.permission.enity.SysPermissionGroup;
import com.celi.system.permission.enity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Ce-li
 * @Date: 2022/10/10 15:05
 */
@Service
public class ApiSysService {


    public SysUser  getUserById(String masterKey) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpRequest.get(setting.get("UserURL")).header("masterKey", masterKey);
        SysUser sysUser = JSONUtil.toBean(request.execute().body(), SysUser.class);
        return sysUser;
    }

    public String listPermissionsByUserId(String masterKey) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpUtil.createGet(setting.get("PermissionURL")).header("masterKey", masterKey);
        return request.execute().body();
    }

    public List<SysPermissionGroup> listPermissionsByAppCode(@RequestParam String masterKey, @RequestParam String appCode) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpUtil.createGet(setting.get("MenuURL")).header("masterKey", masterKey).form("appCode", appCode);
        List<SysPermissionGroup> groupList = JSONUtil.toList(request.execute().body(), SysPermissionGroup.class);
        return groupList;
    }
}
