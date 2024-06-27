package com.celi.system.controller;

import com.celi.cii.common.entity.ResponseDTO;
import com.celi.system.entity.Param;
import com.celi.system.service.ParamService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: changAoWen
 * @Date: 2024/1/10 14:00
 * @Description 描述
 */
@RestController
@RequestMapping("${url.prefix}/param")
@Validated
public class SysParamController {

    @Resource
    private ParamService paramService;

    @GetMapping("/queryByPage")
    public ResponseDTO queryByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(value = "orderByName", required = false) String orderByName,
                                   @RequestParam(value = "orderByDirection", required = false) String orderByDirection,
                                   @RequestParam(value = "configName", required = false) String configName){
        return ResponseDTO.ok(paramService.queryByPage(pageNum, pageSize, orderByName, orderByDirection, configName));
    }

    @PostMapping("/saveParamInfo")
    public ResponseDTO saveParamInfo(@RequestBody Param param){
        return ResponseDTO.ok(paramService.saveParamInfo(param));
    }

    @GetMapping("/findByConfigId")
    public ResponseDTO findByConfigId(@RequestParam String configId){
        return ResponseDTO.ok(paramService.findByConfigId(configId));
    }

    @PutMapping("/updateByConfigId")
    public ResponseDTO updateByConfigId(@RequestBody Param param){
        return ResponseDTO.ok(paramService.updateByConfigId(param));
    }

    @DeleteMapping("/deleteByConfigId")
    public ResponseDTO deleteByConfigId(@RequestParam String configId){
        paramService.deleteByConfigId(configId);
        return ResponseDTO.ok();
    }
}
