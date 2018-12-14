package com.frontend.media.entity;

/**
 * @author xujiping
 * @date 2018/11/6 18:13
 */
public class MyFile {

    /**
     * 文件名
     */
    private String name;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 相对路径
     */
    private String path;

    /**
     * 大小
     */
    private long size;

    /**
     * 时长
     */
    private long time;

    private double rateHw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getRateHw() {
        return rateHw;
    }

    public void setRateHw(double rateHw) {
        this.rateHw = rateHw;
    }

    @Override
    public String toString() {
        return "MyFile{" +
                "name='" + name + '\'' +
                ", suffix='" + suffix + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", time=" + time +
                ", rateHw=" + rateHw +
                '}';
    }
}
