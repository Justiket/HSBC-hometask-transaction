package com.caoyinglong.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author caoyinglong
 * @description 分页基础类
 * @since 2025-01-22 14:17:19
 */

@NoArgsConstructor
@Getter
@Setter
public class PageResult<T> implements Serializable {
    private int total;
    private int totalPage;
    private int pageSize;
    private int pageNum;
    private List<T> datas;

    public static <T> PageResult<T> EMPTY_PAGE() {
        return new PageResult<T>(0, new ArrayList<>());
    }

    public static <T> PageResult<T> of(int total, int totalPage, int pageNum, int pageSize, List<T> datas) {
        return new PageResult<T>(total, totalPage,pageNum, pageSize, datas);
    }

    public static <T> PageResult<T> of(int pageNum, int pageSize) {
        return new PageResult<T>(pageNum, pageSize);
    }

    public boolean notEmpty() {
        return this.datas != null && !this.datas.isEmpty();
    }

    public PageResult(int total, List<T> datas) {
        this.total = total;
        this.datas = datas;
    }

    public PageResult(List<T> datas) {
        this.total = datas  == null ? 0:datas.size();
        this.datas = datas;
    }
    public PageResult(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageResult(int total, int totalPage, int pageNum, int pageSize, List<T> datas) {
        this.total = total;
        this.pageNum = pageNum;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.datas = datas;
    }

    public void ifPresent(Consumer<List<T>> listConsumer) {
        if (this.getDatas() != null && !this.getDatas().isEmpty()) {
            listConsumer.accept(this.datas);
        }
    }

    public static boolean isValid(int pageNum, int pageSize) {
        return pageNum >= 1 && pageSize > 0;
    }
}






