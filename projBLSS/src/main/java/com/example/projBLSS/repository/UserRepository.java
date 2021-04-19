package com.example.projBLSS.repository;

import com.example.projBLSS.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findByID(Long id);
}
