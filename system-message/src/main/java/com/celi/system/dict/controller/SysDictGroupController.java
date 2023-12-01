package com.celi.system.dict.controller;

import com.celi.cii.base.ResponseDTO;
import com.celi.system.dict.entity.SysDictGroup;
import com.celi.system.dict.service.SysDictGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lianghh
 * @date 2023/8/21 12:14
 * @desc 字典组管理控制层
 **/
@RestController
@RequestMapping("${url.prefix}/dictGroup")
public class SysDictGroupController {

    @Resource
    private SysDictGroupService sysDictGroupService;

    @PostMapping("/insertDictGroup")
    public ResponseDTO insertDictGroup(@RequestBody SysDictGroup sysDictGroup) {
        sysDictGroupService.insertSysDictGroup(sysDictGroup);
        return ResponseDTO.ok();
    }

    @PostMapping("/deleteDictGroup")
    public ResponseDTO deleteDictGroup(@RequestBody SysDictGroup sysDictGroup) {
        sysDictGroupService.deleteSysDictGroup(sysDictGroup);
        return ResponseDTO.ok();
    }

    @PostMapping("/updateDictGroup")
    public ResponseDTO updateDictGroup(@RequestBody SysDictGroup sysDictGroup) {
        sysDictGroupService.updateSysDictGroup(sysDictGroup);
        return ResponseDTO.ok();
    }

    @GetMapping("/getDictGroupById")
    public ResponseDTO getDictGroupById(String dictId) {
        return ResponseDTO.ok(sysDictGroupService.getSysDictGroupById(dictId));
    }

    @GetMapping("/listDictGroup")
    public ResponseDTO listDictGroup(SysDictGroup filter) {
        return ResponseDTO.ok(sysDictGroupService.listSysDictGroup(filter));
    }

}
