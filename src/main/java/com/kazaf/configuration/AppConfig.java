package com.kazaf.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Kazaf on 16/5/21.
 */
@Configuration
@ComponentScan(basePackages = "com.kazaf", excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
public class AppConfig {


   /* @Resource(name="dataSource")
    public BasicDataSource dataSource;

    @Resource
    public ClassPathResource[] urlResource;

    @Bean(name = "sqlSessionFactoryBean1")
    SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean sfb=new SqlSessionFactoryBean();
        sfb.setDataSource(dataSource);
        sfb.setMapperLocations(urlResource);
        sfb.setTypeAliasesPackage("com/kazaf/domain");
        return sfb;
    }

    @Bean
    MapperScannerConfigurer mapperconfig(){
        MapperScannerConfigurer msc=new MapperScannerConfigurer();
        msc.setBasePackage("com.kazaf.dao");
        msc.setSqlSessionFactoryBeanName("sqlSessionFactoryBean1");
        return msc;
    }*/



}
