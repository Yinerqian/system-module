package com.celi.license.client;

import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.celi.license.entity.LicenseBean;
import com.celi.license.utils.SecurityUtils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.celi.license.utils.SecurityUtils.parseSecurityWithPublicKey;

/**
 * @Author jiangshengjun
 * @Date 2024/4/9
 * @Description
 */
public class ClientUtils {

    private static Charset UTF8 = Charset.forName("utf-8");

    /**
     * 写入加密的硬件信息
     * @param path 密钥证书路径
     */
    public static void initDeviceSecurity(String path) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----START BASE INFO\n");
        try {
            stringBuffer.append(SecurityUtils.generatePubKeyStr());
            stringBuffer.append("\n----END BASE INFO\n");
            FileUtil.writeString(stringBuffer.toString(), path, UTF8);
        } catch (Exception e) {
            throw new RuntimeException("获取硬件信息失败，请使用管理员权限重新运行程序");
        }
    }

    /**
     * 写入加密服务器返回的信息
     * @param content 服务器返回的加密证书内容
     * @param path 密钥证书路径
     */
    public static void appendSecurity(String content, String path) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("----START LICENSE INFO\n");
        try {
            stringBuffer.append(content);
            stringBuffer.append("\n----END LICENSE INFO\n");
            FileUtil.appendString(stringBuffer.toString(), path, UTF8);
        } catch (Exception e) {
            throw new RuntimeException("写入密钥失败，请使用管理员权限重新运行程序");
        }
    }

    /**
     * 验证工具
     * @param path 密钥证书路径
     */
    public static LicenseBean validSecurity(String path) throws Exception {
        List<String> content = FileUtil.readLines(path, UTF8);
        List<String> parseContent = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        for (String s : content) {
            if (StrUtil.isBlank(s)) {
                continue;
            }
            if (!StrUtil.startWith(s, "----")) {
                s = s.replaceAll("\n", "");
                stringBuffer.append(s);
            }
            if (StrUtil.startWith(s, "---") && stringBuffer.length() > 0) {
                parseContent.add(stringBuffer.toString());
                stringBuffer.setLength(0);
            }
        }
        if (CollectionUtil.size(parseContent) < 1) {
            throw new RuntimeException("证书文件被修改，无法进行验证");
        } else if (CollectionUtil.size(parseContent) == 1) {
            return JSONUtil.toBean(Base64Decoder.decodeStr(parseContent.get(0)), LicenseBean.class);
        } else {
            return parseSecurityWithPublicKey(parseContent.get(1));
        }
    }
}
