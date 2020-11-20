package com.sxau.master.common;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.common
 * @CreateTime: 2020-08-31 17:06
 * @Description:配置中心校验类
 */
public class Assert4Demo {
    /**
     * @Description: 校验表达式必须为真
     * @Param: [expression, message]
     * @return: void
     * @Date: 2020/08/31
     */
    public static void isTrue(boolean expression, ResultEnum resultEnum, String message) {
        if (!expression) {
            throw new ErrorException(resultEnum, message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        isTrue(expression, ResultEnum.PARAM_ERROR, message);
    }

    /**
     * @Description: 校验的对象必须为空
     * @Param: [object, message]
     * @return: void
     * @Date: 2020/08/31
     */
    public static void isNull(Object object, ResultEnum resultEnum, Object... message) {
        if (object != null) {
            throw new ErrorException(resultEnum, message);
        }
    }



    /**
     * @Description: 校验对象必须不为空
     * @Param: [object, message]
     * @return: void
     * @Date: 2020/08/31
     */
    public static void notNull(Object object, ResultEnum resultEnum, Object... message) {
        if (object == null) {
            throw new ErrorException(resultEnum, message);
        }
    }

    /**
     * @Description: 校验字符串长度部位0
     * @Param: [text, message]
     * @return: void
     * @Date: 2020/08/31
     */
    public static void hasLength(String text, ResultEnum resultEnum, Object message) {
        if (text == null || text.length() == 0) {
            throw new ErrorException(resultEnum, message);
        }
    }

}
