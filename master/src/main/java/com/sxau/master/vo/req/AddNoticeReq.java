package com.sxau.master.vo.req;

import com.sxau.master.data.meta.NoticeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-08-24 21:46
 * @Description:
 */
@Data
public class AddNoticeReq {
    @ApiModelProperty(name = "title",value = "标题")
    @NotNull(message = "标题不可为空")
    private String title;
    @ApiModelProperty(name = "desc",value = "描述")
    private String desc;
    @NotNull(message = "类型不能为空")
    @ApiModelProperty(name = "type",value = "类型")
    private NoticeEnum type;
    @ApiModelProperty(name = "targetUser",value = "通知人")
    private String targetUser;
}
