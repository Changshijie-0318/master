package com.sxau.master.dao.mapper;

import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysUser;
import com.sxau.master.vo.ListShowVo;
import com.sxau.master.vo.SysOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-11 13:24
 * @Description:
 */
public interface SysListMapper {

    List<ListShowVo> getAllList(int fId);

    List<SysUser> findAll();

    List<SysOrderVO> findAllByState( @Param("state") byte state);

}
