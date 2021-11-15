package com.bat.laoyin.api.common;

public class ErrorCode {
    /**
     * 参数为空的错误情况
     */

    public final static String P_NOTNULL = "P_NOTNULL";
    /**
     * 系统异常情况
     */
    public final static String SYSTEM_EXCEPTION = "SYSTEM_EXCEPTION";

    /**
     * 用户禁用
     */
    public static final String B_USER_DISABLE = "B_USER_DISABLE";

    /**
     * 用户名或密码错误
     */
    public static final String B_USER_NAME_OR_PASSWORD = "B_USER_NAME_OR_PASSWORD";

    /**
     * 用户token失效
     */
    public static final String B_TOKEN_IS_INVALID = "B_TOKEN_IS_INVALID";

    /**
     * 客户身份错误
     */
    public static final String B_APP_KEY_OR_APP_SECRET_IS_INVALID = "B_APP_KEY_OR_APP_SECRET_IS_INVALID";

    public static final String B_JOB_ADD_ERROR = "B_JOB_ADD_ERROR";
    public static final String B_JOB_ADD_ERROR_MSG = "定时任务创建失败";
    public static final String B_JOB_LOGIN_ERROR = "B_JOB_LOGIN_ERROR";

    /**
     * 产品已经绑定拼版设置
     */
    public static final String B_PRODUCT_HAS_BEEN_BIND_MAKEUP = "B_PRODUCT_HAS_BEEN_BIND_MAKEUP";

    public static final String B_PRODUCT_FAMILY_NULL = "B_PRODUCT_FAMILY_NULL";
    public static final String B_PRODUCT_FAMILY_NULL_MSG = "产品品类为空";
    public static final String B_PRODUCT_CATEGORY_NULL = "B_PRODUCT_CATEGORY_NULL";
    public static final String B_PRODUCT_CATEGORY_NULL_MSG = "产品分类为空";
    public static final String B_PRODUCT_BRAND_NULL = "B_PRODUCT_BRAND_NULL";
    public static final String B_PRODUCT_BRAND_NULL_MSG = "产品品牌为空";
    public static final String B_PRODUCT_SPEC_NULL = "B_PRODUCT_BRAND_NULL";
    public static final String B_PRODUCT_SPEC_NULL_MSG = "产品规格为空";

    public static final String B_MAKEUP_ERROR = "B_MAKEUP_ERROR";
    public static final String B_MAKEUP_DEADLINE_TIME_NULL = "B_MAKEUP_DEADLINE_TIME_NULL";
    public static final String B_MAKEUP_DEADLINE_TIME_NULL_MSG = "截稿时间为空";
    public static final String B_MAKEUP_TASK_NULL = "B_MAKEUP_TASK_NULL";
    public static final String B_MAKEUP_TASK_NULL_MSG = "拼版任务不存在";
    public static final String B_MAKEUP_TASK_STATUS_COMPLETE = "B_MAKEUP_TASK_STATUS_COMPLETE";
    public static final String B_MAKEUP_TASK_STATUS_COMPLETE_MSG = "拼版任务状态为已完成";
    public static final String B_MAKEUP_TASK_STATUS_NOT_COMPLETE = "B_MAKEUP_TASK_STATUS_NOT_COMPLETE";
    public static final String B_MAKEUP_TASK_STATUS_NOT_COMPLETE_MSG = "拼版任务不为已完成状态";
    public static final String B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS =
        "B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS";
    public static final String B_MAKEUP_TASK_STATUS_NOT_DEAD_LINE_SUCCESS_MSG = "拼版任务不为截稿成功状态";

    public static final String B_DISPATCH_LIST_DISTRIBUTION_CODE_NULL = "B_DISPATCH_LIST_DISTRIBUTION_CODE";
    public static final String B_DISPATCH_LIST_DISTRIBUTION_CODE_NULL_MSG = "该配货码所对应的发货单不存在";

    public static final String B_DISPATCH_LIST_ITEM_NOT_ENOUGH = "B_DISPATCH_LIST_ITEM_NOT_ENOUGH";
    public static final String B_DISPATCH_LIST_ITEM_NOT_ENOUGH_MSG = "发货单货没有备齐";
    public static final String B_DISTRIBUTION_CODE_HAS_BEEN_SCANNED = "B_DISTRIBUTION_CODE_HAS_BEEN_SCANNED";
    public static final String B_DISTRIBUTION_CODE_HAS_BEEN_SCANNED_MSG = "配货码已经被扫描过了";
    public static final String B_DISTRIBUTION_CODE_NOT_EXIST = "B_DISTRIBUTION_CODE_NOT_EXIST";
    public static final String B_DISTRIBUTION_CODE_NOT_EXIST_MSG = "配货码不存在";
    public static final String B_FILE_TYPE_ERROR = "B_FILE_TYPE_ERROR";
    public static final String B_FILE_TYPE_ERROR_MSG = "文件类型错误";
    public static final String B_FILE_NOT_EXISTS = "B_FILE_NOT_EXISTS";
    public static final String B_FILE_NOT_EXISTS_MSG = "文件不存在";
}
