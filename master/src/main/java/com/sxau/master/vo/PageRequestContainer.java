package com.sxau.master.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:48
 * @Description:前端页面请求参数容器类
 */

@ApiModel("请求参数")
@Data
@ToString
public class PageRequestContainer<T> implements Serializable {

    private static final long serialVersionUID = 1874070126728105882L;

    @ApiModelProperty("页长")
    @NotNull(message = "页长不能为空")
    private Integer pageSize  ;

    @ApiModelProperty("当前页")
    @NotNull(message = "当前页不能为空")
    private Integer currentPage;

    @ApiModelProperty("请求数据")
//    @NotNull(message = "请求数据不能为空")
    private T data;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public int getIndex(){
        return (currentPage-1)*pageSize;
    }
}
