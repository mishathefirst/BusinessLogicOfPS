package com.example.projBLSS.XA;

import com.example.projBLSS.beans.Album;
import com.example.projBLSS.beans.Picture;
import com.example.projBLSS.exceptions.PictureNotFoundException;
import com.example.projBLSS.repository.AlbumRepository;
import com.example.projBLSS.repository.PictureRepository;
import com.example.projBLSS.service.PictureService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class AlbumDeleteXA {

    @Autowired
    private DataSource ds1;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PictureRepository pictureRepository;


    @Transactional
    public void deleteAlbum(Album album) throws PictureNotFoundException {
        if (album.getPictures().isEmpty()) {
            albumRepository.delete(album);
        } else if (album.getPictures().size() == 1 && checkPicture(pictureRepository.findByID(album.getPictures().get(0).getID())) > 1) {
            albumRepository.delete(album);
        } else if (album.getPictures().size() > 1){
            System.out.println("Cannot delete album.");
        } else {
            pictureRepository.deleteById(album.getPictures().get(0).getID());
            albumRepository.delete(album);
        }

    }

    private int checkPicture(Picture picture) {
        int counter = 0;
        Iterator<Album> iter = albumRepository.findAll().iterator();
        while (iter.hasNext()) {
            List<Picture> picts = iter.next().getPictures();
            if (picts.contains(picture)) {
                counter++;
            }
        }
        return counter;

    }
}
