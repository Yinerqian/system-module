package com.celi.license.entity;

import lombok.Data;

/**
 * @Author jiangshengjun
 * @Date 2024/4/9
 * @Description
 */
@Data
public class LicenseBean {

    // mac
    private String mac;
    // cpu
    private String cpuSerial;
    // 主板
    private String mainBoardSerial;
    // 最大设备
    private Integer maxDevice;
    // 最大点位
    private Integer maxTag;
    // 过期时间 为-1表示长期有效
    private Long expireDate;
    // 宽限时间 单位天
    private Integer reduceDay;
}
