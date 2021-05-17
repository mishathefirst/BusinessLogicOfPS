package com.example.projBLSS.main_server.controllers;


import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.exceptions.UserNotFoundException;
import com.example.projBLSS.main_server.service.ShutterstockUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Profile("dev")
public class AdminController {

    @Autowired
    private ShutterstockUserDetailsService userService;

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            userService.deleteUserById(id);
        }catch (UserNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity(message, e.getErrStatus());
        }
        message.setAnswer("Пользователь с id " + id + " был успешно удален");
        return new ResponseEntity(message, HttpStatus.OK);
    }
}
