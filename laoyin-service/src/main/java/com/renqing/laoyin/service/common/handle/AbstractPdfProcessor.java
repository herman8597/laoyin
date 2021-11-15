package com.bat.laoyin.service.common.handle;

import java.util.List;

import com.bat.laoyin.service.common.handle.dto.BusinessInfoReq;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/15 20:41
 */
public abstract class AbstractPdfProcessor {

    public abstract void makeup(List<BusinessInfoReq> infos);
}
