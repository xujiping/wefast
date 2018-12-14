package com.common.base.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xujiping
 * @date 2018/5/14 18:06
 */
public class SpageUtil<T> implements Serializable {

    /**
     * 每页显示条数
     */
    private int step = 10;
    /**
     * 当前页
     */
    private int page = 1;
    /**
     * 总页数
     */
    private long pages = 0;
    /**
     * 总记录数
     */
    private long total = 0;
    /**
     * 搜索
     */
    private String search;
    /**
     * 排序
     */
    private String sort;

    private String sidx;
    private List<Map<String, String>> searchList = new ArrayList<Map<String, String>>();

    private List<T> rows;

    public SpageUtil() {

    }

    public SpageUtil(int page, int step) {
        this.step = step;
        this.page = page;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public List<Map<String, String>> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Map<String, String>> searchList) {
        this.searchList = searchList;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
