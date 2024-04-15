package com.celi.opc.client;

import com.celi.opc.client.entity.ServerInfo;
import com.celi.opc.client.entity.TreeNode;
import com.celi.opc.client.server.OpcUaClientConfig;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

import java.util.List;

/**
 * @Author changAoWen
 * @Date 2024/4/12
 * @Description 描述
 */
@Slf4j
public class OpcClientTest {


    public static void main(String[] args) throws UaException {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setServerCode("abc");
        OpcUaClientConfig opcUaClientConfig = new OpcUaClientConfig();
        opcUaClientConfig.connect(serverInfo);


        List<TreeNode> treeNode = opcUaClientConfig.getTreeNode(new NodeId(2, "Dynamic"));
        log.info("treeNode ======= {}", treeNode);


    }
}
