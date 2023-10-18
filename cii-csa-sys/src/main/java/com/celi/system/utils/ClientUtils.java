package com.celi.system.utils;

import java.util.HashMap;
import java.util.Map;

public class ClientUtils {

    static ThreadLocal<Integer> userIds = new ThreadLocal<>();
    static ThreadLocal<Integer> tenantId = new ThreadLocal<>();

    static ThreadLocal<Map<Object, Object>> user = new ThreadLocal<>();
    static ThreadLocal<Integer> appId = new ThreadLocal<>();
    static ThreadLocal<String> appName = new ThreadLocal<>();
    static ThreadLocal<String> opName = new ThreadLocal<>();
    static ThreadLocal<String> opNickName = new ThreadLocal<>();
    static ThreadLocal<Integer> opId = new ThreadLocal<>();
    static ThreadLocal<String> opLocation = new ThreadLocal<>();
    static ThreadLocal<String> opAgent = new ThreadLocal<>();
    static ThreadLocal<String> agentVersion = new ThreadLocal<>();
    static ThreadLocal<Integer> status = new ThreadLocal<>();

    public static String getOpLocation() {
        return opLocation.get();
    }

    public static void setOpLocation(String opLocation) {
        ClientUtils.opLocation.set(opLocation);
    }

    public static String getOpAgent() {
        return opAgent.get();
    }

    public static void setOpAgent(String opAgent) {
        ClientUtils.opAgent.set(opAgent);
    }

    public static String getAgentVersion() {
        return agentVersion.get();
    }

    public static void setAgentVersion(String agentVersion) {
        ClientUtils.agentVersion.set(agentVersion);
    }

    public static Integer getStatus() {
        return status.get();
    }

    public static void setStatus(Integer status) {
        ClientUtils.status.set(status);
    }

    public static String getOpName() {
        return opName.get();
    }

    public static void setOpName(String opName) {
        ClientUtils.opName.set(opName);
    }

    public static String getOpNickName() {
        return opNickName.get();
    }

    public static void setOpNickName(String opNickName) {
        ClientUtils.opNickName.set(opNickName);
    }

    public static Integer getOpId() {
        return opId.get();
    }

    public static void setOpId(Integer opId) {
        ClientUtils.opId.set(opId);
    }

    public static String getAppName() {
        return appName.get();
    }

    public static void setAppName(String appName) {
        ClientUtils.setAppName(appName);
    }


    private static ThreadLocal<MyThreadLocal> threadLocalMap = new ThreadLocal<>();

    public static void clear() {
        userIds.remove();
        user.remove();
        threadLocalMap.remove();
        tenantId.remove();
    }

    public static Integer getAppId() {
        return appId.get();
    }

    public static void setAppId(Integer appId) {
        ClientUtils.appId.set(appId);
    }

    public static void setCurrentUserId(Integer userId) {
        userIds.set(userId);
    }

    public static void setCurrentTenantId(Integer tenantId) {
        ClientUtils.tenantId.set(tenantId);
    }

    public static Integer getCurrentTenantId() {
        return ClientUtils.tenantId.get();
    }

    public static final Integer getIntCurrentUserId() {
        if (userIds.get() == null) {
            return null;
        }

        return Integer.valueOf(userIds.get());
    }

    public static Map getCurrentUser() {
        return user.get();
    }

    public static void setCurrentUser(Map map) {
        Map currentUser = user.get();
        if (currentUser == null) {
            currentUser = new HashMap();
            user.set(map);
        }
    }

//    public static void setRequestIp(String ip) {
//        setThreadLocalMap("ip", ip);
//    }

//    public static String getRequestIp() {
//        return getThreadLocalValueString("ip");
//    }

    public static String getCurrentUserName() {
        Map result = getCurrentUser();
        if (result == null) {
            return null;
        }

        return result.get("userName") != null ? result.get("userName").toString() : null;
    }

    public static String getCurrentNickname() {
        Map result = getCurrentUser();
        if (result == null) {
            return null;
        }

        return result.get("nickName") != null ? result.get("nickName").toString() : null;
    }

    public static final Integer getCurrentUserId() {
        if (userIds == null) {
            return null;
        }

        return userIds.get();
    }

    public static void setThreadLocalMap(String key, Object value) {
        MyThreadLocal local = threadLocalMap.get();
        if (local == null) {
            local = new MyThreadLocal();
            threadLocalMap.set(local);
        }
        local.setValue(key, value);
    }

    public static final Object getThreadLocalValue(String key) {
        MyThreadLocal map = threadLocalMap.get();
        return map == null ? null : map.getValue(key);
    }

    public static final String getThreadLocalValueString(String key) {
        MyThreadLocal map = threadLocalMap.get();
        if (map == null) {
            return null;
        }

        Object value = map.getValue(key);

        return value == null ? null : value.toString();
    }

    public static void main(String[] args) {
        int a;
        a = 6;
        System.out.println(a);
        System.out.println(a++);
        System.out.println(++a);
        "john".equals(new String("john"));
    }

}
