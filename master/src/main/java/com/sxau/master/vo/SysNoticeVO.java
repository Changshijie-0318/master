package com.sxau.master.vo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:48
 * @Description:
 */
@Data
public class SysNoticeVO {
    private int id;
    private String title;
    private String content;
    private Timestamp createTime;
}
