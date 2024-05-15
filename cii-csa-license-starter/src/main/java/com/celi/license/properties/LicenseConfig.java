package com.celi.license.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author jiangshengjun
 * @Date 2024/4/25
 * @Description
 */

@ConfigurationProperties("cii.license")
@Component
@Data
public class LicenseConfig {

    private Boolean checkOnStartUp;
    /**
     * 是否启用license校验
     */
    private Boolean licenseEnabled;
    /**
     * 校验过期时间
     */
    private Boolean checkDate;
    /**
     * CPU绑定
     */
    private Boolean checkCpu;
    /**
     * mac地址绑定
     */
    private Boolean checkMac;
    /**
     * 超多多久后无法启动服务,单位天，如果为-1则不自动停止服务
     */
    private Integer graceTime;

}
