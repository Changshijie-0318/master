package com.sxau.master.service.impl;

import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ProjectConstans;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.dao.repostiory.SysFileRepository;
import com.sxau.master.data.SysFile;
import com.sxau.master.data.meta.FileTypeEnum;
import com.sxau.master.service.ISysFileSv;
import com.sxau.master.utils.TimeUtils;
import com.sxau.master.vo.req.DeleteImageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 13:44
 * @Description:
 */
@Service
public class ISysFileSVImpl implements ISysFileSv {
    private static Logger logger = LoggerFactory.getLogger(ISysFileSVImpl.class);
    @Value("${imagesPath}")
    private String imagePath;
    @Resource
    SysFileRepository fileRepository;

    /**
     * 上传图片
     * @param file
     * @param opId
     * @return
     */
    @Override
    public Integer upload(int opId,MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = getSuffixStr(fileName);
        FileTypeEnum fileTypeEnum =  FileTypeEnum.getInstanceByFileSuffix(suffix);
        String newFile = System.currentTimeMillis()+"."+suffix;
        logger.info("新文件名："+newFile);
        String folder = fileTypeEnum.getFileFolder();
        File tempDir = new File(imagePath+ "/"+folder);
        //设置读写权限
        tempDir.setWritable(true, false);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        try {
            File tempFile = new File(imagePath+ "/"+folder+newFile);
            logger.info("文件路径为："+tempFile.getAbsolutePath());
            file.transferTo(tempFile);
        } catch (IOException e) {
            logger.info("上传失败",e);
            throw new ErrorException(ResultEnum.DATA_ERROR,"上传失败");
        }
        SysFile fileToDb = new SysFile();
        fileToDb.setFileName(newFile);
        fileToDb.setFileType(fileTypeEnum.getFileType());
        fileToDb.setDescription(fileTypeEnum.getDesc());
        fileToDb.setFileFolder(fileTypeEnum.getFileFolder());
        fileToDb.setCreateTime(TimeUtils.getCurrentTime());
        fileToDb.setCreatorId(opId);
        fileToDb.setState(ProjectConstans.FILE_STATE_VALID);
        fileToDb.setUpdateTime(TimeUtils.getCurrentTime());
        return save(fileToDb).getId();
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    public String getSuffixStr(String fileName){
        int index = fileName.lastIndexOf(".");
        if(index != -1){
            return fileName.substring(index + 1).toLowerCase();
        }else {
            return "";
        }
    }
    /**
     * 获取图片内容
     *
     * @param id
     * @return
     */
    @Override
    @Transactional
    public byte[] showImage(int id) {
        //查询文件记录
        SysFile dbFile = getFileById(id);
        String fileName = dbFile.getFileName();
        File file = new File(imagePath+dbFile.getFileFolder()+fileName);
        if(!file.exists()){
            throw new  ErrorException(ResultEnum.DATA_ERROR,"图片在文件系统中不存在【"+id+"】");
        }


        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
            return bytes;
        } catch (Exception e) {
            throw new  ErrorException(ResultEnum.DATA_ERROR,"图片在文件系统中不存在【"+id+"】");
        }
    }

    /**
     * 保存文件信息到数据库
     *
     * @param file
     */
    @Override
    public SysFile save(SysFile file) {
        SysFile save = fileRepository.save(file);
        return save;
    }

    /**
     * 通过id找到对应的图片信息
     *
     * @param id
     * @return
     */
    @Override
    public SysFile getFileById(int id) {
        return fileRepository.findSysFileById(id);
    }

    /**
     * 删除图片
     *
     * @param req
     * @return
     */
    @Override
    public void deleteImage(DeleteImageReq req) {
        SysFile sysFile = fileRepository.findSysFileById(req.getId());
        if (sysFile==null) return;
        File file = new File(imagePath+sysFile.getFileFolder()+sysFile.getFileName());
        file.delete();
        logger.info("从本地中删除了图片:"+sysFile.getFileName());
        fileRepository.delete(sysFile);
    }
}
