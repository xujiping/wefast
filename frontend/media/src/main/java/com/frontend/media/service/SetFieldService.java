package com.frontend.media.service;

import com.frontend.media.entity.SetField;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 领域设置 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-07-26
 */
public interface SetFieldService extends IService<SetField> {

    /**
     * 获取可用的类别列表
     * @return
     */
    List<SetField> getFields();

}
