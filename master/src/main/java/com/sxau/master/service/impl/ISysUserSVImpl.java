package com.sxau.master.service.impl;

import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.dao.repostiory.SysUserRepository;
import com.sxau.master.dao.repostiory.SysUserTokenRepository;
import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysUserToken;
import com.sxau.master.service.ISysFileSv;
import com.sxau.master.service.ISysNoticeSV;
import com.sxau.master.service.ISysUserSV;
import com.sxau.master.service.SysUserTokenSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.utils.TokenUtil;
import com.sxau.master.utils.ValidateUtil;
import com.sxau.master.vo.req.*;
import com.sxau.master.vo.token.TokenValidResultVO;
import com.sxau.master.vo.token.UserTokenInfoVO;
import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.dao.repostiory.SysUserRepository;
import com.sxau.master.dao.repostiory.SysUserTokenRepository;
import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysUserToken;
import com.sxau.master.service.ISysFileSv;
import com.sxau.master.service.ISysNoticeSV;
import com.sxau.master.service.ISysUserSV;
import com.sxau.master.service.SysUserTokenSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.utils.TokenUtil;
import com.sxau.master.utils.ValidateUtil;
import com.sxau.master.vo.token.TokenValidResultVO;
import com.sxau.master.vo.token.UserTokenInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.briup.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-09 16:21
 * @Description:
 */
@Service
public class ISysUserSVImpl implements ISysUserSV {
    private static final Logger logger = LoggerFactory.getLogger(ISysUserSVImpl.class);
    private static final ThreadLocal<SysUser> currentUserLocal = new ThreadLocal<SysUser>();
    @Resource
    ISysNoticeSV iSysNoticeSV;
    @Resource
    SysUserRepository userRepository;
    @Autowired
    TokenUtil tokenUtil;
    @Resource
    SysUserTokenRepository tokenRepository;
    @Resource
    SysUserTokenSV sysUserTokenSV;
    @Resource
    ISysFileSv iSysFileSv;

    public static final String COOKIE_TOKEN = "token";

    /**
     * 注册用户
     *
     * @param req
     */
    @Override
    @Transactional
    public void registerUser(RegisterSysUserReq req) {
        String loginName = req.getLoginName();
        String password = req.getPassword();
        String email = req.getEmail();
        Assert4Demo.isTrue(getSysUser(loginName) == null, "用户名已存在");
        Assert4Demo.isTrue(ValidateUtil.check(ValidateUtil.CHECK_PASSWORD, password), "密码不符合规则");
        Assert4Demo.isTrue(ValidateUtil.checkMail(email), "邮箱格式不正确");
        Assert4Demo.isTrue(userRepository.findSysUserByEmailAndState(email, ProjectConstans.STATE_VALID) == null, "该邮箱已被注册");
        SysUser user = new SysUser();
        user.setEmail(email);
        user.setLoginName(loginName);
        user.setPassword(password);
        user.setRole(ProjectConstans.USER_ROLE_USER);
        user.setState(ProjectConstans.USER_ROLE_USER);
        user.setUpdateTime(TimeUtils.getCurrentTime());
        user.setCreateTime(TimeUtils.getCurrentTime());
        System.out.println(user);
        userRepository.save(user);
        logger.info(user.getLoginName() + "：用户注册成功");
        iSysNoticeSV.addNoticeToUser(user.getId());
        logger.info(user.getLoginName() + "用户消息通知成功");
    }

    /***
     * 注销用户
     * @param req
     */
    @Override
    @Transactional
    public void cancelUser(CancelUserReq req) {
        SysUser user = userRepository.findSysUserByIdAndState(req.getId(), ProjectConstans.USER_STATUS_PASS);
        user.setState(ProjectConstans.USER_STATUS_PASS);
        userRepository.saveAndFlush(user);
        logger.info(user.getId() + "：用户注销成功");
    }

