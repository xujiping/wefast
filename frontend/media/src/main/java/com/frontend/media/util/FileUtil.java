package com.frontend.media.util;

import com.alibaba.fastjson.JSONObject;
import com.common.base.constant.Constants;
import com.common.base.util.DateTimeUtil;
import com.common.base.util.IOUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;

/**
 * 文件工具类
 *
 * @author xujiping
 * @date 2018/7/24 16:34
 */
public class FileUtil {

    /**
     * * 文件路径转换成json格式字符串
     *
     * @param filePath 文件路径
     * @param fileSize 文件大小
     * @param fileTime 文件时长
     * @return {"size":1984927,"name":"1527331073423-3392","time":245.0,"type":"mp3","url":"/201805/13540011120/"}
     */
    public static JSONObject pathToJson(String filePath, Long fileSize, Double fileTime, String oldName) {
        JSONObject jsonObject = new JSONObject();
        String fileName = "";
        String url = "";
        String name = "";
        String type = "";
        String separator = "/";
        if (separator.equals(Constants.PATH_SEPARATOR)) {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            url = filePath.substring(0, filePath.lastIndexOf("/"));
        }
        String[] split = fileName.split("\\.");
        if (split.length >= 2) {
            name = split[0];
            type = split[1];
        }
        if (StringUtils.isNotEmpty(url) && !separator.equals(url.substring(0, 1))){
            url = separator + url;
        }
        if (StringUtils.isNotEmpty(url) && !separator.equals(url.substring(url.length()-1))){
            url += separator;
        }
        jsonObject.put("url", url);
        jsonObject.put("name", name);
        jsonObject.put("type", type);
        jsonObject.put("size", fileSize);
        jsonObject.put("time", fileTime);
        jsonObject.put("oldName", oldName);
        return jsonObject;
    }

    /**
     * 获取音频文件相对路劲，如；201805/13201539586/
     *
     * @param releaseName
     * @return
     */
    public static String getContentPath(Date date, String releaseName) {
        StringBuffer sb = new StringBuffer();
        sb.append(DateTimeUtil.getYearMonth(date));
        sb.append(Constants.PATH_SEPARATOR);
        sb.append(releaseName);
        sb.append(Constants.PATH_SEPARATOR);
        return sb.toString();
    }

    /**
     * 生成文件名
     * @return
     */
    public static String generateContentFileName(String fileName){
        String substring = fileName.substring(fileName.indexOf("."));
        Random random = new Random();
        int nextInt = random.nextInt(9999);
        return System.currentTimeMillis() + "-" + nextInt + substring;
    }

    /**
     * 删除文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            if (file.isFile()){
                file.delete();
            }
        }
    }

    public static String read(InputStream inputStream) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        String str;
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            str = result.toString("UTF-8");
        } catch (Exception e) {
            str = "";
        } finally {
            IOUtil.safeClose(result);
            IOUtil.safeClose(inputStream);
        }
        return str;
    }

}