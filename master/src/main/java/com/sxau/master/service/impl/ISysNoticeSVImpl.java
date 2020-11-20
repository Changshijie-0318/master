package com.sxau.master.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.dao.mapper.SysNoticeMapper;
import com.sxau.master.dao.repostiory.SysNoticeRepository;
import com.sxau.master.dao.repostiory.SysUserNoticeRepository;
import com.sxau.master.dao.repostiory.SysUserRepository;
import com.sxau.master.data.SysNotice;
import com.sxau.master.data.SysUser;
import com.sxau.master.data.SysUserNotice;
import com.sxau.master.data.meta.NoticeEnum;
import com.sxau.master.data.meta.NoticeReqEnum;
import com.sxau.master.service.ISysNoticeSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysNoticeVO;
import com.sxau.master.vo.req.AddNoticeReq;
import com.sxau.master.vo.req.ChangeNoticeReq;
import com.sxau.master.vo.req.GetNoticeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020/9/25 20:10
 * @Description:
 */
@Service
public class ISysNoticeSVImpl implements ISysNoticeSV {
    private static Logger logger = LoggerFactory.getLogger(ISysProductSVImpl.class);
    @Resource
    SysNoticeRepository noticeRepository;
    @Resource
    SysUserRepository userRepository;
    @Resource
    SysUserNoticeRepository userNoticeRepository;
    @Resource
    SysNoticeMapper sysNoticeMapper;

    /**
     * 功能描述: <接收消息>
     * @Param: [req, userId]
     * @Return: void
     * @Author: Shijie Chang
     * @Date: 2020/9/25 20:11
     */
    @Override
    @Transactional
    public void save(AddNoticeReq req, int userId) {
        SysNotice notice = new SysNotice();
        notice.setTitle(req.getTitle());
        notice.setContent(req.getDesc());
        Integer action = req.getType().getAction();
        notice.setType(action);
        notice.setCreateTime(TimeUtils.getCurrentTime());
        notice.setUpdateTime(TimeUtils.getCurrentTime());
        notice.setState(ProjectConstans.STATE_VALID);
        if (action== NoticeEnum.NOTICE_USER.getAction()){
            notice.setTargetUser(req.getTargetUser());
            notice.setSourceUserId(userId);
            noticeRepository.save(notice);
        }else {
            noticeRepository.save(notice);
            List<SysUserNotice> userNotices = userNoticeRepository.findAll();
            List<SysUserNotice> newList = new ArrayList<>();
            for (SysUserNotice userNotice : userNotices) {
                JSONObject unDealList = JSONObject.parseObject(userNotice.getUndealNotice());
                List list = (List) unDealList.get(ProjectConstans.UN_DEAL_NOTICE);
                list.add(notice.getId());
                unDealList.put(ProjectConstans.UN_DEAL_NOTICE,list);
                userNotice.setUndealNotice(unDealList.toJSONString());
                newList.add(userNotice);
            }
            userNoticeRepository.saveAll(newList);
        }

    }

    /**
     * 功能描述: <为用户添加消息通知>
     * @Param: [userId]
     * @Return: void
     * @Author: Shijie Chang
     * @Date: 2020/9/25 20:12
     */
    @Override
    public void addNoticeToUser(int userId) {
        SysUser user = userRepository.findSysUserByIdAndState(userId, ProjectConstans.STATE_VALID);
        if (user==null) throw new ErrorException(ResultEnum.SYSTEM_COMMON_ERROR,"该用户不存在");
        SysUserNotice userNotice = new SysUserNotice();
        userNotice.setUserId(userId);
        JSONObject deal = new JSONObject();
        List<Integer> dealList = new ArrayList<>();
        deal.put(ProjectConstans.DEAL_NOTICE,dealList);
        userNotice.setDealNotice(deal.toJSONString());
        SysNotice notice = noticeRepository.findSysNoticeByTypeAndState(NoticeEnum.NOTICE_ALL.getAction(), ProjectConstans.STATE_VALID);
        JSONObject undeal = new JSONObject();
        List<Integer> list = new ArrayList<>();
        list.add(notice.getId());
        undeal.put(ProjectConstans.UN_DEAL_NOTICE,list);
        userNotice.setUndealNotice(undeal.toJSONString());
        userNotice.setCreateTime(TimeUtils.getCurrentTime());
        userNotice.setUpdateTime(TimeUtils.getCurrentTime());
        userNotice.setState(ProjectConstans.STATE_VALID);
        userNoticeRepository.save(userNotice);
        logger.info("用户："+user.getRealName()+" 加入消息通知的行列");
    }

