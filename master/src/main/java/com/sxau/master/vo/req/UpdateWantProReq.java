package com.sxau.master.vo.req;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-10 17:14
 * @Description:
 */
@Data
@ToString
public class UpdateWantProReq {
    @NotNull(message = "操作对象不可为空")
    private int id;
    private int userId;
    @NotNull(message = "求购商品须有名称")
    private String proName;
    private String proDesc;
    @NotNull(message = "求购商品须有价钱")
    private BigDecimal proPrice;
    @NotNull(message = "求购商品须有个数")
    private int proCount;
    private Integer imgUrl;
}
