package com.example.projBLSS.service;

import com.example.projBLSS.beans.Album;
import com.example.projBLSS.beans.User;
import com.example.projBLSS.dto.AlbumDTO;
import com.example.projBLSS.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DTOConverter {

    @Autowired
    private User user;

    @Autowired
    private Album album;

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

    public AlbumDTO convertAlbumToDTO(Album album){
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getID());
        albumDTO.setName(album.getName());
        albumDTO.setUser_id(album.getUser_id());
        albumDTO.setPictures(album.getPictures());
        return albumDTO;
    }

    public Album convertAlbumFromDTO(AlbumDTO albumDTO, User owner){
        album.setID(albumDTO.getId());
        album.setName(albumDTO.getName());
        album.setUser_id(owner.getID());
        return album;
    }
}
