package com.kazaf.servletlistener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Kazaf on 16/4/17.
 */

@WebListener
public class RequestListener1 implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("新建了一次request请求:"+sre.getServletRequest());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }
}
