package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.util.DateTimeUtil;
import com.frontend.media.entity.ClientRelease;
import com.frontend.media.entity.ContentStatistical;
import com.frontend.media.entity.ResContent;
import com.frontend.media.mapper.ContentStatisticalMapper;
import com.frontend.media.service.ContentStatisticalService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.service.ResContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 内容访问记录表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-08-22
 */
@Service
public class ContentStatisticalServiceImpl extends ServiceImpl<ContentStatisticalMapper, ContentStatistical>
        implements ContentStatisticalService {

    @Autowired
    private ResContentService contentService;

    @Override
    public Long playCount(Long contentId) {
        Wrapper<ContentStatistical> wrapper = new EntityWrapper<>();
        wrapper.eq("res_content_id", contentId);
        int count = selectCount(wrapper);
        return Long.valueOf(count);
    }

    @Override
    public Long yestodayPlayCount(String listenId) {
        //in条件最大数量，数量太大，cpu性能下降厉害。
        int maxSelect = 200;
        long total = 0;
        int start = 0;
        int end = 0;
        //查询用户内容列表
        List<ResContent> userContents = contentService.getCurUserContents();
        if (userContents != null && userContents.size() > 0){
            List<String> contentIds = new ArrayList<>();
            for (ResContent resContent : userContents) {
                Long contentId = resContent.getId();
                contentIds.add(String.valueOf(contentId));
            }
            int size = contentIds.size();
            if (size > maxSelect) {
                int count = size / maxSelect + 1;
                for (int i = 0; i < count; i++) {
                    if (i == 0){
                        start = i * maxSelect;
                    }else {
                        start = i * maxSelect - 1;
                    }
                    end = (i + 1) * maxSelect - 1;
                    Wrapper<ContentStatistical> wrapper = new EntityWrapper<>();
                    if (i == count - 1) {
                        wrapper.in("res_content_id", contentIds.subList(start, size));
                    } else {
                        wrapper.in("res_content_id", contentIds.subList(start, end));
                    }
                    Date todayStart = DateTimeUtil.getTodayStart();
                    Date yestodayStart = DateTimeUtil.getDateOffset(todayStart, -1);
                    wrapper.between("create_time", DateTimeUtil.getDateTime(yestodayStart), DateTimeUtil.getDateTime
                            (todayStart));
                    total += selectCount(wrapper);
                }
            }
        }
        return total;
    }

}
