package com.bat.laoyin.service.common.handle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import com.bat.laoyin.service.common.handle.dto.BusinessInfoReq;
import com.bat.laoyin.service.common.handle.dto.ImageFileResp;
import com.bat.laoyin.service.common.handle.dto.ImageFileRespItem;
import com.bat.laoyin.service.common.properties.FontPathProperties;
import com.bat.laoyin.service.common.utils.file.FileUtils;
import com.bat.laoyin.service.common.utils.file.ImageUtils;
import com.bat.laoyin.service.common.utils.file.UnitUtils;
import com.bat.laoyin.service.common.utils.zxing.QRCodeUtil;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: 玻璃壳 处理器
 * @date: 2021/10/15 20:42
 *
 *        pdf 原点在左下角 Graphics2D 原点在左上角
 */
@Component("UV132PdfProcessor")
@Slf4j
public class UV132PdfProcessor extends AbstractPdfProcessor {

    @Resource
    private FontPathProperties fontPath;

    @Override
    public void makeup(List<BusinessInfoReq> infos) {
        log.info("开始生成拼版文件");
        long start = System.currentTimeMillis();
        try {
            // 注意 一个返回参数 一个形式参数
            ImageFileResp imageFileResp = calculationCanvas(infos);
            BufferedImage bufferedImage = imageFileResp.getMainBufferedImage();
            ByteArrayOutputStream outputStream = drawAndReturnOutputStream(imageFileResp);
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage(UnitUtils.mm2PDRectangle(2500F, 1300F));
                doc.addPage(page);
                PDImageXObject xObject = PDImageXObject.createFromByteArray(doc, outputStream.toByteArray(), "");
                try (PDPageContentStream contents = new PDPageContentStream(doc, page);) {
                    // 解释一下 为什么要写两遍 为了生成的文件 导入ps时 能出现智能对象提示，能切换边框 与 作品框。
                    // 而且 体积 与 代码耗时 均没有增加
                    contents.drawImage(xObject, UnitUtils.mm2PDFUnit(38F), UnitUtils.mm2PDFUnit(50F),
                        UnitUtils.px2PDFUnit(bufferedImage.getWidth()),
                        UnitUtils.px2PDFUnit(bufferedImage.getHeight()));
                    contents.drawImage(xObject, UnitUtils.mm2PDFUnit(38F), UnitUtils.mm2PDFUnit(50F),
                        UnitUtils.px2PDFUnit(bufferedImage.getWidth()),
                        UnitUtils.px2PDFUnit(bufferedImage.getHeight()));
                }
                doc.save("./test-tpu.pdf");
                long end = System.currentTimeMillis();
                log.info("文件生成成功 耗时：{}", (end - start));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 绘制 并返回 输出流
     * 
     * @param imageFileResp
     * @return
     * @throws IOException
     */
    private ByteArrayOutputStream drawAndReturnOutputStream(ImageFileResp imageFileResp) throws IOException {
        BufferedImage bufferedImage = imageFileResp.getMainBufferedImage();
        List<ImageFileRespItem> imageFileResps = imageFileResp.getItems();
        Graphics2D graphics = bufferedImage.createGraphics();
        for (ImageFileRespItem item : imageFileResps) {
            handleProcess(item);
            int newX = (int)item.getX();
            int newY = (int)item.getY() + (int)(UnitUtils.mm2px300(115F) - item.getBufferedImage().getHeight());
            graphics.drawImage(item.getBufferedImage(), newX, newY, item.getBufferedImage().getWidth(),
                item.getBufferedImage().getHeight(), null);
        }
        graphics.dispose();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, UnitUtils.PNG_FORMAT_NAME, outputStream);
        return outputStream;
    }

    /**
     * 计算画布 等信息
     * 
     * @return
     */
    private ImageFileResp calculationCanvas(List<BusinessInfoReq> infos) throws IOException {
        int[] config = {12, 11};
        // 图片画布大小
        float width = 0;
        float height = 0;
        int index = 0;
        int newX = 0;
        int newY = 0;
        List<ImageFileRespItem> imageFileResps = new ArrayList<>();
        tab:
        for (int y = 0; y < config[1]; y++) {
            for (int x = 0; x < config[0]; x++) {
                newX = Math.max(x, newX);
                newY = y;
                BusinessInfoReq req = infos.get(index++);
                PDDocument document = Loader.loadPDF(new File(req.getPdfUrl()));
                PDFRenderer renderer = new PDFRenderer(document);
                BufferedImage bufferedImage = renderer.renderImageWithDPI(0, UnitUtils.DPI300, ImageType.ARGB);
                document.close();
                ImageFileRespItem imageFileResp = new ImageFileRespItem();
                // 图像 相对于 左上交的 坐标位置
                imageFileResp.setX(x * UnitUtils.mm2px300(200F));
                // 这一块 算的 很复杂 主要是Graphics2D 原点在左上角，进行的坐标转换。
                int maxCount = Math.min(infos.size(), (config[0] * config[1]));
                int k = maxCount / config[0];
                int j = maxCount % config[0];
                k = j != 0 ? (k + 1) : k;
                imageFileResp.setY(0 * UnitUtils.mm2px300(115F));
                imageFileResp.setY((k - y - 1) * UnitUtils.mm2px300(115F));
                imageFileResp.setBufferedImage(bufferedImage);
                imageFileResp.setInfo(req);
                imageFileResps.add(imageFileResp);
                width = (newX + 1) * UnitUtils.mm2px300(200F);
                height = (newY + 1) * UnitUtils.mm2px300(115F);
                if (index >= infos.size()) {
                    break tab;
                }
            }
        }
        ImageFileResp imageFileResp = new ImageFileResp();
        imageFileResp.setMainBufferedImage(new BufferedImage((int)width, (int)height, BufferedImage.TYPE_INT_ARGB));
        imageFileResp.setItems(imageFileResps);
        return imageFileResp;
    }

    /**
     * 
     *
     *
     * 图片加工 逻辑 二维码 订单号 等
     * 
     * @param imageFileResp
     */
    @SneakyThrows
    private void handleProcess(ImageFileRespItem imageFileResp) {
        BufferedImage originalBufferedImage = imageFileResp.getBufferedImage();
        BusinessInfoReq info = imageFileResp.getInfo();
        int customerHeight = (int)(UnitUtils.mm2px300(12F));
        int qrLength = (int)(UnitUtils.mm2px300(10F));
        int qrLeft = (int)(UnitUtils.mm2px300(20F));
        int textBackPaddingLeft = (int)(UnitUtils.mm2px300(35F));
        // 创建扩展后的画布
        BufferedImage extendBufferedImage = new BufferedImage(originalBufferedImage.getWidth(),
            originalBufferedImage.getHeight() + customerHeight, BufferedImage.TYPE_INT_ARGB);
        // 创建画笔
        Graphics2D graphics = extendBufferedImage.createGraphics();
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除图片锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 把原图片 放入新画布
        graphics.drawImage(originalBufferedImage, 0, customerHeight, originalBufferedImage.getWidth(),
            originalBufferedImage.getHeight(), null);
        // 生成二维码 文件字节数组输出字节流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        QRCodeUtil.encode(info.getQrStr(), outputStream);
        // 输出流转输入流
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        // 把 二维码 绘制指定位置
        graphics.drawImage(ImageIO.read(inputStream), qrLeft, 0, qrLength, qrLength, null);
        Font deriveFont = Font.createFont(Font.TRUETYPE_FONT, fontPath.getPdfBoxFontFile()).deriveFont((float)30);
        // 把文字白色背景 漏出来
        graphics.setBackground(Color.WHITE);
        String orderStr = "三方单号" + info.getOrderStr();
        String specStr = info.getSpecStr();
        graphics.clearRect(textBackPaddingLeft, 0, FileUtils.getMaxWidth(deriveFont, orderStr, specStr), qrLength);
        // 绘制文字
        graphics.setPaint(Color.BLACK);
        graphics.setFont(deriveFont);
        // 画笔粗细
        graphics.setStroke(new BasicStroke(1f));
        graphics.drawString(orderStr, textBackPaddingLeft, 30);
        graphics.drawString(specStr, textBackPaddingLeft, 60);
        graphics.dispose();
        imageFileResp.setBufferedImage(ImageUtils.Rotate(extendBufferedImage, 270));
    }

}
