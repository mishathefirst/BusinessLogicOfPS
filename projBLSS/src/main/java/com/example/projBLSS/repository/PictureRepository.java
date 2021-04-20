package com.example.projBLSS.repository;

import com.example.projBLSS.beans.Album;
import com.example.projBLSS.beans.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends CrudRepository<Picture, Long> {
    Picture findByID(Long id);
    List<Picture> findAll();
}
