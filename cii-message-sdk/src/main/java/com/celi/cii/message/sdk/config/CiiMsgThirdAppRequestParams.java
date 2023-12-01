package com.celi.cii.message.sdk.config;

import com.celi.system.dict.entity.MsgDeviceToken;
import lombok.Data;

@Data
public class CiiMsgThirdAppRequestParams {

    private MsgDeviceToken msgDeviceToken;

    public CiiMsgThirdAppRequestParams() {}

    private synchronized void createParams() {
        if (msgDeviceToken == null) {
            msgDeviceToken = new MsgDeviceToken();
        }
    }

    public CiiMsgThirdAppRequestParams(String appId, String appUserId, String deviceToken) {
        createParams();
        this.msgDeviceToken.setAppId(appId);
        this.msgDeviceToken.setAppUserId(appUserId);
        this.msgDeviceToken.setDeviceToken(deviceToken);
    }

    public CiiMsgThirdAppRequestParams appId(String appId) {
        createParams();
        this.msgDeviceToken.setAppId(appId);
        return this;
    }

    public CiiMsgThirdAppRequestParams appUserId(String appUserId) {
        createParams();
        this.msgDeviceToken.setAppUserId(appUserId);
        return this;
    }

    public CiiMsgThirdAppRequestParams deviceToken(String deviceToken) {
        createParams();
        this.msgDeviceToken.setDeviceToken(deviceToken);
        return this;
    }

}
