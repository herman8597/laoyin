package com.bat.laoyin.service.common.handle;

import com.bat.laoyin.service.common.handle.dto.BusinessInfoReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: lim
 * @description: 热升华6膜 处理器
 * @date: 2021/10/19 13:51
 */
@Component("DyeSublimation6PdfProcessor")
@Slf4j
public class DyeSublimation6PdfProcessor extends AbstractPdfProcessor {
    @Override
    public void makeup(List<BusinessInfoReq> infos) {

    }
}
