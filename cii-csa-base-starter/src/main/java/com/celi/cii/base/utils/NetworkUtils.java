package com.celi.cii.base.utils;

import java.io.IOException;
import java.net.*;

public class NetworkUtils {

    public static String getMacAddress() throws Exception {
        InetAddress ia = InetAddress.getLocalHost();
        // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

        // 下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            // mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length() == 1 ? 0 + s : s);
        }

        // 把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase().replaceAll("-", "");
    }

    public static String getLastMacAddress(String replace) {
        String lastMac = null;

        try {
            String mac = getMacAddress();
            String[] subs = mac.split("-");
            if (subs.length > 0) {
                lastMac = subs[subs.length - 1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastMac == null ? replace : lastMac;
    }

    /**
     * 判断主机端口是否暴露
     *
     * @param hostName 目标主机 host
     * @param port     目标端口
     * @return boolean - true/false
     */
    public static boolean detectPortConnectivity(String hostName, int port) {
        boolean isAlive = false;

        // 创建一个套接字
        SocketAddress socketAddress = new InetSocketAddress(hostName, port);
        Socket socket = new Socket();

        // 超时设置，单位毫秒
        int timeout = 2000;

        try {
            socket.connect(socketAddress, timeout);
            socket.close();
            isAlive = true;

        } catch (SocketTimeoutException exception) {
            System.out.println("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
        } catch (IOException exception) {
            System.out.println(
                    "IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
        }
        return isAlive;
    }

}
