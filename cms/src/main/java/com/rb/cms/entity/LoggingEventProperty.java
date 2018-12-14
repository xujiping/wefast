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
public class LoggingEventProperty extends Model<LoggingEventProperty> {

    private static final long serialVersionUID = 1L;

    private Long eventId;
    private String mappedKey;
    private String mappedValue;


    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public String getMappedKey() {
        return mappedKey;
    }

    public void setMappedKey(String mappedKey) {
        this.mappedKey = mappedKey;
    }

    public String getMappedValue() {
        return mappedValue;
    }

    public void setMappedValue(String mappedValue) {
        this.mappedValue = mappedValue;
    }

    @Override
    protected Serializable pkVal() {
        return this.eventId;
    }

    @Override
    public String toString() {
        return "LoggingEventProperty{" +
        "eventId=" + eventId +
        ", mappedKey=" + mappedKey +
        ", mappedValue=" + mappedValue +
        "}";
    }
}
