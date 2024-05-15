package com.celi.license.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @Author jiangshengjun
 * @Date 2024/4/25
 * @Description
 */

@Configuration
@ComponentScan(basePackages = {"com.celi.license"})
@EnableJpaRepositories(basePackages = "com.celi.license.dao")
@EntityScan(basePackages = {"com.celi.license.entity"})
public class CiiLicenseAutoConfiguration {
}
