package com.example.projBLSS.rabbit_service.consuming;

import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private PictureService pictureService;

//    @Value("${like.goal}")
    private int likeGoal = 3;

    public boolean checkLikeGoal(Long id) throws PictureNotFoundException {
        long likes = pictureService.getPicture(id).getLikes();
        return likes != likeGoal;
    }




}
