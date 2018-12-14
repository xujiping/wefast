package com.frontend.media.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.SpageUtil;
import com.frontend.media.controller.dto.AtlasDto;
import com.frontend.media.entity.Atlas;
import com.frontend.media.entity.ClientListen;
import com.frontend.media.entity.Image;
import com.frontend.media.mapper.AtlasMapper;
import com.frontend.media.service.AtlasService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.service.ClientListenService;
import com.frontend.media.service.ImageService;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.FileUtil;
import com.frontend.media.util.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图集 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
@Service
@Transactional(rollbackFor = BusinessException.class)
public class AtlasServiceImpl extends ServiceImpl<AtlasMapper, Atlas> implements AtlasService {

    @Autowired
    private ClientListenService listenService;

    @Autowired
    private ImageService imageService;

    @Override
    public SpageUtil<Atlas> getUserlist(SpageUtil<Atlas> spageUtil, Map<String, Object> params) {
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        Wrapper<Atlas> wrapper = new EntityWrapper<>();
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
        Page<Atlas> objectPage = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        List<Atlas> list = selectPage(objectPage, wrapper).getRecords();
        int total = selectCount(wrapper);
        if (list != null) {
            spageUtil.setRows(list);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }


    @Override
    public Atlas getOne(String title, String clientListenId, String stat) {
        Wrapper<Atlas> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotEmpty(title)) {
            wrapper.eq("title", title);
        }
        if (StringUtils.isNotEmpty(clientListenId)) {
            wrapper.eq("client_listen_id", clientListenId);
        }
        if (StringUtils.isNotEmpty(stat)) {
            wrapper.eq("stat", stat);
        }
        return selectOne(wrapper);
    }

    @Override
    public void add(String title, int categoryId, JSONArray imgJson, String fileServer) {
        Date date = new Date();
        String content = null;
        int seq = 0;
        Image image;
        StringBuilder sb = new StringBuilder();
        //默认的封面ID
        long defaultCoverId = 0L;
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        //判断是否已存在
        Atlas one = getOne(title, listenId, null);
        if (one != null) {
            throw new BusinessException(ReturnCode.ATLAS_EXIST);
        }
        // 创建图集
        Atlas atlas = new Atlas();
        atlas.setClientListenId(listenId);
        atlas.setCategoryId(categoryId);
        atlas.setCreateTime(date);
        atlas.setUpdateTime(date);
        atlas.setTitle(title);
        atlas.setImgCount(imgJson.size());
        boolean insert = atlas.insert();
        if (!insert) {
            throw new BusinessException(ReturnCode.FAIL);
        }
        atlas = getOne(title, listenId, Constants.STAT_AUDIT);
        Long atlasId = atlas.getId();
        //遍历添加图片
        for (Object object : imgJson) {
            JSONObject jsonObject = (JSONObject) object;
            String url = jsonObject.getString("url");
            url = UrlUtil.toMapStr(url);
            if (jsonObject.containsKey("content")) {
                content = jsonObject.getString("content");
            }
            if (jsonObject.containsKey("seq")) {
                seq = jsonObject.getInteger("seq");
            }
            image = imageService.add(atlasId, url, content, seq, fileServer);
            if (image == null) {
                throw new BusinessException(ReturnCode.FAIL);
            }
            Long imageId = image.getId();
            boolean cover = jsonObject.getBoolean("cover");
            if (cover) {
                sb.append(imageId).append(",");
            }
            if (seq == 0) {
                defaultCoverId = imageId;
            }
        }
        // 更新图集封面ID
        if (StringUtils.isNotEmpty(sb.toString())) {
            atlas.setCoverImgIds(sb.toString());
        } else {
            //没有设置封面，则默认第0个
            atlas.setCoverImgIds(defaultCoverId + ",");
        }
        boolean update = atlas.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
    }

