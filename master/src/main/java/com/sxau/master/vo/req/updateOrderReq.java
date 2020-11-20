package com.sxau.master.vo.req;

import com.sxau.master.data.meta.CommonActionEnum;
import com.sxau.master.data.meta.CommonActionEnum;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-05 13:37
 * @Description:
 */
@Data
@ToString
public class updateOrderReq {
    @NotNull(message = "订单id不可为空")
    private List ids;
    private BigDecimal rate;
    @NotNull(message = "行为不可为空")
    private CommonActionEnum action;
}
