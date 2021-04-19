package com.example.projBLSS.controllers;

import com.example.projBLSS.dto.ResponseMessageDTO;
import com.example.projBLSS.dto.UserDTO;
import com.example.projBLSS.exceptions.UserValidationException;
import com.example.projBLSS.service.ShutterstockUserDetailsService;
import com.example.projBLSS.validation.ValidationUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(value = "Authorization api")
public class AuthorizationController {

    @Autowired
    private ValidationUserService validationUserService;

    @Autowired
    private ShutterstockUserDetailsService userService;

    @PutMapping("/register")
    public ResponseEntity<ResponseMessageDTO> register(@RequestBody UserDTO userDTO) {
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            validationUserService.validateUserDTO(userDTO);
        }catch (UserValidationException e) {
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return userService.registerUserDTO(userDTO);
    }


    @PostMapping("/")
    public ResponseEntity<ResponseMessageDTO> auth(@RequestBody UserDTO userDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try{
            validationUserService.validateUserDTO_FOR_AUTH(userDTO);
        }catch (UserValidationException e) {
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return userService.authUserDTO(userDTO);
    }

}
