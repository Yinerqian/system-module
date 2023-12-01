package com.celi.cii.message.sdk;

import com.alibaba.fastjson.JSONObject;
import com.celi.cii.message.sdk.config.CiiMsgConnectConfig;
import com.celi.cii.message.sdk.config.CiiMsgSendMsgRequestParams;
import com.celi.cii.message.sdk.server.CiiMessageClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendAppDemo {

    public static void main(String[] args) {
        sendMsgDemo();
//        sendMsgToUsersDemo();
//        fetchMsgResultDemo();
//        loginDemo();
//        logoutDemo();
    }

    /**
     * 创建发送客户端
     * @return
     */
    public static CiiMessageClient createCiiMessageClient() {
        CiiMsgConnectConfig connectConfig = new CiiMsgConnectConfig("http://192.168.1.203:9010", "c227ef4acddb492b9a97ea923d66357a355", "celi");
        return new CiiMessageClient(connectConfig);
    }

    /**
     * 发送消息demo
     */
    public static void sendMsgDemo() {
        CiiMessageClient ciiMessageClient = createCiiMessageClient();
        CiiMsgSendMsgRequestParams requestParams = new CiiMsgSendMsgRequestParams();
        requestParams.templateCode("TMP_20231123001")
                .topicCode("TPC_20231029001")
                .templateParams("msg", "2023-11-23第一条消息");
        log.info("执行结果: {}", JSONObject.toJSONString(ciiMessageClient.sendMsg(requestParams)));
    }

    /**
     * 发送消息demo
     */
    public static void sendMsgToUsersDemo() {
        CiiMessageClient ciiMessageClient = createCiiMessageClient();
        CiiMsgSendMsgRequestParams requestParams = new CiiMsgSendMsgRequestParams();
        requestParams.templateCode("TMP_20230824003")
                .toUsers("2814688636@qq.com")
                .templateParams("name", "Celi")
                .templateParams("code", "5656875");
        log.info("执行结果: {}", JSONObject.toJSONString(ciiMessageClient.sendMsgToUsers(requestParams)));
    }

    /**
     * 获取发送结果demo
     */
    public static void fetchMsgResultDemo() {
        CiiMessageClient ciiMessageClient = createCiiMessageClient();
        log.info("执行结果: {}", JSONObject.toJSONString(ciiMessageClient.fetchSendResult("5b8dea6158cf4b0ca3571d266a77393e")));
    }

    /**
     * 第三方APP登录结果
     */
    public static void loginDemo() {
        CiiMessageClient ciiMessageClient = createCiiMessageClient();
        log.info("执行结果: {}", JSONObject.toJSONString(ciiMessageClient.login("3","TEST_01","4564656")));
    }

    /**
     * 第三方APP登出结果
     */
    public static void logoutDemo() {
        CiiMessageClient ciiMessageClient = createCiiMessageClient();
        log.info("执行结果: {}", JSONObject.toJSONString(ciiMessageClient.logout("3","TEST_01","4564656")));
    }

}
