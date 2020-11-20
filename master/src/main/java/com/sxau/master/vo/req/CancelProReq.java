package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 17:35
 * @Description:
 */
@Data
@ToString
public class CancelProReq {
    @NotNull(message = "操作的商品id不可为空")
    private List ids;
}
