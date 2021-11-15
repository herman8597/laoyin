package com.bat.laoyin.api.logistics.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/12 9:49
 */
@NoArgsConstructor
@Data
public class KDNOrderOnlineReq {

    /**
     * OrderCode : 012657018199 ShipperCode : YTO CustomerName : 客户编码 CustomerPwd : MonthCode : 1234567890 SendSite :
     * PayType : 1 ExpType : 1 Cost : 1 OtherCost : 1 Sender :
     * {"Company":"LV","Name":"Taylor","Mobile":"15018442396","ProvinceName":"上海","CityName":"上海市","ExpAreaName":"青浦区","Address":"明珠路"}
     * Receiver :
     * {"Company":"GCCUI","Name":"Yann","Mobile":"15018442396","ProvinceName":"北京","CityName":"北京市","ExpAreaName":"朝阳区","Address":"三里屯街道"}
     * Commodity :
     * [{"GoodsName":"鞋子","Goodsquantity":1,"GoodsWeight":1},{"GoodsName":"衣服","Goodsquantity":1,"GoodsWeight":1}]
     * AddService : [{"Name":"INSURE","Value":"1000"},{"Name":"COD","Value":"1020","CustomerID ":"1234567890"}] Weight :
     * 1 Quantity : 1 Volume : 0 IsReturnPrintTemplate : 1 Remark : 小心轻放
     */

    @JsonProperty("OrderCode")
    private String OrderCode;
    @JsonProperty("ShipperCode")
    private String ShipperCode;
    @JsonProperty("CustomerName")
    private String CustomerName;
    @JsonProperty("CustomerPwd")
    private String CustomerPwd;
    @JsonProperty("MonthCode")
    private String MonthCode;
    @JsonProperty("SendSite")
    private String SendSite;
    @JsonProperty("PayType")
    private Integer PayType;
    @JsonProperty("ExpType")
    private Integer ExpType;
    @JsonProperty("Cost")
    private Integer Cost;
    @JsonProperty("OtherCost")
    private Integer OtherCost;
    @JsonProperty("Sender")
    private SenderDTO Sender;
    @JsonProperty("Receiver")
    private ReceiverDTO Receiver;
    @JsonProperty("Commodity")
    private List<CommodityDTO> Commodity;
    @JsonProperty("AddService")
    private List<AddServiceDTO> AddService;
    @JsonProperty("Weight")
    private Integer Weight;
    @JsonProperty("Quantity")
    private Integer Quantity;
    @JsonProperty("Volume")
    private Integer Volume;
    @JsonProperty("IsReturnPrintTemplate")
    private Integer IsReturnPrintTemplate;
    @JsonProperty("TemplateSize")
    private String TemplateSize;
    @JsonProperty("Remark")
    private String Remark;

    @NoArgsConstructor
    @Data
    public static class SenderDTO {
        /**
         * Company : LV Name : Taylor Mobile : 15018442396 ProvinceName : 上海 CityName : 上海市 ExpAreaName : 青浦区 Address :
         * 明珠路
         */

        @JsonProperty("Company")
        private String Company;
        @JsonProperty("Name")
        private String Name;
        @JsonProperty("Mobile")
        private String Mobile;
        @JsonProperty("ProvinceName")
        private String ProvinceName;
        @JsonProperty("CityName")
        private String CityName;
        @JsonProperty("ExpAreaName")
        private String ExpAreaName;
        @JsonProperty("Address")
        private String Address;
    }

    @NoArgsConstructor
    @Data
    public static class ReceiverDTO {
        /**
         * Company : GCCUI Name : Yann Mobile : 15018442396 ProvinceName : 北京 CityName : 北京市 ExpAreaName : 朝阳区 Address :
         * 三里屯街道
         */

        @JsonProperty("Company")
        private String Company;
        @JsonProperty("Name")
        private String Name;
        @JsonProperty("Mobile")
        private String Mobile;
        @JsonProperty("ProvinceName")
        private String ProvinceName;
        @JsonProperty("CityName")
        private String CityName;
        @JsonProperty("ExpAreaName")
        private String ExpAreaName;
        @JsonProperty("Address")
        private String Address;
    }

    @NoArgsConstructor
    @Data
    public static class CommodityDTO {
        /**
         * GoodsName : 鞋子 Goodsquantity : 1 GoodsWeight : 1
         */

        @JsonProperty("GoodsName")
        private String GoodsName;
        @JsonProperty("Goodsquantity")
        private Integer Goodsquantity;
        @JsonProperty("GoodsWeight")
        private Integer GoodsWeight;
    }

    @NoArgsConstructor
    @Data
    public static class AddServiceDTO {
        /**
         * Name : INSURE Value : 1000 CustomerID : 1234567890
         */

        @JsonProperty("Name")
        private String Name;
        @JsonProperty("Value")
        private String Value;
        @JsonProperty("CustomerID ")
        private String CustomerID;
    }
}
