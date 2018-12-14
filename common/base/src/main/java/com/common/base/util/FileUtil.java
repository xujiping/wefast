package com.common.base.util;

import com.common.base.constant.ReturnCode;
import com.common.base.exception.BusinessException;

import java.io.*;

/**
 * 文件工具类
 *
 * @author xujiping
 * @date 2018/6/20 11:59
 */
public class FileUtil {

    /**
     * 文件上传
     *
     * @param file
     * @param fileName
     * @param filePath
     * @return
     * @throws Exception
     */
    public static File uploadFile(byte[] file, String fileName, String filePath) {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File newFile = new File(filePath + fileName);
        if (newFile.exists()) {
            newFile.delete();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(file);
            out.flush();
        } catch (IOException e) {
            newFile = null;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                newFile = null;
            }
        }
        return newFile;
    }

    /**
     * 获取文件后缀
     *
     * @param file 文件
     * @return 文件后缀
     */
    public static String getSuffix(File file) {
        if (file.isFile()) {
            String fileName = file.getName();
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }

    /**
     * 获取不带后缀名的文件名
     * @param file 文件
     * @return
     */
    public static String getFileNameWithoutSuffix(File file){
        String file_name = file.getName();
        return file_name.substring(0, file_name.lastIndexOf("."));
    }

    /**
     * 删除文件夹和文件
     */
    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()){
            if (!file.isFile() && file.list() != null){
                File[] files = file.listFiles();
                for (File a:files) {
                    a.delete();
                }
            }
            file.delete();
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
