package com.example.projBLSS.repository;

import com.example.projBLSS.beans.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findByID(Long id);


}
