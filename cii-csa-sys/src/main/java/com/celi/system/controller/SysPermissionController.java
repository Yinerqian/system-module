package com.celi.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.celi.cii.common.entity.ResponseDTO;
import com.celi.system.entity.Permission;
import com.celi.system.entity.PermissionGroup;
import com.celi.system.service.PermissionGroupService;
import com.celi.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 14:00
 * @Description 描述
 */
@RestController
@RequestMapping("${url.prefix}/permission")
@Validated
public class SysPermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionGroupService permissionGroupService;

    @GetMapping("/findPermissionByPermissionId")
    public ResponseDTO findPermissionByPermissionId(@RequestParam String permissionId){
        return ResponseDTO.ok(permissionService.findPermissionByPermissionId(permissionId));
    }


    @GetMapping("/findByGroupId")
    public ResponseDTO findByGroupId(@RequestParam String groupId){
        return ResponseDTO.ok(permissionService.findByGroupId(groupId));
    }


    @GetMapping("/findPermissionByGroupId")
    public ResponseDTO findPermissionByGroupId(@RequestParam String groupId){
        return ResponseDTO.ok(permissionGroupService.findPermissionByGroupId(groupId));
    }

    @GetMapping(value = "/listPermissions")
    public ResponseDTO listPermissions(Permission filter) {
        return ResponseDTO.ok(permissionService.listPermissions(filter));
    }

    @PostMapping(value = "/savePermissionGroupInfo")
    public ResponseDTO savePermissionGroupInfo(@RequestBody PermissionGroup permissionGroup) {
        String userId = StpUtil.getLoginIdAsString();
        if (permissionGroup.getSort() == null) {
            permissionGroup.setSort(0);
        }
        return ResponseDTO.ok(permissionGroupService.savePermissionGroupInfo(permissionGroup, userId));
    }

    @PutMapping(value = "/updatePermissionGroupInfo")
    public ResponseDTO updatePermissionGroupInfo(@RequestBody PermissionGroup permissionGroup) {
        String userId = StpUtil.getLoginIdAsString();
        if (permissionGroup.getSort() == null) {
            permissionGroup.setSort(0);
        }
        return ResponseDTO.ok(permissionGroupService.updatePermissionGroupInfo(permissionGroup, userId));
    }

    @DeleteMapping(value = "/deleteByPermissionGroupId")
    public ResponseDTO deleteByPermissionGroupId(@RequestParam String groupId) {
        return ResponseDTO.ok(permissionGroupService.deleteByPermissionGroupId(groupId));
    }

    @PostMapping(value = "/savePermissionInfo")
    public ResponseDTO savePermissionInfo(@RequestBody Permission permission) {
        String userId = StpUtil.getLoginIdAsString();
        if (permission.getSort() == null) {
            permission.setSort(0);
        }
        return ResponseDTO.ok(permissionService.savePermissionInfo(permission, userId));
    }

    @PutMapping(value = "/updatePermissionInfo")
    public ResponseDTO updatePermissionInfo(@RequestBody Permission permission) {
        String userId = StpUtil.getLoginIdAsString();
        if (permission.getSort() == null) {
            permission.setSort(0);
        }
        return ResponseDTO.ok(permissionService.updatePermissionInfo(permission, userId));
    }

    @DeleteMapping(value = "/deleteByPermissionId")
    public ResponseDTO deleteByPermissionId(@RequestParam String permissionId) {
        permissionService.deleteByPermissionId(permissionId);
        return ResponseDTO.ok();
    }

    @GetMapping(value = "/getPermissionTypeList")
    public ResponseDTO getPermissionTypeList() {
        return ResponseDTO.ok(permissionService.getPermissionTypeList());
    }
}
