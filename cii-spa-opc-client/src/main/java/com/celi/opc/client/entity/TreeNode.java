package com.celi.opc.client.entity;
import lombok.Data;
import java.util.List;

/**
 * @Author changAoWen
 * @Date 2024/4/12
 * @Description opc节点结构
 */
@Data
public class TreeNode {

    private String name;

    private List<TreeNode> children;
}
