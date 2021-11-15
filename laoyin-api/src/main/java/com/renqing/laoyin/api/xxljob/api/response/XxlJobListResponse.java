package com.bat.laoyin.api.xxljob.api.response;

import java.util.List;

import com.bat.laoyin.api.xxljob.api.response.dto.XxlJobDTO;

import lombok.Data;

@Data
public class XxlJobListResponse extends XxlJobResponse {
    private List<XxlJobDTO> data;
    private Integer recordsFiltered;
    private Integer recordsTotal;

}
