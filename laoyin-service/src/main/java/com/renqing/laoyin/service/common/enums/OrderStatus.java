package com.bat.laoyin.service.common.enums;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/13 11:09
 */
public enum OrderStatus {
    /**
     * 待审核
     */
    WAIT_CHECKED((short)1, "待审核"),
    /**
     * 审核中
     */
    CHECKING((short)2, "审核中"),
    /**
     * 待生产
     */
    WAIT_PRODUCED((short)3, "待生产"),
    /**
     * 生产中
     */
    PRODUCING((short)4, "生产中"),
    /**
     * 待发货
     */
    WAIT_DELIVERED((short)5, "待发货"),
    /**
     * 已发货
     */
    DELIVERED((short)6, "已发货"),
    /**
     * 已收货
     */
    RECEIVED_GOODS((short)7, "已收货"),
    /**
     * 已取消
     */
    CANCELLED((short)8, "已取消");

    private final String name;
    private short value;

    OrderStatus(short value, String name) {
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }

}
