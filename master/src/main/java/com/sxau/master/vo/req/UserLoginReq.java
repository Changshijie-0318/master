package com.sxau.master.vo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 17:00
 * @Description:
 */
@Data
public class UserLoginReq {

    @NotNull(message = "用户名不能为空")
    private String loginName;

    @NotNull(message = "密码不可为空")
    private String password;
}
