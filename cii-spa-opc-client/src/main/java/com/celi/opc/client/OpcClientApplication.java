package com.celi.opc.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description 描述
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpcClientApplication.class, args);
    }
}
