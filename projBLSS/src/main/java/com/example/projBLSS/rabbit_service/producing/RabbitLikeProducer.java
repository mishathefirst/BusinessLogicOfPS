package com.example.projBLSS.rabbit_service.producing;

import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;


public class RabbitLikeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private MessageContainer messageContainer;

    @Autowired
    private Queue queue;

    @Async
    @Scheduled(fixedDelay = 5000)
    public void produce(){
        if (messageContainer.getQueue().isEmpty()){
            return;
        }
        this.amqpTemplate.convertAndSend(queue.getName(), messageContainer.getQueue().poll());
        System.out.println("Sent");
    }



}
