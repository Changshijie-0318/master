package com.sxau.master.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Auther: Shijie Chang
 * @Date: 2020/09/11 11:49
 * @Description: 配置类
 */
@Component
public class WebSocketConfig {
    /**
     * 功能描述: <ServerEndpointExporter 作用   这个Bean会自动注册使用@ServerEndpoint注解声明的websocket endpoint>
     * @Param: []
     * @Return: org.springframework.web.socket.server.standard.ServerEndpointExporter
     * @Author: Shijie Chang
     * @Date: 2020/9/11 19:21
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}