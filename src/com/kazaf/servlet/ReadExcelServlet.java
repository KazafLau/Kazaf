package com.kazaf.servlet;

import com.kazaf.domain.Bill;
import com.kazaf.plugins.ExecuteMySql;
import com.kazaf.plugins.JDBCMySql;
import com.kazaf.service.ExceltoMySql;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

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
@WebServlet(name = "ReadExcelServlet")
public class ReadExcelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ReadUpLoad(request, response);
        request.getRequestDispatcher("/message.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void ReadUpLoad(HttpServletRequest req,HttpServletResponse response){

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
                ExceltoMySql em=new ExceltoMySql();
                em.insertListStream("bill",3,stream);
                message = "文件读取成功！";
            }


        }catch(IOException x){
            x.printStackTrace();


        }catch(FileUploadException e){
            e.printStackTrace();


        }
        catch (InvalidFormatException e){
            e.printStackTrace();
        }

        req.setAttribute("message", message);
    }

}
