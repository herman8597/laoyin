package com.bat.laoyin.api.logistics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/12 21:43
 */
@NoArgsConstructor
@Data
public class KDNOrderOnlineResp {

    @JsonProperty("Order")
    private OrderDTO Order;
    @JsonProperty("PrintTemplate")
    private String PrintTemplate;
    @JsonProperty("EBusinessID")
    private String EBusinessID;
    @JsonProperty("UniquerRequestNumber")
    private String UniquerRequestNumber;
    @JsonProperty("ResultCode")
    private String ResultCode;
    @JsonProperty("Reason")
    private String Reason;
    @JsonProperty("Success")
    private Boolean Success;

    @NoArgsConstructor
    @Data
    public static class OrderDTO {
        /**
         * MarkDestination : 891- 16-02 03 LogisticCode : 632589663609 ShipperCode : ZTO PackageName : 天津转 OrderCode :
         * F20211011213043607 KDNOrderCode : KDN20211012213953
         */

        @JsonProperty("MarkDestination")
        private String MarkDestination;
        @JsonProperty("LogisticCode")
        private String LogisticCode;
        @JsonProperty("ShipperCode")
        private String ShipperCode;
        @JsonProperty("PackageName")
        private String PackageName;
        @JsonProperty("OrderCode")
        private String OrderCode;
        @JsonProperty("KDNOrderCode")
        private String KDNOrderCode;
    }
}
