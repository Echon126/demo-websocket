package com.example.thymeleaf.controller;


import com.example.thymeleaf.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;
    private List<String> list = new ArrayList<String>();

    //广播推送消息
    @Scheduled(fixedRate = 10000)
    @RequestMapping("/websocket")
    public void sendTopicMessage() {
        logger.info("----后台广播推送----");
        logger.error("----后台广播推送----");
        User user = new User();
        user.setUserName("张文杰");
        user.setAge(18);
        this.template.convertAndSend("/topic/getResponse", user);
        try {
            list.get(0);
        } catch (Exception e) {
            logger.error("----后台广播推送错误----", e);
        }

    }

    /**
     * TODO SimpMessagingTemplate：SpringBoot提供操作WebSocket的对象
     */
    @RequestMapping("/info")
    public void getSession() {
        System.out.println(this.webSocketMessageBrokerStats.getSockJsTaskSchedulerStatsInfo());
        System.out.println(this.webSocketMessageBrokerStats.getWebSocketSessionStatsInfo());
    }
}














