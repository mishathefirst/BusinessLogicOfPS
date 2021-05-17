package com.example.projBLSS.main_server.validation;

import com.example.projBLSS.main_server.dto.AlbumDTO;
import com.example.projBLSS.main_server.exceptions.AlbumValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationAlbumService {

    public void validateAlbumDTO(AlbumDTO albumDTO) throws AlbumValidationException {
        if(!validateName(albumDTO.getName())){
            throw new AlbumValidationException("Invalid name for album. Please, try again", HttpStatus.BAD_REQUEST);
        }
    }




    private boolean validateName(String name){
        if(name.length() < 4 || name.length() > 20){
            return false;
        }
        return true;

    }


}
