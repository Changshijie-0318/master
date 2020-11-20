package com.sxau.master.service;


import com.sxau.master.data.SysProduct;
import com.sxau.master.data.SysSort;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddProductReq;
import com.sxau.master.vo.req.CancelProReq;
import com.sxau.master.vo.req.UpdateProReq;

import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 17:20
 * @Description:
 */
public interface ISysProductSV {
    /**
     * 添加商品
     * @param req
     * @param opId
     */
    void save(Integer opId, AddProductReq req);

    /**
     * 修改商品信息
     * @param req
     * @param opId
     */
    void update(Integer opId, UpdateProReq req);

    /**
     * 注销商品信息
     * @param req
     * @param opId
     */
    void cancel(Integer opId, CancelProReq req);

    /**
     * 获取自己出售的商品信息
     * @param userId
     * @return
     */
    List<SysProduct> getAllProByUserId(int userId);

    /**
     * 分页获取所有商品
     * @param container
     * @return
     */
    PageResultContainer getAllProduct(PageRequestContainer<SysProduct> container);

    /**
     * 分页获取当前用户商品
     * @param userId
     * @param container
     * @return
     */
    PageResultContainer getAllProductForUser(int userId,PageRequestContainer<SysProduct> container);

    /**
     * 获取商品信息
     * @param id
     * @return
     */
    SysProduct getProInfo(int id);

    /**
     * 批量修改商品状态
     * @param proIds
     * @param state
     */
    void changeProState(List<Integer> proIds,byte state);

    /***
     * 查询所有分类
     * @return
     */
    List<SysSort> findAll();
}
