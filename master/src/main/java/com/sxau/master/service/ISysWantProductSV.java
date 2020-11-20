package com.sxau.master.service;

import com.sxau.master.data.SysWantProduct;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddWantProReq;
import com.sxau.master.vo.req.CancelWantProReq;
import com.sxau.master.vo.req.UpdateWantProReq;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 17:10
 * @Description:
 */
public interface ISysWantProductSV {
    /**
     * 生成求购信息
     * @param req
     */
    void save(AddWantProReq req);

    /**
     * 修改求购信息
     * @param req
     */
    void update(UpdateWantProReq req);

    /**
     * 删除求购信息
     * @param req
     */
    void cancel(CancelWantProReq req);

    /**
     * 分页获取所有求购信息
     * @param requestContainer
     * @return
     */
    PageResultContainer getAllWantPro(PageRequestContainer<SysWantProduct> requestContainer);
}
