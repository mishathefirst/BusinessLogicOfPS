package com.example.projBLSS.rabbit_service.consuming;


import com.example.projBLSS.main_server.dto.PictureToStatsServerDTO;
import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.service.PictureService;
import com.example.projBLSS.rabbit_service.websocket.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;


@RabbitListener(queues = "likes1", containerFactory = "jsaFactory")
public class RabbitLikeConsumer {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private ConsumerService consumerService;


    @Autowired
    private WebSocketService webSocketService;


    @RabbitHandler
    public void receive(PictureToStatsServerDTO picture){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            this.pictureService.incrementLikePicture(picture.getId(), picture.getCountLikesToAdd());
            if(!consumerService.checkLikeGoal(picture.getId())){
                message.setAnswer("Лайк был установлен");
            }
            webSocketService.send(message, pictureService.getPicture(picture.getId()).getUserID());
        }catch (PictureNotFoundException e){
            message.setAnswer(e.getErrMessage());
        }
    }


}
