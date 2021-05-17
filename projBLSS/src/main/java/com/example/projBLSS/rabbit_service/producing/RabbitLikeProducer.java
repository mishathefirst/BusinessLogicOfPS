package com.example.projBLSS.rabbit_service.producing;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;

@Profile("dev")
public class RabbitLikeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void produce(){
        this.rabbitTemplate.convertAndSend(queue.getName(), "Hello world");
        System.out.println("Sent");
    }

}
