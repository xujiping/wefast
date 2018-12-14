package com.frontend.media.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.base.constant.Constants;
import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;
import com.frontend.media.controller.dto.ImageDto;
import com.frontend.media.controller.dto.ImageTab;
import com.frontend.media.entity.Atlas;
import com.frontend.media.entity.Image;
import com.frontend.media.mapper.ImageMapper;
import com.frontend.media.service.ImageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.frontend.media.util.AliyunOssUtil;
import com.frontend.media.util.FileUtil;
import com.frontend.media.util.GsonUtil;
import com.frontend.media.util.UrlUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图片表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Override
    public List<Image> getCoverList(Atlas atlas) {
        if (atlas == null) {
            return null;
        }
        String coverImgIds = atlas.getCoverImgIds();
        if (StringUtils.isEmpty(coverImgIds)) {
            return null;
        }
        List<Image> list = new ArrayList<>();
        String[] split = coverImgIds.split(",");
        for (String imgId : split) {
            Image image = selectById(imgId);
            if (image != null) {
                list.add(image);
            }
        }
        return list;
    }

    @Override
    public Image getOne(long atlasId, String url) {
        Wrapper<Image> wrapper = new EntityWrapper<>();
        wrapper.eq("atlas_id", atlasId);
        wrapper.eq("img_url", url);
        return selectOne(wrapper);
    }

    @Override
    public List<Image> getByAtlas(long atlasId) {
        Wrapper<Image> wrapper = new EntityWrapper<>();
        wrapper.orderBy("seq");
        wrapper.eq("atlas_id", atlasId);
        return selectList(wrapper);
    }

    @Override
    public boolean deleteByAtlas(long atlasId, String fileServer) {
        Wrapper<Image> wrapper = new EntityWrapper<>();
        wrapper.eq("atlas_id", atlasId);
        List<Image> imageList = selectList(wrapper);
        if (imageList != null && imageList.size() > 0) {
            String imgUrl;
            long id;
            for (Image image : imageList) {
                imgUrl = image.getImgUrl();
                id = image.getId();
                boolean delete = deleteById(id);
                if (delete) {
                    imgUrl = UrlUtil.mapToUrl(imgUrl);
                    deleteFile(imgUrl, fileServer);
                }
            }
        }
        return true;
    }

    @Override
    public ImageDto wrapper(Image image) {
        ImageDto imageDto = new ImageDto();
        if (image == null) {
            return imageDto;
        }
        BeanUtils.copyProperties(image, imageDto);
        String imgUrl = image.getImgUrl();
        imageDto.setImgUrl(UrlUtil.mapToUrl(imgUrl));
        return imageDto;
    }

    @Override
    public ImageTab wrapperTab(Image image, Atlas atlas, String fileServer) {
        ImageTab imageTab = new ImageTab();
        if (image == null) {
            return imageTab;
        }
        BeanUtils.copyProperties(image, imageTab);
        String imgUrl = image.getImgUrl();
        Long imageId = image.getId();
        imgUrl = UrlUtil.mapToUrl(imgUrl);
        if (!imgUrl.contains(fileServer)){
            imgUrl = fileServer + imgUrl;
        }
        imageTab.setUrl(imgUrl);
        //获取封面列表
        String coverImgIds = atlas.getCoverImgIds();
        if (StringUtils.isNotEmpty(coverImgIds)) {
            String[] split = coverImgIds.split(",");
            for (String id : split) {
                if (String.valueOf(imageId).equals(id)) {
                    imageTab.setCover(true);
                }
            }
        }
        return imageTab;
    }

    @Override
    public Image add(long atlasId, String imgUrl, String content, Integer seq, String fileServer) {
        if (StringUtils.isEmpty(imgUrl)){
            throw new BusinessException(ReturnCode.FAIL);
        }
        // 插入高/宽比率
        imgUrl = imgUrl.replaceAll(fileServer, "");
        String objectName = UrlUtil.mapToUrl(imgUrl);
        double imgRateOfHw = AliyunOssUtil.getImgRateOfHw(objectName);
        Map<String, Object> urlMap = GsonUtil.toMap(imgUrl);
        urlMap.put("rateHw", imgRateOfHw);
        imgUrl = GsonUtil.toStr(urlMap);
        Image image = new Image();
        Date date = new Date();
        image.setAtlasId(atlasId);
        image.setImgUrl(imgUrl);
        image.setContent(content);
        image.setSeq(seq);
        image.setCreateTime(date);
        image.setUpdateTime(date);
        image.setStat(Constants.STAT_NORMAL);
        boolean insert = image.insert();
        if (insert) {
            image = getOne(atlasId, imgUrl);
        }
        return image;
    }

    @Override
    public Image update(long id, String imgUrl, String content, Integer seq, String fileServer) {
        Image image = selectById(id);
        if (image == null) {
            return null;
        }
        if (StringUtils.isNotEmpty(imgUrl)) {
            // 插入高/宽比率
            imgUrl = imgUrl.replaceAll(fileServer, "");
            String objectName = UrlUtil.mapToUrl(imgUrl);
            double imgRateOfHw = AliyunOssUtil.getImgRateOfHw(objectName);
            Map<String, Object> urlMap = GsonUtil.toMap(imgUrl);
            urlMap.put("rateHw", imgRateOfHw);
            imgUrl = GsonUtil.toStr(urlMap);
            image.setImgUrl(imgUrl);
        }
        if (StringUtils.isNotEmpty(content)) {
            image.setContent(content);
        }
        if (seq != null) {
            image.setSeq(seq);
        }
        image.setUpdateTime(new Date());
        boolean update = image.updateById();
        if (!update) {
            return null;
        }
        return image;
    }

    @Override
    public void deleteFile(String imgUrl, String fileServer) {
        FileUtil.deleteFile(imgUrl);
        String ossName = imgUrl.replaceAll(fileServer, "");
        AliyunOssUtil.deleteObject(AliyunOssUtil.TINGDUODUO_BUCKET_NAME, ossName);
    }

}
