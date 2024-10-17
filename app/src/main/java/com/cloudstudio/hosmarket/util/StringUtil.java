package com.cloudstudio.hosmarket.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @ClassName StringUtil
 * @Author Create By matrix
 * @Date 2024/10/17 14:48
 */
public class StringUtil {

    /**
     * 将yyyymmdd格式转为yyyy-mm-dd
     * @param str
     * @return
     */
    public static String dateFormat(String str){
        // 使用substring分割字符串并在指定位置插入短横线
        String part1 = str.substring(0, 4); // 前4个字符
        String part2 = str.substring(4, 6); // 中间2个字符
        String part3 = str.substring(6);// 后2个字符
        return part1 + "-" + part2 + "-" + part3;
    }
    public static String stringFormat(String str){
        return str.replaceAll("-","");
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmptyStr(String str){
        return str == null || str.isEmpty();
    }
}
