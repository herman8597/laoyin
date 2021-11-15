package com.bat.laoyin.service.common.utils;

/**
 * @author: lim
 * @description: TODO
 * @date: 2021/8/7 12:03
 */
public class SqlUtils {
    public static String getSort(String sortStr) {
        String[] sortItem = sortStr.split(",");
        StringBuilder sort = new StringBuilder();
        for (String item : sortItem) {
            String column;
            if (item.startsWith("-")) {
                column = item.substring(1);
                sort.append(column).append(" desc,");
            } else {
                if (item.startsWith("+")) {
                    column = item.substring(1);
                } else {
                    column = item;
                }
                sort.append(column).append(" asc,");
            }
        }
        if (sort.toString().endsWith(",")) {
            sort = new StringBuilder(sort.substring(0, sort.length() - 1));
        }
        return sort.toString();
    }
}
