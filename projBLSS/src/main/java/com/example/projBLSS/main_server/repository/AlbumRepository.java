package com.example.projBLSS.main_server.repository;

import com.example.projBLSS.main_server.beans.Album;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
@Profile("dev")
public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findByID(Long id);


}
