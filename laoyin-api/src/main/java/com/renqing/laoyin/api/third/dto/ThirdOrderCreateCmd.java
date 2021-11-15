package com.bat.laoyin.api.third.dto;

import java.util.List;

import lombok.Data;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/9 18:28
 */
@Data
public class ThirdOrderCreateCmd {
    /**
     * 基本信息
     */
    private OrderInfoCreateCmd orderInfo;
    /**
     * 明细信息
     */
    private List<OrderDetailCreateCmd> orderDetails;
    /**
     * 发货信息
     */
    private OrderDeliverCreateCmd orderDeliver;
}
