package com.sxau.master.utils;

import java.sql.Timestamp;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.utils
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 16:48
 * @Description:
 */
public class TimeUtils {

    public static Timestamp getCurrentTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
