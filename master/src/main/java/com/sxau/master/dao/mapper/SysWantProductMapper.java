package com.sxau.master.dao.mapper;

import com.sxau.master.data.SysWantProduct;
import com.sxau.master.vo.SysWantProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:guanjianjing
 * @CreateTime: 2020-09-19 15:35
 * @Description:
 */

public interface SysWantProductMapper {

    List<SysWantProductVO> findAllByState(@Param("state") byte state, @Param("userId")Integer userId);
}
