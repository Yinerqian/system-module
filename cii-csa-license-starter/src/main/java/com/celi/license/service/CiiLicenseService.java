package com.celi.license.service;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.celi.cii.common.exception.ServiceException;
import com.celi.license.dao.CiiLicenseDao;
import com.celi.license.entity.CiiLicense;
import com.celi.license.entity.LicenseBean;
import com.celi.license.properties.LicenseConfig;
import com.celi.license.simple.SimpleLicense;
import com.celi.license.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Author jiangshengjun
 * @Date 2024/4/25
 * @Description
 */
@Service
public class CiiLicenseService {

    // 2024-5-1 00:00:00
    public static final Long DEFAULT_TIME = 1716220800l;

    @Autowired
    private CiiLicenseDao ciiLicenseDao;
    @Autowired
    private LicenseConfig licenseConfig;

    @PostConstruct
    public void init() throws Exception {
        // 程序启动是是否判断
        if (!licenseConfig.getCheckOnStartUp()) {
            return;
        }
        // 程序启动时判断是否需要校验license，如果需要判断是否有license
        checkLicense(true);
    }

    /**
     * 保存license
     */
    @Transactional
    public void createLicense(String licenseContent) {
        CiiLicense license = new CiiLicense();
        license.setId("1");
        license.setLicenseTs(Base64Encoder.encode(String.valueOf(System.currentTimeMillis() - DEFAULT_TIME)));
        license.setLicenseContent(licenseContent);
        ciiLicenseDao.save(license);
    }

    public void checkLicense(Boolean isStart) throws Exception {
        if (licenseConfig.getLicenseEnabled()) {
            Optional<CiiLicense> licenseOptional = ciiLicenseDao.findById("1");
            // 如果没有license则提示报错
            if (!licenseOptional.isPresent()) {
                // 如果没有则创建默认license
                throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "请检查cii_license表中是否初始化了license数据"));
            }
            // 校验license数据
            CiiLicense license = licenseOptional.get();
            if (StrUtil.isBlank(license.getLicenseContent())) {
                throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "请检查cii_license表中license_content不能为空"));
            }
            try {
                LicenseBean result = JSONUtil.toBean(SimpleLicense.formatKey(license.getLicenseContent()), LicenseBean.class);
                if (licenseConfig.getCheckCpu()) {
                    String cpuSerial = SecurityUtils.serverInfos.getCPUSerial();
                    if (!StrUtil.equals(cpuSerial, result.getCpuSerial())) {
                        throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "CPU串号和授权串号不匹配"));
                    }
                }
                if (licenseConfig.getCheckMac()) {
                    List<String> macList = SecurityUtils.serverInfos.getMacAddress();
                    if (!macList.contains(result.getMac())) {
                        throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "Mac地址和授权地址不匹配"));
                    }
                }
                if (licenseConfig.getCheckDate() && result.getExpireDate() != -1) {
                    // 获取程序授权时间
                    long initTime = (Long.valueOf(Base64Decoder.decodeStr(license.getLicenseTs())) + DEFAULT_TIME) / 1000;
                    // 首先获取用当前时间和过期时间做比较
                    long sysTime = System.currentTimeMillis() / 1000;
                    if (sysTime <= initTime) {
                        throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "授权校验失败，请勿修改时间"));
                    }
                    // 当前时间大于过期时间
                    if (sysTime > result.getExpireDate()) {
                        if (result.getReduceDay() == null
                                || TimeUnit.MILLISECONDS.toDays(sysTime - result.getExpireDate()) > result.getReduceDay()) {
                            throw new ServiceException(String.format("%s，%s", buildPrefix(isStart), "授权已到期，请联系供应商"));
                        }
                    }
                }
            } catch (Exception e) {
                if (e instanceof ServiceException) {
                    throw new ServiceException(e.getMessage());
                } else {
                    throw new ServiceException("license校验不通过" + e.getMessage());
                }
            }
        }
    }

    private String buildPrefix(Boolean isStart) {
        if (isStart) {
            return "程序启动失败";
        }
        return "";
    }

}
