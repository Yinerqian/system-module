package com.celi.opc.client.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.celi.opc.client.cert.KeyStoreLoader;
import com.celi.opc.client.common.SubscriptionCallback;
import com.celi.opc.client.entity.*;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfigBuilder;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author changAoWen
 * @Date 2024/4/9
 * @Description 描述
 */
@Slf4j
@Service
public class OpcUaClientConfig {

    private OpcUaClient opcUaClient;

    private final Map<Double, ManagedSubscription> subscriptionMap = new HashMap<>();

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private ClientProperties clientProperties;

    @Autowired
    private KeyStoreLoader keyStoreLoader;

    public boolean isActive() {
        if (this.opcUaClient == null) {
            return false;
        }
        CompletableFuture<OpcUaSession> session = this.opcUaClient.getSession();
        try {
            OpcUaSession opcUaSession = session.get(3, TimeUnit.MINUTES);
            if (opcUaSession == null) {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public void connect() {
        try {
            KeyStoreLoader loader = null;
           if (StrUtil.isNotEmpty(clientProperties.getKeyPassword())) {
               Path securityTempDir = Paths.get(clientProperties.getCertPath(), "security");

               Files.createDirectories(securityTempDir);
               if (!Files.exists(securityTempDir)) {
                   log.error("无法创建安全证书目录: " + securityTempDir);
                   return;
               }
              loader = keyStoreLoader.load(securityTempDir);
           }

            OpcUaClient client = OpcUaClient.create(serverProperties.getEndpointUrl(),
                    new Function<List<EndpointDescription>, Optional<EndpointDescription>>() {
                        @Override
                        public Optional<EndpointDescription> apply(List<EndpointDescription> endpointDescriptions) {
                            return endpointDescriptions.stream().filter(e -> {
                                return e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri()); // 安全策略，默认无
                            }).findFirst();
                        }
                    }, buildOpcClientConfig(loader));
            client.connect().get();
            this.opcUaClient = client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取节点数据
     * @param identifier
     * @return
     */
    public String readData(String identifier) {
        NodeId nodeId = new NodeId(serverProperties.getNameSpaceIndex(), identifier);
        try {
            DataValue value = this.opcUaClient.readValue(0.0, TimestampsToReturn.Neither, nodeId).get();
            return String.valueOf(value.getValue().getValue());
        } catch (Exception e) {
            log.error("读取节点：{} 数据失败，{}", identifier, e.getMessage());
        }
        return null;
    }


    /**
     * 点位订阅
     * @param acqPointConfs
     * @param callback
     * @return
     */
    public Boolean subscriptionEvent(List<AcqPointConf> acqPointConfs, SubscriptionCallback callback) {
        if (CollectionUtil.isEmpty(acqPointConfs)) {
            return false;
        }
        acqPointConfs.forEach(acqPointConf -> {
            Double publishingInterval = acqPointConf.getPublishingInterval();
            ManagedSubscription subscription = subscriptionMap.get(publishingInterval);
            try {
                if (subscription == null) {
                    subscription = ManagedSubscription.create(this.opcUaClient, publishingInterval);
                    subscriptionMap.put(publishingInterval, subscription);
                }
                NodeId nodeId = new NodeId(serverProperties.getNameSpaceIndex(), acqPointConf.getIdentifier());
                ManagedDataItem dataItem = subscription.createDataItem(nodeId);
                dataItem.addDataValueListener(new Consumer<DataValue>() {
                    @Override
                    public void accept(DataValue dataValue) {
                        acqPointConf.setValue(dataValue.getValue().getValue());
                        callback.subscriptionCallback(acqPointConf);
                    }
                });
                log.info("OPC服务添加一个订阅点位: identifier: {}", acqPointConf.getIdentifier());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(StrUtil.format("创建点位订阅失败, message: {}", e.getMessage()));
            }
        });
        return true;
    }

    /**
     * 取消订阅
     * @param pointConfs
     * @return
     */
    public Boolean cancelSubscriptionEvent(List<AcqPointConf> pointConfs) {
        if (CollectionUtil.isEmpty(pointConfs)) {
            return true;
        }
        Set<String> identifiers = pointConfs.stream().map(AcqPointConf::getIdentifier)
                .filter(item -> StrUtil.isNotBlank(item))
                .collect(Collectors.toSet());
        OpcUaSubscriptionManager subscriptionManager = this.opcUaClient.getSubscriptionManager();
        ImmutableList<UaSubscription> subscriptions = subscriptionManager.getSubscriptions();
        Map<UaSubscription, List<UaMonitoredItem>> delMap = new HashMap<>();
        for (UaSubscription subscription : subscriptions) {
            ImmutableList<UaMonitoredItem> monitoredItems = subscription.getMonitoredItems();
            List<UaMonitoredItem> delItems = new ArrayList<>();
            for (UaMonitoredItem monitoredItem : monitoredItems) {
                ReadValueId readValueId = monitoredItem.getReadValueId();
                NodeId nodeId = readValueId.getNodeId();
                String identifier = StrUtil.toString(nodeId.getIdentifier());
                if (identifiers.contains(identifier)) {
                    delItems.add(monitoredItem);
                }
            }
            if (CollectionUtil.isNotEmpty(delItems)) {
                delMap.put(subscription, delItems);
            }
        }
        if (MapUtil.isEmpty(delMap)) {
            return true;
        }
        for (UaSubscription uaSubscription : delMap.keySet()) {
            uaSubscription.deleteMonitoredItems(delMap.get(uaSubscription));
        }
        log.info("OPC服务删除[{}]个点位监控", identifiers.size());
        return true;
    }

    /**
     * 断开连接
     */
    public void disConnect() {
        subscriptionMap.forEach((publishingInterval, subscription) -> {
            try {
                subscription.delete();
                log.info("OPC执行subscription.delete()方法, 目前OPCUaClient的subscriptions数量为: {}", this.opcUaClient.getSubscriptionManager().getSubscriptions().size());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("disConnect Err: {}", e.getMessage());
            }
        });
        subscriptionMap.clear();
        // 断开OPC连接
        this.opcUaClient.disconnect();
        log.info("OPCUA断开连接");
    }

    private Function<OpcUaClientConfigBuilder, org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig> buildOpcClientConfig(KeyStoreLoader loader) {
        return new Function<OpcUaClientConfigBuilder, org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig>() {
            @Override
            public org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig apply(OpcUaClientConfigBuilder opcUaClientConfigBuilder) {
                opcUaClientConfigBuilder.setApplicationName(LocalizedText.english(clientProperties.getAppName()))
                        .setApplicationUri(clientProperties.getAppUri())
                        .setRequestTimeout(UInteger.valueOf(5000));
                if (loader != null) {
                    opcUaClientConfigBuilder.setCertificate(loader.getClientCertificate()).setKeyPair(loader.getClientKeyPair());
                }
                if (StrUtil.isBlank(serverProperties.getIdpUsername()) || StrUtil.isBlank(serverProperties.getIdpPassword())) {
                    opcUaClientConfigBuilder.setIdentityProvider(new AnonymousProvider());
                } else {
                    opcUaClientConfigBuilder.setIdentityProvider(new UsernameProvider(serverProperties.getIdpUsername(), serverProperties.getIdpPassword()));
                }
                return opcUaClientConfigBuilder.build();
            }
        };
    }

    /**
     * 获取指定节点下的子节点
     * @param nodeId
     * @return
     * @throws UaException
     */
    public List<TreeNode> getTreeNode(NodeId nodeId) throws UaException {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (nodeId == null) {
            nodeId = Identifiers.ObjectsFolder;
        }
        browseNodesRecursively(nodeId, treeNodes);
        return treeNodes;
    }

    private void browseNodesRecursively(NodeId parentNodeId, List<TreeNode> treeNodes) throws UaException {
        List<? extends UaNode> nodes = opcUaClient.getAddressSpace().browseNodes(parentNodeId);

        for (UaNode node : nodes) {
            TreeNode treeNode = new TreeNode();
            treeNode.setName(node.getBrowseName().getName());

            List<TreeNode> children = new ArrayList<>();
            browseNodesRecursively(node.getNodeId(), children);

            treeNode.setChildren(children);
            treeNodes.add(treeNode);
        }
    }
}
