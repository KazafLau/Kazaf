package com.kazaf.servletlistener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * 用来监听Request作用域属性发生的变化
 * Created by Kazaf on 16/4/17.
 */
@WebListener
public class RequestAttributeListener1 implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("Request添加了一个新的属性:"+srae.getName()+"="+srae.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {

        System.out.println("Request删除了一个属性:"+srae.getName()+"="+srae.getValue());

    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {

        System.out.println("Request替换了了一个属性:"+srae.getName()+"="+srae.getValue());

    }
}
