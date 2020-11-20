package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-10 17:16
 * @Description:
 */
@Data
@ToString
public class CancelWantProReq {
    @NotNull(message = "操作的对象不可为空")
    private Integer id;
}
