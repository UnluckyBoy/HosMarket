package com.cloudstudio.hosmarket.util;

import java.util.UUID;

/**
 * @ClassName UUIDNumberUtil
 * @Author Create By matrix
 * @Date 2024/10/18 10:06
 */
public class UUIDNumberUtil {
    /**
     * 生成8位UUID
     * @return
     */
    public static String randUUIDNumber(){
        UUID uuid = UUID.randomUUID();
        return StringUtil.stringFormat(uuid.toString().substring(0, 8));
    }

    /**
     * 生成8位UUID+时间
     * @return
     */
    public static String randUUIDNumberAndTime(){
        UUID uuid = UUID.randomUUID();
        return StringUtil.stringFormat(uuid.toString().substring(0, 8)+TimeUtil.getTime(false));
    }

    /**
     * 时间+生成8位UUID
     * @return
     */
    public static String randTimeAndUUIDNumber(){
        UUID uuid = UUID.randomUUID();
        return StringUtil.stringFormat(TimeUtil.getTime(false)+uuid.toString().substring(0, 8));
    }

    /**
     * 生成8位UUID+时间参数
     * @param time
     * @return
     */
    public static String randUUIDNumberAndTime_Param(String time){
        UUID uuid = UUID.randomUUID();
        return StringUtil.stringFormat(uuid.toString().substring(0, 8)+time);
    }
}
