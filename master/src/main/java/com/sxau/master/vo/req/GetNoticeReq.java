package com.sxau.master.vo.req;

import com.sxau.master.data.meta.NoticeReqEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-25 21:52
 * @Description:
 */
@Data
public class GetNoticeReq {
    @ApiModelProperty(name = "type",value = "处理消息类型")
    private NoticeReqEnum type;
}
