package com.msword.utils;

import com.msword.pojo.Customer;
import com.stock.pojos.TradeInfo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kazaf on 16/5/30.
 */
public class ReadfromExcel {

    List<Customer> customerList=new ArrayList<Customer>();
    private InputStream ins;
    private XSSFWorkbook wb;
    private Sheet excelsheet;

    public List readcustomer(String filepath)throws InvalidFormatException,IOException{
        ins = new FileInputStream(new File(filepath));
        OPCPackage pkg = OPCPackage.open(ins);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(0);
        ins.close();

        Iterator<Row> itrow=excelsheet.rowIterator();
        itrow.next();
        itrow.remove();

        Customer a;
        while(itrow.hasNext()){
            a=new Customer("","","","","");
            Row rowtemp=itrow.next();
            for(Cell cell:rowtemp){
                parseCellBill(cell,a);
            }
            customerList.add(a);
        }
        return customerList;
    }

    private void parseCellBill(Cell cell, Customer customer){
        switch (cell.getColumnIndex()){
            case 0:
                customer.setCustomerid((int) cell.getNumericCellValue()+"");
                break;
            case 1:
                customer.setCustomername(cell.getRichStringCellValue().getString());
                break;
            case 2:
                customer.setCustomertel(cell.getRichStringCellValue().getString());
                break;
            case 3:
                customer.setCustomerphone(cell.getRichStringCellValue().getString());
                break;
            case 4:
                customer.setCustomeremail(cell.getRichStringCellValue().getString());
                break;
        }
    }

    public void printInfo(){
        Iterator<Customer> customerIterator=customerList.iterator();
        Customer a;
        while(customerIterator.hasNext()){
            a=customerIterator.next();
            System.out.println(a.getCustomerid()+" "+a.getCustomername()+" "+a.getCustomerphone()+" "+a.getCustomertel()+" "+a.getCustomeremail());
        }
    }
}
