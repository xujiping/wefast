package com.frontend.media.service;

import com.frontend.media.controller.dto.ImageDto;
import com.frontend.media.controller.dto.ImageTab;
import com.frontend.media.entity.Atlas;
import com.frontend.media.entity.Image;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 图片表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-10-17
 */
public interface ImageService extends IService<Image> {

    /**
     * 获取封面列表
     * @return
     */
    List<Image> getCoverList(Atlas atlas);

    /**
     * 查询
     * @param atlasId
     * @param url
     * @return
     */
    Image getOne(long atlasId, String url);

    /**
     * 查询图集图片列表
     * @return
     */
    List<Image> getByAtlas(long atlasId);

    /**
     * 删除图集图片
     * @param atlasId
     */
    boolean deleteByAtlas(long atlasId, String fileServer);

    ImageDto wrapper(Image image);

    ImageTab wrapperTab(Image image, Atlas atlas, String fileServer);


    /**
     * 新增
     * @param atlasId
     * @param imgUrl
     * @param content
     * @param seq
     * @return
     */
    Image add(long atlasId, String imgUrl, String content, Integer seq, String fileServer);

    /**
     * 更新
     * @param id
     * @param imgUrl
     * @param content
     * @param seq
     * @return
     */
    Image update(long id, String imgUrl, String content, Integer seq, String fileServer);

    /**
     * 删除文件
     * @param imgUrl 图片文件的url
     * @param fileServer 文件服务器路径
     */
    void deleteFile(String imgUrl, String fileServer);
}
