package com.frontend.media.service;

/**
 * 文件服务
 * @author xujiping
 * @date 2018/10/18 15:36
 */
public interface FileService {

    /**
     * 文件上传
     * @param path 文件目录（绝对路径）
     * @param imgName 文件名（包含后缀）
     * @param data 文件上传数据
     * @param ossPathName oss云服务器文件路径
     * @return boolean
     */
    boolean uploadFile(String path, String imgName, String data, String ossPathName);

}
