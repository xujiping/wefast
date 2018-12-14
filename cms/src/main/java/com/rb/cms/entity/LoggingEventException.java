package com.rb.cms.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xujiping
 * @since 2018-11-16
 */
public class LoggingEventException extends Model<LoggingEventException> {

    private static final long serialVersionUID = 1L;

    private Long eventId;
    private Integer i;
    private String traceLine;


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getTraceLine() {
        return traceLine;
    }

    public void setTraceLine(String traceLine) {
        this.traceLine = traceLine;
    }

    @Override
    protected Serializable pkVal() {
        return this.eventId;
    }

    @Override
    public String toString() {
        return "LoggingEventException{" +
        "eventId=" + eventId +
        ", i=" + i +
        ", traceLine=" + traceLine +
        "}";
    }
}
