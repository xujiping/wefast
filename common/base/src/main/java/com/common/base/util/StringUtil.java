package com.common.base.util;

import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author xujiping
 * @date 2018/10/16 16:25
 */
public class StringUtil {

    public static Pattern pattern = Pattern.compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)" +
            "_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");


    public static Pattern pattern2 = Pattern.compile("<:([[-]\\d*[,]]+):>");

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static Boolean isEmpty(Object str) {
        String nullStr = "null";
        if (str == null || StringUtils.isEmpty(String.valueOf(str).trim()) || nullStr.equalsIgnoreCase(String.valueOf
                (str).trim()
        )) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 首字母大写
     *
     * @param s
     * @return
     */
    public static String firstCharUpperCase(String s) {
        StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
        sb.append(s.substring(1, s.length()));
        return sb.toString();
    }

    /**
     * 是否为中文
     *
     * @param str
     * @return
     */
    public static Boolean ifCN(String str) {
        try {
            if (str.length() == str.getBytes().length) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转义字符到HTML
     *
     * @param str
     * @return
     */
    public static String esc2HTML(String str) {
        try {
            str = str.replaceAll(" ", "&nbsp;");
            str = str.replaceAll("\\r", "&nbsp;");
            str = str.replaceAll("\\n", "<br>");
            return str;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 数组转哈希集合对象
     *
     * @param obj
     * @return
     */
    public static Map<Integer, Object> array2HashMap(Object[] obj) {
        Integer i = null;
        Map<Integer, Object> reMap = null;
        try {
            //设置默认大小16
            reMap = Maps.newHashMapWithExpectedSize(16);
            for (i = 0; i < obj.length; i++) {
                reMap.put(i, obj[i]);
            }
            return reMap;
        } catch (Exception e) {
            return null;
        } finally {
            i = null;
            reMap = null;
        }
    }

    /**
     * 数组转树集合对象
     *
     * @param obj
     * @return
     */
    public static Map<Object, Object> array2TreeMap(Object[] obj) {
        Integer i = null;
        Map<Object, Object> reMap = null;
        try {
            reMap = new TreeMap<Object, Object>();
            for (i = 0; i < obj.length; i++) {
                reMap.put(obj[i], obj[i]);
            }
            return reMap;
        } catch (Exception e) {
            return null;
        } finally {
            i = null;
            reMap = null;
        }
    }

    /**
     * 缩写处理
     *
     * @param str
     * @param width
     * @param ellipsis
     * @return
     */
    public static String abbreviate(String str, int width, String ellipsis) {
        // byte length
        Integer d = 0;
        // char length
        Integer n = 0;
        try {
            if (str == null || "".equals(str)) {
                return "";
            }
            for (n = 0; n < str.length(); n++) {
                d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
                if (d > width) {
                    break;
                }
            }
            if (d > width) {
                n = n - ellipsis.length() / 2;
                return str.substring(0, n > 0 ? n : 0) + ellipsis;
            }
            return str = str.substring(0, n);
        } catch (Exception e) {
            return null;
        } finally {
            d = null;
            n = null;
        }
    }

    /**
     * HTML到文本
     *
     * @param str
     * @return
     */
    public static String html2Text(String str) {
        String sciprtReg = null;
        String styleReg = null;
        String tagDulReg = null;
        String tagSigReg = null;
        Pattern scriptPattern = null;
        Matcher scriptMatcher = null;
        Pattern stylePattern = null;
        Matcher styleMatcher = null;
        Pattern tagDulPattern = null;
        Matcher tagDulMatcher = null;
        Pattern tagSigPattern = null;
        Matcher tagSigMatcher = null;
        try {
            // 定义脚本的正则表达式
            sciprtReg = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
            // 定义style的正则表达式
            styleReg = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
            // 定义HTML含结束符标签的正则表达式
            tagDulReg = "<[^>]+>";
            // 定义HTML不含结束符标签的正则表达式
            tagSigReg = "<[^>]+";
            // 过滤script标签
            scriptPattern = Pattern.compile(sciprtReg, Pattern.CASE_INSENSITIVE);
            scriptMatcher = scriptPattern.matcher(str);
            str = scriptMatcher.replaceAll("");
            // 过滤style标签
            stylePattern = Pattern.compile(styleReg, Pattern.CASE_INSENSITIVE);
            styleMatcher = stylePattern.matcher(str);
            str = styleMatcher.replaceAll("");
            // 过滤HTML含结束符标签
            tagDulPattern = Pattern.compile(tagDulReg, Pattern.CASE_INSENSITIVE);
            tagDulMatcher = tagDulPattern.matcher(str);
            str = tagDulMatcher.replaceAll("");
            // 过滤HTML不含结束符标签
            tagSigPattern = Pattern.compile(tagSigReg, Pattern.CASE_INSENSITIVE);
            tagSigMatcher = tagSigPattern.matcher(str);
            str = tagSigMatcher.replaceAll("");
            // 过滤空格
            str = str.replace("&nbsp;", "");
            return str;
        } catch (Exception e) {
            return null;
        } finally {
            sciprtReg = null;
            styleReg = null;
            tagDulReg = null;
            tagSigReg = null;
            scriptPattern = null;
            scriptMatcher = null;
            stylePattern = null;
            styleMatcher = null;
            tagDulPattern = null;
            tagDulMatcher = null;
            tagSigPattern = null;
            tagSigMatcher = null;
        }
    }

    /**
     * 基本功能：过滤指定标签
     * <p>
     *
     * @param str
     * @param tag
     * @return String
     */
    public static String fiterHtmlTag(String str, String tag) {
        String regxp = null;
        StringBuffer sb = null;
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            regxp = "<\\s*" + tag + "\\s+([^>]*)\\s*>";
            pattern = Pattern.compile(regxp);
            matcher = pattern.matcher(str);
            sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, "");
            }
            matcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            return null;
        } finally {
            regxp = null;
            sb = null;
            pattern = null;
            matcher = null;
        }
    }

    /**
     * 把整形数字前置0生成固定长度的字符串，如果原字符串大于或等于固定长度则直接返回
     *
     * @param num
     * @param len
     * @return
     */
    public static String int2FixString(Integer num, int len) {
        String strnum = null;
        Integer zeronum = null;
        Integer i = null;
        StringBuilder restr = null;
        try {
            strnum = num.toString();
            restr.append(strnum);
            if (strnum.length() < len) {
                zeronum = len - strnum.length();
                for (i = 0; i < zeronum; i++) {
                    restr.insert(0, "0");
                }
            }
            return restr.toString();
        } catch (Exception e) {
            return null;
        } finally {
            strnum = null;
            zeronum = null;
            i = null;
            restr = null;
        }
    }

    /**
     * 把字符串分隔成数组
     *
     * @param inputString
     * @param splitFlag
     * @return stringList
     */
    public static List<String> string2List(String inputString, String splitFlag) {
        String[] stringArray = null;
        List<String> stringList = null;
        try {
            if (null != inputString && inputString.indexOf(splitFlag) > 0) {
                stringArray = inputString.split(splitFlag);
                stringList = new ArrayList<String>(0);
                for (String t : stringArray) {
                    stringList.add(t);
                }
                return stringList;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            stringArray = null;
            stringList = null;
        }
    }

    /**
     * 数组到字符串
     *
     * @param arr
     * @return
     */
    public static String arr2Str(List<Object> arr) {
        StringBuilder str = null;
        try {
            if (null != arr) {
                for (Object i : arr) {
                    if (null == str) {
                        str.append(i.toString());
                    } else {
                        str.append("," + i.toString());
                    }
                }
            }
            return str.toString();
        } catch (Exception e) {
            return null;
        } finally {
            str = null;
        }
    }

    /**
     * 获取文件名称
     *
     * @param content
     * @param fix
     * @return
     */
    public static String getFileNames(String content, String fix, String split) {
        String onePic = "";
        String picStr = "";
        String tempCont = content.trim();
        Integer sit = tempCont.indexOf(fix);
        try {
            while (sit != -1) {
                int tempSit = tempCont.lastIndexOf((int) '/', sit);
                onePic = tempCont.substring(tempSit + 1, sit);
                picStr = picStr + onePic + fix + split;
                tempCont = tempCont.substring(sit + 2, tempCont.length() - 1);
                sit = tempCont.indexOf(fix);
            }
            return picStr;
        } catch (Exception e) {
            return null;
        } finally {
            onePic = null;
            picStr = null;
            tempCont = null;
            sit = null;
        }
    }

    /**
     * 获取文件扩展名（包括点）
     *
     * @param fileName
     * @return
     * @author wyj
     */
    public static String getFileTypeIncludePoint(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

    /**
     * 获取文件扩展名（不包括点）
     *
     * @param fileName
     * @return
     * @author wyj
     */
    public static String getFileTypeExcludePoint(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean ifNumeric(String str) {
        String numberRegex = "\\d*";
        if (str.matches(numberRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是字母
     *
     * @param str
     * @return
     */
    public static boolean ifChart(String str) {
        String strRegex = "\\w*";
        if (str.matches(strRegex)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是字母
     *
     * @param c
     * @return
     */
    public static boolean ifLetter(char c) {
        return c / 0x80 == 0 ? true : false;
    }

    /**
     * 获取N位随机数
     *
     * @param length
     * @return
     */
    public static String getSeq(int length) {
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            //取三个随机数追加到StringBuffer
            buffer.append(random.nextInt(10));
        }
        return buffer.toString();
    }

    /**
     * 将字符串转换成ASCII码
     *
     * @param cnStr
     * @return String
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = null;
        try {
            strBuf = new StringBuffer();
            // 将字符串转换成字节序列
            byte[] bGBK = cnStr.getBytes();
            for (int i = 0; i < bGBK.length; i++) {
                // 将每个字符转换成ASCII码
                strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
            }
            return strBuf.toString();
        } catch (Exception e) {
            return null;
        } finally {
            strBuf = null;
        }
    }

    /**
     * 获取小数点位数
     *
     * @param num
     * @return
     */
    public static int getScale(String num) {
        int pos = num.lastIndexOf(".");
        return num.substring(pos + 1).length();
    }

    /**
     * Double转成字符串
     *
     * @param num
     * @return
     */
    public static String double2String(Double num) {
        return null != num ? String.valueOf(num) : "";
    }

    /**
     * Integer转成字符串
     *
     * @param num
     * @return
     */
    public static String integer2String(Integer num) {
        return null != num ? String.valueOf(num) : "";
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将str中的emoji表情转为byte数组
     *
     * @param str
     * @return
     */
    public static String resolveToByteFromEmoji(String str) {
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb2, resolveToByte(matcher.group(0)));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }

    /**
     * 将str中的byte数组类型的emoji表情转为正常显示的emoji表情
     *
     * @param str
     * @return
     */
    public static String resolveToEmojiFromByte(String str) {
        Matcher matcher2 = pattern2.matcher(str);
        StringBuffer sb3 = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb3, resolveToEmoji(matcher2.group(0)));
        }
        matcher2.appendTail(sb3);
        return sb3.toString();
    }

    private static String resolveToByte(String str) {
        byte[] b = str.getBytes();
        StringBuffer sb = new StringBuffer();
        sb.append("<:");
        for (int i = 0; i < b.length; i++) {
            if (i < b.length - 1) {
                sb.append(Byte.valueOf(b[i]).toString() + ",");
            } else {
                sb.append(Byte.valueOf(b[i]).toString());
            }
        }
        sb.append(":>");
        return sb.toString();
    }

    private static String resolveToEmoji(String str) {
        str = str.replaceAll("<:", "").replaceAll(":>", "");
        String[] s = str.split(",");
        byte[] b = new byte[s.length];
        for (int i = 0; i < s.length; i++) {
            b[i] = Byte.valueOf(s[i]);
        }
        return new String(b);
    }

    public static List<String> getVariables(String parent, String regex) {
        List<String> list = new ArrayList<>();
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(parent);
        while (matcher.find()) {
            System.out.println("匹配项：" + matcher.group());
            list.add(matcher.group());
        }
        return list;
    }

    /**
     * 比较两数大小
     *
     * @param a
     * @param b
     * @return
     */
    public static int getLarger(int a, int b) {
        if (a >= b) {
            return a;
        }
        return b;
    }

    /**
     * 格式化手机号
     *
     * @param phone
     * @return 186****1111
     */
    public static String formatPhone(String phone) {
        if (phone.contains("wx")) {
            return phone;
        }
        String substring = phone.substring(3, 7);
        return phone.replaceAll(substring, "****");
    }

    /**
     * 数字列表中获取多个数字
     *
     * @param list
     * @param count
     * @return
     */
    public static List<Integer> getRandomNum(List<Integer> list, int count) {
        List<Integer> results = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int index = random.nextInt(list.size());
            results.add(list.get(index));
            list.remove(index);
        }
        return results;
    }

    /**
     * 统计数组中数字的个数
     * @param numArray
     * @return
     */
    public static Map<Integer, Integer> countNum(int[] numArray) {
        int size = numArray.length;
        Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(size);
        for (int i = 0; i < size; i++) {
            int a = numArray[i];
            if (!map.containsKey(a)) {
                map.put(a, 1);
            } else {
                int count = map.get(a);
                map.put(a, ++count);
            }
        }
        return map;
    }

    public static String splitString(String str, String temp) {
        String result = null;
        if (str.contains(temp)) {
            if (str.substring(str.indexOf(temp)).contains("&")) {
                result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=") + 1,
                        str.substring(str.indexOf(temp)).indexOf("&"));
            } else {
                result = str.substring(str.indexOf(temp)).substring(str.substring(str.indexOf(temp)).indexOf("=") + 1);

            }
        }
        return result;
    }
}
