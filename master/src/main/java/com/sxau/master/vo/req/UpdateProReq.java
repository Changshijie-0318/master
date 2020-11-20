package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 17:34
 * @Description:
 */
@Data
@ToString
public class UpdateProReq {
    private int id;
    private String proName;
    private String proDesc;
    private BigDecimal proPrice;
    private int proCount;
    private byte state;
    private Integer sortId;
    private Integer imgUrl;
}