    /**
     * 功能描述: <分页获取通知消息>
     * @Param: [container, userId]
     * @Return: com.sxau.master.vo.PageResultContainer
     * @Author: Shijie Chang
     * @Date: 2020/9/25 20:13
     */
    @Override
    public PageResultContainer getAllNotices(PageRequestContainer<GetNoticeReq> container, int userId) {
        Page<SysNoticeVO> page = PageHelper.startPage(container.getCurrentPage(), container.getPageSize(), "id desc");
        NoticeReqEnum type = container.getData().getType();
        SysUserNotice userNotice = userNoticeRepository.findSysUserNoticeByUserIdAndState(userId, ProjectConstans.STATE_VALID);
        String notice = userNotice.getUndealNotice();
        if (type.getAction()== NoticeReqEnum.DEAL_NOTICE.getAction()) notice= userNotice.getDealNotice();
        JSONObject jsonObject = JSONObject.parseObject(notice);
        List<Integer> list = (List) jsonObject.get(type.getDescription());
        if (list.size()<1)return new PageResultContainer();
        List<SysNoticeVO> notices = sysNoticeMapper.getAllNotices(list);
        PageResultContainer result = new PageResultContainer();
        result.setTotal(page.getTotal());
        result.setEntities(notices);
        return result;
    }

    /**
     * 功能描述: <获取当前用户未读取的信息个数>
     * @Param: [userId]
     * @Return: int
     * @Author: Shijie Chang
     * @Date: 2020/9/25 20:14
     */
    @Override
    public int getAllUnDealNoticeCount(int userId) {
        SysUserNotice notice = userNoticeRepository.findSysUserNoticeByUserIdAndState(userId, ProjectConstans.STATE_VALID);
        JSONObject jsonObject = JSONObject.parseObject(notice.getUndealNotice());
        List list = (List) jsonObject.get(ProjectConstans.UN_DEAL_NOTICE);
        return list.size();
    }

    /**
     * 功能描述: <处理为处理的消息>
     * @Param: [req, userId]
     * @Return: void
     * @Author: Shijie Chang
     * @Date: 2020/9/25 20:14
     */
    @Override
    public void changeNoticeState(ChangeNoticeReq req, int userId) {
        SysUserNotice notice = userNoticeRepository.findSysUserNoticeByUserIdAndState(userId, ProjectConstans.STATE_VALID);
        JSONObject jsonObject = JSONObject.parseObject(notice.getUndealNotice());
        List list = (List) jsonObject.get(ProjectConstans.UN_DEAL_NOTICE);
        list.removeAll(req.getIds());
        JSONObject undeal = new JSONObject();
        undeal.put(ProjectConstans.UN_DEAL_NOTICE,list);
        notice.setUndealNotice(undeal.toJSONString());
        List deals = (List) JSONObject.parseObject(notice.getDealNotice()).get(ProjectConstans.DEAL_NOTICE);
        deals.addAll(req.getIds());
        JSONObject deal = new JSONObject();
        deal.put(ProjectConstans.DEAL_NOTICE,deals);
        notice.setDealNotice(deal.toJSONString());
        notice.setCreateTime(TimeUtils.getCurrentTime());
        userNoticeRepository.saveAndFlush(notice);
    }


}
