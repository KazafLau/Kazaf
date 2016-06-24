package com.msword.utils;

import com.msword.pojo.Customer;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kazaf on 16/5/30.
 */
public class WritefromExcel{

    String templatePath = "templatenew.doc";


        public void testWrite(Customer a)throws IOException{
            InputStream is = new FileInputStream(templatePath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            range.replaceText("${username}", a.getCustomername());
            range.replaceText("${userID}", a.getCustomerid());
            range.replaceText("${userphone}", a.getCustomerphone());
            range.replaceText("${usertel}", a.getCustomertel());
            range.replaceText("${useremail}", a.getCustomeremail());
            OutputStream os = new FileOutputStream("antimoney/"+a.getCustomername()+a.getCustomerid()+".doc");
            doc.write(os);
            this.closeStream(os);
            this.closeStream(is);
        }

        /**
         * 关闭输出流
         * @param os
         */
        private void closeStream(OutputStream os) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 关闭输入流
         * @param is
         */
        private void closeStream(InputStream is) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void WritefromList(List<Customer> customerList){


        Iterator<Customer> customerIterator=customerList.iterator();
        Customer a;
        while(customerIterator.hasNext()){
            a=customerIterator.next();
            System.out.println(a.getCustomerid()+" "+a.getCustomername()+" "+a.getCustomerphone()+" "+a.getCustomertel()+" "+a.getCustomeremail());
            try{
                testWrite(a);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }

}
