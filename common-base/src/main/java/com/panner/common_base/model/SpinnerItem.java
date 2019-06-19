package com.panner.common_base.model;

/**
 * Spinner 数据
 *
 * @author panzhijie
 * @version 2018-07-21 19:55
 */
public class SpinnerItem {
    private String name;
    private Object object;

    public SpinnerItem() {
    }

    public SpinnerItem(String name) {
        this.name = name;
    }

    public SpinnerItem(String name, Object object) {
        this.name = name;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
