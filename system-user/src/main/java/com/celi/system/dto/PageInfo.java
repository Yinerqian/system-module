package com.celi.system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author ce-li
 * @date 2021/11/10
 */
@Data
@NoArgsConstructor
public class PageInfo<T> {

    private int pageNum;
    private int pageSize;
    private long total;
    private List<T> list;

    public PageInfo(Page<T> page) {
        this.pageNum = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.total = page.getTotalElements();
        this.list = page.getContent();
    }

    public PageInfo(int pageNum, int pageSize, long total, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = list;
    }
}
