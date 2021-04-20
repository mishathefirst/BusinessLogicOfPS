package com.example.projBLSS.service;

import com.example.projBLSS.beans.RefreshToken;
import com.example.projBLSS.beans.Role;
import com.example.projBLSS.beans.User;
import com.example.projBLSS.dto.ResponseMessageDTO;
import com.example.projBLSS.dto.TokenObject;
import com.example.projBLSS.dto.UserDTO;
import com.example.projBLSS.exceptions.UserNotFoundException;
import com.example.projBLSS.repository.RefreshTokenRepository;
import com.example.projBLSS.repository.RoleRepository;
import com.example.projBLSS.repository.UserRepository;
import com.example.projBLSS.utils.JWTutils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;



public class ShutterstockUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User user;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private JWTutils jwTutils;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    Logger logger = LogManager.getLogger(ShutterstockUserDetails.class);

    public ResponseEntity<ResponseMessageDTO> registerUserDTO(UserDTO userDTO){
        ResponseMessageDTO message = new ResponseMessageDTO();
        user = dtoConverter.convertUserFromDTO(userDTO);
        String answerText = "";
        try{
            this.save(user);
            message.setAnswer("Вы были успешно зарегистрированы");
        }catch (DataIntegrityViolationException e){
            if(e.getCause().getClass() == ConstraintViolationException.class){
                answerText = "Пользователь с таким логином уже существует";
            }else{
                answerText = "УПС! Произошла ошибка, пожалуйста, попробуйте позднее";
            }
            message.setAnswer(answerText);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }



    public ResponseEntity<TokenObject> authUserDTO(UserDTO userDTO){
        TokenObject message = new TokenObject();
        try {
            user = this.findByLoginAndPassword(userDTO.getLogin(), userDTO.getPassword());
            TokenObject token = new TokenObject(jwTutils.generateToken(user.getLogin()), jwTutils.generateRefreshToken(user.getLogin()));
            refreshTokenRepository.save(new RefreshToken(user.getID(), token.getRefreshToken()));
            message.setAccessToken(token.getAccessToken());
            message.setRefreshToken(token.getRefreshToken());
            return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
        }catch (UserNotFoundException e){
            message.setAnswer(e.getErrMessage());
            return new ResponseEntity<>(message, e.getErrStatus());
        }
    }

    public User findByLoginAndPassword(String login, String password) throws UserNotFoundException{
        User user  = this.userRepository.findByLoginAndPassword(login, password);
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким login не найден", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    public void save(User user)  {
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_ADMIN")));
        this.userRepository.save(user);
    }

    public User findByLogin(String login) throws UserNotFoundException{
        User user  = this.userRepository.findByLogin(login);
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким login не найден", HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    public void deleteUserById(Long id) throws UserNotFoundException {
        user = this.userRepository.findByID(id);
        if(user == null){
            throw new UserNotFoundException("Пользователь с таким id не существует", HttpStatus.BAD_REQUEST);
        }
        this.userRepository.deleteById(id);
    }

    public User getUserFromRequest(HttpServletRequest request) throws UserNotFoundException{
        String token = jwTutils.getTokenFromRequest(request);
        String login = jwTutils.getLoginFromToken(token);
        try {
            User user = this.findByLogin(login);
            logger.log(Level.INFO, "getting user from request" + user.getLogin());
            return user;
        }catch (UserNotFoundException e){
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        return new ShutterstockUserDetails(user);
    }
}
