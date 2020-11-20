package com.sxau.master.common.permission;

import com.sxau.master.service.ISysUserSV;
import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysUserSV;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.common.permission
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-06 18:10
 * @Description:
 */
@Aspect
//作用是把当前类表示为一个切面供容器读取
@Component
public class PermissionAspect {
    private static Logger logger = LoggerFactory.getLogger(PermissionAspect.class);
    @Resource
    ISysUserSV iSysUserSV;

    @Pointcut("@annotation(com.sxau.master.common.permission.Permission)")
    //是植入Advice的触发条件
    public void check() {

    }

    @Before(value = "check()")
    //标识一个前置增强方法
    private void checkUserHasPer() {
        SysUser currentUser = iSysUserSV.getCurrentUser();
        logger.info(currentUser.getLoginName()+"用户正在访问root权限");
        Assert4Demo.isTrue(currentUser.getRole()== ProjectConstans.USER_ROLE_ROOT,"对不起你的权限不够");
    }

}
