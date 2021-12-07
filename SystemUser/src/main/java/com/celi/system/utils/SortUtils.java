package com.celi.system.utils;

import org.springframework.data.domain.Sort;

/**
 * @author ce-li
 * @date 2021/11/10
 * @desc 排序工具类
 */
public class SortUtils {

    private static final String DEFAULT_SORT_FIELD = "createdTime";

    public static Sort defaultSort() {
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, DEFAULT_SORT_FIELD);
        return Sort.by(order);
    }
}
