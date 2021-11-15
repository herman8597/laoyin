package com.bat.laoyin.service.common.constants;

import com.bat.laoyin.service.common.enums.SendGoodsPlatformEnum;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/9/7 10:44
 */
public class Constant {
    public static final String UNKNOWN = "unknown";
    public static final String DEFAULT_PLATFORM_CODE = SendGoodsPlatformEnum.TB.name();

    /**
     * 状态 0 禁用 1 启用
     */
    public final static Short OPEN_NO = 0;
    public final static Short OPEN_YES = 1;

    /**
     * 删除标志位 0 正常 1 删除
     */
    public final static Short DEL_NO = 0;
    public final static Short DEL_YES = 1;

    /**
     * 扫描标志位 0 正常 1 删除
     */
    public final static Short SCAN_NO = 0;
    public final static Short SCAN_YES = 1;

    /**
     * 操作类型 1 新增 2 修改 3 删除
     */
    public final static Short OPERATION_TYPE_CREATE = 1;
    public final static Short OPERATION_TYPE_UPDATE = 2;
    public final static Short OPERATION_TYPE_DELETE = 3;
}
