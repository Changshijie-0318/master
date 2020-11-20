package com.sxau.master.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 08:48
 * @Description:
 */
@Data
public class SysWantProductVO {
    private int id;
    private int useId;
    private String userName;
    private String phone;
    private String proName;
    private String proDesc;
    private BigDecimal proPrice;
    private int proCount;
    private Timestamp updateTime;
    private Timestamp createTime;
    private byte state;
    private Integer imgUrl;
}
