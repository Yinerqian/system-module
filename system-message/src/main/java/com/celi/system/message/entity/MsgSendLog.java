package com.celi.system.message.entity;

import com.celi.cii.base.entity.BaseCreateBy;
import com.celi.system.message.enums.MsgSendStatusEnum;
import lombok.Data;

/**
 * 发送日志实体类
 */
@Data
public class MsgSendLog extends BaseCreateBy {

    /**
     * 每次请求随机生成的uuid 主键
     */
    private String requestId;

    /**
     * 请求使用的渠道编码
     */
    private String channelCode;

    /**
     * 渠道名称 (数据库中不存在该字段 与 channel关联获取)
     */
    private String channelName;

    /**
     * 请求使用的消息模型编码
     */
    private String templateCode;

    /**
     * 消息模板名称 (与template表关联查询)
     */
    private String templateName;

    /**
     * 请求使用的业务场景编码
     */
    private String topicCode;

    /**
     * 业务场景名称 (与topic关联查询)
     */
    private String topicName;

    /**
     * 接受发送请求后 转发到kafka的具体内容
     */
    private String messageBody;

    /**
     * 发送请求的返回体关键信息
     */
    private String sendResponse;

    /**
     * 发送的状态
     */
    private MsgSendStatusEnum sendStatus;

    /**
     * 备注信息
     */
    private String remark;

}
