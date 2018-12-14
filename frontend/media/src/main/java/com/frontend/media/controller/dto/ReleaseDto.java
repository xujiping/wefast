package com.frontend.media.controller.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author xujiping
 * @date 2018/8/22 13:44
 */
public class ReleaseDto {

    private Long id;
    /**
     * 发布号名称
     */
    private String name;
    /**
     * 头条号介绍
     */
    private String introduce;
    /**
     * 头像全路径（包含绝对路径、头像名称、扩展名）
     */
    private String headUrl;
    /**
     * 领域
     */
    private String field;
    /**
     * 领域子类
     */
    private String subField;
    /**
     * 状态（normal：正常、audit：待审核、pass：通过、failed：未通过、block：冻结）
     */
    private String stat;
    /**
     * 提交时间
     */
    private String submitTime;
    /**
     * 1精品 0普通
     */
    private Integer boutique;
    /**
     * 费用
     */
    private BigDecimal cost;
    /**
     * 0不需要会员
     */
    private Integer vip;
    /**
     * 被关注数
     */
    private Long follow;
    /**
     * 是否默认的关注专辑
     */
    private Boolean defaultFollow;

    private Long contentSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getSubField() {
        return subField;
    }

    public void setSubField(String subField) {
        this.subField = subField;
    }


    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getBoutique() {
        return boutique;
    }

    public void setBoutique(Integer boutique) {
        this.boutique = boutique;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Long getFollow() {
        return follow;
    }

    public void setFollow(Long follow) {
        this.follow = follow;
    }

    public Boolean getDefaultFollow() {
        return defaultFollow;
    }

    public void setDefaultFollow(Boolean defaultFollow) {
        this.defaultFollow = defaultFollow;
    }


    public Long getContentSize() {
        return contentSize;
    }

    public void setContentSize(Long contentSize) {
        this.contentSize = contentSize;
    }

    @Override
    public String toString() {
        return "ReleaseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", field='" + field + '\'' +
                ", subField='" + subField + '\'' +
                ", stat='" + stat + '\'' +
                ", submitTime='" + submitTime + '\'' +
                ", boutique=" + boutique +
                ", cost=" + cost +
                ", vip=" + vip +
                ", follow=" + follow +
                ", defaultFollow=" + defaultFollow +
                ", contentSize=" + contentSize +
                '}';
    }
}
