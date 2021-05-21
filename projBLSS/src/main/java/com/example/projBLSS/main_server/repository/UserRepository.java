package com.example.projBLSS.main_server.repository;

import com.example.projBLSS.main_server.beans.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findByID(Long id);

    @Override
    void delete(User user);

    @Modifying
    @Transactional
    @Query("update User u set u.email = :email where u.ID = :id")
    int changeEmail(@Param("id") Long id,
                           @Param("email") String email);
}
