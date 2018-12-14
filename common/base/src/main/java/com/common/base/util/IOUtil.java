package com.common.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xujiping
 * @date 2018/11/22 15:02
 */
public class IOUtil {

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException var2) {
                ;
            }
        }

    }
}
