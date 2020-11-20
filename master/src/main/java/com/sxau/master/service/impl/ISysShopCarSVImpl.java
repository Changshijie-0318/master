package com.sxau.master.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.mapper.SysShopCarMapper;
import com.sxau.master.dao.repostiory.SysProductRepository;
import com.sxau.master.dao.repostiory.SysShopCarRepository;
import com.sxau.master.data.SysProduct;
import com.sxau.master.data.SysShopCar;
import com.sxau.master.data.meta.CommonActionEnum;
import com.sxau.master.service.ISysShopCarSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.ShopCarVO;
import com.sxau.master.vo.req.AddShopCarReq;
import com.sxau.master.vo.req.CancelShopCarReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.briup.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 09:28
 * @Description:
 */
@Service
@Transactional
public class ISysShopCarSVImpl implements ISysShopCarSV {
    private static Logger logger = LoggerFactory.getLogger(ISysShopCarSVImpl.class);
    @Resource
    SysShopCarRepository repository;
    @Resource
    SysProductRepository productRepository;
    @Resource
    SysShopCarMapper mapper;

    /**
     * 生成订单
     *
     * @param opId
     * @param req
     */
    @Override
    public void save(int opId, AddShopCarReq req) {
        Assert4Demo.isTrue(repository.findSysShopCarByUserIdBuyAndProIdAndState(opId, req.getProId(),ProjectConstans.BUSI_STATUS_PASS) == null, "购物车已有此物品，请不要重复添加");
        SysShopCar shopCar = repository.findSysShopCarByUserIdBuyAndProIdAndState(opId, req.getProId(), ProjectConstans.BUSI_STATUS_NO);
        SysProduct sysProduct = productRepository.findSysProductById(req.getProId());
        if (shopCar == null){
            shopCar = new SysShopCar();
        }
        Assert4Demo.isTrue(opId!=sysProduct.getUserId(),"你不能购买自己发布的商品！");
        shopCar.setProId(req.getProId());
        shopCar.setUserIdBuy(opId);
        shopCar.setUserIdSell(sysProduct.getUserId());
        shopCar.setCreateTime(TimeUtils.getCurrentTime());
        shopCar.setUpdateTime(TimeUtils.getCurrentTime());
        shopCar.setState(ProjectConstans.SHOP_STATE_VALID);
        repository.save(shopCar);
        logger.info("用户：【" + opId + "】的购物车消息已经生成");
    }

    /**
     * 取消订单/完成订单
     *
     * @param req
     * @param opId
     */
    @Override
    public void cancel(int opId, CancelShopCarReq req) {
        SysShopCar shopCar = repository.findSysShopCarById(req.getId());
        if (req.getAction().getAction() == CommonActionEnum.CREATE.getAction()) {
            shopCar.setState(ProjectConstans.SHOP_STATE_UNVALID);
            shopCar.setUpdateTime(TimeUtils.getCurrentTime());
            repository.saveAndFlush(shopCar);
            logger.info("用户：【" + opId + "】的订单已经删除");
            return;
        }
        int proId = shopCar.getProId();
        SysProduct product = productRepository.findSysProductById(proId);
        product.setState(ProjectConstans.BUSI_STATUS_PAY);
        product.setUpdateTime(TimeUtils.getCurrentTime());
        productRepository.saveAndFlush(product);
        List<SysShopCar> carList = findAllShopCarByProId(req.getId());
        List<Integer> carIds = new ArrayList();
        String targetUser = "";
        for (SysShopCar sysShopCar : carList) {
            carIds.add(sysShopCar.getId());
        }
        mapper.saveToBatch(carIds, ProjectConstans.SHOP_STATE_BUY);
        logger.info("用户：【" + opId + "】的订单已经完成，并且通知其他用户");

    }

    /**
     * 通过商品id确定有多少购物订单
     *
     * @param proId
     * @return
     */
    @Override
    public List<SysShopCar> findAllShopCarByProId(int proId) {
        return repository.findAllByProId(proId);
    }

    /***
     * 获取当前用户下所有的购物车消息
     * @param userId
     * @return
     */
    @Override
    public List<ShopCarVO> findAllShopCarByUser(int userId , byte state) {
        return mapper.findAllShopCarByUserIdAndState(userId,state);
    }

    /**
     * @param userId
     * @param container
     * @return
     */
    @Override
    public PageResultContainer findAllShopCarByUser(int userId, PageRequestContainer<ShopCarVO> container) {
        ShopCarVO data = container.getData();
        Page<SysProduct> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(), "id desc");
        List<ShopCarVO> list = mapper.findAllShopCarByUserIdAndState(userId, data.getState());
        PageResultContainer result = new PageResultContainer();
        result.setEntities(list);
        result.setTotal(page.getTotal());
        return result;
    }
}
