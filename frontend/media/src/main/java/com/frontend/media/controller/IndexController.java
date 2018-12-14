package com.frontend.media.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xujiping
 * @date 2018/7/13 17:22
 */
@Controller
public class IndexController {

    @GetMapping("")
    public String index() {
        return "login";
    }

    @GetMapping("/open/agreement")
    public String userAgreement() {
        return "userAgreement";
    }

    @GetMapping("/open/operation")
    public String operation() {
        return "operation";
    }

    @GetMapping("/open/dispatch")
    public String dispatch() {
        return "dispatch";
    }

    @GetMapping("/open/complaint")
    public String complaint() {
        return "complaint";
    }

    @GetMapping("/open/punish")
    public String punish() {
        return "pubnish";
    }

    @GetMapping("/open/state")
    public String state() {
        return "state";
    }

    @GetMapping("/open/declaration")
    public String declaration() {
        return "declaration";
    }

    @GetMapping("/open/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/open/intellectual")
    public String intellectual() {
        return "intellectual_agreement";
    }

    /**
     * 读取服务器文件
     *
     * @param filename 文件完整路径，如：/data/0/hyzy/201808/13254891350/test.png
     * @param response response
     */
    @GetMapping("/file")
    @ResponseBody
    public void getFile(String filename, HttpServletResponse response) {
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(filename);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
