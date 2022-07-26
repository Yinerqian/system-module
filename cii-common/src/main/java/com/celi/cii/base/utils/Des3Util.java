package com.celi.cii.base.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.util.Base64;

/**
 * 3DES 加密/解密
 */
public class Des3Util {
    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";
    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    /**
     * 默认编码
     */
    private static final String CHARSET = "utf-8";

    /**
     * 生成key
     */
    private static Key generateKey(String password) throws Exception {
        DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * DES加密字符串
     *
     * @param data 待加密字符串
     * @param key  加密密码，长度不能够小于8位
     * @return 加密后内容
     */
    public static String encrypt(String data, String key) {
        if (key == null || key.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));
            //JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
            //Android平台可以使用android.util.Base64
            return new String(Base64.getEncoder().encode(bytes));

        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    /**
     * DES解密字符串
     *
     * @param data 待解密字符串
     * @param key  解密密码，长度不能够小于8位
     * @return 解密后内容
     */
    public static String decrypt(String data, String key) {
        if (key == null || key.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null)
            return null;
        try {
            Key secretKey = generateKey(key);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
            return data;
        }
    }

    public static void main(String[] args) {
        String data = "123";
        String key = MD5Utils.string2MD5("mysql").substring(0, 24);
        System.out.println(key);
        String encrypt = encrypt(data, key);
        System.out.println("encrypt: " + encrypt);
        String decrypt = decrypt(encrypt, key);
        System.out.println("decrypt: " + decrypt);
    }
}
