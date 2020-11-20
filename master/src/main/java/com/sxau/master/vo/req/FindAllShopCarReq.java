package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:43
 * @Description:
 */
@Data
@ToString
public class FindAllShopCarReq {
    @NotNull(message = "状态不可为空")
    private byte state;
}
