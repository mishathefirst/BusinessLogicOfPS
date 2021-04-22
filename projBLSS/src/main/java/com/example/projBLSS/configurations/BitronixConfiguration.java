package com.example.projBLSS.configurations;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import bitronix.tm.resource.jdbc.PoolingDataSource;
import com.example.projBLSS.XA.AlbumDeleteXA;
import org.springframework.boot.orm.jpa.hibernate.SpringJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class BitronixConfiguration {

    @Bean
    public AlbumDeleteXA albumDeleteXA(){
        return new AlbumDeleteXA();
    }


    @Bean(destroyMethod = "shutdown")
    public BitronixTransactionManager bitronixManager() {
        return TransactionManagerServices.getTransactionManager();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager jtaTransactionManager() {
        JtaTransactionManager jta = new JtaTransactionManager();
        jta.setTransactionManager(bitronixManager());
        jta.setUserTransaction(bitronixManager());
        jta.setRollbackOnCommitFailure(true);
        return jta;
    }



//    @Bean(destroyMethod = "close", name = "dataSourceOne")
//    public DataSource dataSourceOne() {
//        PoolingDataSource ds = new PoolingDataSource();
//        ds.setUniqueName("ds1");
//        ds.setMaxPoolSize(10);
//        Properties props = new Properties();
//        props.put("url", "jdbc:postgresql://localhost:5500/studs");
//        props.put("user", "s265085");
//        props.put("password", "ble545");
//        ds.setDriverProperties(props);
//        ds.init();
//        return ds;
//    }
//
//    @Bean(destroyMethod = "close", name = "dataSourceTwo")
//    public DataSource dataSourceTwo() {
//        PoolingDataSource ds = new PoolingDataSource();
//        // another in-memory database...make sure to use the XADatasources for other databases
//        ds.setUniqueName("ds2");
//        ds.setMaxPoolSize(10);
//        Properties props = new Properties();
//        props.put("url", "jdbc:h2:mem:ds2");
//        props.put("user", "sa");
//        props.put("password", "");
//        ds.setDriverProperties(props);
//        ds.init();
//        return ds;
//    }


}
