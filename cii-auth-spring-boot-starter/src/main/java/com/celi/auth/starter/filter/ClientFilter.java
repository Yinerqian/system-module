package com.celi.auth.starter.filter;

import com.celi.auth.starter.util.ClientUtils;
import com.celi.auth.starter.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.servlet.*;
import java.io.IOException;

/**
 * @project: cii-auth-spring-boot-starter
 * @title: ClientFilter
 * @author: lihq
 * @date: 2023/12/5 18:58
 * @version: v1.0
 * @description: 最先执行过滤器
 */
@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class ClientFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("cii-auth-spring-boot-starter ClientFilter-------------------------");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String userIdEncode = ((RequestFacade) request).getHeader("USER_ID");
        String tenantIdEncode = ((RequestFacade) request).getHeader("TENANT_ID");
        log.info("ClientFilter: userId加密: {}", userIdEncode);
        log.info("ClientFilter: tenantId加密: {}", tenantIdEncode);

        String userId = null;
        if (!StringUtils.isEmpty(userIdEncode)) {
            userId = SecurityUtil.decode(userIdEncode);
        }

        String tenantId = null;
        if (!StringUtils.isEmpty(userIdEncode)) {
            tenantId = SecurityUtil.decode(tenantIdEncode);
        }
        log.info("ClientFilter: userId {}", userId);
        log.info("ClientFilter: tenantId {}", tenantId);
        if (userId != null) {
            ClientUtils.setCurrentUserId(userId);
        }
        if (tenantId != null) {
            ClientUtils.setCurrentTenantId(Integer.valueOf(tenantId));
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
