package com.celi.system.enums;

import javax.persistence.AttributeConverter;

/**
 * @Author: Changaowen
 * @Date: 2021/12/13 9:54
 */
public class SysParaConvert implements AttributeConverter<SysParaEnum, String> {


    @Override
    public String convertToDatabaseColumn(SysParaEnum attribute) {
        return attribute.getCode();
    }

    @Override
    public SysParaEnum convertToEntityAttribute(String dbData) {
        return SysParaEnum.fromValue(dbData);
    }

}
