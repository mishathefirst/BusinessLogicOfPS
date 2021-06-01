package com.example.projBLSS.rabbit_service.consuming;


import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.filter.JwtFilter;
import com.example.projBLSS.main_server.service.PictureService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSConsumer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerMessageListener implements MessageListener {

    @Autowired
    private PictureService pictureService;


    Logger logger = LogManager.getLogger(JwtFilter.class);

    @Override
    public void onMessage(Message message) {
        PictureToStatsServerDTO picture = null;
        try {
            picture = message.getBody(PictureToStatsServerDTO.class);
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            logger.log(Level.INFO, "Received picture with id=" + picture.getId());
            this.pictureService.incrementLikePicture(picture.getId(), picture.getCountLikesToAdd());
        }catch (PictureNotFoundException e){
            logger.log(Level.ERROR, "Picture with id=" + picture.getId() + " doesn't exists");
        }
    }
}

