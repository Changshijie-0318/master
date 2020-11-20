package com.sxau.master.dao.mapper;

import com.sxau.master.data.SysShopCar;
import com.sxau.master.data.SysShopCar;
import com.sxau.master.vo.ShopCarVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 16:11
 * @Description:
 */
public interface SysShopCarMapper {

    void saveToBatch(@Param("cars") List<Integer> cars,@Param("state") byte state);

    List<ShopCarVO> findAllShopCarByUserIdAndState(@Param("userId") int userId , @Param("state") byte state);

    void changeAllShopCarStateByProId(@Param("list") List ids , @Param("state") byte state);

    void changeAllShopCarState(@Param("list")List<SysShopCar> list , @Param("state") byte state);

    List<SysShopCar> findAllShopCarByIdsAndState(@Param("list") List ids , @Param("state") byte state);


}
