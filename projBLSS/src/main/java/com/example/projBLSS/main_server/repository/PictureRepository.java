package com.example.projBLSS.main_server.repository;


import com.example.projBLSS.main_server.beans.Picture;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
    Picture findByID(Long id);

    @Override
    <S extends Picture> S save(S s);

    List<Picture> findAll();

    @Modifying
    @Transactional
    @Query("update Picture p set p.likes = p.likes + :incValue where p.ID = :id")
    int incrementLikeField(@Param("id") Long id,
                           @Param("incValue") Long incValue);

    @Modifying
    @Transactional
    @Query("update Picture p set p.isNotificateUser = true where p.ID = :id")
    int changeIsNotificate(@Param("id") Long id);


    @Transactional
    @Query("select p from Picture p where p.isNotificateUser = false and p.likes >= :likeGoal")
    ArrayList<Picture> getPictureWithoutNotificateAndLikeGoals(@Param("likeGoal") long likeGoal);
}
