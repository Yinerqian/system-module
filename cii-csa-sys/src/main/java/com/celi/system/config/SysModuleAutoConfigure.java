package com.celi.system.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author jiangshengjun
 * @date 2023/10/10
 */

@Configuration
@ComponentScan({"com.celi.system", "com.celi.cii.common"})
@EnableJpaRepositories(basePackages = "com.celi.system.dao")
@EntityScan(basePackages = {"com.celi.system.entity"})
public class SysModuleAutoConfigure {
}
