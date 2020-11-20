package com.sxau.master.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:48
 * @Description:前端页面请求结果容器类
 */

@ApiModel
public class PageResultContainer<T> implements Serializable {

    private static final long serialVersionUID = -6697572120265371577L;

    @ApiModelProperty("总数")
    private long total;

    @ApiModelProperty("返回结果集")
    private List<T> entities;

    public PageResultContainer(){

    }

    public PageResultContainer(Page<T> page){
        total = page.getTotalElements();
        entities = page.getContent();
    }

    public PageResultContainer(List<T> entities,int count){
        this.entities = entities;
        this.total = count;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }
}
