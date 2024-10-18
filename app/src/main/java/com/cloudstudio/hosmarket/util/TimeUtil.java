package com.cloudstudio.hosmarket.util;

import android.os.Build;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

/**
 * @ClassName TimeUtil
 * @Author Create By matrix
 * @Date 2024/10/18 10:09
 */
public class TimeUtil {
    public static String getTime(boolean includeTime){
        DateTimeFormatter formatter = null;
        String pattern;
        if (includeTime) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        } else {
            pattern = "yyyy-MM-dd"; // 仅包含日期，不包含时间
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // API级别26及以上
            LocalDateTime currentTime = LocalDateTime.now();
            formatter = DateTimeFormatter.ofPattern(pattern);
            return currentTime.format(formatter);
        } else {
            // API级别26以下
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            return sdf.format(calendar.getTime());
        }
    }
}
