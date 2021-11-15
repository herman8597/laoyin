package com.bat.laoyin.service.common.utils.file;

import java.awt.*;
import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.font.FontDesignMetrics;

/**
 * @author Lim
 * @date 2021/9/30 13:50
 */
public class FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 删除单个文件
     *
     * @param fileName
     *            要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        return deleteFile(new File(fileName));
    }

    public static boolean deleteFile(File file) {
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除单个文件" + file.getName() + "成功！");
                return true;
            } else {
                log.info("删除单个文件" + file.getName() + "失败！");
                return false;
            }
        } else {
            log.info("删除单个文件失败：" + file.getName() + "不存在！");
            return false;
        }
    }

    /**
     * 强制删除
     * 
     * @param fileName
     * @return
     */
    public static boolean forceDelete(String fileName) {
        return forceDelete(new File(fileName));
    }

    public static boolean forceDelete(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        boolean result = false;
        int tryCount = 0;
        while (!result && tryCount++ < 10) {
            log.info("try to delete file " + file.getName() + " cnt:" + tryCount);
            System.gc();
            result = file.delete();
        }
        if (result) {
            log.info("强制删除单个文件：" + file.getName() + "成功！");
        } else {
            log.info("强制删除单个文件：" + file.getName() + " 达到最大删除次数（10），放弃删除！");
        }
        return result;
    }

    /**
     * 创建文件夹
     *
     * @param destPath
     * @return
     */
    public static File mkdirs(String destPath) {
        log.info("mkdis destPath:{}", destPath);
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists()) {
            int i = destPath.lastIndexOf("\\");
            String substring = destPath.substring(i);
            // H20210927202441401-1.html 有文件后缀的也会变成文件夹
            if (substring.contains(".")) {
                mkdirs(destPath.substring(0, i));
            } else {
                file.mkdirs();
            }
        }
        return file;
    }

    public static int getMaxWidth(Font font, String... texts) {
        int maxLength = 0;
        for (String text : texts) {
            int wordWidth = getWordWidth(font, text);
            if (wordWidth > maxLength) {
                maxLength = wordWidth;
            }
        }
        return maxLength;
    }

    private static int getWordWidth(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return width;
    }

    public static String getFontStr(List<String> paths) {
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                return path;
            }
        }
        return null;
    }

    public static File getFontFile(List<String> paths) {
        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    /**
     * 处理路径中的各种 斜杠
     *
     * @param url
     * @return
     */
    public static String processingSlashes(String url) {
        return url.replace("\\", "/").replace("//", "/").replace(":/", "://");
    }

}
