package com.celi.auth.starter.config;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.celi.auth.starter.annotation.PermissionCheck;
import com.celi.auth.starter.entity.PermissionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Method;
import java.util.*;


@ConditionalOnProperty(prefix = "cii.auth", value = "active", matchIfMissing = false)
@Configuration
@Slf4j
public class AutoConfiguration implements ApplicationContextAware {


    /**
     * 是否激活: cii.auth.active 必须配置为 true 才会加载该容器, 不配或者为其它均不加载
     */
    @Value("${cii.auth.active}")
    private boolean active;

    /**
     * 配置的权限url
     */
    @Value("${cii.auth.permission-url}")
    private String permissionUrl;

    @Value("${cii.auth.master-key}")
    private String masterKey;

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${url.prefix}")
    private String prefix;

    private String port = "9010";

    private String url = "/cii-sys/permission/insertSSOPermissionList";

    private ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // 1. 获取所有带Controller注解的bean(包含RestController注解) TestController, UserController
        Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Controller.class);

        // 用户存放发送权限数据的集合
        List<PermissionEntity> permList = new ArrayList<>();
        for (String bean : controllerMap.keySet()) {
            // 2. 根据bean获取全类名
            Object object = applicationContext.getBean(bean);

            // 3. 获取Controller内的方法 TestController内的getDemo()方法, UserController内的getDemo2()方法
            Method[] methods = object.getClass().getMethods();
            if (methods != null && methods.length > 0) {
                String[] roles = null;                     // 获取方法上配置的角色信息 @PermissionCheck(role = {RolePermissionEnum.Constants.TENANT_ADMIN})
                String[] permissions = null;               // 获取方法上配置的角色信息 @PermissionCheck(operate = {OperatePermissionEnum.Constants.OP_USER_ADD})
                String[] methodMapping = null;    // 当配置了操作权限时, 获取请求url @RequestMapping({"/handle01"})
                for (Method method : methods) {
                    // 4. 处理带有 PermissionCheck 注解的方法, 存在 PermissionCheck注解
                    PermissionCheck pkAnno = method.getAnnotation(PermissionCheck.class);
                    if (pkAnno != null) {
                        // 判断roles情况
                        roles = method.getAnnotation(PermissionCheck.class).role();
                        permissions = method.getAnnotation(PermissionCheck.class).operate();

                        boolean roleIsEmpty = arrayIsEmpty(roles);
                        boolean permissionIsEmpty = arrayIsEmpty(permissions);

                        // 情况一: roles 和 operate 都配置了空
                        if (roleIsEmpty && permissionIsEmpty) {
                            log.info("roles 和 operate 都配置了空: {}", method.getName());
                            continue;
                        }


                        // 5. 获取带有 @PermissionCheck注解的方法 @RequestMapping上的url @RequestMapping({"/handle01"})
                        methodMapping = getRequestMappings(method);
                        // 6. 获取该类上的@RequestMapping
                        String clazzMapping = getClassMapping(method);
                        // 7. 拼接类请求路径 + 方法请求路径 /boot2/hello/handle01
                        String requestURI = dealUri(clazzMapping, methodMapping[0]);

                        // 情况二: roles是空 和 operate 不为空
                        if (roleIsEmpty && !permissionIsEmpty) {
                            log.info("情况二: roles是空 和 operate 不为空: {}", method.getName());

                            PermissionEntity entity = new PermissionEntity(requestURI, serviceName, null, permissions);
                            permList.add(entity);
                        }

                        // 情况三: roles不是空 和 operate为空
                        if (!roleIsEmpty && permissionIsEmpty) {
                            log.info("情况三: roles不是空 和 operate为空: {}", method.getName());

                            PermissionEntity entity = new PermissionEntity(requestURI, serviceName, roles, null);
                            permList.add(entity);
                        }

                        // 情况四: roles不为空  operate不为空
                        if (!roleIsEmpty && !permissionIsEmpty) {
                            log.info("情况四: roles不为空  operate不为空 {}", method.getName());

                            PermissionEntity entity = new PermissionEntity(requestURI, serviceName, roles, permissions);
                            permList.add(entity);
                        }
                    }

                }
            }
        }
        if (permList != null && permList.size() > 0) {
            final String concatUrl = this.permissionUrl.concat(":").concat(port).concat(url);
         //   log.info("请求的url {}", concatUrl);
            String jsonStr = JSONUtil.toJsonStr(permList);
            log.info("发送数据: " + jsonStr);
            String result = HttpRequest.post(concatUrl)
                    .header("masterKey", masterKey)
                    .body(jsonStr)
                    .execute().body();
            log.info("结果: " + result);
        }
    }

    private boolean arrayIsEmpty(String[] array) {
        if (array == null || array.length == 0 || StringUtils.isEmpty(array[0])) {
            return true;
        }
        return false;
    }

    /**
     * 将class的@RequestMapping(value = "/demo")和方法的@RequestMapping(value = "/getDemo01")做拼接
     * 1. 如果class的为null, 只返回方法的mapping
     * 2. 如果class不为null, 将class的和方法拼接返回，
     * 3. 以上两种情况都处理了"/", 如果不是以"/"开头将会动态添加"/", 如果有则忽略
     * @param clazzRequestMapping
     * @param requestMapping
     * @return
     */
    private String dealUri(String clazzRequestMapping, String requestMapping) {
        if (clazzRequestMapping == null) {
            return dealGang(requestMapping);
        }
        return dealGang(clazzRequestMapping + requestMapping);
    }

    /**
     * 如果不是以"/"开头将会动态添加"/", 如果有则忽略
     * @param str
     * @return
     */
    public String dealGang(String str) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }

        if ("/".equals(str.substring(0, 1))) {
            return str;
        } else {
            return  "/" + str;
        }
    }

    /**
     * @RestController
     * @RequestMapping(value = "/demo")
     * public class TestController {}
     * 返回类上配置的 RequestMapping url,
     * 1. 如果没配置或者只配置了"/"(@RequestMapping(value = ""), @RequestMapping(value = "/")) 返回null
     * 2. 如果没配置${}表达式((@RequestMapping(value = "/demo")), 直接返回demo
     * 3. 如果配置了表达式(@RequestMapping(value = "/${url.prefix}/demo"), 将${url.prefix}转换成实际值返回
     *      注意: 表达式必须是${url.prefix}, 已规定好
     * @param method
     * @return
     */
    private String getClassMapping(Method method) {
        Class clazz = method.getDeclaringClass();
        RequestMapping requestMapping = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
        if (requestMapping == null) {
            return null;
        }
        // 获取mapping 值
        String value = requestMapping.value()[0];
        // 配置了空字符串
        if (StringUtils.isEmpty(value) || "/".equals(value)) {
            return null;
        }
        // 没有配置 ${} 表达式 直接返回
        if (!value.contains("$")) {
            return value;
        }

        if (value.contains("${url.prefix}")) {
            value = value.replace("${url.prefix}", prefix);
        }
        return value;
    }

    private String getClassRequestMapping(Method method) {
        Class clazz = method.getDeclaringClass();
        log.info("2. 获取目标类名: {}", clazz);

        RequestMapping requestMapping = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
        // [${url.prefix}/hello]
        if (requestMapping == null) {
            return null;
        }
        String value = requestMapping.value()[0];
        return deal$(value);
    }

    private String deal$(String value) {
        if (value.contains("${")) {
            return prefix + value.substring(value.indexOf("}") + 1, value.length());
        }
        return value;
    }

    /**
     * 支持常用的Mapping
     * @param method
     * @return
     */
    private String[] getRequestMappings(Method method) {
        RequestMapping requestMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            return requestMapping.value();
        }

        GetMapping getMapping = (GetMapping) method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return getMapping.value();
        }

        PostMapping postMapping = (PostMapping) method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return postMapping.value();
        }

        PutMapping putMapping = (PutMapping) method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return putMapping.value();
        }
        DeleteMapping deleteMapping = (DeleteMapping) method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return deleteMapping.value();
        }
        return null;
    }

    /**
     * 判断是何种类型代理
     * @param bean
     * @return
     */
    private Method[] dealDeclaredMethods(Object bean) {
        Method[] declaredMethods = null;
        if (AopUtils.isJdkDynamicProxy(bean)) {
            Object singletonTarget = AopProxyUtils.getSingletonTarget(bean);
            if (singletonTarget != null) {
                declaredMethods = singletonTarget.getClass().getDeclaredMethods();
            }
        } else if (AopUtils.isCglibProxy(bean)) {
            declaredMethods = bean.getClass().getSuperclass().getDeclaredMethods();
        } else {
         //   declaredMethods = bean.getClass().getDeclaredMethods();
            declaredMethods = bean.getClass().getMethods();
        }
        return declaredMethods;
    }


}
