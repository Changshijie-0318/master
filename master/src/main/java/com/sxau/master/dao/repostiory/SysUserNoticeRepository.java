package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysUserNotice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-24 21:36
 * @Description:
 */
public interface SysUserNoticeRepository extends JpaRepository<SysUserNotice,Integer> {
    SysUserNotice findSysUserNoticeByUserIdAndState(int userId,byte state);
}
