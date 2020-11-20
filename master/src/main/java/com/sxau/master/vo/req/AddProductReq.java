package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-08-09 17:32
 * @Description:
 */
@Data
@ToString
public class AddProductReq {
    private String proName;
    private String proDesc;
    private Integer sortId;
    private BigDecimal proPrice;
    private int proCount;
    private Integer imgUrl;
}
