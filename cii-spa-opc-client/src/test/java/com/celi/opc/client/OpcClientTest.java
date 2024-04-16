package com.celi.opc.client;

import com.celi.opc.client.common.SubscriptionCallback;
import com.celi.opc.client.entity.*;
import com.celi.opc.client.server.OpcUaClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author changAoWen
 * @Date 2024/4/12
 * @Description 描述
 */
@Slf4j
@SpringBootTest
public class OpcClientTest {

    @Autowired
    private OpcUaClientConfig opcUaClientConfig;

    @Test
    public void baseTest() throws InterruptedException {
        opcUaClientConfig.connect();
        String readData = opcUaClientConfig.readData("Dynamic/RandomInt32");
        log.info("readData ======== {}", readData);

        AcqPointConf conf1 = new AcqPointConf();
        conf1.setIdentifier("Dynamic/RandomInt32");
        conf1.setPublishingInterval(500.0);
        conf1.setPointName("值1");

        AcqPointConf conf2 = new AcqPointConf();
        conf2.setIdentifier("Dynamic/RandomInt64");
        conf2.setPublishingInterval(1000.0);
        conf2.setPointName("值2");

        AcqPointConf conf3 = new AcqPointConf();
        conf3.setIdentifier("Dynamic/RandomDouble");
        conf3.setPublishingInterval(3000.0);
        conf3.setPointName("值3");

        SubscriptionCallback callback = new SubscriptionCallback() {
            @Override
            public void subscriptionCallback(AcqPointConf acqPointConf) {
                log.info("点位名称 ========= {}, 值 =========== {}", acqPointConf.getPointName(), acqPointConf.getValue());
            }
        };
        opcUaClientConfig.subscriptionEvent(List.of(conf1, conf2, conf3), callback);
        Thread.sleep(10000);
        opcUaClientConfig.cancelSubscriptionEvent(List.of(conf1));
        Thread.sleep(10000);

    }
}
