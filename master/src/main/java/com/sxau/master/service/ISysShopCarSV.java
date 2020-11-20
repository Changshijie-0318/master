package com.sxau.master.service;

import com.sxau.master.data.SysShopCar;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.ShopCarVO;
import com.sxau.master.vo.req.AddShopCarReq;
import com.sxau.master.vo.req.CancelShopCarReq;

import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 09:28
 * @Description:
 */
public interface ISysShopCarSV{
    /**
     * 生成订单
     * @param opId
     * @param req
     */
    void save(int opId, AddShopCarReq req);

    /**
     * 取消订单/完成订单
     * @param req
     * @param opId
     */
    void cancel(int opId,CancelShopCarReq req);

    /**
     * 通过商品id确定有多少购物订单
     * @param proId
     * @return
     */
    List<SysShopCar> findAllShopCarByProId(int proId);

    /***
     * 获取当前用户下所有的购物车消息
     * @param userId
     * @return
     */
    List<ShopCarVO> findAllShopCarByUser(int userId , byte state);

    /**
     *
     * @param userId
     * @param container
     * @return
     */
    PageResultContainer findAllShopCarByUser(int userId, PageRequestContainer<ShopCarVO> container);
}
