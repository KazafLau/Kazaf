package com.kazaf.test;

import com.kazaf.configuration.SpringMVCConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Kazaf on 16/5/17.
 */
public class SpringMybatisTest {

    public static void main(String[] args){
        //ApplicationContext ap=new ClassPathXmlApplicationContext("spring-mybatis.xml");

        ApplicationContext context=new AnnotationConfigApplicationContext(SpringMVCConfig.class);
        //String a=(String)context.getBean("hello");

       // System.out.print(a);
    }
}
