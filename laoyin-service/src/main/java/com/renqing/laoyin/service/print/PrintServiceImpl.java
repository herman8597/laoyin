package com.bat.laoyin.service.print;

import static com.bat.laoyin.api.common.PrintConstant.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.kernel.geom.PageSize;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.api.makeup.dto.data.DispatchListRespDTO;
import com.bat.laoyin.api.makeup.dto.data.DistributionCodeItemRespDTO;
import com.bat.laoyin.api.makeup.dto.data.DistributionCodeRespDTO;
import com.bat.laoyin.api.makeup.dto.data.MaterialRequisitionRespDTO;
import com.bat.laoyin.api.print.PrintService;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListDTO;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListItemDTO;
import com.bat.laoyin.api.sendgoods.dto.data.DispatchListItemItemDTO;
import com.bat.laoyin.service.common.constants.SystemConstant;
import com.bat.laoyin.service.common.utils.file.FileUtils;
import com.bat.laoyin.service.common.utils.file.PdfItext7Utils;
import com.bat.laoyin.service.common.utils.file.ThymeleafUtils;
import com.bat.laoyin.service.common.utils.zxing.BarCodeUtils;
import com.bat.laoyin.service.common.utils.zxing.QRCodeUtil;
import com.bat.laoyin.service.makeup.executor.MakeupTaskMaterialQryExe;
import com.bat.laoyin.service.makeup.executor.MakeupTaskQryExe;
import com.bat.laoyin.service.sendgoods.executor.DispatchListQryExe;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/26 19:31
 */
@Service
@Slf4j
public class PrintServiceImpl implements PrintService {

    @Resource
    private MakeupTaskQryExe makeupTaskQryExe;

    @Resource
    private MakeupTaskMaterialQryExe makeupTaskMaterialQryExe;

    @Resource
    private DispatchListQryExe dispatchListQryExe;

    @Value("${print.url}")
    private String url;

    @Resource
    private PdfItext7Utils pdfUtils;

