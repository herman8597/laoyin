package com.bat.laoyin.service.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

import com.bat.laoyin.service.common.constants.TimeFormatConstant;

/**
 * @author: lim
 * @description: 编号生成工具
 * @date: 2021/9/3 16:25
 */
public class NoUtils {
    /**
     * 商品
     */
    private static String GOODS_PREFIX = "P";
    /**
     * 产品
     */
    private static String PRODUCT_PREFIX = "C";
    /**
     * 订单
     */
    private static String ORDER_PREFIX = "O";
    /**
     * 发货单
     */
    private static String DISPATCH_LIST_PREFIX = "F";
    /**
     * 配货单
     */
    private static String DISTRIBUTION_PREFIX = "H";
    /**
     * 拼版任务
     */
    private static String MAKEUP_PREFIX = "B";

    /**
     * 获取商品号
     *
     * @return
     */
    public static String getGoodsNo() {
        return GOODS_PREFIX + getShortUUID();
    }

    /**
     * 获取产品号
     *
     * @return
     */
    public static String getProductNo() {
        return PRODUCT_PREFIX + getShortUUID();
    }

    /**
     * 获取订单号
     * 
     * @return
     */
    public static String getOrderNo() {
        return ORDER_PREFIX + getDateTimeStr(TimeFormatConstant.PATTERN3);
    }

    /**
     * 获取发货单
     * 
     * @return
     */
    public static String getDispatchListNo() {
        return DISPATCH_LIST_PREFIX + getDateTimeStr(TimeFormatConstant.PATTERN3);
    }

    /**
     * 获取配货单
     *
     * @return
     */
    public static String getDistributionNo() {
        return DISTRIBUTION_PREFIX + getRandomIntStr(999999);
    }

    /**
     * 获取拼版任务
     * 
     * @return
     */
    public static String getMakeUpNo() {
        return MAKEUP_PREFIX + getDateTimeStr(TimeFormatConstant.PATTERN3);
    }

    /**
     * 获取用户key
     * 
     * @return
     */
    public static String getAppKey() {
        return "rq" + getShortUUID();
    }

    /**
     * 获取用户秘钥 算法： sha1(appKey+uuid) 生成AppSecret
     * 
     * @return
     */
    public static String getAppSecret(String appKey) {
        try {
            StringBuilder sb = new StringBuilder();
            String uuid = UUID.randomUUID().toString();

            sb.append(appKey).append(uuid);

            String str = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder hexStr = new StringBuilder();
            String shaHex = "";
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return appKey;
    }

    private static String getShortUUID() {
        return UUIDUtils.generateShortUuid().substring(0, 6);
    }

    /**
     * 标识码(X)+6位年月日+5位流水号或数字随机码
     * 
     * @param pattern
     * @return
     */
    private static String getDateTimeStr(String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String dataTimeStr = formatter.format(LocalDateTime.now());
        return dataTimeStr + getRandomIntStr(99999);
    }

    private static String getRandomIntStr(int bound) {
        int num = new Random().nextInt(bound);
        int length = (bound + "").length();
        return String.format("%0" + length + "d", num);
    }

    public static void main(String[] args) {
        // String appKey = getAppKey();
        // String appSecret = getAppSecret(appKey);
        // System.out.println(appKey);
        // System.out.println(appSecret);
        for (int i = 0; i < 10; i++) {
            System.out.println(getDateTimeStr("yyMMdd"));;
        }
    }
}
