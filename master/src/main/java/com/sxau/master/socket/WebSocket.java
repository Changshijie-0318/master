package com.sxau.master.socket;
 
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
 
/**
 * @Auther: Shijie Chang
 * @Date: 2020/09/11 11:48
 * @Description: websocket 服务类
 */
/**
 *
 * @ServerEndpoint 这个注解有什么作用？
 *
 * 这个注解用于标识作用在类上，它的主要功能是把当前类标识成一个WebSocket的服务端
 * 注解的值用户客户端连接访问的URL地址
 *
 */
 
@Slf4j
@Component
@ServerEndpoint("/websocket/{name}")
public class WebSocket {
 
    /**
     *  与某个客户端的连接对话，需要通过它来给客户端发送消息
     */
    private Session session;
 
     /**
     * 标识当前连接客户端的用户名
     */
    private Integer id;
 
    /**
     *  用于存所有的连接服务的客户端，这个对象存储是安全的
     */
    private static ConcurrentHashMap<Integer,WebSocket> webSocketSet = new ConcurrentHashMap<>();
 
 
    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "name") Integer id){
        this.session = session;
        this.id = id;
        // name是用来表示唯一客户端，如果需要指定发送，需要指定发送通过name来区分
        webSocketSet.put(id,this);
        log.info("id为"+id+"的用户加入");
        log.info("[WebSocket] 连接成功，当前连接人数为：={}",webSocketSet.size());
    }
 
 
    @OnClose
    public void OnClose(){
        webSocketSet.remove(this.id);
        log.info("[WebSocket] 退出成功，当前连接人数为：={}",webSocketSet.size());
    }
 
    @OnMessage
    public void OnMessage(String message){
        log.info("[WebSocket] 收到消息：{}",message);
        //判断是否需要指定发送，具体规则自定义
        if(message.indexOf("TOUSER") == 0){
            String name = message.substring(message.indexOf("TOUSER")+6,message.indexOf(";"));
            AppointSending(name,message.substring(message.indexOf(";")+1,message.length()));
        }else{
            GroupSending(message);
        }
 
    }
 
    /**
     * 群发
     * @param message
     */
    public void GroupSending(String message){
        for (Integer id : webSocketSet.keySet()){
            try {
                webSocketSet.get(id).session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
 
    /**
     * 指定发送
     * @param name
     * @param message
     */
    public void AppointSending(String name,String message){
        try {
            webSocketSet.get(name).session.getBasicRemote().sendText(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}