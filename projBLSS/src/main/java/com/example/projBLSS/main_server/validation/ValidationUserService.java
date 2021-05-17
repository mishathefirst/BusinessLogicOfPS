package com.example.projBLSS.main_server.validation;


import com.example.projBLSS.main_server.dto.UserDTO;
import com.example.projBLSS.main_server.exceptions.UserValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ValidationUserService {

    public void validateUserDTO(UserDTO userDTO) throws UserValidationException {
        if(!validatePasswordUserDTO(userDTO.getPassword())){
            throw new UserValidationException("Invalid password. Please, try again", HttpStatus.UNAUTHORIZED);
        }else if(!validateLoginUserDTO(userDTO.getLogin())){
            throw new UserValidationException("Invalid login. Please, try again", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateUserDTO_FOR_AUTH(UserDTO userDTO) throws UserValidationException{
        if(!validatePasswordUserDTO(userDTO.getPassword())){
            throw new UserValidationException("Invalid password. Please, try again", HttpStatus.UNAUTHORIZED);
        }else if(!validateLoginUserDTO(userDTO.getLogin())){
            throw new UserValidationException("Invalid login. Please, try again", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validatePasswordUserDTO(String password){
        int length = password.length();
        if(length == 0){
            return false;
        }else if(length>20){
            return false;
        }
        return true;
    }



    private boolean validateLoginUserDTO(String login){
        if(login.length() < 4 || login.length() > 20){
            return false;
        }
        return true;

    }

    private static boolean checkIntString(String sequence){
        char[] chars = sequence.toCharArray();

        for (char ch : chars) {
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;

    }
}


