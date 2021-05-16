package com.example.projBLSS.repository;

import com.example.projBLSS.beans.Album;
import com.example.projBLSS.beans.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
    Picture findByID(Long id);
    List<Picture> findAll();
    @Modifying
    @Transactional
    @Query("update Picture p set p.likes = p.likes + :incValue where p.ID = :id")
    int incrementLikeField(@Param("id") Long id,
                           @Param("incValue") Long incValue);
}
