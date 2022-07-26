package com.celi.cii.base.enums;

import com.celi.cii.base.entity.BaseEnum;
import lombok.Getter;

/**
 *  设备状态枚举值
 */
@Getter
public enum DataSetTypeEnum implements BaseEnum {

    TEXT("text", "文本类"),
    IMAGE("image", "图像类"),
    AUDIO("audio", "语音类"),
    VIDEO("video", "视频类"),
    EXCEL("excel", "Excel类"),
    ;

    private String code;
    private String desc;

    DataSetTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getTitle() {
        return desc;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
