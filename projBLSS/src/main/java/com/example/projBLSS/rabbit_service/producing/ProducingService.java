package com.example.projBLSS.rabbit_service.producing;

import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ProducingService {


    @Autowired
    private RabbitLikeProducer producer;

    public void likePicture(Long id, Long incValue){
        PictureToStatsServerDTO picture = new PictureToStatsServerDTO();
        picture.setId(id);
        picture.setCountLikesToAdd(incValue);
        producer.produce(picture);
    }
}
