package com.dazaza.model;

import java.io.Serializable;

/**
 * Created by cunqingli on 2015/8/26.
 * 下发菜单
 */
public class ModelMenu implements Serializable {
    private static final long serialVersionUID = 131071537293715114L;

    private String categoryId ;
    private String categoryName;
    private int order;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
