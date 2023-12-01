package com.celi.system.message.enums;

import com.celi.cii.base.entity.BaseEnum;

public enum MsgSendStatusEnum implements BaseEnum {
    WAIT_SENDING("0", "等待发送"), // 在kakfa中状态为等待发送
    SENDING("1", "发送中"), // 进入kafka消费者
    SEND_NEED_VALID("2", "待验证回执"),
    SEND_SUCCESS("3", "发送成功"),
    SEND_PART_SUCCESS("4", "部分发送成功"),
    WAIT_CALLBACK("5", "等待回调通知"),
    SEND_FAIL("-1", "发送失败");

    private String code;
    private String title;

    MsgSendStatusEnum(String code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getTitle() {
        return this.title;
    }
}
