package com.kazaf.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Kazaf on 16/5/21.
 */
public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {



    @Override
    protected String[] getServletMappings() {
        return new String[]{"/Bill/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{SpringMVCConfig.class};
    }

}
