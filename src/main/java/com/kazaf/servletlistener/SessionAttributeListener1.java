package com.kazaf.servletlistener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 用来监听Session作用域属性发生的变化
 * Created by Kazaf on 16/4/17.
 */

@WebListener
public class SessionAttributeListener1 implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

        System.out.println("session添加了一个新的属性:"+se.getName()+"="+se.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {


        System.out.println("session删除了一个属性:"+se.getName()+"="+se.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

        System.out.println("session替换了一个属性:"+se.getName()+"="+se.getValue());

    }
}
