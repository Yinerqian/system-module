package com.celi.license.simple;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.json.JSONUtil;
import com.celi.license.entity.LicenseBean;
import com.celi.license.utils.SecurityUtils;

import java.io.UnsupportedEncodingException;

/**
 * @Author jiangshengjun
 * @Date 2024/5/15
 * @Description 简单校验，不通过公钥和密钥的方式来校验
 */
public class SimpleLicense {

    private static Integer KEY_STR = 10;
    private static String KEY_PREFIX = "CII";
    private static String AES_KEY = "Celi@012345@Celi";

    public static String generateKey(LicenseBean licenseBean) {
        String jsonStr = JSONUtil.toJsonStr(licenseBean).replaceAll("\n", "");
        // base64加密
        String base64Str = Base64.encode(jsonStr);
        StringBuffer securityValue = new StringBuffer(KEY_PREFIX);
        for (char c : base64Str.toCharArray()) {
            securityValue.append((char) (c - KEY_STR));
        }
        try {
            AES aes = SecureUtil.aes(AES_KEY.getBytes("utf-8"));
            return aes.encryptHex(securityValue.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatKey(String securityKey) {
        AES aes = null;
        try {
            aes = SecureUtil.aes(AES_KEY.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("不知道AES加密");
        }
        String securityStr = aes.decryptStr(securityKey);
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
        //LicenseBean licenseBean = SecurityUtils.generateDeviceInfo();
        //System.out.println(generateKey(licenseBean));

        String key = "ee2b6be1cdd439be46bdb149de08451f4eaf37cc9fcc183513979ad4c6226c6a08028a4ef2e8544a74f3c5e2812ff1004688b6627b191f024ef3efb89ee378f0a5cf2a52e2e16cf379d3593a33aba3052bcd3f2a77b954a0ee2e6368ebc95af72b0a0c928343c26ebf4c3e1e0e0936665677f545b2ab1ecb50fd3f80a517d6dfe57b16696843ca09d7440cd0f6d0f6345c6a419dfef17db25cf2cb648acabb2ab8d39173fe7b6695af11830e0f027d22047fff495f85a6a4672306c4bab9f1fe";
        System.out.println(formatKey(key));
    }
}
