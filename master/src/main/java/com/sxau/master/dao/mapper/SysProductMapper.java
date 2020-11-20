package com.sxau.master.dao.mapper;

import com.sxau.master.data.SysProduct;
import com.sxau.master.data.SysProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 11:13
 * @Description:
 */
@Mapper
public interface SysProductMapper {

    List<SysProduct> getAllProByUserIdAndState(@Param("userId") int userId, @Param("list") List states);

    List<SysProduct> getAllProduct(@Param("state") byte state,@Param("sortId")Integer sortId);

    List<SysProduct> getAllProductForUser(@Param("userId")int userId,@Param("state") byte state);

    void cancelSomePro(@Param("list") List ids, @Param("time")Timestamp updateTime, @Param("state") byte state);

    void batchToChangeProState(@Param("list")List<Integer> proIds,@Param("time")Timestamp updateTime, @Param("state") byte state);

}
