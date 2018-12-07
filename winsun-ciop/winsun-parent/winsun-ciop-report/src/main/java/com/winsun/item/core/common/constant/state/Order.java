package com.winsun.item.core.common.constant.state;

/**
 * @author fulonghuang
 * @date 2018/12/5
 */
public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