    @Override
    @Async("createFileAsyncTaskExecutor")
    public Future<MaterialRequisitionRespDTO> getMaterialRequisition(String code) {
        log.info("开始生成领料单 拼版任务编码：{}", code);
        MaterialRequisitionRespDTO materialRequisition = makeupTaskQryExe.getMaterialRequisition(code);
        String pdfFilePath = MATERIAL_REQUISITION + File.separator + materialRequisition.getMakeupTaskCode() + ".pdf";
        materialRequisition.setCrossPrintUrl(FileUtils.processingSlashes(url + PRINT_VISIT_URL + pdfFilePath));
        materialRequisition.setNoCrossPrintUrl(FileUtils.processingSlashes(url + pdfFilePath));
        // 如果文件已存在 直接返回
        if (new File(pdfFilePath).exists()) {
            return AsyncResult.forValue(materialRequisition);
        }
        File htmlFile = null;
        String qrCodeFilePath =
            SystemConstant.TEMP_PATH + MATERIAL_REQUISITION + File.separator + materialRequisition.getMakeupTaskCode()
                + "-qrcode.jpg";
        try {
            String htmlFilePath =
                MATERIAL_REQUISITION + File.separator + materialRequisition.getMakeupTaskCode() + ".html";
            String makeupTaskCode = materialRequisition.getMakeupTaskCode();
            // 生成二维码
            QRCodeUtil.encode(makeupTaskCode, qrCodeFilePath);
            Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(materialRequisition), Map.class);
            map.put("qrImgPath", qrCodeFilePath);
            // 生成静态html
            htmlFile = ThymeleafUtils.createHtml(map, htmlFilePath, MATERIAL_REQUISITION);
            // 生成pdf
            pdfUtils.convertToDocument(htmlFile, pdfFilePath);
            return AsyncResult.forValue(materialRequisition);
        } catch (Exception e) {
            materialRequisition.setCrossPrintUrl(null);
            materialRequisition.setNoCrossPrintUrl(null);
            e.printStackTrace();
        } finally {
            FileUtils.forceDelete(htmlFile);
            FileUtils.deleteFile(qrCodeFilePath);
        }
        return AsyncResult.forValue(materialRequisition);
    }

    @Override
    @Async("createFileAsyncTaskExecutor")
    public Future<DistributionCodeRespDTO> getDistributionCodeLabel(String makeupTaskCode) {
        log.info("开始生成配货 拼版任务code：{}", makeupTaskCode);
        DistributionCodeRespDTO dto = new DistributionCodeRespDTO();
        List<DistributionCodeItemRespDTO> distributionCodes =
            makeupTaskMaterialQryExe.listDistributionCode(makeupTaskCode);
        dto.setDistributionCodeItems(distributionCodes);
        String fileName = distributionCodes.get(0).getDistributionCode();
        String pdfFilePath = DISTRIBUTION_CODE + File.separator + fileName + ".pdf";
        dto.setCrossPrintUrl(FileUtils.processingSlashes(url + PRINT_VISIT_URL + pdfFilePath));
        dto.setNoCrossPrintUrl(FileUtils.processingSlashes(url + pdfFilePath));
        // 如果文件已存在 直接返回
        if (new File(pdfFilePath).exists()) {
            return AsyncResult.forValue(dto);
        }
        List<Map> maps = JSONObject.parseArray(JSONObject.toJSONString(distributionCodes), Map.class);
        List<File> htmlFiles = new ArrayList<>();
        List<String> barCodePath = new ArrayList<>();
        try {
            for (int i = 0; i < maps.size(); i++) {
                String distributionCode = (String)maps.get(i).get("distributionCode");
                Integer distributionCodeIndex = (Integer)maps.get(i).get("distributionCodeIndex");
                distributionCode += "-" + distributionCodeIndex;
                String barCodeFilePath =
                    SystemConstant.TEMP_PATH + DISTRIBUTION_CODE + File.separator + distributionCode + "-barcode.jpg";
                BarCodeUtils.encode(distributionCode, 88, 20, barCodeFilePath);
                barCodePath.add(barCodeFilePath);
                String htmlFilePath = DISTRIBUTION_CODE + File.separator + distributionCode + ".html";
                Map map = maps.get(i);
                map.put("barImgPath", barCodeFilePath);
                htmlFiles.add(ThymeleafUtils.createHtml(map, htmlFilePath, DISTRIBUTION_CODE));
            }
            pdfUtils.convertToDocument(htmlFiles, pdfFilePath, new PageSize(198.34F, 141.67F), "0,0,0,0");
            return AsyncResult.forValue(dto);
        } catch (IOException e) {
            dto.setCrossPrintUrl(null);
            dto.setNoCrossPrintUrl(null);
            e.printStackTrace();
        } finally {
            htmlFiles.forEach(FileUtils::forceDelete);
            barCodePath.forEach(FileUtils::deleteFile);
        }
        return AsyncResult.forValue(dto);
    }

    @Override
    @Async("createFileAsyncTaskExecutor")
    public Future<DispatchListRespDTO> getDispatchList(String distributionCode) {
        log.info("开始生成配货 配货标签code(带-xx)：{}", distributionCode);
        // xxxx-1 xxxx-2 代表一个商品多件
        // 宽72mm 长不限 new PageSize(204, 181+73+31+31);
        DispatchListDTO dispatchList = dispatchListQryExe.getDispatchList(distributionCode);
        DispatchListRespDTO dto = new DispatchListRespDTO();
        dto.setFileType(dispatchList.getFileType());
        dto.setDispatchListCode(dispatchList.getCode());
        dto.setScanSum(dispatchList.getScanSum());
        // 标签pdf
        dto.setLabelPdfUrl(dispatchList.getPdfUrl());
        if (dto.getFileType() == 1) {
            String pdfFilePath = DISPATCH_LIST + File.separator + dispatchList.getCode() + ".pdf";
            dto.setCrossPrintUrl(FileUtils.processingSlashes(url + PRINT_VISIT_URL + pdfFilePath));
            dto.setNoCrossPrintUrl(FileUtils.processingSlashes(url + pdfFilePath));
            // 如果文件已存在 直接返回
            if (new File(pdfFilePath).exists()) {
                return AsyncResult.forValue(dto);
            }
            // 第一次打印 生成文件
            if (dto.getScanSum() == 1) {
                // 打印发货单
                File htmlFile = null;
                String htmlFilePath = DISPATCH_LIST + File.separator + dispatchList.getCode() + ".html";
                String barCodeFilePath =
                    SystemConstant.TEMP_PATH + DISPATCH_LIST + File.separator + dispatchList.getCode() + "-barcode.jpg";
                Map map = JSONObject.parseObject(JSONObject.toJSONString(dispatchList), Map.class);
                try {
                    // 生成静态html
                    BarCodeUtils.encode(dispatchList.getCode(), 150, 44, barCodeFilePath);
                    map.put("barImgPath", barCodeFilePath);
                    htmlFile = ThymeleafUtils.createHtml(map, htmlFilePath, DISPATCH_LIST);
                    // 生成pdf
                    pdfUtils.convertToDocument(htmlFile, pdfFilePath, new PageSize(204, calcHeight(dispatchList)),
                        "0,0,0,0");
                    return AsyncResult.forValue(dto);
                } catch (IOException e) {
                    dto.setNoCrossPrintUrl(null);
                    dto.setNoCrossPrintUrl(null);
                    e.printStackTrace();
                } finally {
                    FileUtils.forceDelete(htmlFile);
                    FileUtils.deleteFile(barCodeFilePath);
                }
            }
        }
            // 打印运单
            return AsyncResult.forValue(dto);

    }

    /**
     * 获取动态的高度 但是貌似打印机可以动态打印，但是为了保险起见动态计算pdf高度
     * 
     * @param dispatchList
     * @return
     */
    private int calcHeight(DispatchListDTO dispatchList) {
        int height = 181;
        for (DispatchListItemDTO dispatchListItem : dispatchList.getDispatchListItems()) {
            height += 71;
            for (DispatchListItemItemDTO dispatchListItemItem : dispatchListItem.getDispatchListItemItems()) {
                height += 31;
            }
        }
        return height;
    }

    @Override
    @Async("createFileAsyncTaskExecutor")
    public Future<KDNOrderOnlineResp> getWaybill(String dispatchListCode) {
        log.info("开始打印运单 发货单标签code:{}", dispatchListCode);
        KDNOrderOnlineResp waybill = dispatchListQryExe.getWaybill(dispatchListCode);
        return AsyncResult.forValue(waybill);
    }

}
