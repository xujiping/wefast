package com.mall.gelunbu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 福利表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public class Weal extends Model<Weal> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private String clientListenId;
    /**
     * 总零钱
     */
    private Double allCoin;
    /**
     * 当前零钱
     */
    private Double currentCoin;
    /**
     * 今日零钱
     */
    private Double todayCoin;
    /**
     * 当前金币
     */
    private Double currentGold;
    /**
     * 今日金币
     */
    private Double todayGold;
    /**
     * 总时长 (秒)
     */
    private Long allUseTime;
    /**
     * 今日时长（秒）
     */
    private Integer todayUseTime;
    /**
     * 可提现
     */
    private Double withdraw;
    /**
     * 昨日金币
     */
    private Double yestodayGold;
    /**
     * 昨日金币兑换的零钱
     */
    private Double yestodayGoldToCoin;
    /**
     * 福豆（用于音频消费）
     */
    private BigDecimal currentBean;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    public Double getAllCoin() {
        return allCoin;
    }

    public void setAllCoin(Double allCoin) {
        this.allCoin = allCoin;
    }

    public Double getCurrentCoin() {
        return currentCoin;
    }

    public void setCurrentCoin(Double currentCoin) {
        this.currentCoin = currentCoin;
    }

    public Double getTodayCoin() {
        return todayCoin;
    }

    public void setTodayCoin(Double todayCoin) {
        this.todayCoin = todayCoin;
    }

    public Double getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(Double currentGold) {
        this.currentGold = currentGold;
    }

    public Double getTodayGold() {
        return todayGold;
    }

    public void setTodayGold(Double todayGold) {
        this.todayGold = todayGold;
    }

    public Long getAllUseTime() {
        return allUseTime;
    }

    public void setAllUseTime(Long allUseTime) {
        this.allUseTime = allUseTime;
    }

    public Integer getTodayUseTime() {
        return todayUseTime;
    }

    public void setTodayUseTime(Integer todayUseTime) {
        this.todayUseTime = todayUseTime;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Double getYestodayGold() {
        return yestodayGold;
    }

    public void setYestodayGold(Double yestodayGold) {
        this.yestodayGold = yestodayGold;
    }

    public Double getYestodayGoldToCoin() {
        return yestodayGoldToCoin;
    }

    public void setYestodayGoldToCoin(Double yestodayGoldToCoin) {
        this.yestodayGoldToCoin = yestodayGoldToCoin;
    }

    public BigDecimal getCurrentBean() {
        return currentBean;
    }

    public void setCurrentBean(BigDecimal currentBean) {
        this.currentBean = currentBean;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Weal{" +
        "id=" + id +
        ", clientListenId=" + clientListenId +
        ", allCoin=" + allCoin +
        ", currentCoin=" + currentCoin +
        ", todayCoin=" + todayCoin +
        ", currentGold=" + currentGold +
        ", todayGold=" + todayGold +
        ", allUseTime=" + allUseTime +
        ", todayUseTime=" + todayUseTime +
        ", withdraw=" + withdraw +
        ", yestodayGold=" + yestodayGold +
        ", yestodayGoldToCoin=" + yestodayGoldToCoin +
        ", currentBean=" + currentBean +
        "}";
    }
}
