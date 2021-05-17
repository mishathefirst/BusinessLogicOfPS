package com.example.projBLSS.main_server.repository;

import com.example.projBLSS.main_server.beans.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findByID(Long id);

    @Override
    void delete(User user);
}
