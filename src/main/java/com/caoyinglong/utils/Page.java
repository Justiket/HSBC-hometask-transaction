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
public class Page<T> implements Serializable {
    private int total;
    private List<T> datas;

    public static <T> Page<T> EMPTY_PAGE() {
        return new Page<T>(0, new ArrayList<>());
    }

    public static <T> Page<T> of(int total, List<T> datas) {
        return new Page<T>(total, datas);
    }

    public boolean notEmpty() {
        return this.datas != null && !this.datas.isEmpty();
    }

    public Page(int total, List<T> datas) {
        this.total = total;
        this.datas = datas;
    }

    public Page(List<T> datas) {
        this.total = 0;
        this.datas = datas;
    }

    public void ifPresent(Consumer<List<T>> listConsumer) {
        if (this.getDatas() != null && !this.getDatas().isEmpty()) {
            listConsumer.accept(this.datas);
        }
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return this.datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

}






