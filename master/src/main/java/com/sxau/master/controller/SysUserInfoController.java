package com.sxau.master.controller;

import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.Result;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysUserSV;
import com.sxau.master.vo.req.RegisterSysUserReq;
import com.sxau.master.vo.req.UpdateUserReq;
import com.sxau.master.vo.req.UserLoginReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@Api("用户接口")
@RequestMapping("/userInfo")
public class SysUserInfoController extends BaseController {
    @Resource
    ISysUserSV iSysUserSV;

    @ApiOperation(value = "注册用户", notes = "注册用户")
    @ApiImplicitParam(name = "req",value = "注册用户入参",dataType = "RegisterSysUserReq",required = true, paramType = "body")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> register(@Valid @RequestBody RegisterSysUserReq req) {
        iSysUserSV.registerUser(req);
        return new Result<>("注册成功");
    }
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParam(name = "req",value = "登陆信息入参",dataType = "UserLoginReq",required = true, paramType = "body")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<SysUser> login(@Valid @RequestBody UserLoginReq req, HttpServletRequest request, HttpServletResponse response) {
        SysUser login = iSysUserSV.login(req,response);
        request.getSession().setAttribute(ProjectConstans.SESSION_USER_INFO_KEY,login);
        return new Result<>(login);
    }
    @ApiOperation(value = "展示用户信息", notes = "展示用户信息")
    @RequestMapping(value = "/showUser",method = RequestMethod.POST)
    @ResponseBody
    public Result<SysUser> showUser() {
        return new Result(super.getCurrentUser());
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParam(name = "req",value = "用户信息入参",dataType = "UpdateUserReq",required = true, paramType = "body")
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateInfo(@Valid @RequestBody UpdateUserReq req, HttpServletRequest request) {
        SysUser sysUser = iSysUserSV.updateUser(req);
        request.getSession().setAttribute(ProjectConstans.SESSION_USER_INFO_KEY,sysUser);
        return new Result("修改成功");
    }

}

