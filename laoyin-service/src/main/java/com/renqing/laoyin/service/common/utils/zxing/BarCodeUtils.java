package com.bat.laoyin.service.common.utils.zxing;

import static com.bat.laoyin.service.common.utils.file.FileUtils.mkdirs;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * @author Lim
 * @date 2021/9/30 11:16
 */
public class BarCodeUtils {

    private static final Logger log = LoggerFactory.getLogger(BarCodeUtils.class);
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // LOGO宽度
    private static final int WIDTH = 220;
    // LOGO高度
    private static final int HEIGHT = 50;

    /**
     * 条形码编码
     *
     * @param contents
     * @param destPath
     */
    public static void encode(String contents, String destPath) {
        encode(contents, WIDTH, HEIGHT, destPath);
    }

    /**
     * 条形码编码
     *
     * @param contents
     * @param destPath
     */
    public static void encode(String contents, int width, int height, String destPath) {
        int codeWidth = 3 + // start guard
            (7 * 6) + // left bars
            + // middle guard
            (7 * 6) + // right bars
            3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix =
                new MultiFormatWriter().encode(contents, BarcodeFormat.CODE_128, codeWidth, height, null);
            mkdirs(destPath);
            MatrixToImageWriter.writeToPath(bitMatrix, FORMAT_NAME, new File(destPath).toPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 条形码解码
     *
     * @param destPath
     * @return String
     */
    public static String decode(String destPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(destPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String imgPath = "F:/zxing_EAN-13.png";
        String contents = "6926557300360";
        encode(contents, imgPath);
        System.out.println("finished zxing EAN-13 encode.");
        String decodeContent = decode(imgPath);
        System.out.println("解码内容如下：" + decodeContent);
        System.out.println("finished zxing EAN-13 decode.");
    }
}
