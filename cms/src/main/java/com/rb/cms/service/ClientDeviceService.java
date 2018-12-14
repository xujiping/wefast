package com.rb.cms.service;

import com.rb.cms.entity.ClientDevice;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-11-10
 */
public interface ClientDeviceService extends IService<ClientDevice> {

    List<ClientDevice> getByClientListen(String listenId);
}
