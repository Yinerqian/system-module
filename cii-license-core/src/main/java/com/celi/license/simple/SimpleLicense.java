package com.celi.license.simple;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.celi.license.entity.LicenseBean;
import com.celi.license.utils.SecurityUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author jiangshengjun
 * @Date 2024/5/15
 * @Description 简单校验，不通过公钥和密钥的方式来校验
 */
public class SimpleLicense {

    private static Integer KEY_STR = 10;
    private static String KEY_PREFIX = "CII";

    public static String generateKey(LicenseBean licenseBean) {
        String jsonStr = JSONUtil.toJsonStr(licenseBean).replaceAll("\n", "");
        // base64加密
        String base64Str = Base64.encode(jsonStr);
        StringBuffer securityValue = new StringBuffer(KEY_PREFIX);
        for (char c : base64Str.toCharArray()) {
            securityValue.append((char) (c - KEY_STR));
        }
        return securityValue.toString();
    }

    public static String formatKey(String securityKey) {
        String securityStr = securityKey.toString();
        // 解密
        StringBuffer originValue = new StringBuffer();
        for (int i = 0; i < securityStr.toCharArray().length; i++) {
            if (i < KEY_PREFIX.length()) {
                continue;
            }
            originValue.append((char) (securityStr.charAt(i) + KEY_STR));
        }
        return Base64.decodeStr(originValue.toString());
    }

    public static void main(String[] args) {
        LicenseBean licenseBean = SecurityUtils.generateDeviceInfo();
        generateKey(licenseBean);
    }
}
