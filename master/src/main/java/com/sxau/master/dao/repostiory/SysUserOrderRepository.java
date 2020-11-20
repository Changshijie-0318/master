package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysUserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 10:40
 * @Description:
 */
@Repository
public interface SysUserOrderRepository extends JpaRepository<SysUserOrder,Integer> {

}