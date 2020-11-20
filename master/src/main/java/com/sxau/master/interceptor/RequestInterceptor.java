package com.sxau.master.interceptor;

import com.alibaba.fastjson.JSON;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.Result;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysUserSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component("requestInterceptor")
public class RequestInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Resource
    ISysUserSV iSysUserSV;

    private List<String> unInterceptUrls = new ArrayList<>();

    @PostConstruct
    private void init() {
        unInterceptUrls.add(contextPath + "/userInfo/login");
        unInterceptUrls.add(contextPath + "/userInfo/register");
        unInterceptUrls.add(contextPath + "/file/download-image");
        unInterceptUrls.add(contextPath + "/product/getAllProduct");
        unInterceptUrls.add(contextPath + "/product/getProInfo");
        unInterceptUrls.add(contextPath + "/product/getAllSort");
        unInterceptUrls.add(contextPath + "/want/getAllWantPro");
    }

    @Override
    //业务请求之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("拦截请求" + request.getRequestURI());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACES");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        if (unInterceptUrls.contains(request.getRequestURI())) {
            return true;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Object obj = request.getSession().getAttribute(ProjectConstans.SESSION_USER_INFO_KEY);
        try {
            String token = request.getHeader("Authorization");
            Cookie[] cookies = request.getCookies();
            if (obj == null && token != null) {
                logger.info("用户通过token自动登录");
                SysUser sysUser = iSysUserSV.loginToken(token, request.getSession(), response);
                if (!ObjectUtils.isEmpty(sysUser)) {
                    logger.info(sysUser.getRealName() + "通过token登录正在访问：" + request.getRequestURI() + "接口");
                    iSysUserSV.saveCurrentUser(sysUser);
                    return true;
                }
            }
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")){
//                    logger.info("用户通过cookie自动登录");
//                    SysUser sysUser = iSysUserSV.loginToken(cookie.getValue(), request.getSession(), response);
//                    logger.info(sysUser.getRealName() + "正在访问：" + request.getRequestURI() + "接口");
//                    this.iSysUserSV.saveCurrentUser(sysUser);
//                    return true;
//                }
//            }
            logger.info("用户通过session自动登录"+obj);
            logger.info(request.getSession().getId());
            SysUser sysUser = (SysUser) obj;
            logger.info(sysUser.getRealName() + "正在访问：" + request.getRequestURI() + "接口");
            this.iSysUserSV.saveCurrentUser(sysUser);
            return true;
        } catch (Exception e) {
            Result<String> resp = new Result<>();
            resp.setErrorCode(ResultEnum.NOT_LOGIN_ERROR.getErrorCode());
            resp.setResult(ResultEnum.NOT_LOGIN_ERROR.getErrorMsg());
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String respStr = JSON.toJSONString(resp);
            response.getWriter().print(respStr);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
