package com.example.projBLSS.rabbit_service.consuming;

import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.filter.JwtFilter;
import com.example.projBLSS.main_server.service.PictureService;
import com.example.projBLSS.rabbit_service.mail.MailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Profile("stats")
public class ConsumerService {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private MailService mailService;

    Logger logger = LogManager.getLogger(JwtFilter.class);


    @Async
    public void checkLikeGoal(Picture pictureFromDb) throws PictureNotFoundException {
        try {
            mailService.sendMessage(
                    pictureFromDb.getName(),
                    pictureFromDb.getUserID()
            );
            logger.log(Level.INFO, "Picture with id=" + pictureFromDb.getID() + " has reached likes goal");
            pictureService.changeIsNotificate(pictureFromDb.getID());
        }catch (MailException e){
            System.out.println(e.getMessage());
        }
    }

}
