package com.celi.system.permission;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.Setting;
import com.celi.cii.base.ResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Ce-li
 * @Date: 2022/10/10 15:05
 */
@RestController
@RequestMapping("/api-user")
public class ApiSysUserController {


    @GetMapping("/getUserById")
    public ResponseDTO getUserById(String masterKey) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpRequest.get(setting.get("UserURL")).header("masterKey", masterKey);
        return ResponseDTO.ok(request.execute().body());
    }

    @GetMapping("/listPermissionsByUserId")
    public ResponseDTO listPermissionsByUserId(String masterKey) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpUtil.createGet(setting.get("PermissionURL")).header("masterKey", masterKey);
        return ResponseDTO.ok(request.execute().body());
    }

    @GetMapping("/listPermissionsByAppCode")
    public ResponseDTO listPermissionsByAppCode(@RequestParam String masterKey, @RequestParam String appCode) {
        Setting setting = new Setting("config.properties");
        HttpRequest request = HttpUtil.createGet(setting.get("MenuURL")).header("masterKey", masterKey).form("appCode", appCode);
        return ResponseDTO.ok(request.execute().body());
    }
}
