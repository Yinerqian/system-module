package com.celi.system.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ArrayUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * @author ce-li
 * @date 2021/11/11
 * @desc saToken配置类
 */
@Log4j2
public class SaTokenConfigure implements AbstractSaTokenConfiguration, WebMvcConfigurer {

    private String prefix;
    private String[] whiteList;

    public SaTokenConfigure(String prefix,String[] whiteList) {
        this.prefix = prefix;
        this.whiteList = whiteList;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        log.debug("进入默认拦截器配置方法");
        // 合并默认的登录和退出登录
        String[] defaultUrl = new String[] {
                String.format("%s/oauth/login", prefix),
                String.format("%s/oauth/logout", prefix)
        };
        String[] noMatchUrl = ArrayUtil.addAll(defaultUrl, whiteList);
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
            SaRouter.match("/**")
                            .notMatch(noMatchUrl)
                                    .check(r -> {
                                        StpUtil.checkLogin();
                                    });
        }));
    }
}
