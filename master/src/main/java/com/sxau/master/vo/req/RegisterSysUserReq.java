package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 16:26
 * @Description:
 */
@Data
@ToString
public class RegisterSysUserReq {
    private String loginName;
    private String password;
    private String email;
}
