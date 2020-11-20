package com.sxau.master.vo.req;

import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.vo.req
 * @Author:Shijie Chang
 * @CreateTime: 2020-08-02 10:49
 * @Description:
 */
@Data
public class AddOrderReq {
    private List<Integer> carIds;
}
