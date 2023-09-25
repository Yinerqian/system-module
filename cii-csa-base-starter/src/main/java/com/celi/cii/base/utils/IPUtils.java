package com.celi.cii.base.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.celi.cii.base.enums.RequestMethodEnum;

import java.util.HashMap;
import java.util.Map;

public class IPUtils {

    /**
     * 接口样式形如："http://ip.ws.126.net/ipquery?ip=222.174.177.146"
     * 接口返回数据:
     * var lo="山东省", lc="临沂市";
     * var localAddress={city:"临沂市", province:"山东省"}
     */
    private static final String IP_URL = "http://ip.ws.126.net/ipquery";
    /**
     * 超时时间，默认5秒，单位：毫秒
     */
    private static final int TIMEOUT = 5000;

    private static final int INT_5 = 5;
    private static final int INT_6 = 6;
    private static final char CHAR_DOT = '.';
    private static final char CHAR_1 = '1';
    private static final char CHAR_6 = '6';

    private static final String IP_10 = "10.";
    private static final String IP_172 = "172";
    private static final String IP_172_1 = "172.1";
    private static final String IP_172_2 = "172.2";
    private static final String IP_172_3 = "172.3";
    private static final String IP_192_168 = "192.168";
    private static final String IP_127 = "127.";


    public static void main(String[] args) {
        String ip = "222.174.177.146";
//        String ip = "220.248.12.158";
//        String ip = "192.168.0.4";
        if (isInnerNet(ip)) {
            System.out.println("内网");
        } else {
            System.out.println("外网");
        }
        System.out.println(getAddressByIp(ip, TIMEOUT));
    }


    /**
     * 根据ip获取地址，目前只支持国内ip
     * timeout: 单位毫秒
     */
    public static MyIpAddress getAddressByIp(String ip, int timeout) {
        MyIpAddress ipAddress = new MyIpAddress();
        ipAddress.setInnerNet(isInnerNet(ip));
        HashMap<String, Object> map = new HashMap<>();
        map.put("ip", ip);
        String responseStr = MyHttpClientUtils.executeMethod(IP_URL, RequestMethodEnum.METHOD_GET.getCode(), map, new HashMap<>(), new HashMap<>(), timeout);
        if (StrUtil.isNotEmpty(responseStr)) {
            String retStr = responseStr.substring(responseStr.indexOf("{"), responseStr.lastIndexOf("}") + 1);
            Map<String, String> addressMap = (Map<String, String>) JSON.parse(retStr);
            ipAddress.setCity(addressMap.get("city"));
            ipAddress.setProvince(addressMap.get("province"));

            ThreadPoolUtils.execute(new Runnable() {
                @Override
                public void run() {
                    //TODO 异步执行更新操作
//                    System.out.println("异步执行更新操作");
                }
            });
        }
        return ipAddress;
    }

    /**
     * 判断ip是否为内网，是内网返回true，外网返回false
     * 内网ip段：
     * A类 10.0.0.0--10.255.255.255
     * B类 172.16.0.0--172.31.255.255
     * C类 192.168.0.0--192.168.255.255
     */
    public static boolean isInnerNet(String ip) {
        if (ip.startsWith(IP_10) || ip.startsWith(IP_127)) {
            return true;
        } else if (ip.startsWith(IP_172)) {
            if (ip.startsWith(IP_172_1) && ip.charAt(INT_5) >= CHAR_6 && ip.charAt(INT_6) == CHAR_DOT) {
                return true;
            } else if (ip.startsWith(IP_172_2) && ip.charAt(INT_6) == CHAR_DOT) {
                return true;
            } else if (ip.startsWith(IP_172_3) && ip.charAt(INT_5) <= CHAR_1) {
                return true;
            } else {
                return false;
            }
        } else if (ip.startsWith(IP_192_168)) {
            return true;
        } else {
            return false;
        }
    }

}

