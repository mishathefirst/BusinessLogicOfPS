package com.example.projBLSS.XA;

import com.example.projBLSS.beans.Album;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AlbumDeleteXA {


//    @Autowired
//    @Qualifier(value = "dataSourceOne")
//    private DataSource ds1;


//    @Transactional
//    public void deleteAlbum(Album album){
//        try {
//            ds1.getConnection().commit();
//        } catch (SQLException throwables) {
//            System.out.println("suka");
//        }
//    }

}
