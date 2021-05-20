package com.example.projBLSS.rabbit_service.consuming;


import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.service.PictureService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;


@RabbitListener(queues = "likes1", containerFactory = "jsaFactory")
public class RabbitLikeConsumer {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private ConsumerService consumerService;


    @RabbitHandler
    public void receive(PictureToStatsServerDTO picture) throws PictureNotFoundException {
        System.out.println(" [x] Received '" + picture.getId() + "'");
        this.pictureService.incrementLikePicture(picture.getId(), picture.getCountLikesToAdd());
        if(!consumerService.checkLikeGoal(picture.getId())){
            System.out.println("YEEEP");
        }
    }


}
