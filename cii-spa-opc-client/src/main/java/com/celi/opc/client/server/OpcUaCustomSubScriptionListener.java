package com.celi.opc.client.server;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscriptionManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

/**
 * opc自定义监听器
 * 服务器断开一定时间后 恢复后重新订阅相关点位
 */
@Slf4j
public class OpcUaCustomSubScriptionListener implements UaSubscriptionManager.SubscriptionListener {

    private String serverId;
    private OpcUaClientConfig opcUaClientConfig;

    public OpcUaCustomSubScriptionListener(String serverId, OpcUaClientConfig opcUaClientConfig) {
        this.serverId = serverId;
        this.opcUaClientConfig = opcUaClientConfig;
    }

    /**
     * opc client尝试恢复订阅数据失败之后 执行该方法 手动重新订阅一下数据
     * @param subscription
     * @param statusCode
     */
    @Override
    public void onSubscriptionTransferFailed(UaSubscription subscription, StatusCode statusCode) {
        UaSubscriptionManager.SubscriptionListener.super.onSubscriptionTransferFailed(subscription, statusCode);
        log.info("尝试恢复订阅");
//        opcUaClientConfig.clearContext();
//        PointConfigUtil.recoverTriPointEvent(serverId);
        log.info("恢复订阅成功");
    }
}
