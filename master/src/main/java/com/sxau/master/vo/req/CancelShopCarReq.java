package com.sxau.master.vo.req;

import com.sxau.master.data.meta.CommonActionEnum;
import com.sxau.master.data.meta.CommonActionEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-10 09:31
 * @Description:
 */
@Data
public class CancelShopCarReq {
    @NotNull(message = "操作的对象不能为空")
    private int id;
    @NotNull(message = "行为不可为空")
    private CommonActionEnum action;
}
