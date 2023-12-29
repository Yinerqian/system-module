package com.celi.auth.starter.util;

import java.util.HashMap;
import java.util.Map;

public class ClientUtils {
    static ThreadLocal<String> userIds = new ThreadLocal();
    static ThreadLocal<Integer> tenantId = new ThreadLocal();
    static ThreadLocal<Map<Object, Object>> user = new ThreadLocal();
    static ThreadLocal<Integer> appId = new ThreadLocal();
    static ThreadLocal<String> appName = new ThreadLocal();
    static ThreadLocal<String> opName = new ThreadLocal();
    static ThreadLocal<String> opNickName = new ThreadLocal();
    static ThreadLocal<Integer> opId = new ThreadLocal();
    static ThreadLocal<String> opLocation = new ThreadLocal();
    static ThreadLocal<String> opAgent = new ThreadLocal();
    static ThreadLocal<String> agentVersion = new ThreadLocal();
    static ThreadLocal<Integer> status = new ThreadLocal();

    public ClientUtils() {
    }

    public static String getOpLocation() {
        return (String)opLocation.get();
    }

    public static void setOpLocation(String opLocation) {
        ClientUtils.opLocation.set(opLocation);
    }

    public static String getOpAgent() {
        return (String)opAgent.get();
    }

    public static void setOpAgent(String opAgent) {
        ClientUtils.opAgent.set(opAgent);
    }

    public static String getAgentVersion() {
        return (String)agentVersion.get();
    }

    public static void setAgentVersion(String agentVersion) {
        ClientUtils.agentVersion.set(agentVersion);
    }

    public static Integer getStatus() {
        return (Integer)status.get();
    }

    public static void setStatus(Integer status) {
        ClientUtils.status.set(status);
    }

    public static String getOpName() {
        return (String)opName.get();
    }

    public static void setOpName(String opName) {
        ClientUtils.opName.set(opName);
    }

    public static String getOpNickName() {
        return (String)opNickName.get();
    }

    public static void setOpNickName(String opNickName) {
        ClientUtils.opNickName.set(opNickName);
    }

    public static Integer getOpId() {
        return (Integer)opId.get();
    }

    public static void setOpId(Integer opId) {
        ClientUtils.opId.set(opId);
    }

    public static String getAppName() {
        return (String)appName.get();
    }

    public static void setAppName(String appName) {
        setAppName(appName);
    }

    public static void clear() {
        userIds.remove();
        user.remove();
        tenantId.remove();
    }

    public static Integer getAppId() {
        return (Integer)appId.get();
    }

    public static void setAppId(Integer appId) {
        ClientUtils.appId.set(appId);
    }

    public static void setCurrentUserId(String userId) {
        userIds.set(userId);
    }

    public static void setCurrentTenantId(Integer tenantId) {
        ClientUtils.tenantId.set(tenantId);
    }

    public static Integer getCurrentTenantId() {
        return (Integer)tenantId.get();
    }

    public static void setCurrentUserId(Integer userId) {
        userIds.set(userId.toString());
    }

    public static final Integer getIntCurrentUserId() {
        return userIds.get() == null ? null : Integer.valueOf((String)userIds.get());
    }

    public static Map getCurrentUser() {
        return (Map)user.get();
    }

    public static void setCurrentUser(Map map) {
        Map currentUser = (Map)user.get();
        if (currentUser == null) {
            new HashMap();
            user.set(map);
        }

    }

    public static String getCurrentUserName() {
        Map result = getCurrentUser();
        if (result == null) {
            return null;
        } else {
            return result.get("userName") != null ? result.get("userName").toString() : null;
        }
    }

    public static String getCurrentNickname() {
        Map result = getCurrentUser();
        if (result == null) {
            return null;
        } else {
            return result.get("nickName") != null ? result.get("nickName").toString() : null;
        }
    }

    public static final String getCurrentUserId() {
        return userIds == null ? null : (String)userIds.get();
    }
}
