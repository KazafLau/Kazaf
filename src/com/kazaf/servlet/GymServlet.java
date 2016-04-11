package com.kazaf.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kazaf on 16/4/4.
 */
@WebServlet(name = "GymServlet")
public class GymServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));

       // request.getRequestDispatcher("gym.jsp").include(request,response);
        request.getRequestDispatcher("gym.jsp").forward(request,response);
       // response.sendRedirect("gym.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
