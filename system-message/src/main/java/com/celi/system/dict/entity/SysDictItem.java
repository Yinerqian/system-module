package com.celi.system.dict.entity;

import cn.hutool.Hutool;
import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

import java.util.List;

/**
 * @author lianghh
 * @date 2023/8/21 12:17
 * @desc 字典具体项实体类
 **/
@Data
public class SysDictItem extends BaseCreateBy {

    /**
     * 主键
     */
    private String dictItemId;

    /**
     * 字典名称
     */
    private String dictItemName;

    /**
     * 字典编码 同租户下不可重复
     */
    private String dictItemCode;

    /**
     * 字典值
     */
    private String dictItemValue;

    /**
     * 字典分组ID 外键
     */
    private String dictId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 筛选时如果多个字典组 可以将dictId添加到该list中
     */
    private List<String> dictIdList;
}
