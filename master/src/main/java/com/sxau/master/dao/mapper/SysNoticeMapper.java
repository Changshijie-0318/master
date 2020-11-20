package com.sxau.master.dao.mapper;

import com.sxau.master.vo.SysNoticeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.dao.mapper
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-25 22:00
 * @Description:
 */
public interface SysNoticeMapper {
    List<SysNoticeVO> getAllNotices(@Param("list")List<Integer> list);
}
