package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.service.ISysNoticeSV;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddNoticeReq;
import com.sxau.master.vo.req.ChangeNoticeReq;
import com.sxau.master.vo.req.GetNoticeReq;
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
 * @CreateTime: 2020-09-25 21:17
 * @Description:
 */
@Api("消息接口")
@RequestMapping("/notice")
@Controller
public class SysNoticeController extends BaseController{
    @Resource
    ISysNoticeSV iSysNoticeSV;
    @ApiOperation(value = "添加消息", notes = "添加消息")
    @ApiImplicitParam(name = "req", value = "生成订单", dataType = "AddNoticeReq", required = true, paramType = "body")
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addNotice(@Valid @RequestBody AddNoticeReq req) {
        iSysNoticeSV.save(req,super.getCurrentUser().getId());
        return new Result<>("添加消息成功");
    }
    @ApiOperation(value = "获取当前用户的通知消息", notes = "获取当前用户的通知消息")
    @ApiImplicitParam(name = "req", value = "获取当前用户的通知消息", dataType = " PageRequestContainer<GetNoticeReq>", required = true, paramType = "body")
    @RequestMapping(value = "/getAllNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result<PageResultContainer> getAllNotice(@Valid @RequestBody PageRequestContainer<GetNoticeReq> req) {
        return new Result<>(iSysNoticeSV.getAllNotices(req,super.getCurrentUser().getId()));
    }
    @ApiOperation(value = "获取未处理消息总数", notes = "获取未处理消息总数")
    @RequestMapping(value = "/getUnDealCount", method = RequestMethod.GET)
    @ResponseBody
    public Result<Integer> getUnDealCount() {
        return new Result<>(iSysNoticeSV.getAllUnDealNoticeCount(super.getCurrentUser().getId()));
    }

    @ApiOperation(value = "处理未处理的消息", notes = "处理未处理的消息")
    @ApiImplicitParam(name = "req", value = "处理未处理的消息", dataType = "ChangeNoticeReq", required = true, paramType = "body")
    @RequestMapping(value = "/changeNotice", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> changeNotice(@Valid @RequestBody ChangeNoticeReq req) {
        iSysNoticeSV.changeNoticeState(req,super.getCurrentUser().getId());
        return new Result<>("操作成功");
    }
}
