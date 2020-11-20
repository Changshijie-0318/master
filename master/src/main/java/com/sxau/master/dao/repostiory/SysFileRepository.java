package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysFile;
import com.sxau.master.data.SysFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 10:38
 * @Description:
 */
@Repository
public interface SysFileRepository extends JpaRepository<SysFile, Integer> {
    SysFile findSysFileById(int id);
}
