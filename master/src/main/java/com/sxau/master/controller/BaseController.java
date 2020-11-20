package com.sxau.master.controller;

import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysUserSV;
import com.sxau.master.data.SysUser;
import com.sxau.master.service.ISysUserSV;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 14:07
 * @Description:
 */

public class BaseController {
    @Autowired
    ISysUserSV iUserInfoSV;
    public SysUser getCurrentUser(){
        return  iUserInfoSV.getCurrentUser();
    }

}
