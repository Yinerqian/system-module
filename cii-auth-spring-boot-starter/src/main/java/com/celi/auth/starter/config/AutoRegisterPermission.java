package com.celi.auth.starter.config;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.celi.auth.starter.annotation.PermissionCheck;
import com.celi.auth.starter.entity.PermissionEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class AutoRegisterPermission implements ApplicationContextAware {

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

    private static final String PORT = "9010";

    private static final String REGISTER_PATH = "/cii-sys/permission/insertSSOPermissionList";

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 1. 获取所有带Controller注解的bean(包含RestController注解) TestController, UserController
        Map<String, Object> controllerMap = applicationContext.getBeansWithAnnotation(Controller.class);

        // 用户存放发送权限数据的集合
        List<PermissionEntity> permList = new ArrayList<>();
        for (String bean : controllerMap.keySet()) {
            // 2. 根据bean获取全类名
            Object object = applicationContext.getBean(bean);

            Class<?> clazz = object.getClass();
            // 3. 获取Controller内的方法 TestController内的getDemo()方法, UserController内的getDemo2()方法
            Method[] methods = clazz.getMethods();
            if (methods != null && methods.length > 0) {
                String[] roles = null;                     // 获取方法上配置的角色信息 @PermissionCheck(role = {RolePermissionEnum.Constants.TENANT_ADMIN})
                String[] permissions = null;               // 获取方法上配置的角色信息 @PermissionCheck(operate = {OperatePermissionEnum.Constants.OP_USER_ADD})
                String methodMapping = null;    // 当配置了操作权限时, 获取请求url @RequestMapping({"/handle01"})
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

                        log.info("4. 方法名: {}", method.getName());

                        // 5. 获取带有 @PermissionCheck注解的方法 @RequestMapping上的url @RequestMapping({"/handle01"})
                        methodMapping = getRequestMappings(method);
                        log.info("5. 获取带有 @PermissionCheck注解的方法 @RequestMapping上的url: {}", methodMapping);

                        String requestURI = "";
                        if (StringUtils.isEmpty(methodMapping)) {
                            log.info("5.1. 未从自己类获取到mapping, 尝试从父接口获取: {}", methodMapping);
                            methodMapping = getFromInterface(clazz, method);
                            log.info("5.2. 从父接口获取到mapping, 尝试从父接口获取: {}", methodMapping);
                            requestURI = methodMapping;
                        } else {
                            // 6. 获取该类上的@RequestMapping
                            String clazzMapping = getClassMapping(method);
                            log.info("6. 获取该类上的@RequestMapping: {}", methodMapping);

                            // 7. 拼接类请求路径 + 方法请求路径 /boot2/hello/handle01
                            requestURI = dealUri(clazzMapping, methodMapping);
                            log.info("7. 拼接类请求路径 + 方法请求路径: {}", requestURI);
                        }

                        if (StringUtils.isEmpty(methodMapping)) {
                            log.error("8. 从自己和父接口均未获取到mapping, 继续: {}", method.getName());
                            continue;
                        }

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
        if (CollectionUtil.isNotEmpty(permList)) {
            final String concatUrl = this.permissionUrl.concat(":").concat(PORT).concat(REGISTER_PATH);
            //log.info("请求的url {}", concatUrl);
            String jsonStr = JSONUtil.toJsonStr(permList);
            log.info("发送数据: " + jsonStr);
            try (HttpResponse response = HttpRequest.post(concatUrl)
                         .header("masterKey", masterKey)
                         .body(jsonStr)
                         .execute()) {
                log.info("结果: " + response.body());
            } catch (Exception e) {
             //   e.printStackTrace();
               log.error("注册失败");
            }
        }
    }

    private String getFromInterface(Class clazz, Method method) {
        final Class parentInterface = clazz.getInterfaces()[0];
        if (parentInterface == null) {
            log.error("10-error. 未获取到父类接口: {}", clazz);
            return null;
        }
        log.info("10. 从父接口getFromInterface 获取到的class: {}", parentInterface);
        try {
            Method parentMethod = parentInterface.getMethod(method.getName(), method.getParameterTypes());
            if (parentMethod == null) {
                log.error("11-error. 未获取到父类接口的mapping: {}", clazz);
                return null;
            }
            log.info("11. 从父接口getFromInterface 获取到的class: {}", parentMethod);
            String parentMapping = getRequestMappings(parentMethod);
            return parentMapping;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean arrayIsEmpty(String[] array) {
        return ArrayUtil.isEmpty(array) || CollectionUtil.isEmpty(Arrays.stream(Arrays.stream(array)
                .toArray()).filter(o -> {
            return o != null && StrUtil.isNotBlank(o.toString());
        }).collect(Collectors.toList()));
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

    /**
     * 支持常用的Mapping
     * @param method
     * @return
     */
    private String getRequestMappings(Method method) {
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            return requestMapping.value()[0];
        }
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            return getMapping.value()[0];
        }

        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        if (postMapping != null) {
            return postMapping.value()[0];
        }

        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        if (putMapping != null) {
            return putMapping.value()[0];
        }
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        if (deleteMapping != null) {
            return deleteMapping.value()[0];
        }
        return null;
    }

}
