package com.frontend.media.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * url工具类
 *
 * @author xujiping
 * @date 2018/5/12 10:07
 */
public class UrlUtil {

    /**
     * 拼装url
     * 转成/201705/15522210381/15522210381.png
     *
     * @param mapUrl {"name":"15522210381","type":"png","url":"/201705/15522210381/"}
     * @return
     */
    public static String mapToUrl(String mapUrl) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isEmpty(mapUrl)) {
            return sb.toString();
        }
        Map<String, Object> map = GsonUtil.toMap(mapUrl);
        if (map == null || map.size() <= 0) {
            return sb.toString();
        }
        //相对路径
        if (map.containsKey("url")) {
            String url = (String) map.get("url");
            sb.append(url.replace("\\", "/"));
        }
        if (map.containsKey("path")) {
            String path = (String) map.get("path");
            sb.append(path.replace("\\", "/"));
        }
        //文件名称
        if (map.containsKey("name")) {
            String name = (String) map.get("name");
            sb.append(name);
        }
        //后缀
        if (map.containsKey("type")) {
            String type = (String) map.get("type");
            sb.append(".").append(type);
        }
        if (map.containsKey("suffix")) {
            String suffix = (String) map.get("suffix");
            sb.append(".").append(suffix);
        }
        return sb.toString();
    }

    /**
     * url转成map字符串
     *
     * @return
     */
    public static String toMapStr(String str) {
        String[] split = str.split("/");
        int length = split.length;
        String fileName = split[length - 1];
        String[] split1 = fileName.split("\\.");
        String name = split1[0];
        String type = split1[1];
        String url = str.replaceAll(fileName, "");
        Map<String, String> map = Maps.newHashMapWithExpectedSize(3);
        map.put("url", url);
        map.put("name", name);
        map.put("type", type);
        return GsonUtil.toStr(map);
    }

}
