package com.bat.laoyin.api.makeup.dto.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: lim
 * @description: 领料单行项 并不是真实存在的表 所以My开头
 * @date: 2021/9/23 15:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MaterialRequisitionRespDTO extends PdfFileBaseRespDTO {
    private String makeupTaskCode;
    private List<MaterialRequisitionItemRespDTO> materialRequisitionItems;
}
