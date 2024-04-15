package com.celi.opc.client.common;

import com.celi.opc.client.entity.AcqPointConf;

/**
 * @Author changAoWen
 * @Date 2024/4/15
 * @Description 描述
 */
public interface SubscriptionCallback {
    public void subscriptionCallback(AcqPointConf acqPointConf);
}
