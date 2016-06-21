package com.kazaf.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kazaf on 16/3/29.
 */


//@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println(java.nio.charset.Charset.defaultCharset() );

        System.out.println("doGet()........初次");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost()........");
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service(HS,HS)........");
        super.service(req, resp);
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service(S,S)........");
        super.service(req, res);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init(ServletConfig)........");
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init()........");
        super.init();
    }

    @Override
    public void destroy() {
        System.out.println("destory()........");
        super.destroy();
    }
}
