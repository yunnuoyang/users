package com.user.controller;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CopyOnWriteArraySet;

@SuppressWarnings("ALL")
/**连接实现websocket的类
 *
 */
@Component
@ServerEndpoint("/webSocketByTomcat/{username}")
public class WebSoket {
    /** concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     在外部可以获取此连接的所有websocket对象，并能对其触发消息发送功能，我们的定时发送核心功能的实现在与此变量 */
    private static CopyOnWriteArraySet<WebSoket> webSocketSet = new CopyOnWriteArraySet<WebSoket>();

    private Session session;

    public Session getSession() {
        return session;
    }

    @OnOpen
    public void onOpen() {
        System.out.println("成功建立了连接");
    }

    public static CopyOnWriteArraySet<WebSoket> getWebSocketSet() {
        return webSocketSet;
    }

    public static void setWebSocketSet(CopyOnWriteArraySet<WebSoket> webSocketSet) {
        WebSoket.webSocketSet = webSocketSet;
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception{
        this.session=session;
        webSocketSet.add(this);
        System.out.println("来自客户端的消息....." + message);
//		System.out.println("来自客户端的消息:" + message);
        //发送服务器端的消息给前端的页面
        this.session.getBasicRemote().sendText("this is server message....");
    }

    /**
     * 定时的任务，每分钟推送一次
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public  void scheduled() throws Exception {
        System.out.println("开始我的定时任务.........");
        webSocketSet.forEach(webSoket -> {
            try {
                webSoket.getSession().getBasicRemote().sendText("当前服务器定时推送信息"+new SimpleDateFormat().toLocalizedPattern());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
