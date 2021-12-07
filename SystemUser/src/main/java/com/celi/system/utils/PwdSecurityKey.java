package com.celi.system.utils;

import cn.dev33.satoken.secure.SaSecureUtil;

/**
 * @author ce-li
 * @date 2021/11/12
 */
public class PwdSecurityKey {

    private static final String SECURITY_KEY = "Celi@123";

    public static String decryptPwd(String cipherText) {
        return SaSecureUtil.aesDecrypt(SECURITY_KEY, cipherText);
    }

    public static String encryptionPwd(String plainText) {
        return SaSecureUtil.aesEncrypt(SECURITY_KEY, plainText);
    }

    public static void main(String[] args) {
        System.out.println(encryptionPwd("Celi@123456"));
    }

}
