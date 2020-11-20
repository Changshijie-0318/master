package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysOrderSV;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddOrderReq;
import com.sxau.master.vo.req.updateOrderReq;
import com.sxau.master.vo.SysOrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-10-10 18:20
 * @Description:
 */
@Api("订单接口")
@RequestMapping("/order")
@Controller
public class SysOrderController extends BaseController {
    @Resource
    ISysOrderSV iSysOrderSV;

    @ApiOperation(value = "生成订单", notes = "生成订单")
    @ApiImplicitParam(name = "req", value = "生成订单", dataType = "AddOrderReq", required = true, paramType = "body")
    @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addOrder(@Valid @RequestBody AddOrderReq req) {
        return new Result<>(iSysOrderSV.save(super.getCurrentUser(), req));
    }

    @ApiOperation(value = "分页获取当前用户订单", notes = "分页获取当前用户订单")
    @ApiImplicitParam(name = "container", value = "分页获取当前用户订单", dataType = " PageRequestContainer<SysOrderVO>", required = true, paramType = "body")
    @RequestMapping(value = "/findAllOrderByUser", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> findAllOrderByUser(@Valid @RequestBody PageRequestContainer<SysOrderVO> container) {
        SysUser currentUser = super.getCurrentUser();
        return new Result<>(iSysOrderSV.findAllOrderByUser(currentUser.getId(), container));
    }

    @ApiOperation(value = "修改订单信息", notes = "修改订单信息")
    @ApiImplicitParam(name = "req", value = "修改订单信息", dataType = "updateOrderReq", required = true, paramType = "body")
    @RequestMapping(value = "/editOrders", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> editOrders(@Valid @RequestBody updateOrderReq req) {
        SysUser currentUser = super.getCurrentUser();
        iSysOrderSV.updateOrder(req);
        return new Result<>("修改成功");
    }
    @ApiOperation(value = "获取未处理的订单", notes = "获取未处理的订单")
    @RequestMapping(value = "/getAllUnDealOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Integer>> getAllUnDealOrder() {
        SysUser currentUser = super.getCurrentUser();
        return new Result<>(iSysOrderSV.getAllUndelOrder(currentUser.getId()));
    }
}
