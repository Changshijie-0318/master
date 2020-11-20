package com.sxau.master.vo.req;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-08-11 16:11
 * @Description:
 */
@Data
public class AddUserReq {
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
