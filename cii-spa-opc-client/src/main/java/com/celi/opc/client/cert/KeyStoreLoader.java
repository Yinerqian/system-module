package com.celi.opc.client.cert;
import com.celi.opc.client.entity.ClientProperties;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.server.util.HostnameUtil;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateBuilder;
import org.eclipse.milo.opcua.stack.core.util.SelfSignedCertificateGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;

/**
 * @Author changAoWen
 * @Date 2024/4/12
 * @Description 描述
 */
@Component
@Slf4j
public class KeyStoreLoader {


    @Resource
    private ClientProperties clientProperties;

    private static final Pattern IP_ADDR_PATTERN = Pattern
            .compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    // 证书对象
    private X509Certificate clientCertificate;
    // 密钥对对象
    private KeyPair clientKeyPair;


    public KeyStoreLoader load(Path baseDir) throws Exception {
        // 获取私钥的密码
        char[] password = clientProperties.getKeyPassword().toCharArray();

        // 证书别名
        String certAlias = clientProperties.getCertAlias();

        // 创建一个使用`PKCS12`加密标准的KeyStore。KeyStore在后面将作为读取和生成证书的对象。
        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        // PKCS12的加密标准的文件后缀是.pfx，其中包含了公钥和私钥。
        // 而其他如.der等的格式只包含公钥，私钥在另外的文件中。
        Path serverKeyStore = baseDir.resolve(clientProperties.getCertFile());

        log.info("正在加载证书 {}", serverKeyStore);

        // 如果文件不存在则创建.pfx证书文件。
        if (!Files.exists(serverKeyStore)) {
            keyStore.load(null, password);

            // 用2048位的RAS算法。`SelfSignedCertificateGenerator`为Milo库的对象。
            KeyPair keyPair = SelfSignedCertificateGenerator.generateRsaKeyPair(2048);

            // `SelfSignedCertificateBuilder`也是Milo库的对象，用来生成证书。
            SelfSignedCertificateBuilder builder = new SelfSignedCertificateBuilder(keyPair)
                    .setCommonName(clientProperties.getCommonName())
                    .setOrganization(clientProperties.getOrganization())
                    .setOrganizationalUnit(clientProperties.getOrgUnit())
                    .setLocalityName(clientProperties.getLocalityName())
                    .setStateName(clientProperties.getStateName())
                    .setCountryCode(clientProperties.getCountryCode())
                    .setApplicationUri(clientProperties.getAppUri())
                    .addDnsName(clientProperties.getDnsName())
                    .addIpAddress(clientProperties.getIpAddress());

            //获取证书中列出的尽可能多的主机名和IP地址
            for (String hostname : HostnameUtil.getHostnames("0.0.0.0")) {
                if (IP_ADDR_PATTERN.matcher(hostname).matches()) {
                    builder.addIpAddress(hostname);
                } else {
                    builder.addDnsName(hostname);
                }
            }
            // 创建证书  X.509 证书可以与 OPC UA 的各种安全策略兼容，包括 Basic256
            X509Certificate certificate = builder.build();

            // 设置对应私钥的别名，密码，证书链
            keyStore.setKeyEntry(certAlias, keyPair.getPrivate(), password, new X509Certificate[] { certificate });
            try (OutputStream out = Files.newOutputStream(serverKeyStore)) {
                // 保存证书到输出流
                keyStore.store(out, password);
            }
        } else {
            try (InputStream in = Files.newInputStream(serverKeyStore)) {
                // 如果文件存在则读取
                keyStore.load(in, password);
            }
        }

        // 用密码获取对应别名的私钥。
        Key serverPrivateKey = keyStore.getKey(certAlias, password);
        if (serverPrivateKey instanceof PrivateKey) {
            // 获取对应别名的证书对象。
            clientCertificate = (X509Certificate) keyStore.getCertificate(certAlias);
            // 获取公钥
            PublicKey serverPublicKey = clientCertificate.getPublicKey();
            // 创建Keypair对象。
            clientKeyPair = new KeyPair(serverPublicKey, (PrivateKey) serverPrivateKey);
        }

        return this;
    }

    // 返回证书
    public X509Certificate getClientCertificate() {
        return clientCertificate;
    }

    // 返回密钥对
    public KeyPair getClientKeyPair() {
        return clientKeyPair;
    }
}
