package com.bat.laoyin.service.common.handle;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

import com.bat.laoyin.service.common.handle.dto.BusinessInfoReq;
import com.bat.laoyin.service.common.handle.dto.ImageFileResp;
import com.bat.laoyin.service.common.utils.file.UnitUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: 热升华8膜 处理器
 * @date: 2021/10/19 13:51
 */
@Component("DyeSublimation8PdfProcessor")
@Slf4j
public class DyeSublimation8PdfProcessor extends AbstractPdfProcessor {
    @Override
    public void makeup(List<BusinessInfoReq> infos) {
        log.info("开始生成拼版文件");
        long start = System.currentTimeMillis();
        try {
            // 注意 一个返回参数 一个形式参数
            ImageFileResp imageFileResp = calculationCanvas(infos);
            // BufferedImage bufferedImage = imageFileResp.getMainBufferedImage();
            ByteArrayOutputStream outputStream = drawAndReturnOutputStream(imageFileResp);
            try (PDDocument doc = new PDDocument()) {
                PDPage page = new PDPage(UnitUtils.mm2PDRectangle(610F, 420F));
                doc.addPage(page);
                PDImageXObject xObject = PDImageXObject.createFromByteArray(doc, outputStream.toByteArray(), "");
                try (PDPageContentStream contents = new PDPageContentStream(doc, page);) {
                    // 解释一下 为什么要写两遍 为了生成的文件 导入ps时 能出现智能对象提示，能切换边框 与 作品框。
                    // 而且 体积 与 代码耗时 均没有增加
                    contents.drawImage(xObject, 0, 0, UnitUtils.mm2PDFUnit(610), UnitUtils.mm2PDFUnit(420));
                    contents.drawImage(xObject, 0, 0, UnitUtils.mm2PDFUnit(610), UnitUtils.mm2PDFUnit(420));
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
     * 计算画布 等信息
     *
     * @return
     */
    private ImageFileResp calculationCanvas(List<BusinessInfoReq> infos) throws IOException {
        int[] config = {4, 2};
        // // 图片画布大小
        // float width = 0;
        // float height = 0;
        // int index = 0;
        // int newX = 0;
        // int newY = 0;
        // List<ImageFileRespItem> imageFileResps = new ArrayList<>();
        // tab:
        // for (int y = 0; y < config[1]; y++) {
        // for (int x = 0; x < config[0]; x++) {
        // newX = Math.max(x, newX);
        // newY = y;
        // BusinessInfoReq req = infos.get(index++);
        // PDDocument document = Loader.loadPDF(new File(req.getPdfUrl()));
        // PDFRenderer renderer = new PDFRenderer(document);
        // BufferedImage bufferedImage = renderer.renderImageWithDPI(0, UnitUtils.DPI300, ImageType.ARGB);
        // document.close();
        // ImageFileRespItem imageFileResp = new ImageFileRespItem();
        // // 图像 相对于 左上交的 坐标位置
        // imageFileResp.setX(x * UnitUtils.mm2px300(200F));
        // // 这一块 算的 很复杂 主要是Graphics2D 原点在左上角，进行的坐标转换。
        // int maxCount = Math.min(infos.size(), (config[0] * config[1]));
        // int k = maxCount / config[0];
        // int j = maxCount % config[0];
        // k = j != 0 ? (k + 1) : k;
        // imageFileResp.setY(0 * UnitUtils.mm2px300(115F));
        // imageFileResp.setY((k - y - 1) * UnitUtils.mm2px300(115F));
        // imageFileResp.setBufferedImage(bufferedImage);
        // imageFileResp.setInfo(req);
        // imageFileResps.add(imageFileResp);
        // width = (newX + 1) * UnitUtils.mm2px300(200F);
        // height = (newY + 1) * UnitUtils.mm2px300(115F);
        // if (index >= infos.size()) {
        // break tab;
        // }
        // }
        // }
        ImageFileResp imageFileResp = new ImageFileResp();
        imageFileResp.setMainBufferedImage(
            new BufferedImage((int)UnitUtils.mm2px300(610F), (int)UnitUtils.mm2px300(420F), BufferedImage.TYPE_INT_ARGB));
        // imageFileResp.setItems(imageFileResps);
        return imageFileResp;
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
        // List<ImageFileRespItem> imageFileResps = imageFileResp.getItems();
        Graphics2D graphics = bufferedImage.createGraphics();
        // for (ImageFileRespItem item : imageFileResps) {
        //// handleProcess(item);
        // int newX = (int)item.getX();
        // int newY = (int)item.getY() + (int)(UnitUtils.mm2px300(115F) - item.getBufferedImage().getHeight());
        // graphics.drawImage(item.getBufferedImage(), newX, newY, item.getBufferedImage().getWidth(),
        // item.getBufferedImage().getHeight(), null);
        // }
        // 消除文字锯齿
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        // 消除图片锯齿
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setPaint(Color.RED);
        graphics.setStroke(new BasicStroke(10F));
        graphics.drawLine((int)UnitUtils.mm2px300(125.5F), 0, (int)UnitUtils.mm2px300(125.5F), (int)UnitUtils.mm2px300(420F));
        graphics.drawLine((int)UnitUtils.mm2px300(245.5F), 0, (int)UnitUtils.mm2px300(245.5F), (int)UnitUtils.mm2px300(420F));
        graphics.drawLine((int)UnitUtils.mm2px300(365.5F), 0, (int)UnitUtils.mm2px300(365.5F), (int)UnitUtils.mm2px300(420F));
        graphics.drawLine((int)UnitUtils.mm2px300(485.5F), 0, (int)UnitUtils.mm2px300(485.5F), (int)UnitUtils.mm2px300(420F));
        graphics.drawLine(0, (int)UnitUtils.mm2px300(120.1F), (int)UnitUtils.mm2px300(610.5F), (int)UnitUtils.mm2px300(120.1F));
        graphics.drawLine(0, (int)UnitUtils.mm2px300(297.6F), (int)UnitUtils.mm2px300(610.5F), (int)UnitUtils.mm2px300(297.6F));
        graphics.setPaint(Color.BLACK);
        graphics.fillOval((int)UnitUtils.mm2px300(21.5F),(int)UnitUtils.mm2px300(14.7F),(int)UnitUtils.mm2px300(3),(int)UnitUtils.mm2px300(3));
        graphics.fillOval((int)UnitUtils.mm2px300(21.5F),(int)UnitUtils.mm2px300(404.84F),(int)UnitUtils.mm2px300(3),(int)UnitUtils.mm2px300(3));
        graphics.fillOval((int)UnitUtils.mm2px300(586.5F),(int)UnitUtils.mm2px300(14.7F),(int)UnitUtils.mm2px300(3),(int)UnitUtils.mm2px300(3));
        graphics.fillOval((int)UnitUtils.mm2px300(586.5F),(int)UnitUtils.mm2px300(404.84F),(int)UnitUtils.mm2px300(3),(int)UnitUtils.mm2px300(3));
        // graphics.drawLine(10, 0, 10, 20);
        graphics.dispose();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, UnitUtils.PNG_FORMAT_NAME, outputStream);
        // ImageIO.write(bufferedImage, UnitUtils.PNG_FORMAT_NAME, new File("./a.png"));
        return outputStream;
    }
}
