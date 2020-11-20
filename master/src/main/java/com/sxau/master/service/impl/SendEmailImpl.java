package com.sxau.master.service.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @BelongsProject: master
 * @BelongsPackage: com.sxau.master.service.impl
 * @Author:Shijie Chang
 * @CreateTime: 2020-09-10 15:51
 * @Description:
 */
@Service
public class SendEmailImpl {
    @Value("${MyQQEmail.email}")
    private  String email;
    @Value("${MyQQEmail.recipient}")
    private  String recipient;
    @Value("${MyQQEmail.authCode}")
    private  String authCode;
    @Value("${MyQQEmail.host}")
    private  String host;

    /**
     * 发送邮件接受验证码
     */
    public  Integer getCode(String acceptEmail) throws Exception{
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com"); //// 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码

        // QQ邮箱设置SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);
        //创建一个session对象
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(recipient,authCode);
            }
        });
        //开启debug模式
        session.setDebug(true);
        //获取连接对象
        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //连接服务器
        transport.connect(host,email,authCode);
        //创建一个邮件发送对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //邮件发送人
        mimeMessage.setFrom(new InternetAddress(recipient));
        //邮件接收人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(acceptEmail));
        //邮件标题
        mimeMessage.setSubject("网站注册成功");
        //生成验证码
        int code = (int) (Math.random() * 1000000);
        //邮件内容
        mimeMessage.setContent("网站注册成功，验证码为"+code+"，用于山西农业大学二手交易平台。泄露有风险","text/html;charset=UTF-8");
        //发送邮件
        transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        transport.close();
        return code;
    }
}
