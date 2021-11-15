package com.bat.laoyin.service.common.enums;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/13 11:09
 */
public enum MakeupTaskStatus {
    /**
     * 无效
     */
    INVALID((short)0, "无效"),
    /**
     * 批次等待中
     */
    WAIT_MAKEUP((short)1, "批次等待中"),
    /**
     * 拼版完成
     */
    MAKEUP_COMPLETE((short)2, "拼版完成"),
    /**
     * 拼版失败
     */
    MAKEUP_FAIL((short)3, "拼版失败"),

    /**
     * 截稿完成
     */
    DO_DEAD_LINE_SUCCESS((short)4, "截稿完成"),

    /**
     * 截稿失败
     */
    DO_DEAD_LINE_FAIL((short)5, "截稿失败");

    private short value;
    private String name;

    MakeupTaskStatus(short value, String name) {
        this.value = value;
        this.name = name;
    }

    public short getValue() {
        return value;
    }

}
