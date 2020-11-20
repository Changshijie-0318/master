package com.sxau.master.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.dao.mapper.SysWantProductMapper;
import com.sxau.master.dao.repostiory.SysWantProductRepository;
import com.sxau.master.data.SysWantProduct;
import com.sxau.master.service.ISysWantProductSV;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.PageRequestContainer;
import com.sxau.master.vo.PageResultContainer;
import com.sxau.master.vo.SysWantProductVO;
import com.sxau.master.vo.req.AddWantProReq;
import com.sxau.master.vo.req.CancelWantProReq;
import com.sxau.master.vo.req.UpdateWantProReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-01-10 17:10
 * @Description:
 */
@Service
public class ISysWantProductSVImpl implements ISysWantProductSV {
    private static Logger logger = LoggerFactory.getLogger(ISysFileSVImpl.class);
    @Resource
    SysWantProductRepository repository;
    @Resource
    SysWantProductMapper productMapper;
    /**
     * 生成求购信息
     * @param req
     */
    @Override
    public void save(AddWantProReq req) {
        SysWantProduct wantProduct = new SysWantProduct();
        BeanUtils.copyProperties(req,wantProduct);
        wantProduct.setUseId(req.getUserId());
        wantProduct.setState(ProjectConstans.WANT_STATE_VALID);
        wantProduct.setCreateTime(TimeUtils.getCurrentTime());
        wantProduct.setUpdateTime(TimeUtils.getCurrentTime());
        repository.save(wantProduct);
        logger.info("添加求购信息成功");
    }

    /**
     * 修改求购信息
     *
     * @param req
     */
    @Override
    public void update(UpdateWantProReq req) {
        SysWantProduct wantProduct = repository.findSysWantProductByIdAndState(req.getId(), ProjectConstans.WANT_STATE_VALID);
        BeanUtils.copyProperties(req,wantProduct);
        wantProduct.setState(ProjectConstans.WANT_STATE_VALID);
        wantProduct.setUpdateTime(TimeUtils.getCurrentTime());
        repository.saveAndFlush(wantProduct);
        logger.info("修改求购信息成功");
    }

    /**
     * 删除求购信息
     *
     * @param req
     */
    @Override
    public void cancel(CancelWantProReq req) {
        SysWantProduct wantProduct = repository.findSysWantProductByIdAndState(req.getId(), ProjectConstans.WANT_STATE_VALID);
        wantProduct.setState(ProjectConstans.WANT_STATE_UNVALID);
        repository.saveAndFlush(wantProduct);
        logger.info("删除求购信息成功");
    }

    /**
     * 分页获取所有求购信息
     *
     * @param requestContainer
     * @return
     */
    @Override
    public PageResultContainer getAllWantPro(PageRequestContainer<SysWantProduct> requestContainer) {
        Page<Object> page = PageHelper.startPage(requestContainer.getCurrentPage(), requestContainer.getPageSize(), "id desc");
        List<SysWantProductVO> list = productMapper.findAllByState(requestContainer.getData().getState(),requestContainer.getData().getUseId());
        PageResultContainer result = new PageResultContainer();
        result.setTotal(page.getTotal());
        result.setEntities(list);
        return result;
    }
}
