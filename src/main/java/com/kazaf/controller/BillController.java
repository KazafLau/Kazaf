package com.kazaf.controller;

import com.kazaf.service.IBillService;
import com.kazaf.service.IDateCalculatorService;
import com.kazaf.service.impl.BillServiceImpl;
import com.kazaf.service.impl.DateCalculatorServiceImpl;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

/**
 * Created by Kazaf on 16/5/20.
 */
@Controller
public class BillController {

    @Resource
    private IBillService BillService;

    @Resource
    private IDateCalculatorService DateCalculatorService;

    @RequestMapping(value = "/uploadservlet")
    public String  ReadUpLoad(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        String message = "";
        ServletFileUpload upload = new ServletFileUpload();
        try{
            FileItemIterator iter = upload.getItemIterator(req);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    System.out.println("Form field "+name+" with value "+ Streams.asString(stream)+ " detected.");
                } else {
                    System.out.println("File field "+name+" with file name "+item.getName()+" detected.");
                }
               //此处使用了Spring中的Bean
                BillService.insertListStream(4,stream);
                message = "文件读取成功！";
            }
        }catch(IOException x){
            x.printStackTrace();
            System.out.println("ReadUpLoad中的IOException");
        }catch(FileUploadException e){
            e.printStackTrace();
            System.out.println("ReadUpLoad中的FileUpLoadException");
        }
        req.setAttribute("message", message);
        //req.getRequestDispatcher("/message.jsp").forward(req, resp);
        return "message";
    }

    @RequestMapping(value = "/getbillbymonth")
    public String getBillbyMonth(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{

        int month=1;
        month=Integer.parseInt(req.getParameter("month"));

        DateCalculatorService=new DateCalculatorServiceImpl(month);

        Date firstdate=new Date(DateCalculatorService.getFirstday().getTime());
        Date lastday=new Date(DateCalculatorService.getLastday().getTime());

        req.setAttribute("getBiibyMonth",BillService.getBill(firstdate,lastday));
        req.setAttribute("firstday",firstdate);
        req.setAttribute("lastday",lastday);
        req.setAttribute("cosumedays",DateCalculatorService.getCosumedays());
        req.setAttribute("days",DateCalculatorService.getDays());
        req.getRequestDispatcher("/getBillbyMonth.jsp").forward(req,resp);

        return null;
    }

    @RequestMapping(value = "/GymServlet")
    public String gymtest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        System.out.println("转发前的mmmmmmmm"+request.getAttribute("rtest"));
        //request.getRequestDispatcher("gym.jsp").include(request,response);
        //request.getRequestDispatcher("gym.jsp").forward(request, response);
        //response.sendRedirect("gym.jsp");
        //System.out.println(request.getAttribute("rtest"));
        System.out.println("转发后的mmmmmmmm"+request.getAttribute("rtest"));
        request.setAttribute("rtest", "hello");
        //HttpSession session=request.getSession();
        request.getSession().setAttribute("stest", "hellosession");
        System.out.println(request.getSession().getAttribute("stest"));
        System.out.println(request.getAttribute("rtest"));
        System.out.println("from controller");
        return  "gym";
    }
}
