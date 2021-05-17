package com.example.projBLSS.main_server.XA;

import com.example.projBLSS.main_server.beans.Album;
import com.example.projBLSS.main_server.beans.Picture;
import com.example.projBLSS.main_server.exceptions.PictureNotFoundException;
import com.example.projBLSS.main_server.repository.AlbumRepository;
import com.example.projBLSS.main_server.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;
@Profile("dev")
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
