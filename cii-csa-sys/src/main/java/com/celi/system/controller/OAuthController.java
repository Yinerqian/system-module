package com.celi.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.celi.cii.common.entity.ResponseDTO;
import com.celi.system.entity.UserLoginEntity;
import com.celi.system.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 14:11
 * @Description 描述
 */
@RestController
@RequestMapping("${url.prefix}/oauth")
public class OAuthController {

    @Autowired
    private OAuthService oAuthService;


    @PostMapping("/login")
    public ResponseDTO userLogin(@RequestBody UserLoginEntity userLoginEntity) {
        oAuthService.userLogin(userLoginEntity);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        return ResponseDTO.ok(tokenInfo.tokenValue);
    }

    @GetMapping("/userInfo")
    public ResponseDTO userInfo() {
        String userId = StpUtil.getLoginIdAsString();
        return ResponseDTO.ok(oAuthService.queryUserInfo(userId));
    }

    @GetMapping("/logout")
    public ResponseDTO userLogOut() {
        StpUtil.logout();
        return ResponseDTO.ok();
    }

    @GetMapping("/userMenus")
    public ResponseDTO queryUserMenu() {
        return ResponseDTO.ok(oAuthService.userPermissionByGroup());
    }

    @GetMapping("/userFlatMenus")
    public ResponseDTO queryUserFlatMenu() {
        return ResponseDTO.ok(oAuthService.userPermissionFlatByGroup());
    }
}
