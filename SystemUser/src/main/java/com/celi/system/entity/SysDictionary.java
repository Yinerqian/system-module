package com.celi.system.entity;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangshengjun
 * @date 2021/12/9
 */
//@Table(name = "SYS_DICTIONARY")
@Data
//@Entity
public class SysDictionary extends BaseCreateBy {
    /**
     * 主键
     */
//    @Id
//    @Column(name = "DC_ID", columnDefinition = "varchar(32)")
    private String dcId;
    /**
     * 字典名称
     */
//    @Column(name = "DC_NAME", columnDefinition = "varchar(32)")
    private String dcName;
    /**
     * 字典编码
     */
//    @Column(name = "DC_CODE", columnDefinition = "varchar(32)", unique = true)
    private String dcCode;
    /**
     * 字典值
     */
//    @Column(name = "DC_VALUE", columnDefinition = "varchar(200) not null", unique = true)
    private String dcValue;
    /**
     * 字典描述
     */
//    @Column(name = "DC_REMARK", columnDefinition = "text")
    private String dcRemark;
    /**
     * 字典父级ID
     */
//    @Column(name = "PARENT_ID", columnDefinition = "varchar(32)")
    private String parentId;
    /**
     * 是否是叶子节点
     */
//    @Column(name = "IS_LEAF", columnDefinition = "char(1)")
    private String isLeaf;
    /**
     * 是否是目录
     */
//    @Column(name = "IS_MENU", columnDefinition = "char(1)")
    private String isMenu;
    /**
     * 排序字段
     */
//    @Column(name = "ORDER_NO", columnDefinition = "int(10)")
    private Integer orderNo;

    @Transient
    private List<SysDictionary> children = new ArrayList<>();

    public void addChildren(SysDictionary children) {
        this.children.add(children);
    }

    public void addAllChildren(List<SysDictionary> childrens) {
        this.children.addAll(childrens);
    }
}
