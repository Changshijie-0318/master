package com.sxau.master.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.mapper.SysListMapper;
import com.sxau.master.dao.repostiory.SysOrderRepository;
import com.sxau.master.dao.repostiory.SysUserRepository;
import com.sxau.master.dao.repostiory.SysWantProductRepository;
import com.sxau.master.data.SysOrder;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.RootManagerSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.utils.ValidateUtil;
import com.sxau.master.vo.ListShowVo;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.vo.req.AddUserReq;
import com.sxau.master.vo.req.UpdateWantProReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-02-11 13:20
 * @Description:
 */
@Service
public class RootManagerSVImpl implements RootManagerSV {

    @Resource
    SysListMapper listMapper;
    @Resource
    SysUserRepository userRepository;
    @Resource
    SysWantProductRepository repository;
    @Resource
    SysOrderRepository orderRepository;
    /**
     * 获取所有的分类列表
     *
     * @return
     */
    @Override
    public List<ListShowVo> getAllListShow() {
        return listMapper.getAllList(ProjectConstans.STATE_UNVALID);
    }

    /**
     * 添加用户
     *
     * @param req
     */
    @Override
    public String addUser(AddUserReq req) {
    SysUser user = new SysUser();
        if (req.getId()==0) {
            String loginName = req.getLoginName();
            String password = req.getPassword();
            String email = req.getEmail();
            Assert4Demo.isTrue(userRepository.findSysUserByLoginNameAndState(loginName,ProjectConstans.USER_STATUS_PASS)==null,"用户名已存在");
            Assert4Demo.isTrue(ValidateUtil.check(ValidateUtil.CHECK_PASSWORD, password), "密码不符合规则");
            Assert4Demo.isTrue(ValidateUtil.checkMail(email), "邮箱格式不正确");
            Assert4Demo.isTrue(userRepository.findSysUserByEmailAndState(email,ProjectConstans.STATE_VALID)==null,"该邮箱已被注册");
            BeanUtils.copyProperties(req,user);
            user.setCreateTime(TimeUtils.getCurrentTime());
            user.setUpdateTime(TimeUtils.getCurrentTime());
            userRepository.save(user);
            return "添加用户成功";
        }
        BeanUtils.copyProperties(req,user);
        userRepository.saveAndFlush(user);
        return "修改信息成功";
    }

    /**
     * 分页获取所有的订单信息
     *
     * @param container
     * @return
     */
    @Override
    public PageResultContainer getAllOrderInfo(PageRequestContainer<SysOrderVO> container) {
        Page<SysOrderVO> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(),"id desc");
        List<SysOrderVO> list = listMapper.findAllByState(container.getData().getState());
        PageResultContainer result = new PageResultContainer();
        result.setEntities(list);
        result.setTotal(page.getTotal());
        return result;
    }

    @Override
    public void updateWantProduct(UpdateWantProReq req) {
    }

    /**
     * 功能描述: <获取每月的销售量>
     * @Param: []
     * @Return: java.lang.String
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:33
     */
    @Override
    public String getAllMonths() {
        List<SysOrder> orders = orderRepository.findAll();
        Map<String,Integer> result  = new HashMap();
        for (SysOrder order : orders) {
            long time = order.getCreateTime().getTime();
            DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);
            String mon = df.format(new Date(time));
            Integer value = result.get(mon);
            if (value!=null) value++;
            else value = 1;
            result.put(mon,value);
        }
        return JSONObject.toJSON(result).toString();
    }

    /**
     * 获取所有的用户信息
     *
     * @return
     */
    @Override
    public PageResultContainer getAllUserInfo(PageRequestContainer<SysUser> container) {
        Page<SysUser> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(),"id");
        List<SysUser> users = listMapper.findAll();
        PageResultContainer result = new PageResultContainer();
        result.setEntities(users);
        result.setTotal(page.getTotal());
        return result;

    }
}
