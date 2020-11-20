package com.sxau.master.data.meta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sxau.master.utils.EnumUtil;
import com.sxau.master.utils.EnumUtil;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.data.meta
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 21:38
 * @Description:
 */
public enum NoticeEnum implements BaseEnum<NoticeEnum, Integer> {
    NOTICE_ALL(0, "通知全体"),
    NOTICE_USER(1, "通知个人用户"),
    ;

    NoticeEnum(Integer action, String description) {
        this.action = action;
        this.description = description;
    }

    private Integer action;
    private String description;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonValue
    @Override
    public Integer getValue() {
        return getAction();
    }

    @JsonCreator
    public CommonActionEnum getInstance(int value) {
        return EnumUtil.getEnumInstance(CommonActionEnum.class, value);
    }
}
