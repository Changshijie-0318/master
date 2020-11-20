package com.sxau.master.vo;

import lombok.Data;
import lombok.ToString;

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
@ToString
public class SysOrderVO {
    private Integer id;
    private String userIdSell;
    private String userIdBuy;
    private String phone;
    private String proName;
    private BigDecimal proPrice;
    private Timestamp createTime;
    private String address;
    private Integer imgUrl;
    private BigDecimal rate;
    private byte state;
}
