package com.celi.cii.base.utils;


import com.celi.cii.base.entity.BaseEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumUtils {
    public static <T extends Enum<?> & BaseEnum> T codeOf(Class<T> enumClass, String code) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }

    public static <T extends Enum<?> & BaseEnum> T titleOf(Class<T> enumClass, String title) {
        T[] enumConstants = enumClass.getEnumConstants();
        for (T t : enumConstants) {
            if (t.getTitle().equals(title)) {
                return t;
            }
        }
        return null;
    }

    public static <T extends Enum<T> & BaseEnum> T create(Class<T> enumClass, String name) {
        try {
            return T.valueOf(enumClass, name);
        } catch (IllegalArgumentException e) {
            //如果是数字类型
            //int code = Integer.parseInt(name);
            T[] enumConstants = enumClass.getEnumConstants();
            for (T t : enumConstants) {
                if (t.getCode().equals(name)) {
                    return t;
                }
            }
        }
        throw new IllegalArgumentException("No element matches " + name);
    }

    public static <T extends Enum<T> & BaseEnum> List<Map> listEnums(Class<T> enumClass) {
        List<Map> list = new ArrayList<>();
        T[] enumConstants = enumClass.getEnumConstants();
        for (T statusEnum : enumConstants) {
            Map obj = new HashMap();
            obj.put("key", statusEnum.getCode());
            obj.put("title", statusEnum.getTitle());
            list.add(obj);
        }
        return list;
    }

    /**
     * 获取枚举类的值显示在页面下拉框
     * @param enumClass
     * @param <T>
     * @return
     */
//    public static <T extends Enum<T> & BaseEnum> List<PageSelect> listPageEnums(Class<T> enumClass) {
//        List<PageSelect> list = new ArrayList<>();
//        T[] enumConstants = enumClass.getEnumConstants();
//        for (T statusEnum : enumConstants) {
//            PageSelect pageSelect = new PageSelect();
//            pageSelect.setKey(statusEnum.getCode());
//            pageSelect.setTitle(statusEnum.getTitle());
//            list.add(pageSelect);
//        }
//        return list;
//    }

    public static <T extends Enum<T> & BaseEnum> Integer getEnumCount(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        return enumConstants.length;
    }
}
