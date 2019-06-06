package com.user.controller;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@SuppressWarnings("ALL")
@Component
@ServerEndpoint("/webSocketByTomcat/{username}")
public class WebSoket {
    private Session session;
    @OnOpen
    public void onOpen() {
        System.out.println("成功建立了连接");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        this.session=session;
        System.out.println("来自客户端的消息....." + message);
//		System.out.println("来自客户端的消息:" + message);
        //发送服务器端的消息给前端的页面
        this.session.getBasicRemote().sendText("this is server message....");
    }
}
