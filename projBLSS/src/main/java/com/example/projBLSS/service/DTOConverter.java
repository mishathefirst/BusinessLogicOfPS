package com.example.projBLSS.service;

import com.example.projBLSS.beans.User;
import com.example.projBLSS.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {

    @Autowired
    private User user;

    public UserDTO convertUserToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getID());
        return userDTO;
    }

    public User convertUserFromDTO(UserDTO userDTO){
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        if(userDTO.getId() != null){
            userDTO.setId(userDTO.getId());
        }
        return user;
    }
}
