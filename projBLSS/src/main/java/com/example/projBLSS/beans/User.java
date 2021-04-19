package com.example.projBLSS.beans;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_users", uniqueConstraints =
@UniqueConstraint(columnNames = "login")
)
public class User {
    @Id
    @GeneratedValue
    private Long ID;

    @NotNull
    @Column(unique = true)
    private String login;
    @NotNull
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;



    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

