package com.sxau.master.dao.mapper;

import com.sxau.master.data.SysOrder;
import com.sxau.master.vo.SysOrderVO;
import com.sxau.master.data.SysOrder;
import com.sxau.master.vo.SysOrderVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:guanjianjing
 * @CreateTime: 2020-09-10 18:10
 * @Description:
 */
public interface SysOrderMapper {

   void saveToBatch(@Param("list") List<SysOrder> list);

   List<SysOrderVO> findAllByUserIdBuyAndState(@Param("userId")int userId, @Param("state") byte state);

   void deleteOrdersState(@Param("list") List list ,@Param("state") byte state);

   void updateOrderState(@Param("list")List list , @Param("rate")BigDecimal rate ,@Param("state")byte state);

   List<Integer> getAllUndealOrder(@Param("userId")Integer userId,@Param("state")byte state);
}
