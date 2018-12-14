package com.rb.cms.entity;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * @author xujiping
 * @date 2018/6/9 17:35
 */
public class SuperEntity<T extends Model> extends Model<T>{

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
