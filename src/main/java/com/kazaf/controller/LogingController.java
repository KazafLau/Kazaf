package com.kazaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kazaf on 16-6-23.
 */
@Controller
public class LogingController {

    @RequestMapping("/Login")
    public String Login(HttpServletRequest req, HttpServletResponse resp){
        String message=null;
        if(req.getParameter("username").equals("kazaf")&&req.getParameter("password").equals("19881129"))
            message="upload";
        else message="index";
        return message;
    }
}
