package com.sxau.master.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-09 18:57
 * @Description:
 */
@Data
@ToString
public class ChangeNoticeReq {
    @ApiModelProperty(value = "ids",name = "处理的对象ids")
    @NotNull(message = "需要操作的对象不能为空")
    private List ids;
}
