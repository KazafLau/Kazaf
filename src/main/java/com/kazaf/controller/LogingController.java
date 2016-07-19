package com.kazaf.controller;

import com.kazaf.service.ExecuteMySql;
import com.kazaf.utils.ExceltoMySql;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kazaf on 16-6-23.
 */
@Controller
public class LogingController {

    @Resource
    private ExceltoMySql etm;

    @RequestMapping("/Login")
    public String Login(HttpServletRequest req, HttpServletResponse resp){
        String message=null;
        if(req.getParameter("username").equals("kazaf")&&req.getParameter("password").equals("19881129"))
            message="upload";
        else message="index";
        if(req.getSession().getAttribute("GroupMonth")==null)
        {
        req.getSession().setAttribute("GroupMonth", ExecuteMySql.getGroupMonthList());
        }
        System.out.println("thisis the groupmonth"+req.getSession().getAttribute("GroupMonth"));
        return message;
    }
}
