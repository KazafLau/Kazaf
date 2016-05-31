package com.kazaf.utils;

import com.kazaf.pojos.BGinter;
import com.kazaf.pojos.Bill;
import com.kazaf.service.ExecuteMySql;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kazaf on 16/3/21.
 */
@Repository
public class ExceltoMySql {

    private List<BGinter> BGList=new ArrayList<BGinter>();
    private List mysqlList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
    private String result;
    private InputStream ins;
    private XSSFWorkbook wb;
    private Sheet excelsheet;

    //批量将mysqlList中的元素插入到数据库中,需结合readExcel方法的进行
    public void insertList(String type,int month,String file)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        month=month-1;

        ExecuteMySql ems=new ExecuteMySql();

        try{
            mysqlList= readExcel(file, month, type);
            if(type.equals("gym")){
                ems.getCommonDao().insertGymList(mysqlList);
            }else{
                ems.getCommonDao().insertBillList(mysqlList);
            }
            ems.sessionCommit();
        }
        finally {
            ems.CloseMysql();
        }

    }

    //打开excel文件,将文件中的相应页面读取到List列表中
    public  List readExcel(String file, int page, String type) throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {


        if (type.equals( "gym")) { page = page + 13;}

        ins = new FileInputStream(new File(file));

        OPCPackage pkg = OPCPackage.open(ins);
        wb = new XSSFWorkbook(pkg);
        //month=month-1;
        //wb=WorkbookFactory.create(ins);
        excelsheet = wb.getSheetAt(page);
        ins.close();


        Iterator<Row> itrow=excelsheet.rowIterator();
        itrow.next();
        itrow.remove();


            Bill billtemp;
            while(itrow.hasNext()){
                billtemp = new Bill();
                Row rowtemp=itrow.next();
                for(Cell cell:rowtemp){
                    parseCellBill(cell,billtemp);
                }
                BGList.add(billtemp);
            }



        //out = new FileOutputStream(file);
        //wb.write(out);
        return BGList;
    }

    //直接打开流文件来获取excel,是servlet中采取的方法,但是没有实例化ExceltoMysql,而是使用了静态方法
    public void insertListStream(String type,int month,InputStream stream)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        month=month-1;
        OPCPackage pkg = OPCPackage.open(stream);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(month);

        if(stream!=null)
        {
            stream.close();
        }
        Iterator<Row> itrow=excelsheet.rowIterator();
        itrow.next();
        itrow.remove();


        try {
            Bill billtemp;
            while (itrow.hasNext()) {
                billtemp = new Bill();
                Row rowtemp = itrow.next();
                for (Cell cell : rowtemp) {
                    parseCellBill(cell, billtemp);
                }
                BGList.add(billtemp);
            }
            mysqlList = BGList;
            ExecuteMySql.getCommonDao().insertBillList(mysqlList);
            ExecuteMySql.getSession().commit();
        }
        finally {
            ExecuteMySql.CloseMysql();
        }

    }

    //根据Excel单元格的列号来读取相应的数据
    private void parseCellBill(Cell cell,Bill billtemp){
        switch (cell.getColumnIndex()){
            case 0:
                java.util.Date date = cell.getDateCellValue();
                result = sdf.format(date);
                billtemp.setBill_date(StringtoDate(result));
                break;
            case 1:
                billtemp.setBill_comments(cell.getRichStringCellValue().getString());
                break;
            case 2:
                billtemp.setBill_cost((float) cell.getNumericCellValue());
                break;
            case 3:
                billtemp.setBill_level((int) cell.getNumericCellValue());
                break;
        }
    }

    //将相应的String类型转化成日期类型
    public Date StringtoDate(String sDate){
        Date dDate= Date.valueOf(sDate);
        return dDate;
    }


}
