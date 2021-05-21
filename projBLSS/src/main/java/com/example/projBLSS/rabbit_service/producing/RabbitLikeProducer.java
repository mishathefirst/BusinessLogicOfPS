package com.example.projBLSS.rabbit_service.producing;

import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.filter.JwtFilter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;

@Profile("dev")
public class RabbitLikeProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    Logger logger = LogManager.getLogger(JwtFilter.class);

    @Autowired
    private Queue queue;

    @Async
    public void produce(PictureToStatsServerDTO picture){
        this.amqpTemplate.convertAndSend(queue.getName(), picture);
        logger.log(Level.INFO, "Sent picture with id=" + picture.getId() + " to increment like");
    }



}