    @Override
    public void update(long atlasId, String title, int categoryId, JSONArray imgJson, String fileServer) {
        Date date = new Date();
        Image image = null;
        String content = null;
        int seq = 0;
        //默认的封面ID
        long defaultCoverId = 0L;
        StringBuilder coverImage = new StringBuilder();
        Atlas atlas = selectById(atlasId);
        //先更新图片
        for (Object object : imgJson) {
            JSONObject jsonObject = (JSONObject) object;
            Long imageId = jsonObject.getLong("id");
            String url = jsonObject.getString("url");
            url = UrlUtil.toMapStr(url);
            if (jsonObject.containsKey("content")) {
                content = jsonObject.getString("content");
            }
            if (jsonObject.containsKey("seq")) {
                seq = jsonObject.getInteger("seq");
            }
            if (imageId != null) {
                //更新
                image = imageService.update(imageId, url, content, seq, fileServer);
                if (image == null) {
                    throw new BusinessException(ReturnCode.FAIL);
                }
            } else {
                //新增
                image = imageService.add(atlasId, url, content, seq, fileServer);
                imageId = image.getId();
            }
            boolean cover = jsonObject.getBoolean("cover");
            if (cover) {
                coverImage.append(imageId).append(",");
            }
            if (seq == 0) {
                defaultCoverId = imageId;
            }
        }
        // 更新图集封面ID
        if (StringUtils.isNotEmpty(coverImage.toString())) {
            atlas.setCoverImgIds(coverImage.toString());
        } else {
            //没有设置封面，则默认第0个
            atlas.setCoverImgIds(defaultCoverId + ",");
        }
        atlas.setTitle(title);
        atlas.setUpdateTime(date);
        atlas.setCategoryId(categoryId);
        atlas.setImgCount(imgJson.size());
        boolean update = atlas.updateById();
        if (!update) {
            throw new BusinessException(ReturnCode.FAIL);
        }
    }

    @Override
    public boolean checkPermission(Long atlasId) {
        Atlas atlas = getUserAtlas(atlasId);
        return atlas != null;
    }

    @Override
    public Atlas getUserAtlas(Long atlasId) {
        ClientListen curListen = listenService.getCurListen();
        String listenId = curListen.getId();
        Wrapper<Atlas> wrapper = new EntityWrapper<>();
        wrapper.eq("client_listen_id", listenId);
        wrapper.eq("id", atlasId);
        return selectOne(wrapper);
    }

    @Override
    public AtlasDto wrapper(Atlas atlas, String fileServer) {
        AtlasDto atlasDto = new AtlasDto();
        if (atlas == null) {
            return atlasDto;
        }
        BeanUtils.copyProperties(atlas, atlasDto);
        Date createTime = atlas.getCreateTime();
        atlasDto.setCreateTime(DateTimeUtil.getDate(createTime));
        //查询封面
        List<Image> imageList = imageService.getCoverList(atlas);
        if (imageList != null && imageList.size() > 0) {
            String url = imageList.get(0).getImgUrl();
            url = UrlUtil.mapToUrl(url);
            url = url.replaceAll(fileServer, "");
            url = AliyunOssUtil.getImgUrl(url, 100, 100);
            atlasDto.setCoverImgUrl(url);
        }
        return atlasDto;
    }

    @Override
    public void removeCover(String imageId) {
        Image image = imageService.selectById(imageId);
        if (image != null) {
            Long atlasId = image.getAtlasId();
            Atlas atlas = selectById(atlasId);
            if (atlas != null) {
                String coverImgIds = atlas.getCoverImgIds();
                String newCoverImgIds = coverImgIds.replace(imageId + ",", "");
                if (StringUtils.isEmpty(newCoverImgIds)) {
                    //设置图集第一张图片为封面
                    newCoverImgIds += getFirstImageId(atlasId) + ",";
                }
                atlas.setCoverImgIds(newCoverImgIds);
                boolean update = atlas.updateById();
                if (!update) {
                    throw new BusinessException(ReturnCode.FAIL);
                }
            }
        }
    }

    @Override
    public long getFirstImageId(long atlasId) {
        Wrapper<Image> wrapper = new EntityWrapper<>();
        wrapper.orderBy("seq");
        wrapper.eq("atlas_id", atlasId);
        Image image = imageService.selectOne(wrapper);
        if (image != null) {
            return image.getId();
        }
        return 0;
    }

    @Override
    public void remove(long atlasId, String fileServer) {
        boolean delete = imageService.deleteByAtlas(atlasId, fileServer);
        if (!delete) {
            throw new BusinessException();
        }
        deleteById(atlasId);
    }
}
