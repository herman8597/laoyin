package com.bat.laoyin.service.logistics;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bat.laoyin.api.common.LaoYinException;
import com.bat.laoyin.api.logistics.LogisticsService;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineReq;
import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineResp;
import com.bat.laoyin.service.common.utils.logistics.KDNUtils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: lim
 * @description: 快递鸟实现
 * @date: 2021/10/12 9:32
 */
@Service
@Slf4j
public class KDNLogisticsServiceImpl implements LogisticsService {

    private static final OkHttpClient client = new OkHttpClient();

    /**
     * 用户ID，快递鸟提供，注意保管，不要泄漏 即用户ID，登录快递鸟官网会员中心获取 https://www.kdniao.com/UserCenter/v4/UserHome.aspx
     */
    @Value("${logistics.kdn.EBusinessID:test1622925}")
    private String EBusinessID;
    /**
     * API key，快递鸟提供，注意保管，不要泄漏 即API key，登录快递鸟官网会员中心获取 https://www.kdniao.com/UserCenter/v4/UserHome.aspx
     */
    @Value("${logistics.kdn.ApiKey:079b429d-8359-4715-a6a8-04631aa8b204}")
    private String ApiKey;
    /**
     * 请求地址
     */
    @Value("${logistics.kdn.ReqURL:http://sandboxapi.kdniao.com:8080/kdniaosandbox/gateway/exterfaceInvoke.json}")
    private String ReqURL;

    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        KDNOrderOnlineReq kdnOrderOnlineReq = new KDNOrderOnlineReq();
        String requestDataJson = objectMapper.writeValueAsString(kdnOrderOnlineReq);
        requestDataJson =
            "{'OrderCode': '012657018199','ShipperCode': 'YTO','CustomerName': '客户编码','CustomerPwd': '','MonthCode': '密钥','SendSite': '','PayType': 1,'MonthCode': '1234567890','ExpType': 1,'Cost': 1.0,'OtherCost': 1.0,'Sender': {'Company': 'LV','Name': 'Taylor','Mobile': '15018442396','ProvinceName': '上海','CityName': '上海市','ExpAreaName': '青浦区','Address': '明珠路'},'Receiver': {'Company': 'GCCUI','Name': 'Yann','Mobile': '15018442396','ProvinceName': '北京','CityName': '北京市','ExpAreaName': '朝阳区','Address': '三里屯街道'},'Commodity': [{'GoodsName': '鞋子','Goodsquantity': 1,'GoodsWeight': 1.0},{'GoodsName': '衣服','Goodsquantity': 1,'GoodsWeight': 1.0}],'AddService': [{'Name': 'INSURE','Value': '1000'},{'Name': 'COD','Value': '1020','CustomerID ': '1234567890'}],'Weight': 1.0,'Quantity': 1,'Volume': 0.0,'IsReturnPrintTemplate':1,'Remark': '小心轻放'}";
        String encrypt = KDNUtils.encrypt(requestDataJson, "079b429d-8359-4715-a6a8-04631aa8b204", "UTF-8");
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("RequestData", URLEncoder.encode(requestDataJson, "UTF-8"));
        builder.add("EBusinessID", "test1622925");
        builder.add("RequestType", "1007");
        builder.add("DataSign", URLEncoder.encode(encrypt, "UTF-8"));
        builder.add("DataType", "2");
        FormBody formBody = builder.build();
        Request request = new Request.Builder()
            .url("http://sandboxapi.kdniao.com:8080/kdniaosandbox/gateway/exterfaceInvoke.json").post(formBody).build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
        }
    }

    @Override
    public KDNOrderOnlineResp orderOnline(KDNOrderOnlineReq kdnOrderOnlineReq) {
        try {
            kdnOrderOnlineReq.setIsReturnPrintTemplate(1);
            kdnOrderOnlineReq.setTemplateSize("130");
            ObjectMapper objectMapper = new ObjectMapper();
            String requestDataJson = objectMapper.writeValueAsString(kdnOrderOnlineReq);
            log.info("requestDataJson json:{}", requestDataJson);
            String encrypt = KDNUtils.encrypt(requestDataJson, ApiKey, "UTF-8");
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("RequestData", URLEncoder.encode(requestDataJson, "UTF-8"));
            builder.add("EBusinessID", EBusinessID);
            builder.add("RequestType", "1007");
            builder.add("DataSign", URLEncoder.encode(encrypt, "UTF-8"));
            builder.add("DataType", "2");
            FormBody formBody = builder.build();
            Request request = new Request.Builder().url(ReqURL).post(formBody).build();
            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String respJson = response.body().string();
                    log.info("respJson:{}", respJson);
                    KDNOrderOnlineResp kdnOrderOnlineResp = objectMapper.readValue(respJson, KDNOrderOnlineResp.class);
                    if (!kdnOrderOnlineResp.getSuccess()) {
                        throw LaoYinException.buildException(kdnOrderOnlineResp.getResultCode(),
                            kdnOrderOnlineResp.getReason());
                    }
                    return kdnOrderOnlineResp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
