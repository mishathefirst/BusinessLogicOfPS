package com.example.projBLSS.rabbit_service.producing;

import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducingService {

    @Autowired
    private MessageContainer messageContainer;

    public void likePicture(Long id, Long incValue){
        PictureToStatsServerDTO picture = new PictureToStatsServerDTO();
        picture.setId(id);
        picture.setCountLikesToAdd(incValue);
        messageContainer.getQueue().add(picture);
    }
}
