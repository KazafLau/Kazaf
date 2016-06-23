package com.kazaf.servlet;

import com.kazaf.service.IBillService;
import com.kazaf.service.impl.BillServiceImpl;
import com.kazaf.utils.ExceltoMySql;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kazaf on 16/4/15.
 */

//@WebServlet(urlPatterns = {"/ReadExcelServlet"},name = "ReadExcelServlet")
public class ReadExcelServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ReadUpLoad(request);
        request.getRequestDispatcher("/message.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void ReadUpLoad(HttpServletRequest req){


        String message = "";
        ServletFileUpload upload = new ServletFileUpload();

        try{
            FileItemIterator iter = upload.getItemIterator(req);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                InputStream stream = item.openStream();
                if (item.isFormField()) {
                    System.out.println("Form field "+name+" with value "+Streams.asString(stream)+ " detected.");
                } else {
                    System.out.println("File field "+name+" with file name "+item.getName()+" detected.");
                }
                IBillService billService=new BillServiceImpl();
                billService.insertListStream(4,stream);
                message = "文件读取成功！!这是来自Servlet";
            }


        }catch(IOException x){
            x.printStackTrace();
            System.out.println("ReadUpLoad中的IOException");


        }catch(FileUploadException e){
            e.printStackTrace();
            System.out.println("ReadUpLoad中的FileUpLoadException");


        }


        req.setAttribute("message", message);
    }

}
