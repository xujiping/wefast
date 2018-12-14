package com.frontend.media.util;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.frontend.media.common.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 阿里云OSS工具类
 *
 * @author xujiping
 * @date 2018/10/24 15:44
 */
public class AliyunOssUtil {

    /**
     * 华北2
     */
    private static final String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";

    /**
     * 阿里云用户AccessKey
     */
    private static final String ACCESS_KEY_ID = "LTAIhZrmUYNQwk3K";
    private static final String ACCESS_KEY_SECRET = "RHWmbbw29hD3ubBpFez93PhMTN7kTz";

    public static final String TINGDUODUO_BUCKET_NAME = "rb-tdd";

    /**
     * 创建存储空间
     *
     * @param bucketName 名称
     * @return
     */
    public static boolean createBucket(String bucketName) {
        if (StringUtils.isEmpty(bucketName)) {
            return false;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        Bucket bucket = ossClient.createBucket(bucketName);
        ossClient.shutdown();
        System.out.println("bucket=" + bucket);
        return true;
    }

    /**
     * 上传文件
     *
     * @param bucketName  存储空间
     * @param objectName  对象名
     * @param inputStream 文件流
     * @return
     */
    public static boolean putInputStream(String bucketName, String objectName, InputStream inputStream) {
        if (StringUtils.isEmpty(objectName)) {
            return false;
        }
        final char beginStr = objectName.charAt(0);
        if (Constants.OSS_NOT_START == beginStr) {
            objectName = objectName.substring(1, objectName.length());
        }
        if (StringUtils.isEmpty(bucketName)){
            bucketName = TINGDUODUO_BUCKET_NAME;
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        ossClient.putObject(bucketName, objectName, inputStream);
        ossClient.shutdown();
        return true;
    }

    /**
     * 获取对象
     *
     * @param bucketName 存储空间
     * @param objectName 对象名
     */
    public static void getObject(String bucketName, String objectName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println("\n" + line);
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 列举文件
     *
     * @param bucketName 存储空间
     * @return
     */
    public static String listObjects(String bucketName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // ossClient.listObjects返回ObjectListing实例，包含此次listObject请求的返回结果。
        ObjectListing objectListing = ossClient.listObjects(bucketName);
        // objectListing.getObjectSummaries获取所有文件的描述信息。
        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println(" - " + objectSummary.getKey() + "  " +
                    "(size = " + objectSummary.getSize() + ")");
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return null;
    }

    /**
     * 删除文件
     *
     * @param bucketName 存储空间
     * @param objectName 对象名
     */
    public static void deleteObject(String bucketName, String objectName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 删除文件。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 删除多个文件
     * @param keys
     * @return
     */
    public static List<String> deleteObjects(List<String> keys){
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 删除文件。
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(TINGDUODUO_BUCKET_NAME).withKeys
                (keys));
        // 返回删除成功的文件列表
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭OSSClient。
        ossClient.shutdown();
        return deletedObjects;
    }

  /**
     * 获取签名的临时URL，期限30分钟
     * @param bucketName
     * @param objectName
     * @return
     */
    public static String getSignUrl(String bucketName, String objectName) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 设置url过期时间30分钟
        Date expiration = new Date(System.currentTimeMillis() + 1800 * 1000);
        // 生成已GET方法访问的签名URL
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }

    /**
     * 获取签名的临时图片URL，期限30分钟
     *
     * @param objectName 图片路径
     * @return String
     */
    public static String getImgInfo(String objectName) {
        final char beginStr = objectName.charAt(0);
        if (Constants.OSS_NOT_START == beginStr) {
            objectName = objectName.substring(1, objectName.length());
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 设置图片处理样式。
        String style = "image/info";
        // 设置url过期时间30分钟
        Date expiration = new Date(System.currentTimeMillis() + 1800 * 1000);
        GetObjectRequest req = new GetObjectRequest(TINGDUODUO_BUCKET_NAME, objectName);
        req.setProcess(style);
        // 生成已GET方法访问的签名URL
        OSSObject ossObject = ossClient.getObject(req);
        InputStream objectContent = ossObject.getObjectContent();
        String imgInfo = FileUtil.read(objectContent);
        // 关闭OSSClient。
        ossClient.shutdown();
        return imgInfo;
    }

    /**
     * 获取图片高/宽的比率
     * @param objectName
     * @return
     */
    public static double getImgRateOfHw(String objectName) {
        String imgInfo = getImgInfo(objectName);
        JSONObject jsonObject = JSONObject.parseObject(imgInfo);
        JSONObject imageHeight = jsonObject.getJSONObject("ImageHeight");
        double height = imageHeight.getDouble("value");
        JSONObject imageWidth = jsonObject.getJSONObject("ImageWidth");
        double width = imageWidth.getDouble("value");
        return height / width;
    }

    /**
     * 获取签名的临时图片URL，期限30分钟
     *
     * @param objectName 图片路径
     * @param width      宽
     * @param height     高
     * @return String
     */
    public static String getImgUrl(String objectName, int width, int height) {
        final char beginStr = objectName.charAt(0);
        if (Constants.OSS_NOT_START == beginStr) {
            objectName = objectName.substring(1, objectName.length());
        }
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // 设置图片处理样式。
        String style = "image/resize,m_fixed,w_" + width + ",h_" + height;
        // 设置url过期时间30分钟
        Date expiration = new Date(System.currentTimeMillis() + 1800 * 1000);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(TINGDUODUO_BUCKET_NAME, objectName,
                HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        // 生成已GET方法访问的签名URL
        URL url = ossClient.generatePresignedUrl(req);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }

    public static void main(String[] args) {
        listObjects(TINGDUODUO_BUCKET_NAME);
    }

}
