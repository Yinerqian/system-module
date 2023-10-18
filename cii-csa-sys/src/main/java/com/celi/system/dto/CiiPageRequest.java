package com.celi.system.dto;

import com.celi.system.utils.SortUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/11
 */
public class CiiPageRequest extends PageRequest {

    protected CiiPageRequest(int page, int size) {
        super(page, size, SortUtils.defaultSort());
    }

    protected CiiPageRequest(int page, int size, Sort sort) {
        super(page, size, sort);
    }

    public static CiiPageRequest formatPagination(int page, int size) {
        if (page > 0) {
            page -= 1;
        }
        return new CiiPageRequest(page, size);
    }

    public static CiiPageRequest formatPagination(int page, int size, String sortStr) {
        if (page > 0) {
            page -= 1;
        }
        Sort sort = null;
        if (StringUtils.isBlank(sortStr)) {
            sort = SortUtils.defaultSort();
        } else {
            String[] sortArray = sortStr.split(";");
            List<Sort.Order> orderList = new ArrayList<>();
            for (String sortParam : sortArray) {
                String[] sortParamArray = sortParam.split(",");
                if (sortParamArray.length == 2) {
                    // 正序
                    if (StringUtils.equals(Sort.Direction.ASC.toString(), sortParamArray[1].toUpperCase())) {
                        orderList.add(Sort.Order.asc(sortParamArray[0]));
                    } else {
                        orderList.add(Sort.Order.desc(sortParamArray[0]));
                    }
                }
            }
            if (CollectionUtils.isEmpty(orderList)) {
                sort = SortUtils.defaultSort();
            } else {
                sort = Sort.by(orderList);
            }
        }
        return new CiiPageRequest(page, size, sort);
    }
}
