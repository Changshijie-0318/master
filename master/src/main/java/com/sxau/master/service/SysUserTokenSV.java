package com.sxau.master.service;

import com.sxau.master.data.SysUserToken;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-17 16:49
 * @Description:
 */
public interface SysUserTokenSV {
    /**
     * 保存用户token信息
     * @param token
     */
    void save(SysUserToken token);

    /**
     * 通过id保存
     * @param userId
     * @param token
     */
    void saveTodb(int userId,String token);
}
