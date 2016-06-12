package com.kazaf.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Kazaf on 16/5/21.
 */


//@PropertySource({"classpath:/jdbc.properties"})
public class DataSourceConfig {

  /*  @Value("${jdbc.driver}")
    String driver;
    @Value("${jdbc.username}")
    String username;
    @Value("${jdbc.password}")
    String password;
    @Value("${jdbc.url}")
    String url;
   @Value("${jdbc.initialSize}")
    int initialSize;
    @Value("${jdbc.maxTotal}")
    int maxTotal;
    @Value("${jdbc.maxIdle}")
    int maxIdle;
    @Value("${jdbc.minIdle}")
    int minIdle;
    @Value("${jdbc.maxWait}")
    long maxWait;

    @Bean(name="dataSource")
    public BasicDataSource dataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
       dataSource.setInitialSize(initialSize);
        dataSource.setMaxTotal(maxTotal);
        dataSource.setMaxIdle(maxIdle);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWaitMillis(maxWait);
        return  dataSource;
    }

    @Bean
    public ClassPathResource[] urlResource(){
        ClassPathResource[] a=new ClassPathResource[1];
        a[0]=new  ClassPathResource("classpath*:com/kazaf/mapping/*Mapper.xml");
        return a;
    }
*/


}
