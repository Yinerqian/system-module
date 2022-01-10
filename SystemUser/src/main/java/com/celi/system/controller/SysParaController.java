package com.celi.system.controller;


import com.celi.system.dto.ResponseDTO;
import com.celi.system.entity.SysPara;
import com.celi.system.service.SysParaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 *  系统参数控制层
 */
@RestController
@RequestMapping(value = "${url.prefix}/sysPara")
@Slf4j
public class SysParaController {

    @Autowired
    private SysParaService sysParaService;

    /**
     * 分页查询参数列表
     * @param pageNum
     * @param pageSize
     * @param orderByName
     * @param orderByDirection
     * @param paraName
     * @param paraValue
     * @return
     */
    @GetMapping(value = "/pageSysPara")
    public ResponseDTO pageSysPara(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(value = "orderByName", required = false) String orderByName,
                                   @RequestParam(value = "orderByDirection", required = false) String orderByDirection,
                                   @RequestParam(value = "keyword", required = false) String paraName,
                                   @RequestParam(value = "keyword", required = false) String paraValue ) {
        return ResponseDTO.ok(sysParaService.pageSysPara(pageNum, pageSize,orderByName ,orderByDirection, paraName, paraValue));
    }

    /**
     * 查询系统参数详情
     * @param paraId    参数id
     * @return
     */
    @GetMapping(value = "/getSysPara")
    public ResponseDTO getSysPara(@RequestParam String paraId) {
        return ResponseDTO.ok(sysParaService.getSysPara(paraId));
    }


    /**
     * 新增一条系统参数信息
     * @param sysPara
     * @return
     */
    @PostMapping(value = "/insertSysPara")
    public ResponseDTO insertSysPara(@RequestBody SysPara sysPara) {
        sysParaService.insertSysPara(sysPara);
        return ResponseDTO.ok();
    }

    /**
     *  修改系统参数信息
     * @param sysPara
     * @return
     */
    @PutMapping(value = "/updateSysPara")
    public ResponseDTO updateSysPara(@RequestBody SysPara sysPara) {
        sysParaService.updateSysPara(sysPara);
        return ResponseDTO.ok();
    }

    /**
     *  逻辑删除系统参数信息
     * @param paraId    参数id
     * @return
     */
    @DeleteMapping(value = "/removeSysPara")
    public ResponseDTO removeSysPara(@RequestParam String paraId) {
        sysParaService.removeSysPara(paraId);
        return ResponseDTO.ok();
    }


}