    /**
     * 修改用户信息
     *
     * @param req
     */
    @Override
    @Transactional
    public SysUser updateUser(UpdateUserReq req) {
        SysUser user = userRepository.findSysUserByIdAndState(req.getId(), ProjectConstans.STATE_VALID);
        if (user.getImgUrl() != null && user.getImgUrl() != req.getImgUrl()) {
            DeleteImageReq imageReq = new DeleteImageReq();
            imageReq.setId(user.getImgUrl());
            iSysFileSv.deleteImage(imageReq);
        }
        BeanUtils.copyProperties(req, user);
        SysUser sysUser = userRepository.saveAndFlush(user);
        logger.info(user.getId() + "：用户修改信息成功");
        return sysUser;
    }

    /**
     * 通过用户名判断用户是否存在
     *
     * @param loginName
     * @return
     */
    @Override
    public SysUser getSysUser(String loginName) {
        return userRepository.findSysUserByLoginNameAndState(loginName, ProjectConstans.USER_STATUS_PASS);
    }

    /**
     * 登录验证
     *
     * @param req
     * @param response
     * @return
     */
    @Override
    public SysUser login(UserLoginReq req, HttpServletResponse response) {
        String loginName = req.getLoginName();
        String password = req.getPassword();
        SysUser sysUser = getSysUser(loginName);
        Assert4Demo.isTrue(sysUser != null, "该用户不存在");
        Assert4Demo.isTrue(password.equals(sysUser.getPassword()), "对不起，密码不正确");
        saveCurrentUser(sysUser);
        String token = setCookieToUserResponse(sysUser.getLoginName(), sysUser.getId(), response);
        sysUserTokenSV.saveTodb(sysUser.getId(), token);
        return sysUser;
    }

    /**
     * 设置cookie到response中
     *
     * @param loginName           用户名称
     * @param userId              用户主键
     * @param httpServletResponse 用户response
     * @return 用户token
     */
    public String setCookieToUserResponse(String loginName, int userId, HttpServletResponse httpServletResponse) {
        UserTokenInfoVO userTokenInfoVO = new UserTokenInfoVO();
        userTokenInfoVO.setUserName(loginName);
        userTokenInfoVO.setUserId(userId);
        String token = tokenUtil.createToken(userTokenInfoVO);
        Cookie cookie = new Cookie(COOKIE_TOKEN, token);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return token;
    }

    /**
     * 保存当前用户
     *
     * @param user
     */
    @Override
    public void saveCurrentUser(SysUser user) {
        currentUserLocal.set(user);
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    @Override
    public SysUser getCurrentUser() {
        return currentUserLocal.get();
    }

    /**
     * 通过token登录用户
     *
     * @param token
     * @param httpSession
     * @param httpServletResponse
     * @return SysUser
     */
    @Override
    public SysUser loginToken(String token, HttpSession httpSession, HttpServletResponse httpServletResponse) {
        TokenValidResultVO<UserTokenInfoVO> payload = tokenUtil.valid(token, UserTokenInfoVO.class);
        switch (payload.getState()) {
            case TokenUtil.PASS:
                UserTokenInfoVO userTokenInfoVO = payload.getData();
                //查看数据库中是否存在token
                SysUser sysUser = validUser(userTokenInfoVO.getUserId());
                httpSession.setAttribute(ProjectConstans.SESSION_USER_INFO_KEY, sysUser);
                return sysUser;
            case TokenUtil.FAIL:
                throw new ErrorException(ResultEnum.PARAM_ERROR, "token验证失败");
            case TokenUtil.EXPIRE:
                throw new ErrorException(ResultEnum.PARAM_ERROR, "token失效");
            default:
                throw new ErrorException(ResultEnum.PARAM_ERROR, "系统未知错误");
        }
    }

    /**
     * @param userId
     * @return
     */
    private SysUser validUser(int userId) {
        SysUserToken userToken = tokenRepository.findSysUserTokenByUserIdAndStatus(userId, ProjectConstans.STATE_VALID);
        if (ObjectUtils.isEmpty(userToken)) throw new ErrorException(ResultEnum.PARAM_ERROR, "用户不存在");
        SysUser user = userRepository.findSysUserByIdAndState(userToken.getUserId(), ProjectConstans.STATE_VALID);
        if (ObjectUtils.isEmpty(userToken)) throw new ErrorException(ResultEnum.PARAM_ERROR, "用户不存在");
        return user;
    }

}
