package com.bat.laoyin.service.common.utils.logistics;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * @author: lim
 * @description: 快递鸟 签名工具类
 * @date: 2021/10/12 10:34
 */
public class KDNUtils {
    /**
     * 电商Sign签名生成 content 内容 keyValue ApiKey charset 编码方式
     * 
     * @throws UnsupportedEncodingException
     *             ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    public static String encrypt(String content, String keyValue, String charset)
        throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * base64编码 str 内容 charset 编码方式
     * 
     * @throws UnsupportedEncodingException
     */
    private static String base64(String str, String charset) throws UnsupportedEncodingException {
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    /**
     * MD5加密 str 内容 charset 编码方式
     * 
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }
}
