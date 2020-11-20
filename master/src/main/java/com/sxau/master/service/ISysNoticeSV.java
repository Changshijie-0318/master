package com.sxau.master.service;

import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.req.AddNoticeReq;
import com.sxau.master.vo.req.ChangeNoticeReq;
import com.sxau.master.vo.req.GetNoticeReq;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 16:58
 * @Description:
 */
public interface ISysNoticeSV {
    /**
     * 新建通知
     *
     * @param req
     * @param userId
     */
    void save(AddNoticeReq req, int userId);

    /**
     * 为用户添加消息通知
     *
     * @param userId
     */
    void addNoticeToUser(int userId);


    /**
     * 分页获取通知消息
     * @param container
     * @param userId
     * @return
     */
    PageResultContainer getAllNotices(PageRequestContainer<GetNoticeReq> container, int userId);

    /**
     * 获取当前用户未读取的信息个数
     * @param userId
     * @return
     */
    int getAllUnDealNoticeCount(int userId);

    /**
     * 处理为处理的消息
     * @param req
     * @param userId
     */
    void changeNoticeState(ChangeNoticeReq req,int userId);
}
