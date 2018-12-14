package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.util.SpageUtil;
import com.frontend.media.entity.MediaNotice;
import com.frontend.media.mapper.MediaNoticeMapper;
import com.frontend.media.service.MediaNoticeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自媒体平台公告 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-09-03
 */
@Service
public class MediaNoticeServiceImpl extends ServiceImpl<MediaNoticeMapper, MediaNotice> implements MediaNoticeService {

    @Override
    public SpageUtil<MediaNotice> listByPage(Map<String, Object> params, SpageUtil<MediaNotice> spageUtil) {
        //条件
        Wrapper<MediaNotice> wrapper = new EntityWrapper<>();
        String sort = spageUtil.getSort();
        if (StringUtils.isNotEmpty(sort)){
            wrapper.orderBy(sort);
        }
        //分页
        Page<MediaNotice> objectPage = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<MediaNotice> list = selectPage(objectPage, wrapper).getRecords();
        if (list != null && list.size() > 0) {
            int total = selectCount(wrapper);
            spageUtil.setTotal(total);
            spageUtil.setRows(list);
        }
        return spageUtil;
    }
}
