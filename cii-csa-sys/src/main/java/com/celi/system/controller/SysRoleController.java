package com.celi.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.celi.cii.common.entity.ResponseDTO;
import com.celi.system.entity.Role;
import com.celi.system.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 14:00
 * @Description 描述
 */
@RestController
@RequestMapping("${url.prefix}/role")
@Validated
public class SysRoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/queryByPage")
    public ResponseDTO queryByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(value = "orderByName", required = false) String orderByName,
                                   @RequestParam(value = "orderByDirection", required = false) String orderByDirection,
                                   @RequestParam(value = "roleName", required = false) String roleName){
        return ResponseDTO.ok(roleService.queryByPage(pageNum, pageSize, orderByName, orderByDirection, roleName));
    }

    @PostMapping("/saveRoleInfo")
    public ResponseDTO saveRoleInfo(@RequestBody Role role){
        String userId = StpUtil.getLoginIdAsString();
        return ResponseDTO.ok(roleService.saveRoleInfo(role, userId));
    }

    @GetMapping("/findAllByRoleId")
    public ResponseDTO findAllByRoleId(@RequestParam String roleId){
        return ResponseDTO.ok(roleService.findAllByRoleId(roleId));
    }

    @PutMapping("/updateByRoleId")
    public ResponseDTO updateByRoleId(@RequestBody Role role){
        String userId = StpUtil.getLoginIdAsString();
        return ResponseDTO.ok(roleService.updateByRoleId(role, userId));
    }

    @DeleteMapping("/deleteByRoleId")
    public ResponseDTO deleteByRoleId(@RequestParam String roleId){
        roleService.deleteByRoleId(roleId);
        return ResponseDTO.ok();
    }
}
