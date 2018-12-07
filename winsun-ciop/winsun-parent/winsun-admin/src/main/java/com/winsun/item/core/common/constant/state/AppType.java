package com.winsun.item.core.common.constant.state;

/**
 * 手机系统类型
 *
 * @author fulonghuang
 * @date 2018/11/29
 */
public enum AppType {

    ALL("",null),
    ANDROID("0", "Android"),
    IOS("1", "IOS");

    String val;
    String softSystem;

    AppType(String val, String softSystem) {
        this.val = val;
        this.softSystem = softSystem;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getSoftSystem() {
        return softSystem;
    }

    public void setSoftSystem(String softSystem) {
        this.softSystem = softSystem;
    }

    public static String valOf(String value){
        if (value == null) {
            return null;
        } else {
            for (AppType appType : AppType.values()) {
                if (appType.getVal().equals(value)) {
                    return appType.getSoftSystem();
                }
            }
            return null;
        }
    }
}
