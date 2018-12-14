package com.frontend.media.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FFmpeg工具类
 * 对视频、音频文件做处理
 *
 * @author xujiping
 * @date 2018/11/6 16:04
 */
public class FfmpegUtil {

    /**
     * 视频截图
     *
     * @param ffmpegPath  ffmpeg工具绝对路径
     * @param videoPath   目标文件绝对路径
     * @param coverSecond 获取第几秒的截图
     * @return 文件路径
     */
    public static boolean coverVideoImg(String ffmpegPath, String videoPath, String coverSecond, String
            outputFilePath) {
        // 必须用list，不然报错找不到文件
        List<String> commands = new ArrayList<>();
        commands.add(ffmpegPath);
        //偏移量
        commands.add("-ss");
        //这个参数是设置截取视频多少秒时的画面
        commands.add(coverSecond);
        // 输入
        commands.add("-i");
        commands.add(videoPath);
        //格式化，要输出什么格式的截图
        commands.add("-f");
        commands.add("mjpeg");
        commands.add("-vframes");
        //截取帧数
        commands.add("1");
        commands.add(outputFilePath);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process process = builder.start();
            InputStream input = process.getInputStream();
            int data;
            //取走子进程输出流信息
            while ((data = input.read()) != -1) {
                System.out.println(data);
            }
            //取走子进程错误流信息
            InputStream errorStream = process.getErrorStream();
            int data2;
            while ((data2 = errorStream.read()) != -1) {
                System.out.println(data2);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取视频文件时长
     *
     * @param ffmpegPath ffmpeg路径
     * @param videoPath  视频路径
     * @return 秒
     */
    public static int getVideoTime(String ffmpegPath, String videoPath) {
        List<String> commands = new java.util.ArrayList<>();
        commands.add(ffmpegPath);
        commands.add("-i");
        commands.add(videoPath);
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(commands);
            final Process process = pb.start();
            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(sb.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                System.out.println(videoPath + ",视频时长：" + time + ", 开始时间：" + m.group(2) + ",比特率：" + m.group(3) +
                        "kb/s");
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 格式:"00:00:10.68"
     *
     * @param timelen 时间字符串
     * @return 秒
     */
    private static int getTimelen(String timelen) {
        int min = 0;
        int one = 1;
        int two = 2;
        String zeroStr = "0";
        String[] split = timelen.split(":");
        if (split[min].compareTo(zeroStr) > min) {
            //秒
            min += Integer.valueOf(split[min]) * 60 * 60;
        }
        if (split[one].compareTo(zeroStr) > min) {
            min += Integer.valueOf(split[one]) * 60;
        }
        if (split[two].compareTo(zeroStr) > min) {
            min += Math.round(Float.valueOf(split[two]));
        }
        return min;
    }

}
