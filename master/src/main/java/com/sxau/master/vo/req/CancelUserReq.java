package com.sxau.master.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 16:39
 * @Description:
 */
@Data
public class CancelUserReq {
    @NotNull(message = "操作的用户不可为空")
    private Integer id;
}
