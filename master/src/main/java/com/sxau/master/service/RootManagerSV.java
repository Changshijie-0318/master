package com.sxau.master.service;

import com.alibaba.fastjson.JSONObject;
import com.sxau.master.data.SysUser;
import com.sxau.master.vo.ListShowVo;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.vo.req.AddUserReq;
import com.sxau.master.vo.req.UpdateWantProReq;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 13:20
 * @Description:
 */
public interface RootManagerSV {
    /**
     * 功能描述: <获取所有的分类列表>
     * @Param: []
     * @Return: java.util.List<com.sxau.master.vo.ListShowVo>
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:35
     */
    List<ListShowVo> getAllListShow();

    /**
     * 功能描述: <获取所有的用户信息>
     * @Param: [container]
     * @Return: com.sxau.master.vo.PageResultContainer
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:36
     */
    PageResultContainer getAllUserInfo(PageRequestContainer<SysUser> container);

    /**
     * 功能描述: <添加用户>
     * @Param: [req]
     * @Return: java.lang.String
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:36
     */
    String addUser(AddUserReq req);

    /**
     * 功能描述: <分页获取所有的订单信息>
     * @Param: [container]
     * @Return: com.sxau.master.vo.PageResultContainer
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:36
     */
    PageResultContainer getAllOrderInfo(PageRequestContainer<SysOrderVO> container);

    /**
     * 功能描述: <修改求购商品>
     * @Param: [req]
     * @Return: void
     * @Author: Shijie Chang
     * @Date: 2020/9/27 9:36
     */
    void updateWantProduct(UpdateWantProReq req);

    /**
     * 功能描述: <获取每月的销售量>
     * @Param: []
     * @Return: com.alibaba.fastjson.JSONObject
     * @Author: Shijie Chang
     * @Date: 2020/9/26 11:56
     */
    String getAllMonths();
}
