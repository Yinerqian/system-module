package com.celi.cii.message.sdk.server;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.celi.cii.base.ResponseDTO;
import com.celi.cii.message.sdk.config.CiiMsgConnectConfig;
import com.celi.cii.message.sdk.config.CiiMsgSendMsgRequestParams;
import com.celi.cii.message.sdk.config.CiiMsgThirdAppRequestParams;
import lombok.NonNull;

import java.util.Map;

/**
 * 消息推送客户端
 */
public class CiiMessageClient {

    private CiiMsgConnectConfig connectConfig;

    /**
     * 初始化时指定配置类
     * @param config
     */
    public CiiMessageClient(CiiMsgConnectConfig config) {
        if (config == null || StrUtil.isBlank(config.getCiiGateway())) {
            throw new RuntimeException("配置文件为空, 无法创建CiiMessageCilent");
        }
        this.connectConfig = config;
    }

    /**
     * 执行具体的请求
     * @param url
     * @param body
     * @return
     */
    private ResponseDTO execHttpRequest(String url, String body) {
        if (!StrUtil.startWithIgnoreCase(url, "/")) {
            url = StrUtil.format("/{}", url);
        }
        HttpRequest httpRequest = HttpUtil.createPost(StrUtil.format("{}{}", connectConfig.getCiiGateway(), url));
        httpRequest.header("masterKey", connectConfig.getMasterKey());
        httpRequest.header("version", connectConfig.getVersion());
        httpRequest.body(body);
        try {
            HttpResponse httpResponse = httpRequest.execute();
            String resBody = httpResponse.body();
            return JSONObject.isValidObject(resBody) ? JSONObject.parseObject(resBody, ResponseDTO.class) : ResponseDTO.errorMessage(resBody);
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }

    public ResponseDTO login(@NonNull String appUserId, @NonNull String deviceToken, @NonNull String appId) {
        return login(buildThirdAppRequestParams(appUserId, deviceToken, appId));
    }

    /**
     * 第三方APP登录
     * @param appConfig
     * @return
     */
    public ResponseDTO login(CiiMsgThirdAppRequestParams appConfig) {
        try {
            return execHttpRequest(CiiMsgConnectConfig.THIRD_APP_LOGIN_URL, JSONObject.toJSONString(appConfig.getMsgDeviceToken()));
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }

    public ResponseDTO logout(@NonNull String appUserId, @NonNull String deviceToken, @NonNull String appId) {
        return logout(buildThirdAppRequestParams(appUserId, deviceToken, appId));
    }

    private CiiMsgThirdAppRequestParams buildThirdAppRequestParams(@NonNull String appUserId, @NonNull String deviceToken, @NonNull String appId) {
        CiiMsgThirdAppRequestParams requestParams = new CiiMsgThirdAppRequestParams();
        return requestParams.appUserId(appUserId)
                .deviceToken(deviceToken)
                .appId(appId);
    }

    /**
     * 第三方APP登出
     * @param appConfig
     * @return
     */
    public ResponseDTO logout(CiiMsgThirdAppRequestParams appConfig) {
        try {
            return execHttpRequest(CiiMsgConnectConfig.THIRD_APP_LOGINOUT_URL, JSONObject.toJSONString(appConfig.getMsgDeviceToken()));
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }

    public ResponseDTO sendMSg(@NonNull String templateCode, @NonNull String topicCode) {
        return sendMsg(null, templateCode, null, topicCode, null);
    }

    public ResponseDTO sendMsgToUsers(Map<String, Object> channelParams,
                                      @NonNull String templateCode, Map<String, Object> templateParams,
                                      @NonNull String toUsers, String appId) {
        CiiMsgSendMsgRequestParams requestParams = new CiiMsgSendMsgRequestParams();
        requestParams.appId(appId)
                .channelParams(channelParams)
                .templateCode(templateCode)
                .templateParams(templateParams)
                .toUsers(toUsers);
        return sendMsg(requestParams);
    }

    public ResponseDTO sendMsg(Map<String, Object> channelParams,
                               @NonNull String templateCode, Map<String, Object> templateParams,
                               @NonNull String topicCode, String appId) {
        CiiMsgSendMsgRequestParams requestParams = new CiiMsgSendMsgRequestParams();
        requestParams.appId(appId)
                .channelParams(channelParams)
                .templateCode(templateCode)
                .templateParams(templateParams)
                .topicCode(topicCode);
        return sendMsg(requestParams);
    }

    /**
     * 请求发送消息
     * @param sendConfig
     * @return
     */
    public ResponseDTO sendMsg(CiiMsgSendMsgRequestParams sendConfig) {
        try {
            String errorMsg = "";
            if (sendConfig == null || sendConfig.getRequestDTO() == null) {
                errorMsg = "未指定相关参数";
            }
            if (StrUtil.isBlank(sendConfig.getRequestDTO().getTemplateCode())) {
                errorMsg = "未指定消息编码";
            }
            if (StrUtil.isNotBlank(errorMsg)) {
                return ResponseDTO.errorMessage(errorMsg);
            }
            return execHttpRequest(CiiMsgConnectConfig.SEND_MSG_URL, JSONObject.toJSONString(sendConfig.getRequestDTO()));
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }

    public ResponseDTO sendMsgToUsers(CiiMsgSendMsgRequestParams sendConfig) {
        try {
            String errorMsg = "";
            if (sendConfig == null || sendConfig.getRequestDTO() == null) {
                errorMsg = "未指定相关参数";
            }
            if (StrUtil.isBlank(sendConfig.getRequestDTO().getTemplateCode())) {
                errorMsg = "未指定消息编码";
            }
            if (StrUtil.isNotBlank(errorMsg)) {
                return ResponseDTO.errorMessage(errorMsg);
            }
            return execHttpRequest(CiiMsgConnectConfig.SEND_TO_SERUS_MSG_URL, JSONObject.toJSONString(sendConfig.getRequestDTO()));
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }


    public ResponseDTO fetchSendResult(String requestId) {
        CiiMsgSendMsgRequestParams requestParams = new CiiMsgSendMsgRequestParams();
        requestParams.requestId(requestId);
        return fetchSendResult(requestParams);
    }

    /**
     * 获取发送请求结果
     * @param sendConfig
     * @return
     */
    public ResponseDTO fetchSendResult(CiiMsgSendMsgRequestParams sendConfig) {
        try {
            if (sendConfig == null || sendConfig.getRequestDTO() == null || StrUtil.isBlank(sendConfig.getRequestDTO().getRequestId())) {
                return ResponseDTO.errorMessage("未指定请求ID, 无法获取结果");
            }
            return execHttpRequest(CiiMsgConnectConfig.FETCH_RES_URL, JSONObject.toJSONString(sendConfig.getRequestDTO()));
        } catch (Exception ex) {
            return ResponseDTO.errorMessage(ex.getMessage());
        }
    }

}
