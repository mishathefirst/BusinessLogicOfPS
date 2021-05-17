package com.example.projBLSS.rabbit_service.configuration;


import com.example.projBLSS.rabbit_service.consuming.RabbitLikeConsumer;
import com.example.projBLSS.rabbit_service.producing.RabbitLikeProducer;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfiguration {

    @Bean
    public Queue likePictureQueue(){
        return new Queue("likeQueue");
    }

    @Profile("stats")
    @Bean
    public RabbitLikeConsumer consumer(){
        return new RabbitLikeConsumer();
    }

    @Profile("dev")
    @Bean
    public RabbitLikeProducer producer(){
        return new RabbitLikeProducer();
    }







}
