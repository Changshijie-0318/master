package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.service.ISysShopCarSV;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.ShopCarVO;
import com.sxau.master.vo.req.AddShopCarReq;
import com.sxau.master.vo.req.CancelShopCarReq;
import com.sxau.master.vo.req.FindAllShopCarReq;
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
 * @CreateTime: 2020-09-10 12:56
 * @Description:
 */
@Controller
@Api("购物车接口")
@RequestMapping("/shop")
public class SysShopCarController extends BaseController{
    @Resource
    ISysShopCarSV shopCarSV;

    @ApiOperation(value = "生成购物车消息", notes = "生成购物车消息")
    @ApiImplicitParam(name = "req",value = "生成购物车消息",dataType = "AddShopCarReq",required = true, paramType = "body")
    @RequestMapping(value = "/addShopCar",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addShopCar(@Valid @RequestBody AddShopCarReq req) {
        int id = super.getCurrentUser().getId();
        shopCarSV.save(id,req);
        return new Result<>("添加成功");
    }

    @ApiOperation(value = "操作订单", notes = "操作订单")
    @ApiImplicitParam(name = "req",value = "操作订单",dataType = "CancelShopCarReq",required = true, paramType = "body")
    @RequestMapping(value = "/cancelCar",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> cancelCar(@Valid @RequestBody CancelShopCarReq req) {
        int id = super.getCurrentUser().getId();
        shopCarSV.cancel(id,req);
        return new Result<>("操作成功");
    }

    @ApiOperation(value = "查看当前用户的购物车消息", notes = "查看当前用户的购物车消息")
    @ApiImplicitParam(name = "req",value = "查看当前用户的购物车消息",dataType = "FindAllShopCarReq",required = true, paramType = "body")
    @RequestMapping(value = "/getAllShopCarByUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<ShopCarVO>> findAllShopCarByUser(@Valid @RequestBody FindAllShopCarReq req) {
        int id = super.getCurrentUser().getId();
        return new Result<>(shopCarSV.findAllShopCarByUser(id,req.getState()));
    }

    @ApiOperation(value = "查看当前用户的购物车消息", notes = "查看当前用户的购物车消息")
    @ApiImplicitParam(name = "req",value = "查看当前用户的购物车消息",dataType = " PageRequestContainer<ShopCarVO>",required = true, paramType = "body")
    @RequestMapping(value = "/findAllShopCarByUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> findAllShopCarByUser(@Valid @RequestBody PageRequestContainer<ShopCarVO> req) {
        int id = super.getCurrentUser().getId();
        return new Result<>(shopCarSV.findAllShopCarByUser(id,req));
    }
}
