package com.sxau.master.service;


import com.sxau.master.data.SysUser;
import com.sxau.master.vo.req.CancelUserReq;
import com.sxau.master.vo.req.RegisterSysUserReq;
import com.sxau.master.vo.req.UpdateUserReq;
import com.sxau.master.vo.req.UserLoginReq;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 16:21
 * @Description:
 */
public interface ISysUserSV {
    /**
     * 注册用户
     * @param req
     */
    void registerUser(RegisterSysUserReq req);

    /***
     * 注销用户
     * @param req
     */
    void cancelUser(CancelUserReq req);

    /**
     * 修改用户信息
     * @param req
     */
    SysUser updateUser(UpdateUserReq req);

    /**
     * 通过用户名判断用户是否存在
     * @param loginName
     * @return
     */
    SysUser getSysUser(String loginName);

    /**
     * 登录验证
     * @param req
     * @param response
     * @return
     */
    SysUser login(UserLoginReq req, HttpServletResponse response);

    /**
     * 保存当前用户
     * @param user
     */
    void saveCurrentUser(SysUser user);

    /**
     * 获取当前用户
     * @return
     */
    SysUser getCurrentUser();

    /**
     * 通过token登录用户
      * @param token
     * @param httpSession
     * @param httpServletResponse
     * @return SysUser
     */
    SysUser loginToken(String token, HttpSession httpSession, HttpServletResponse httpServletResponse);
}
