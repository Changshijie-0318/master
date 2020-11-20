package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 16:44
 * @Description:
 */
@Data
@ToString
public class UpdateUserReq {
    private int id;
    private String realName;
    private String loginName;
    private String password;
    private String email;
    private Byte state;
    private String phone;
    private Timestamp createTime;
    private Timestamp updateTime;
    private String reason;
    private String address;
    private Integer imgUrl;
    private Byte role;
}
