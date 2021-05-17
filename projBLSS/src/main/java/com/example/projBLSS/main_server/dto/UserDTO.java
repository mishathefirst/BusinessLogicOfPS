package com.example.projBLSS.main_server.dto;
import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long Id;
    private String password;
    private String login;


//    public void settingWrapperUser(String surname,
//                                   String name,
//                                   long id){
//        this.name = name;
//        this.surname = surname;
//        this.Id = id;
//    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
