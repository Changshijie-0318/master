package com.sxau.master.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-03 16:37
 * @Description:
 */
@Data
public class DeleteImageReq {
    @NotNull(message = "id不可为空")
    private int id;
}
