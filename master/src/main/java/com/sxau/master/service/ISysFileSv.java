package com.sxau.master.service;

import com.sxau.master.data.SysFile;
import com.sxau.master.vo.req.DeleteImageReq;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 13:45
 * @Description:
 */
public interface ISysFileSv {
    /**
     * 上传图片
     * @param file
     * @param opId
     * @return
     */
    Integer upload(int opId, MultipartFile file);

    /**
     * 获取图片内容
     * @param id
     * @return
     */
    byte[]  showImage(int id);

    /**
     * 保存文件信息到数据库
     * @param file
     */
    SysFile save(SysFile file);

    /**
     * 通过id找到对应的图片信息
     * @param id
     * @return
     */
    SysFile getFileById(int id);

    /**
     * 删除图片
     * @param req
     * @return
     */
    void deleteImage(DeleteImageReq req);
}
