package com.sxau.master.controller;

import com.sxau.master.common.Assert4Demo;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.Result;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.utils.ValidateUtil;
import com.sxau.master.service.impl.SendEmailImpl;
import com.sxau.master.utils.ValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.controller
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 15:30
 * @Description:
 */
@Controller
@Api("邮件接口")
@RequestMapping("/e-mail")
public class SendEmailController {

    @Resource
    SendEmailImpl sendEMail;

    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @PostMapping("/sendEmail")
    @ResponseBody
    public Result<Integer> sendEmail(@RequestParam String email){
        try {
            Assert4Demo.isTrue(ValidateUtil.checkMail(email), "邮箱格式不正确");
            Integer code = sendEMail.getCode(email);
            return new Result<>(code);
        } catch (Exception e) {
            throw new ErrorException(ResultEnum.DATA_ERROR,"发送失败");
        }
    }
}
