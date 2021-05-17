package com.example.projBLSS.rabbit_service.consuming;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.context.annotation.Profile;

@RabbitListener(queues = "likeQueue")
@Profile("stats")
public class RabbitLikeConsumer {

    @RabbitHandler
    public void consume(String in) {
        System.out.println(" [x] Received '" + in + "'");
    }


}
