package com.frontend.media.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author xujiping
 * @since 2018-08-28
 */
public class ClientListenFollow extends Model<ClientListenFollow> {

    private static final long serialVersionUID = 1L;

    private String clientListenId;
    private String clientReleaseId;
    private Date createTime;


    public String getClientListenId() {
        return clientListenId;
    }

    public void setClientListenId(String clientListenId) {
        this.clientListenId = clientListenId;
    }

    public String getClientReleaseId() {
        return clientReleaseId;
    }

    public void setClientReleaseId(String clientReleaseId) {
        this.clientReleaseId = clientReleaseId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "ClientListenFollow{" +
        "clientListenId=" + clientListenId +
        ", clientReleaseId=" + clientReleaseId +
        ", createTime=" + createTime +
        "}";
    }
}
