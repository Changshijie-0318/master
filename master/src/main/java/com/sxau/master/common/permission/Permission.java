package com.sxau.master.common.permission;

import java.lang.annotation.*;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.common.permission
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-06 18:07
 * @Description:
 */
@Documented
//如果一个类型声明被注释了文档化，它的注释成为公共API的一部分
@Retention(RetentionPolicy.RUNTIME)
//这个枚举类型的常量描述保留注释的各种策略，它们与元注释(@Retention)一起指定注释要保留多长时间
@Target({ElementType.METHOD})
//注释可能出现在Java程序中的语法位置（这些常量与元注释类型(@Target)一起指定在何处写入注释的合法位置）
public @interface Permission {

}
