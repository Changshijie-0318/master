package com.sxau.master.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-22 17:09
 * @Description:
 */
@Data
public class GetProInfoReq {
    @NotNull(message = "商品id不可为空")
    private Integer proId;
}
