package com.sxau.master.data.meta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sxau.master.utils.EnumUtil;
import com.sxau.master.utils.EnumUtil;

public enum CommonActionEnum implements BaseEnum<CommonActionEnum,Integer> {
    CREATE(0,"删除"),
    EDIT(1,"完成"),
    ;
    CommonActionEnum(Integer action,String description){
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
    public CommonActionEnum getInstance(int value){
        return EnumUtil.getEnumInstance(CommonActionEnum.class,value);
    }
}
