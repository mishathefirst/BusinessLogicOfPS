package com.example.projBLSS.rabbit_service.db_selector;

import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.filter.JwtFilter;
import com.example.projBLSS.main_server.repository.PictureRepository;
import com.example.projBLSS.main_server.service.PictureService;
import com.example.projBLSS.rabbit_service.consuming.ConsumerService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Profile("stats")
public class DbSelector {

    @Autowired
    private PictureRepository pictureRepository;

    Logger logger = LogManager.getLogger(JwtFilter.class);

    @Autowired
    private ConsumerService consumerService;

    @Value("${like.goal}")
    private int likeGoal;

    @Async
    @Scheduled(fixedDelay = 10000)
    public void checkLikeGoalInDB(){
        ArrayList<Picture> pictures = pictureRepository.getPictureWithoutNotificateAndLikeGoals(Integer.toUnsignedLong(likeGoal));
        for(Picture p : pictures){
            try {
                consumerService.checkLikeGoal(p);
            }catch (PictureNotFoundException e){
                logger.log(Level.INFO, "Picture not found in database");
            }
        }
    }
}
