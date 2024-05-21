package com.celi.system.controller;

import com.celi.cii.common.entity.ResponseDTO;
import com.celi.system.entity.User;
import com.celi.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 14:00
 * @Description 描述
 */
@RestController
@RequestMapping(value = "/${url.prefix}/user")
public class SysUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryByPage")
    public ResponseDTO queryByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(value = "orderByName", required = false) String orderByName,
                                   @RequestParam(value = "orderByDirection", required = false) String orderByDirection,
                                   @RequestParam(value = "keyword", required = false) String keyword ){
        return ResponseDTO.ok(userService.queryByPage(pageNum, pageSize,orderByName ,orderByDirection, keyword));
    }

    @PostMapping("/saveUserInfo")
    public ResponseDTO saveUserInfo(@RequestBody User user){
        return ResponseDTO.ok(userService.saveUserInfo(user));
    }

    @GetMapping("/findByUserId")
    public ResponseDTO findByUserId(@RequestParam String userId){
        return ResponseDTO.ok(userService.findByUserId(userId));
    }

    @PutMapping("/updateByUserId")
    public ResponseDTO updateByUserId(@RequestBody User user){
        return ResponseDTO.ok(userService.updateByUserId(user));
    }

    @DeleteMapping("/deleteByUserId")
    public ResponseDTO deleteByUserId(@RequestParam String userId){
        userService.deleteByUserId(userId);
        return ResponseDTO.ok();
    }

    @PutMapping("/updatePassword")
    public ResponseDTO updatePassword(@RequestBody User user){
        int value = userService.updatePassword(user);
        if (Integer.valueOf(1).equals(value)){
            return ResponseDTO.ok();
        }
        return ResponseDTO.errorMessage("旧密码不正确，请重新输入");
    }

    @PutMapping("/resetPassword")
    public ResponseDTO resetPassword(@RequestBody User user){
        userService.resetPassword(user);
        return ResponseDTO.ok();
    }
}
