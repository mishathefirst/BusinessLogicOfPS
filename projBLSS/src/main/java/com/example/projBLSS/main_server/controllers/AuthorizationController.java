package com.example.projBLSS.main_server.controllers;

import com.example.projBLSS.main_server.dto.ResponseMessageDTO;
import com.example.projBLSS.main_server.dto.TokenObject;
import com.example.projBLSS.main_server.dto.UserDTO;
import com.example.projBLSS.main_server.exceptions.UserNotFoundException;
import com.example.projBLSS.main_server.exceptions.UserValidationException;
import com.example.projBLSS.main_server.service.ShutterstockUserDetailsService;
import com.example.projBLSS.main_server.utils.JWTutils;
import com.example.projBLSS.main_server.validation.ValidationUserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Profile("dev")
@Api(value = "Authorization api")
public class AuthorizationController {

    @Autowired
    private ValidationUserService validationUserService;

    @Autowired
    private ShutterstockUserDetailsService userService;

    @Autowired
    private JWTutils jwTutils;

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
    public ResponseEntity<TokenObject> auth(@RequestBody UserDTO userDTO){
        TokenObject message = new TokenObject();
        try{
            validationUserService.validateUserDTO_FOR_AUTH(userDTO);
        }catch (UserValidationException e) {
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
        return userService.authUserDTO(userDTO);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenObject> refresh(HttpServletRequest servletRequest){
        String token = jwTutils.getTokenFromRequest((HttpServletRequest) servletRequest);
        TokenObject message = new TokenObject();
        try{
            jwTutils.validateToken(token);
            return jwTutils.refreshToken(token);
        }catch (ExpiredJwtException e) {
            message.setAnswer("Refresh token expired time ended, please authorize again");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }catch (SignatureException e){
            message.setAnswer("Refresh token is not valid, please try again");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping ("/change/email")
    public ResponseEntity<ResponseMessageDTO> changeEmail(@RequestBody UserDTO userDTO, HttpServletRequest servletRequest){
        ResponseMessageDTO message = new ResponseMessageDTO();
        try {
            return this.userService.changeEmail(userService.getUserFromRequest(servletRequest).getID(), userDTO.getEmail());
        }catch (UserNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
    }

}
