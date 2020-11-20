package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysNotice;
import com.sxau.master.data.SysNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 10:39
 * @Description:
 */
@Repository
public interface SysNoticeRepository extends JpaRepository<SysNotice,Integer> {
    SysNotice findSysNoticeByTypeAndState(int type,byte state);
}
