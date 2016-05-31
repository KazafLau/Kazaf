package com.kazaf.servletlistener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Kazaf on 16/4/17.
 */

@WebListener
public class SessionListener1 implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        System.out.println("新建了一个session:"+se.getSession().getId());
        int n=0;
        if(se.getSession().isNew()){
            ServletContext application=se.getSession().getServletContext();
            String counter=(String)application.getAttribute("counter");
            if(counter==null) n=1;
            else{
                n=Integer.parseInt(counter);
                n++;
            }
            application.setAttribute("counter",String.valueOf(n));
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
