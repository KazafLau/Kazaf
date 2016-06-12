package com.kazaf.Interceptors;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Kazaf on 2016/6/10.
 */
public class TimeInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long starttime=System.currentTimeMillis();
        request.setAttribute("starttime",starttime);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long starttime=(Long)request.getAttribute("starttime");
        request.removeAttribute("starttime");
        long endtime=System.currentTimeMillis();
        System.out.println("本次请求处理时间为:"+new Long(endtime-starttime)+"ms");
        request.setAttribute("handlingTime",endtime -starttime);
    }
}
