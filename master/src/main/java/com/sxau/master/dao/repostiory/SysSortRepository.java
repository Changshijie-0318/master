package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysSort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 16:57
 * @Description:
 */
public interface SysSortRepository extends JpaRepository<SysSort,Integer> {

    List<SysSort> findAll();
}
