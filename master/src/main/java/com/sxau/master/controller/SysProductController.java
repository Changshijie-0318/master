package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.data.SysProduct;
import com.sxau.master.data.SysSort;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysProductSV;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddProductReq;
import com.sxau.master.vo.req.CancelProReq;
import com.sxau.master.vo.req.GetProInfoReq;
import com.sxau.master.vo.req.UpdateProReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 11:00
 * @Description:
 */
@Controller
@Api("商品接口")
@RequestMapping("/product")
public class SysProductController extends BaseController{
    @Resource
    ISysProductSV iSysProDuctSV;

    @ApiOperation(value = "添加商品", notes = "添加商品")
    @ApiImplicitParam(name = "req",value = "添加商品",dataType = "AddProductReq",required = true, paramType = "body")
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addProduct(@Valid @RequestBody AddProductReq req) {
        SysUser currentUser = super.getCurrentUser();
        iSysProDuctSV.save(currentUser.getId(),req);
        return new Result<>("添加商品成功");
    }
    @ApiOperation(value = "修改商品信息", notes = "修改商品信息")
    @ApiImplicitParam(name = "req",value = "修改商品信息",dataType = "UpdateProReq",required = true, paramType = "body")
    @RequestMapping(value = "/updateProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateProduct(@Valid @RequestBody UpdateProReq req) {
        SysUser currentUser = super.getCurrentUser();
        iSysProDuctSV.update(currentUser.getId(),req);
        return new Result<>("修改商品信息成功");
    }

    @ApiOperation(value = "注销商品信息", notes = "注销商品信息")
    @ApiImplicitParam(name = "req",value = "注销商品信息",dataType = "CancelProReq",required = true, paramType = "body")
    @RequestMapping(value = "/cancelProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> cancelProduct(@Valid @RequestBody CancelProReq req) {
        SysUser currentUser = super.getCurrentUser();
        iSysProDuctSV.cancel(currentUser.getId(),req);
        return new Result<>("所选商品已失效");
    }

    @ApiOperation(value = "获取当前用户所出售的商品", notes = "获取当前用户所出售的商品")
    @RequestMapping(value = "/getAllProductByUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<List> getAllProductByUser() {
        SysUser currentUser = super.getCurrentUser();
        return new Result<>(iSysProDuctSV.getAllProByUserId(currentUser.getId()));
    }

    @ApiOperation(value = "获取所出售的商品", notes = "获取所出售的商品")
    @ApiImplicitParam(name = "container",value = "获取所出售的商品",dataType = " PageRequestContainer<SysProduct>",required = true, paramType = "body")
    @PostMapping("/getAllProduct")
    @ResponseBody
    public Result<PageResultContainer> getAllProduct(@Valid @RequestBody PageRequestContainer<SysProduct> container) {
        return new Result<>(iSysProDuctSV.getAllProduct(container));
    }
    @ApiOperation(value = "分页获取当前用户所出售的商品", notes = "分页获取当前用户所出售的商品")
    @ApiImplicitParam(name = "container",value = "分页获取当前用户所出售的商品",dataType = " PageRequestContainer<SysProduct>",required = true, paramType = "body")
    @RequestMapping(value = "/getAllProductForUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> getAllProductForUser(@Valid @RequestBody PageRequestContainer<SysProduct> container) {
        SysUser currentUser = super.getCurrentUser();
        return new Result<>(iSysProDuctSV.getAllProductForUser(currentUser.getId(),container));
    }

    @ApiOperation(value = "获取指定商品详细信息", notes = "获取指定商品详细信息")
    @ApiImplicitParam(name = "req",value = "注销商品信息",dataType = "GetProInfoReq",required = true, paramType = "body")
    @RequestMapping(value = "/getProInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result<SysProduct> getProInfo(@Valid @RequestBody GetProInfoReq req) {
        return new Result<>(iSysProDuctSV.getProInfo(req.getProId()));
    }
    @ApiOperation(value = "获取所有分类", notes = "获取所有分类")
    @RequestMapping(value = "/getAllSort",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<SysSort>> getAllSort() {
        return new Result<>(iSysProDuctSV.findAll());
    }
}
