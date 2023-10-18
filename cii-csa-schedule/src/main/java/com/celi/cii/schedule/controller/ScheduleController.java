package com.celi.cii.schedule.controller;

import com.celi.cii.common.entity.ResponseDTO;
import com.celi.cii.schedule.entity.ScheduleConfig;
import com.celi.cii.schedule.service.ScheduleConfigService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@RestController
@RequestMapping("/cii-schedule")
public class ScheduleController {

    @Resource
    private ScheduleConfigService scheduleConfigService;

    @PostMapping("/save")
    public ResponseDTO saveScheduleJob(@RequestBody ScheduleConfig scheduleConfig) {
        scheduleConfigService.saveNewTask(scheduleConfig);
        return ResponseDTO.ok();
    }

    @PutMapping("/update")
    public ResponseDTO updateScheduleJob(@RequestBody ScheduleConfig scheduleConfig) {
        scheduleConfigService.updateTask(scheduleConfig);
        return ResponseDTO.ok();
    }

    @DeleteMapping("/delete")
    public ResponseDTO delJobById(Integer jonId) {
        scheduleConfigService.delTask(jonId);
        return ResponseDTO.ok();
    }

    @PutMapping("/modifyStatus")
    public ResponseDTO modifyStatus(Integer jobId, Integer status) {
        scheduleConfigService.jobStatusUpdate(jobId, status);
        return ResponseDTO.ok();
    }

    @GetMapping("/pageSchedule")
    public ResponseDTO pageSchedule(Integer pageNum, Integer pageSize, @RequestParam(required = false) Integer status) {
        return ResponseDTO.ok(scheduleConfigService.pageSchedule(pageNum, pageSize, status));
    }
}
