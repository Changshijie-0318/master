package com.sxau.master.controller;

import com.sxau.master.common.Result;
import com.sxau.master.service.ISysFileSv;
import com.sxau.master.vo.req.DeleteImageReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 14:03
 * @Description:
 */
@Controller
@Api("文件接口")
@RequestMapping("/file")
public class SysFileController extends BaseController{
    @Resource
    ISysFileSv iSysFileSv;

    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping("/upload-image")
    @ResponseBody
    public Result<Integer> uploadImage(@RequestParam("file") MultipartFile file){
        Assert.isTrue(!file.isEmpty(),"上传失败，请选择文件");
        int id = super.getCurrentUser().getId();
        return new Result<>(iSysFileSv.upload(id, file));
    }

    @ApiOperation(value = "查询图片base64内容", notes = "查询图片base64内容")
    @GetMapping(value = "/download-image",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
        public  byte[]  getImage(@RequestParam Integer imageId){
        return iSysFileSv.showImage(imageId);
    }


    @ApiOperation(value = "删除图片", notes = "删除图片")
    @ApiImplicitParam(name = "req",value = "删除图片",dataType = " DeleteImageReq",required = true, paramType = "body")
    @RequestMapping(value = "/deleteImage",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> deleteImage(@Valid @RequestBody DeleteImageReq req) {
        iSysFileSv.deleteImage(req);
        return new Result<>("删除成功");
    }
}
