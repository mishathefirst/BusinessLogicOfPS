package com.example.projBLSS.rabbit_service.websocket;


import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.text.SimpleDateFormat;

@Service
public class WebSocketService {

    @SendTo("/topic/messages/{id}")
    public ResponseMessageDTO send(ResponseMessageDTO message, @PathParam(value = "id") Long id) {
        return message;
    }
}
