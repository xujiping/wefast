package com.mall.gelunbu.entity;

import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 提现表
 * </p>
 *
 * @author xujiping
 * @since 2018-06-26
 */
public class Withdraw extends Model<Withdraw> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户ID
     */
    private String clientListenId;
    /**
     * 支付账户
     */
    private String payAccount;
    /**
     * 1支付宝 2微信
     */
    private Integer type;
    /**
     * 金额
     */
    private BigDecimal coin;
    /**
     * 提现时间
     */
    private Date time;
    /**
     * 到账时间
     */
    private Date successTime;
    /**
     * 0待审核 1已发货 2审核不通过
     */
    private Integer status;


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

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getCoin() {
        return coin;
    }

    public void setCoin(BigDecimal coin) {
        this.coin = coin;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Withdraw{" +
        "id=" + id +
        ", clientListenId=" + clientListenId +
        ", payAccount=" + payAccount +
        ", type=" + type +
        ", coin=" + coin +
        ", time=" + time +
        ", successTime=" + successTime +
        ", status=" + status +
        "}";
    }
}
