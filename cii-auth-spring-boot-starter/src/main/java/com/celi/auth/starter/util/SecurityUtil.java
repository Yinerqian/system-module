package com.celi.auth.starter.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @project: cii-gateway
 * @title: SecurityUtil
 * @author: lihq
 * @date: 2023/12/6 9:42
 * @version: v1.0
 * @description: 对称加密解密
 */
public class SecurityUtil {

    //设置编码格式
    private static final String UTF8 = StandardCharsets.UTF_8.name();

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            return Base64.getEncoder().encodeToString(str.getBytes(UTF8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            byte[] decode = Base64.getDecoder().decode(str.getBytes(UTF8));
            return new String(decode,UTF8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
