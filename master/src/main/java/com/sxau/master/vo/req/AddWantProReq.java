package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-08-10 17:10
 * @Description:
 */
@Data
@ToString
public class AddWantProReq {
    private int userId;
    private String proName;
    private String proDesc;
    private BigDecimal proPrice;
    private int proCount;
    private Integer imgUrl;
}
