package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysWantProduct;
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
public interface SysWantProductRepository extends JpaRepository<SysWantProduct,Integer> {

    SysWantProduct findSysWantProductByIdAndState(int id , byte state);
}