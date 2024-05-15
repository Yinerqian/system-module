package com.celi.license.utils;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.StrUtil;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * @Author jiangshengjun
 * @Date 2024/4/9
 * @Description
 */
public class SignUtils {

    /**
     * 加载密钥库并获取私钥和公钥
     * @param keyName 公钥/私钥文件名
     * @param keystorePassword 密码
     * @return
     * @throws Exception
     */
    public static KeyStore loadKeyStore(String keyName, char[] keystorePassword) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream inputStream = ResourceUtil.getStream(keyName)) {
            keyStore.load(inputStream, keystorePassword);
        }
        return keyStore;
    }

    public static KeyStore loadKeyStore(String keyName, char[] keystorePassword, Charset charset) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (BufferedReader bufferedReader = ResourceUtil.getReader(keyName, charset);
             InputStream inputStream = new ByteArrayInputStream(bufferedReader.readLine().getBytes());) {
            keyStore.load(inputStream, keystorePassword);
        }
        return keyStore;
    }

    public static KeyStore loadKeyStore(String keyName, char[] keystorePassword, Class<?> baseClass) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (InputStream inputStream = ResourceUtil.getResource(keyName).openStream()) {
            keyStore.load(inputStream, keystorePassword);
        }
        return keyStore;
    }

    // 使用私钥加密数据
    public static String encryptWithPrivateKey(PrivateKey privateKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 使用公钥加密
    public static String encryptWithPublicKey(PublicKey publicKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // 使用公钥解密数据
    public static String decryptWithPublicKey(PublicKey publicKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    // 使用私钥解密
    public static String decryptWithPrivateKey(PrivateKey privateKey, String encryptedData) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }
}
