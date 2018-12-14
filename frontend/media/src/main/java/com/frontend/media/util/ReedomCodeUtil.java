package com.frontend.media.util;

import java.util.Random;
import java.util.UUID;

/**
 * 随机不重复邀请码
 *
 * @author xujiping
 * @date 2018/5/8 10:57
 */
public class ReedomCodeUtil {

    /**
     * 生成邀请码
     *
     * @param inputStr 输入字符串
     * @return 邀请码
     */
    public static String generate(String inputStr) {
        String result;
        try {
            String key = UUID.randomUUID().toString();
            result = hloveyrRc4(inputStr, key);
            result = strTo16(result);
        } catch (Exception e) {
            result = "";
        }
        return result.toUpperCase();
    }

    /**
     * RC4加密解密算法
     *
     * @param aInput 明文或密文
     * @param aKey   密钥
     * @return 如果是明文返回密文，反之亦然
     */
    private static String hloveyrRc4(String aInput, String aKey) {
        int a = 255;
        int[] iS = new int[a + 1];
        byte[] iK = new byte[a + 1];
        for (int i = 0; i < a + 1; i++) {
            iS[i] = i;
        }
        int j = 1;
        for (short i = 0; i < a + 1; i++) {
            iK[i] = (byte) aKey.charAt((i % aKey.length()));
        }
        j = 0;
        for (int i = 0; i < a; i++) {
            j = (j + iS[i] + iK[i]) % a + 1;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
        }
        int i = 0;
        j = 0;
        char[] iInputChar = aInput.toCharArray();
        char[] iOutputChar = new char[iInputChar.length];
        for (short x = 0; x < iInputChar.length; x++) {
            i = (i + 1) % a + 1;
            j = (j + iS[i]) % a + 1;
            int temp = iS[i];
            iS[i] = iS[j];
            iS[j] = temp;
            int t = (iS[i] + (iS[j] % a + 1)) % a + 1;
            int iY = iS[t];
            char iCY = (char) iY;
            iOutputChar[x] = (char) (iInputChar[x] ^ iCY);
        }

        return new String(iOutputChar);
    }

    /**
     * 字符串转16进制
     *
     * @param s 需要转换的字符串
     * @return 16进制
     */
    private static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str += s4;
        }
        return str;
    }

    /**
     * 打乱字符串排序，只能是11位的手机号
     *
     * @param str
     * @return
     */
    public static String radomStr(String str) {
        if (str.length() != 11){
            return str;
        }
        StringBuffer buffer = new StringBuffer();
        str.charAt(0);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(str.length());
            buffer.append(str.charAt(index));
            String a = str.substring(0, index);
            String b = str.substring(index + 1, str.length());
            str = a + b;
        }
        buffer.append(str);
        return buffer.toString();
    }

    public static void main(String[] args) {
//        String inputStr = "12546231589";
//        inputStr = inputStr.substring(inputStr.length()-4);
//        String key = UUID.randomUUID().toString();
//        String str = hloveyrRc4(inputStr, key);
//        System.out.println(generate(inputStr));
        for (int i = 0; i < 20; i++) {
            String s = radomStr("13452638972");
            System.out.println(s);
        }
//        Random random = new Random();
//        for (int i = 0; i < 5; i++) {
//            System.out.println(random.nextInt(24472));
//        }
    }
}
