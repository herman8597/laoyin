package com.bat.laoyin.service.common.utils.logistics;

import java.util.Arrays;
import java.util.List;

import com.bat.laoyin.api.logistics.dto.KDNOrderOnlineReq;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/10/12 21:08
 */
public class KDNAdressConvertor {
    /**
     * 地址转换 规整
     *
     * 收件省 (如广东省，不要缺少“省”；如是直辖市，请直接传北京、上海等； 如是自治区，请直接传广西壮族自治区等)
     *
     * 收件市(如深圳市，不要缺少“市”； 如果是市辖区，请直接传北京市、上海市等)
     *
     * 收件区/县(如福田区，不要缺少“区”或“县”)
     *
     *
     * 根据现有地址库数据 来改
     * 
     * @param kdnOrderOnlineReq
     * @return
     */
    private static List<String> municipalities = Arrays.asList("北京市", "天津市", "上海市", "重庆市");

    public static void convertor(KDNOrderOnlineReq kdnOrderOnlineReq) {
        KDNOrderOnlineReq.SenderDTO sender = kdnOrderOnlineReq.getSender();
        KDNOrderOnlineReq.ReceiverDTO receiver = kdnOrderOnlineReq.getReceiver();
        if (municipalities.contains(sender.getProvinceName())) {
            sender.setExpAreaName(sender.getCityName());
            sender.setCityName(sender.getProvinceName());
            sender.setProvinceName(sender.getProvinceName().replace("市", ""));
        }
        if (municipalities.contains(receiver.getProvinceName())) {
            receiver.setExpAreaName(sender.getCityName());
            receiver.setCityName(sender.getProvinceName());
            receiver.setProvinceName(sender.getProvinceName().replace("市", ""));
        }
    }
}
