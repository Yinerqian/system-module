package com.celi.system.dict.controller;

import com.celi.cii.base.ResponseDTO;
import com.celi.system.dict.entity.SysDictItem;
import com.celi.system.dict.service.SysDictItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lianghh
 * @date 2023/8/21 12:14
 * @desc 字典项管理控制层代码
 **/
@RestController
@RequestMapping("${url.prefix}/dictItem")
public class SysDictItemController {

    @Resource
    private SysDictItemService sysDictItemService;

    @PostMapping("/insertDictItem")
    public ResponseDTO insertDictItem(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.insertSysDictItem(sysDictItem);
        return ResponseDTO.ok();
    }

    @PostMapping("/deleteDictItem")
    public ResponseDTO deleteDictItem(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.deleteSysDictItem(sysDictItem);
        return ResponseDTO.ok();
    }

    @PostMapping("/updateDictItem")
    public ResponseDTO updateDictItem(@RequestBody SysDictItem sysDictItem) {
        sysDictItemService.updateSysDictItem(sysDictItem);
        return ResponseDTO.ok();
    }

    @GetMapping("/getDictItemById")
    public ResponseDTO getDictItemById(String dictItemId) {
        return ResponseDTO.ok(sysDictItemService.getSysDictItemById(dictItemId));
    }

    @GetMapping("/pageDictItem")
    public ResponseDTO pageDictItem(Integer pageNum, Integer pageSize, SysDictItem filter) {
        return ResponseDTO.ok(sysDictItemService.pageSysDictItem(pageNum, pageSize, filter));
    }

    @GetMapping("/getDictItemByGroup")
    public ResponseDTO getDictItemByGroup(String dictCode,String remark){
        return ResponseDTO.ok(sysDictItemService.getSysDictItemByGroup(dictCode,remark));
    }

}
