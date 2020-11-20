package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.common.permission.Permission;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.RootManagerSV;
import com.sxau.master.vo.ListShowVo;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.vo.req.AddUserReq;
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
import java.util.Map;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 13:31
 * @Description:
 */
@Controller
@RequestMapping("/root")
@Api("管理员接口")
//标识这个类是Swagger资源
public class RootManagerController extends BaseController {
    @Resource
    RootManagerSV rootManagerSV;

    @ApiOperation(value = "获取所有列表", notes = "获取所有列表")
    //表示一个Http请求的操作
    @RequestMapping(value = "/getAllList",method = RequestMethod.POST)
    @ResponseBody
    //返回的对象不是html界面，而是某种格式（json，xml等）
    @Permission
    public Result<List<ListShowVo>> getAllList(){
        return new Result<>(rootManagerSV.getAllListShow());
    }

    @ApiOperation(value = "获取所有用户信息", notes = "获取所有列表")
    @ApiImplicitParam(name = "container", value = "获取所有用户信息", dataType = " PageRequestContainer<SysOrderVO>", required = true, paramType = "body")
    //标识单独的请求参数
    @RequestMapping(value = "/getAllUserInfo",method = RequestMethod.POST)
    @ResponseBody
    @Permission
    public Result<PageResultContainer> getAllUserInfo(@Valid @RequestBody PageRequestContainer<SysUser> container){
        return new Result<>(rootManagerSV.getAllUserInfo(container));
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "req", value = "获取所有用户信息", dataType = "AddUserReq", required = true, paramType = "body")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    @Permission
    public Result<String> addUser(@Valid @RequestBody AddUserReq req){
        return new Result<>(rootManagerSV.addUser(req));
    }

    @ApiOperation(value = "获取所有订单", notes = "获取所有订单")
    @ApiImplicitParam(name = "container", value = "获取所有订单", dataType = "PageRequestContainer<SysOrderVO>", required = true, paramType = "body")
    @RequestMapping(value = "/getAllOrderInfo",method = RequestMethod.POST)
    @ResponseBody
    @Permission
    public Result<PageResultContainer> getAllOrderInfo(@Valid @RequestBody PageRequestContainer<SysOrderVO> container){
        return new Result<>(rootManagerSV.getAllOrderInfo(container));
    }

    @ApiOperation(value = "获取所有订单", notes = "获取所有订单")
    @RequestMapping(value = "/getMonths",method = RequestMethod.POST)
    @ResponseBody
    @Permission
    public Result<String> getAllOrderInfo(){
        return new Result<>(rootManagerSV.getAllMonths());

    }


}
