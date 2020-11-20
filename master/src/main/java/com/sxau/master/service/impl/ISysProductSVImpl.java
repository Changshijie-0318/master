package com.sxau.master.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.mapper.SysProductMapper;
import com.sxau.master.dao.mapper.SysShopCarMapper;
import com.sxau.master.dao.repostiory.SysProductRepository;
import com.sxau.master.dao.repostiory.SysSortRepository;
import com.sxau.master.data.SysProduct;
import com.sxau.master.data.SysSort;
import com.sxau.master.service.ISysProductSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddProductReq;
import com.sxau.master.vo.req.CancelProReq;
import com.sxau.master.vo.req.UpdateProReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.briup.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 17:20
 * @Description:
 */
@Service
public class ISysProductSVImpl implements ISysProductSV {
    private static Logger logger = LoggerFactory.getLogger(ISysProductSVImpl.class);
    @Resource
    SysProductRepository repository;
    @Resource
    SysProductMapper sysProductMapper;
    @Resource
    SysShopCarMapper shopCarMapper;
    @Resource
    SysSortRepository sortRepository;

    /**
     * 添加商品
     *
     * @param req
     * @param opId
     */
    @Override
    @Transactional
    public void save(Integer opId, AddProductReq req) {
        SysProduct product = new SysProduct();
        BeanUtils.copyProperties(req, product);
        product.setUserId(opId);
        product.setState(ProjectConstans.BUSI_STATUS_PASS);
        product.setCreateTime(TimeUtils.getCurrentTime());
        product.setUpdateTime(TimeUtils.getCurrentTime());
        repository.save(product);
        logger.info("商品添加成功");
    }

    /**
     * 修改商品信息
     *
     * @param req
     * @param opId
     */
    @Override
    @Transactional
    public void update(Integer opId, UpdateProReq req) {
        SysProduct product = repository.findSysProductById(req.getId());
        BeanUtils.copyProperties(req, product);
        product.setUserId(opId);
        product.setUpdateTime(TimeUtils.getCurrentTime());
        repository.saveAndFlush(product);
    }

    /**
     * 注销商品信息
     *
     * @param req
     * @param opId
     */
    @Override
    @Transactional
    public void cancel(Integer opId, CancelProReq req) {
        List ids = req.getIds();
        sysProductMapper.cancelSomePro(ids,TimeUtils.getCurrentTime(),ProjectConstans.STATE_UNVALID);
        /**
         * 通知所有购物车订单的人商品已经失效
         */
        shopCarMapper.changeAllShopCarStateByProId(ids,ProjectConstans.STATE_UNVALID);
    }

    /**
     * 获取自己出售的商品信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysProduct> getAllProByUserId(int userId) {
        List states = new ArrayList();
        states.add(ProjectConstans.BUSI_STATUS_PASS);
        states.add(ProjectConstans.BUSI_STATUS_PAY);
        return sysProductMapper.getAllProByUserIdAndState(userId,states);
    }

    /**
     * 分页获取所有商品
     * @param container
     * @return
     */
    @Override
    public PageResultContainer getAllProduct(PageRequestContainer<SysProduct> container) {
        SysProduct data = container.getData();
        Page<SysProduct> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(), "id desc");
        List<SysProduct> products = sysProductMapper.getAllProduct(data.getState(),data.getSortId());
        PageResultContainer result = new PageResultContainer();
        result.setEntities(products);
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 分页获取当前用户商品
     *
     * @param userId
     * @param container
     * @return
     */
    @Override
    public PageResultContainer getAllProductForUser(int userId, PageRequestContainer<SysProduct> container) {
        SysProduct data = container.getData();
        Page<SysProduct> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(), "id desc");
        List<SysProduct> products = sysProductMapper.getAllProductForUser(userId,data.getState());
        PageResultContainer result = new PageResultContainer();
        result.setEntities(products);
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 获取商品信息
     *
     * @param id
     * @return
     */
    @Override
    public SysProduct getProInfo(int id) {
        return repository.findSysProductById(id);
    }

    /**
     * 批量修改商品状态
     *
     * @param proIds
     * @param state
     */
    @Override
    @Transactional
    public void changeProState(List<Integer> proIds, byte state) {
        sysProductMapper.batchToChangeProState(proIds,TimeUtils.getCurrentTime(),state);
    }

    /***
     * 查询所有分类
     * @return
     */
    @Override
    public List<SysSort> findAll() {
        return sortRepository.findAll();
    }
}
