package com.sxau.master.service.impl;

import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.repostiory.SysUserTokenRepository;
import com.sxau.master.data.SysUserToken;
import com.sxau.master.service.SysUserTokenSV;
import com.sxau.master.utils.TimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-17 16:50
 * @Description:
 */
@Service
public class SysUserTokenSVImpl implements SysUserTokenSV {

    @Resource
    SysUserTokenRepository repository;
    /**
     * 保存用户token信息
     *
     * @param token
     */
    @Override
    public void save(SysUserToken token) {
        repository.save(token);
    }

    /**
     * 通过id保存
     *
     * @param userId
     * @param token
     */
    @Override
    @Transactional
    public void saveTodb(int userId,String token) {
        SysUserToken status = repository.findSysUserTokenByUserIdAndStatus(userId, ProjectConstans.STATE_VALID);
        if(status==null){
            status = new SysUserToken();
            status.setCreateTime(TimeUtils.getCurrentTime());
            status.setStatus(ProjectConstans.STATE_VALID);
        }
        status.setUpdateTime(TimeUtils.getCurrentTime());
        status.setValue(token);
        status.setUserId(userId);
        repository.saveAndFlush(status);
    }
}
