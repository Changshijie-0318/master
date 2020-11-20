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
public class ShopCarVO {
    private Integer id;
    private String proName;
    private BigDecimal proPrice;
    private int proCount;
    private Integer imgUrl;
    private Timestamp createTime;
    private Timestamp updateTime;
    private byte state;
}
