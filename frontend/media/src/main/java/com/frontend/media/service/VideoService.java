package com.frontend.media.service;

import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.VideoDto;
import com.frontend.media.entity.MyFile;
import com.frontend.media.entity.Video;
import com.baomidou.mybatisplus.service.IService;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-27
 */
public interface VideoService extends IService<Video> {

    /**
     * 获取当前用户的列表
     * @return
     */
    SpageUtil<Video> getUserlist(SpageUtil<Video> spageUtil, Map<String, Object> params);

    /**
     * 新增视频
     * @param title
     * @param url
     * @param categoryId
     * @param cover
     * @return
     */
    boolean add(String title, String url, int categoryId, String cover, String fileServer);

    /**
     * 获取视频文件信息
     *
     * @param ffmpeg   ffmpeg
     * @param file     文件
     * @param filePath 视频文件相对路径
     * @return
     */
    MyFile getVideoInfo(String ffmpeg, File file, String filePath, String videoPrefix);

    /**
     * 视频截图
     *
     * @param ffmpeg
     * @param newFile
     * @return 截图路径列表
     */
    List<String> coverImage(String ffmpeg, File newFile, String videoPrefix, String fileName);

    VideoDto wrapper(Video video, String fileServer);

    /**
     * 删除记录和数据
     * @param id
     * @param fileServer
     * @return
     */
    boolean deleteFile(Long id, String fileServer);

    /**
     * 更改状态
     * @param id
     * @param stat
     * @return
     */
    boolean changeStat(long id, String stat);
}
