package com.sxau.master.service;

import com.sxau.master.data.SysUser;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.vo.req.AddOrderReq;
import com.sxau.master.vo.req.updateOrderReq;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 09:04
 * @Description:
 */
public interface ISysOrderSV {
    /**
     * 生成订单
     * @param req
     * @param user
     */
    String save(SysUser user, AddOrderReq req);

    /**
     * 分页查询订单
     * @param userId
     * @param container
     * @return
     */
    PageResultContainer findAllOrderByUser(int userId,PageRequestContainer<SysOrderVO> container);

    /**
     * 修改订单
     * @param req
     */
    void updateOrder(updateOrderReq req);

    /**
     * 获取当前用户为完成的订单
     * @param userId
     * @return
     */
    List<Integer> getAllUndelOrder(int userId);
}
