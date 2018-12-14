package com.frontend.media.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.FileUtil;
import com.common.base.util.SpageUtil;
import com.frontend.media.common.Constants;
import com.frontend.media.controller.dto.VideoDto;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.MyFile;
import com.frontend.media.entity.Video;
import com.frontend.media.mapper.VideoMapper;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.VideoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.FfmpegUtil;
import com.frontend.media.util.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-27
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    private static final Logger LOG = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private VideoService videoService;

    @Override
    public SpageUtil<Video> getUserlist(SpageUtil<Video> spageUtil, Map<String, Object> params) {
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        Wrapper<Video> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", listenId);
        String sort = spageUtil.getSort();
        if (StringUtils.isNotEmpty(sort)) {
            wrapper.orderBy(sort);
        }
        if (params != null && params.containsKey("title")) {
            String title = (String) params.get("title");
            wrapper.like("title", "%" + title + "%");
        }
        if (params != null && params.containsKey("stat")) {
            String stat = (String) params.get("stat");
            wrapper.eq("stat", stat);
        }
        Page<Video> objectPage = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<Video> list = selectPage(objectPage, wrapper).getRecords();
        int total = selectCount(wrapper);
        if (list != null) {
            spageUtil.setRows(list);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }

    @Override
    public boolean add(String title, String url, int categoryId, String cover, String fileServer) {
        Date date = new Date();
        Video video = new Video();
        ClientListen curListen = listenService.getCurListen();
        video.setClientListenId(curListen.getId());
        video.setTitle(title);
        if (StringUtils.isNotEmpty(url)){
            url = url.replaceAll(fileServer, "");
            // 新增高/宽比率
            double imgRateOfHw = AliyunOssUtil.getImgRateOfHw(cover);
            MyFile myFile = JSONObject.parseObject(url, MyFile.class);
            myFile.setRateHw(imgRateOfHw);
            url = JSONArray.toJSONString(myFile);
            video.setUrl(url);
        }
        video.setCreateTime(date);
        video.setUpdateTime(date);
        video.setCoverUrl(cover);
        video.setCategoryId(categoryId);
        return video.insert();
    }

    @Override
    public MyFile getVideoInfo(String ffmpeg, File file, String filePath, String videoPrefix) {
        MyFile myFile = new MyFile();
        if (file.exists() || file.isFile()) {
            String fileNameWithoutSuffix = FileUtil.getFileNameWithoutSuffix(file);
            if (StringUtils.isNotEmpty(fileNameWithoutSuffix)) {
                myFile.setName(fileNameWithoutSuffix);
            }
            String suffix = FileUtil.getSuffix(file);
            if (StringUtils.isNotEmpty(suffix)) {
                myFile.setSuffix(suffix);
            }
            // 时长
            int videoTime = FfmpegUtil.getVideoTime(ffmpeg, filePath);
            myFile.setTime(videoTime);
            // 大小
            myFile.setSize(file.length());
            // 相对路径
            myFile.setPath(videoPrefix);
        }
        return myFile;
    }

    @Override
    public List<String> coverImage(String ffmpeg, File newFile, String videoPrefix, String fileName) {
        // 第一个是视频文件路径，之后是截图的路径
        List<String> list = new ArrayList<>();
        if (newFile.isFile() && newFile.exists()) {
            String canonicalPath = "";
            try {
                canonicalPath = newFile.getCanonicalPath();
                //视频文件信息
                MyFile videoInfo = getVideoInfo(ffmpeg, newFile, canonicalPath, videoPrefix);
                if (videoInfo != null) {
                    long time = videoInfo.getTime();
                    long timeOffset = time / Constants.VIDEO_COVER_IMAGE_COUNT;
                    long coverSecond;
                    //添加视频信息
                    list.add(JSONArray.toJSONString(videoInfo));
                    //截图
                    for (int i = 0; i < Constants.VIDEO_COVER_IMAGE_COUNT; i++) {
                        String imgSuffix = "-" + i + ".jpg";
                        // 截图文件路径
                        StringBuilder sb = new StringBuilder();
                        sb.append(canonicalPath, 0, canonicalPath.lastIndexOf(".")).append(imgSuffix);
                        coverSecond = i * timeOffset + 1;
                        FfmpegUtil.coverVideoImg(ffmpeg, canonicalPath, String.valueOf(coverSecond), sb.toString());
                        list.add(videoPrefix + fileName + imgSuffix);
                    }
                }
            } catch (IOException e) {
                JSONObject errorJson = new JSONObject();
                errorJson.put("video", canonicalPath);
                LOG.error("读取短视频文件失败-->" + errorJson.toJSONString(), e);
            }
        }
        return list;
    }

    @Override
    public VideoDto wrapper(Video video, String fileServer) {
        VideoDto videoDto = new VideoDto();
        if (video == null) {
            return videoDto;
        }
        BeanUtils.copyProperties(video, videoDto);
        String url = video.getUrl();
        Date createTime = video.getCreateTime();
        if (StringUtils.isNotEmpty(url)) {
            url = UrlUtil.mapToUrl(url);
            url = url.replace(fileServer, "");
            url = AliyunOssUtil.getSignUrl(AliyunOssUtil.TINGDUODUO_BUCKET_NAME, url);
            videoDto.setUrl(url);
        }
        videoDto.setCreateTime(DateTimeUtil.getDate(createTime));
        return videoDto;
    }

    @Override
    public boolean deleteFile(Long id, String fileServer) {
        Video video = selectById(id);
        String url = video.getUrl();
        //删除记录
        boolean delete = deleteById(id);
        if (delete) {
            //删除本地文件
            JSONObject jsonObject = JSONObject.parseObject(url);
            String path = jsonObject.getString("path");
            String name = jsonObject.getString("name");
            String suffix = jsonObject.getString("suffix");
            FileUtil.deleteFile(path);
            //删除oss文件
            path = path.replace(fileServer, "");
            List<String> keys = new ArrayList<>();
            //删除视频
            keys.add(path + name + "." + suffix);
            //删除封面截图
            for (int i = 0; i < 8; i++) {
                String key = path + name + "-" + i + ".jpg";
                keys.add(key);
            }
            AliyunOssUtil.deleteObjects(keys);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeStat(long id, String stat) {
        Video video = new Video();
        video.setId(id);
        video.setStat(stat);
        video.setUpdateTime(new Date());
        return updateById(video);
    }


}
