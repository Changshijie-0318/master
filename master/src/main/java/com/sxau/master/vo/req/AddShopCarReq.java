package com.sxau.master.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-01-10 09:29
 * @Description:
 */
@Data
public class AddShopCarReq {
    @NotNull(message = "需要操作的对象不能为空")
    private int proId;
}
