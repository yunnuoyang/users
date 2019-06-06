package com.user.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@EnableScheduling
@Component
public class Timer {
    @Scheduled(cron = "0/5 * * * * ?")   //每分钟执行一次
    public void test(){
        System.err.println("*********   定时任务执行   **************");
        CopyOnWriteArraySet<WebSoket> webSocketSet =
                WebSoket.getWebSocketSet();
        int i = 0 ;
        webSocketSet.forEach(c->{
            try {
                c.getSession().getBasicRemote().sendText("定时任务启动，发送消息给客户端.....");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.err.println("/n 定时任务完成.......");
    }

}
