package com.celi.cii.base.entity.function;

import cn.hutool.http.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileFunction {

    public static final String functionName = "file";

    /**
     * @param url 下载地址
     * @return 下载后的文件路径
     */
    public String download(String url) throws IOException {
        String baseFilePath = "/usr/local/download";
        Path path = Paths.get(baseFilePath);

        // 检测父目录是否存在
        if (!Files.exists(path)) {
            if (!Files.isDirectory(path)) {
                Files.delete(path);
            }
            Files.createDirectory(path);
        }

        // 实际下载文件
        File file = HttpUtil.downloadFileFromUrl(url, String.format("%s/", baseFilePath));
        return file.getAbsolutePath();
    }

}
