package com.example.projBLSS.main_server.configurations;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import com.example.projBLSS.main_server.XA.AlbumDeleteXA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
@Profile("dev")
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
