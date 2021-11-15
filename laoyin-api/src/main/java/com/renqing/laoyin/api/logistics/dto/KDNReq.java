package com.bat.laoyin.api.logistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/12 10:03
 */
@Data
@NoArgsConstructor
public class KDNReq {

    @JsonProperty("RequestData")
    private String RequestData;
    @JsonProperty("EBusinessID")
    private String EBusinessID;
    @JsonProperty("RequestType")
    private String RequestType;
    @JsonProperty("DataSign")
    private String DataSign;
    @JsonProperty("DataType")
    private String DataType = "2";

}
