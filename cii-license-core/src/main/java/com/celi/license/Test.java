package com.celi.license;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.json.JSONUtil;
import com.celi.license.client.ClientUtils;
import com.celi.license.entity.LicenseBean;
import com.celi.license.utils.SignUtils;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import static com.celi.license.utils.SignUtils.*;

/**
 * @Author jiangshengjun
 * @Date 2024/4/9
 * @Description
 */
public class Test {

        public static void main(String[] args) throws Exception {
            String filePath = "D:\\test.cer";
            //ClientUtils.initDeviceSecurity(filePath);

            // 密钥库密码
            char[] keystorePassword = "Celi@123".toCharArray();

            // 加载私钥密钥库
            KeyStore privateKeyStore = loadKeyStore("privateKey.keystore", keystorePassword);
            // 获取私钥
            PrivateKey privateKey = (PrivateKey) privateKeyStore.getKey("privateKey", keystorePassword);

            String originData = Base64Decoder.decodeStr("eyJtYWMiOiJCNC00NS0wNi0zRi1FNC0xMDswMC01MC01Ni1DMC0wMC0wMTswMC01MC01Ni1DMC0wMC0wOCIsImNwdVNlcmlhbCI6IkJGRUJGQkZGMDAwODA2QzIiLCJtYWluQm9hcmRTZXJpYWwiOiIvM05QTkxHMy9DTldTQzAwMUJLMDFVRy8iLCJtYXhEZXZpY2UiOjIsIm1heFRhZyI6MTAwfQ==");
            LicenseBean licenseBean = JSONUtil.toBean(originData, LicenseBean.class);
            licenseBean.setMaxTag(100);
            licenseBean.setMaxDevice(10);
            String securityData = encryptWithPrivateKey(privateKey, JSONUtil.toJsonStr(licenseBean));

            //ClientUtils.appendSecurity(securityData, filePath);


            LicenseBean res = ClientUtils.validSecurity(filePath);
            System.out.println(JSONUtil.toJsonPrettyStr(res));
        }
}
