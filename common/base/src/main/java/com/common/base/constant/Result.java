package com.common.base.constant;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author xujiping
 * @date 2018/6/20 13:40
 */
public class Result implements Serializable {

    /**
     * 错误码，没有错误返回0
     */
    private Integer errno;

    private String[] data;

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "errno=" + errno +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
