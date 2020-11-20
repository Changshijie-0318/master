package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysWantProduct;
import com.sxau.master.service.ISysWantProductSV;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddWantProReq;
import com.sxau.master.vo.req.CancelWantProReq;
import com.sxau.master.vo.req.UpdateWantProReq;
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


/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 17:22
 * @Description:
 */
@Api("求购接口")
@RequestMapping("/want")
@Controller
public class SysWantProductController extends BaseController {
    @Resource
    ISysWantProductSV wantProductSV;
    @ApiOperation(value = "添加求购信息", notes = "添加求购信息")
    @ApiImplicitParam(name = "req",value = "添加求购信息",dataType = "AddWantProReq",required = true, paramType = "body")
    @RequestMapping(value = "/addWantProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addWantProduct(@Valid @RequestBody AddWantProReq req) {
        SysUser currentUser = super.getCurrentUser();
        req.setUserId(currentUser.getId());
        wantProductSV.save(req);
        return new Result<>("添加求购信息成功");
    }
    @ApiOperation(value = "修改求购信息", notes = "修改求购信息")
    @ApiImplicitParam(name = "req",value = "修改求购信息",dataType = "UpdateWantProReq",required = true, paramType = "body")
    @RequestMapping(value = "/updateWantProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateWantProduct(@Valid @RequestBody UpdateWantProReq req) {
        wantProductSV.update(req);
        return new Result<>("修改求购信息成功");
    }
    @ApiOperation(value = "删除求购信息", notes = "删除求购信息")
    @ApiImplicitParam(name = "req",value = "删除求购信息",dataType = "UpdateWantProReq",required = true, paramType = "body")
    @RequestMapping(value = "/cancelWantProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> cancelWantProduct(@Valid @RequestBody CancelWantProReq req) {
        wantProductSV.cancel(req);
        return new Result<>("删除求购信息");
    }

    @ApiOperation(value = "分页获取求购信息", notes = "分页获取求购信息")
    @ApiImplicitParam(name = "req",value = "分页获取求购信息",dataType = "PageRequestContainer<SysWantProduct>",required = true, paramType = "body")
    @RequestMapping(value = "/getAllWantPro",method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> getAllWantPro(@Valid @RequestBody PageRequestContainer<SysWantProduct> req) {
        return new Result<>(wantProductSV.getAllWantPro(req));
    }

    @ApiOperation(value = "分页获取当前用户求购信息", notes = "分页获取当前用户求购信息")
    @ApiImplicitParam(name = "req",value = "分页获取求购信息",dataType = "PageRequestContainer<SysWantProduct>",required = true, paramType = "body")
    @RequestMapping(value = "/getAllWantProByUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> getAllWantProByUser(@Valid @RequestBody PageRequestContainer<SysWantProduct> req) {
        req.getData().setUseId(super.getCurrentUser().getId());
        return new Result<>(wantProductSV.getAllWantPro(req));
    }
}
