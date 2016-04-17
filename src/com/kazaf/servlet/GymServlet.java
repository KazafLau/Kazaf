package com.kazaf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Kazaf on 16/4/4.
 */
@WebServlet(name = "GymServlet")
public class GymServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));


        System.out.println("转发前的mmmmmmmm"+request.getAttribute("rtest"));
       // request.getRequestDispatcher("gym.jsp").include(request,response);
        request.getRequestDispatcher("gym.jsp").forward(request, response);
       // response.sendRedirect("gym.jsp");
        //System.out.println(request.getAttribute("rtest"));

        System.out.println("转发后的mmmmmmmm"+request.getAttribute("rtest"));


        request.setAttribute("rtest", "hello");
        //HttpSession session=request.getSession();
        request.getSession().setAttribute("stest", "hellosession");

        System.out.println(request.getSession().getAttribute("stest"));
        System.out.println(request.getAttribute("rtest"));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
