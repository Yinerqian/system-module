package com.celi.system.dict.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

/**
 * @author lianghh
 * @date 2023/8/21 12:17
 * @desc 字典组实体类
 **/
@Data
public class SysDictGroup extends BaseCreateBy {

    /**
     * 主键
     */
    private String dictId;

    /**
     * 字典分组名称
     */
    private String dictName;

    /**
     * 字典分组编码 同租户下不可重复
     */
    private String dictCode;

    /**
     * 备注
     */
    private String remark;

}
