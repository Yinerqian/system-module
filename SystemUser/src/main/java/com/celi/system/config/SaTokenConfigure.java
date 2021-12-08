package com.celi.system.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ce-li
 * @date 2021/11/11
 * @desc saToken配置类
 */
@Log4j2
public class SaTokenConfigure implements AbstractSaTokenConfiguration, WebMvcConfigurer {

    private String prefix;

    public SaTokenConfigure(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        log.debug("进入默认拦截器配置方法");
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
            SaRouter.match("/**")
                            .notMatch(String.format("%s/oauth/login", prefix), String.format("%s/oauth/logout", prefix))
                                    .check(r -> {
                                        StpUtil.checkLogin();
                                    });
        }));
    }
}
