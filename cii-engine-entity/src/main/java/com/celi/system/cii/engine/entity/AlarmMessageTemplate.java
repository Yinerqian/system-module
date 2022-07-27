package com.celi.system.cii.engine.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import lombok.Data;

/**
 * 告警消息模板
 */
@Data
public class AlarmMessageTemplate extends BaseCreateBy {

    // 模板id
    private String templateId;

    // 模板名称
    private String templateName;

    // 模板编码
    private String templateCode;

    // 模板内容，json形式
    private String templateContent;

    // 备注
    private String remark;
}
