package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 10:39
 * @Description:
 */
@Repository
public interface SysOrderRepository extends JpaRepository<SysOrder, Integer> {

    List<SysOrder> findAllByUserIdBuyAndState(int userId, byte state);

}
