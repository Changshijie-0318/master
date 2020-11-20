package com.sxau.master.dao.repostiory;

import com.sxau.master.data.SysShopCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @BelongsProject: master-java
 * @BelongsPackage: com.sxau.dao.repostiory
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 09:26
 * @Description:
 */
@Repository
public interface SysShopCarRepository extends JpaRepository<SysShopCar,Integer> {

    SysShopCar findSysShopCarByUserIdBuyAndProIdAndState(int userId,int proId,byte state);

    SysShopCar findSysShopCarById(int id);

    List<SysShopCar> findAllByProId(int proId);

    List<SysShopCar> findAllByProIdAndState(int proId,byte state);

    List<SysShopCar> findAllByUserIdBuyAndState(int userId,byte state);
}
