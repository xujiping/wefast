package com.rb.cms.entity;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2018/11/19 18:57
 */
public class Redis implements Serializable {

    private static final long serialVersionUID = 1918365834107162049L;
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Redis{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
