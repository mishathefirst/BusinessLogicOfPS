package com.example.projBLSS.rabbit_service.consuming;


import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.filter.JwtFilter;
import com.example.projBLSS.main_server.service.PictureService;
import com.example.projBLSS.rabbit_service.mail.MailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;


@RabbitListener(queues = "likes1", containerFactory = "jsaFactory")
@Profile("stats")
public class RabbitLikeConsumer {

    @Autowired
    private PictureService pictureService;

    Logger logger = LogManager.getLogger(JwtFilter.class);


    @RabbitHandler
    public void receive(PictureToStatsServerDTO picture){
        try {
            logger.log(Level.INFO, "Received picture with id=" + picture.getId());
            this.pictureService.incrementLikePicture(picture.getId(), picture.getCountLikesToAdd());
        }catch (PictureNotFoundException e){
            logger.log(Level.ERROR, "Picture with id=" + picture.getId() + " doesn't exists");
        }
    }


}
