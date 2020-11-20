package com.sxau.master.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.mapper.SysOrderMapper;
import com.sxau.master.dao.mapper.SysShopCarMapper;
import com.sxau.master.dao.repostiory.*;
import com.sxau.master.data.*;
import com.sxau.master.data.meta.CommonActionEnum;
import com.sxau.master.data.meta.NoticeEnum;
import com.sxau.master.service.ISysOrderSV;
import com.sxau.master.service.ISysProductSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.vo.req.AddOrderReq;
import com.sxau.master.vo.req.updateOrderReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-31 09:04
 * @Description:
 */
@Service
public class ISysOrderSVImpl implements ISysOrderSV {
    private static Logger logger = LoggerFactory.getLogger(ISysOrderSVImpl.class);
    @Resource
    SysShopCarMapper shopCarMapper;
    @Resource
    SysOrderMapper orderMapper;
    @Resource
    ISysProductSV iSysProductSV;
    @Resource
    SysShopCarRepository shopCarRepository;
    @Resource
    SysOrderRepository orderRepository;
    @Resource
    SysNoticeRepository noticeRepository;
    @Resource
    SysUserNoticeRepository userNoticeRepository;
    @Resource
    SysProductRepository productRepository;
    /**
     * 生成订单
     *
     * @param user
     * @param req
     */
    @Override
    @Transactional
    public String save(SysUser user, AddOrderReq req) {
        String realName = user.getRealName();
        boolean realNameIsNull = realName != null && !realName.trim().equals("");
        String phone = user.getPhone();
        boolean phoneIsNull = phone != null && !phone.trim().equals("");
        String address = user.getAddress();
        boolean addressINull = address != null && !address.trim().equals("");
        Assert4Demo.isTrue(realNameIsNull&&phoneIsNull&&addressINull,"你的信息不全，请前去完善信息");
        synchronized (this) {
            List<SysShopCar> shopCars = shopCarMapper.findAllShopCarByIdsAndState(req.getCarIds(), ProjectConstans.STATE_UNVALID);
            Assert4Demo.isTrue(shopCars.size()<1,"请刷新页面，你所选的商品已被抢");
            List<SysShopCar> carList = shopCarMapper.findAllShopCarByIdsAndState(req.getCarIds(), ProjectConstans.STATE_VALID);
            List<SysOrder> orders = new ArrayList<>();
            List<Integer> proIds = new ArrayList<>();
            for (SysShopCar shopCar : carList) {
                Assert4Demo.isTrue(shopCar.getUserIdBuy()==user.getId(),"你无权操作不是你的购物车消息");
                SysOrder order = new SysOrder();
                order.setProId(shopCar.getProId());
                order.setState(ProjectConstans.STATE_VALID);
                order.setUserIdBuy(shopCar.getUserIdBuy());
                order.setUserIdSell(shopCar.getUserIdSell());
                order.setCreateTime(TimeUtils.getCurrentTime());
                order.setUpdateTime(TimeUtils.getCurrentTime());
                order.setAddress(user.getAddress());
                List<SysShopCar> cars = shopCarRepository.findAllByProIdAndState(shopCar.getProId(), ProjectConstans.STATE_VALID);
                if (cars != null && cars.size() > 0)
                    shopCarMapper.changeAllShopCarState(cars, ProjectConstans.STATE_UNVALID);
                orders.add(order);
                proIds.add(shopCar.getProId());
            }
            orderMapper.saveToBatch(orders);
            shopCarMapper.changeAllShopCarState(carList, ProjectConstans.BUSI_STATUS_PAY);
            iSysProductSV.changeProState(proIds, ProjectConstans.BUSI_STATUS_PAY);
            addNoticeToBusiness(carList,user);
            logger.info("订单已经生成");
            return "订单已经生成";
        }
    }

    /**
     * 生成通知消息通知卖家
     */
    private void addNoticeToBusiness(List<SysShopCar> carList,SysUser user){
        for (SysShopCar shopCar : carList) {
            SysNotice notice = new SysNotice();
            notice.setTitle("商品出售");
            SysProduct product = productRepository.findSysProductById(shopCar.getProId());
            notice.setContent("您出售的商品:"+product.getProName()+"已被"+user.getRealName()+"购买，请及时联系！买家电话为："+user.getPhone());
            notice.setState(ProjectConstans.STATE_VALID);
            notice.setCreateTime(TimeUtils.getCurrentTime());
            notice.setUpdateTime(TimeUtils.getCurrentTime());
            notice.setType(NoticeEnum.NOTICE_USER.getAction());
            notice.setSourceUserId(user.getId());
            notice.setTargetUser(shopCar.getUserIdSell()+"");
            noticeRepository.save(notice);
            SysUserNotice userNotice = userNoticeRepository.findSysUserNoticeByUserIdAndState(shopCar.getUserIdSell(), ProjectConstans.STATE_VALID);
            JSONObject jsonObject = JSONObject.parseObject(userNotice.getUndealNotice());
            List list = (List) jsonObject.get(ProjectConstans.UN_DEAL_NOTICE);
            list.add(notice.getId());
            jsonObject.put(ProjectConstans.UN_DEAL_NOTICE,list);
            userNotice.setUndealNotice(jsonObject.toJSONString());
            userNoticeRepository.saveAndFlush(userNotice);
        }
    }
    /**
     * 分页查询订单
     *
     * @param userId
     * @param container
     * @return
     */
    @Override
    public PageResultContainer findAllOrderByUser(int userId, PageRequestContainer<SysOrderVO> container) {
        SysOrderVO data = container.getData();
        Page<SysProduct> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(), "id desc");
        List<SysOrderVO> list = orderMapper.findAllByUserIdBuyAndState(userId,data.getState());
        PageResultContainer result = new PageResultContainer();
        result.setEntities(list);
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 修改订单
     *
     * @param req
     */
    @Override
    public void updateOrder(updateOrderReq req) {
        List ids = req.getIds();
        Integer action = req.getAction().getAction();
        if (action == CommonActionEnum.CREATE.getAction()){
            orderMapper.deleteOrdersState(ids,ProjectConstans.BUSI_STATUS_PAY);
        } else if (action == CommonActionEnum.EDIT.getAction()){
            orderMapper.updateOrderState(ids,req.getRate(),ProjectConstans.BUSI_STATUS_PAY);
        }
    }

    /**
     * 获取当前用户为完成的订单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> getAllUndelOrder(int userId) {
        List<Integer> list = orderMapper.getAllUndealOrder(userId, ProjectConstans.SHOP_STATE_BUY);
        return list;
    }
}
