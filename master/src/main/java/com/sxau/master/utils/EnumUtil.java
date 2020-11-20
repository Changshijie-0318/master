package com.sxau.master.utils;

import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.data.meta.BaseEnum;

public class EnumUtil {
    public static <T extends BaseEnum> T getEnumInstance(Class<T> clz, Integer value){
        T [] enums = clz.getEnumConstants();
        for( T t: enums){
            if(t.getValue() == value){
                return t;
            }
        }
        throw new ErrorException(ResultEnum.DATA_ERROR,"获取枚举异常,枚举类:"+clz.getName()+",枚举值:"+value);
    }
}
