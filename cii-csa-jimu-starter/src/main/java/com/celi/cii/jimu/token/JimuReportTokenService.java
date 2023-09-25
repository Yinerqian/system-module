package com.celi.cii.jimu.token;

import cn.dev33.satoken.stp.StpUtil;
import com.celi.system.dao.UserRepository;
import com.celi.system.entity.User;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author jiangshengjun
 * @date 2021/12/20
 */
@Component
public class JimuReportTokenService implements JmReportTokenServiceI {

    @Resource
    private UserRepository userRepository;

    @Override
    public String getToken(HttpServletRequest request) {
        User userInfo = userRepository.findUserByLoginName("admin");
        // 模拟登录
        StpUtil.login(userInfo);
        return StpUtil.getLoginIdAsString();
    }

    @Override
    public String getUsername(String s) {
        return "admin";
    }

    @Override
    public Boolean verifyToken(String s) {
        return true;
    }

    @Override
    public HttpHeaders customApiHeader() {
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", String.format("Bearer %s", StpUtil.getTokenValue()));
        return header;
    }
}
