package com.bat.laoyin.service.common.enums;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/13 11:09
 */
public enum OrderType {
    /**
     * API导入
     */
    API_IMPORT((short)1, "API导入"),
    /**
     * 售后订单
     */
    AFTER_SALE_ORDER((short)2, "售后订单");

    private final String name;
    private short value;

    OrderType(short value, String name) {
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }

}
