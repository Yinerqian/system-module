package com.celi.license.utils;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.celi.license.entity.LicenseBean;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import static com.celi.license.constant.Constant.PUB_KEY_PWD;
import static com.celi.license.utils.SignUtils.*;

/**
 * @Author jiangshengjun
 * @Date 2024/4/9
 * @Description
 */
public class SecurityUtils {

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static AbstractServerInfos serverInfos = null;

    static {
        if (OS.indexOf("windows") >= 0) {
            // windows
            serverInfos = new WindowsServerInfos();
        } else {
            // linux
            serverInfos = new LinuxServerInfos();
        }
    }

    public static LicenseBean generateDeviceInfo() {
        LicenseBean licenseBean = new LicenseBean();
        try {
            licenseBean.setMac(serverInfos.getMacAddress().stream().findFirst().get());
            licenseBean.setCpuSerial(serverInfos.getCPUSerial());
            licenseBean.setMainBoardSerial(serverInfos.getMainBoardSerial());
            // 试用版默认为2个设备，100个点位,无过期时间
            licenseBean.setMaxDevice(2);
            licenseBean.setMaxTag(100);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return licenseBean;
    }

    /**
     * 客户端通过公钥对硬件信息进行加密后提交到服务器
     * @return
     * @throws Exception
     */
    public static String generatePubKeyStr() throws Exception {
        LicenseBean deviceInfo = SecurityUtils.generateDeviceInfo();
        // 原始数据
        String originalData = JSONUtil.toJsonStr(deviceInfo);
        // 使用BASE64 进行数据加密
        return Base64Encoder.encode(originalData.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 服务器是用私钥对提交的字符串进行解密，并添加过期时间和点位、设备数量后再使用私钥进行加密后返回客户端
     * @param deviceInfo
     * @return
     * @throws Exception
     */
    public static String generatePriKeyStr(LicenseBean deviceInfo) throws Exception {
        // 密钥库密码
        char[] keystorePassword = PUB_KEY_PWD.toCharArray();

        // 加载私钥密钥库
        KeyStore privateKeyStore = loadKeyStore("privateKey.keystore", keystorePassword);
        // 获取私钥
        PrivateKey privateKey = (PrivateKey) privateKeyStore.getKey("privateKey", keystorePassword);
        // 使用公钥加密
        return encryptWithPrivateKey(privateKey, JSONUtil.toJsonStr(deviceInfo));
    }

    /**
     * 使用公钥解密
     * @param str
     * @return
     * @throws Exception
     */
    public static LicenseBean parseSecurityWithPublicKey(String str) throws Exception {
        // 密钥库密码
        char[] keystorePassword = PUB_KEY_PWD.toCharArray();

        // 加载公钥密钥库
        KeyStore publicKeyStore = loadKeyStore("publicKey.keystore", keystorePassword);
        // 获取公钥证书
        Certificate certificate = publicKeyStore.getCertificate("publicKey");
        PublicKey publicKey = certificate.getPublicKey();
        return JSONUtil.toBean(decryptWithPublicKey(publicKey, str), LicenseBean.class);
    }

    public static LicenseBean parseDeviceInfo(String licenseBeanStr) {
        return JSONUtil.toBean(licenseBeanStr, LicenseBean.class);
    }
}
