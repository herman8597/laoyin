package com.bat.laoyin.service.common.enums;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/13 11:09
 */
public enum OrderDetailStatus {
    /**
     * 无效
     */
    INVALID((short)0, "无效"),
    /**
     * 拼版中
     */
    IN_MAKEUP((short)1, "拼版中"),
    /**
     * 拼版完成
     */
    MAKEUP_COMPLETE((short)2, "拼版完成");

    private short value;

    private String name;

    OrderDetailStatus(short value, String name) {
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }

}
