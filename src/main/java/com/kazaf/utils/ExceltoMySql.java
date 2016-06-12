package com.kazaf.utils;

import com.kazaf.pojos.BGinter;
import com.kazaf.pojos.Bill;
import com.kazaf.service.ExecuteMySql;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private Bill billtemp;
    private  Iterator<Row> itrow;
    private  Row rowtemp;

    //1.通过filepath导入excel表数据
    public void insertList(int month,String file)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        month=month-1;
        try{
            mysqlList= readExcel(file, month);
            ExecuteMySql.getCommonDao().insertBillList(mysqlList);
            ExecuteMySql.sessionCommit();
        }
        finally {
            ExecuteMySql.CloseMysql();
        }
    }

    //2.通过InputStream导入excel表数据
    public void insertListStream(int month,InputStream stream)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        month=month-1;
        OPCPackage pkg = OPCPackage.open(stream);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(month);
        if(stream!=null)
        {
            stream.close();
        }
        try {
            mysqlList =excelcell();
            ExecuteMySql.getCommonDao().insertBillList(mysqlList);
            ExecuteMySql.getSession().commit();
        }
        finally {
            ExecuteMySql.CloseMysql();
        }

    }

    //打开excel文件,将文件中的相应页面读取到List列表中----内部方法
    public  List readExcel(String file, int page) throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        ins = new FileInputStream(new File(file));
        OPCPackage pkg = OPCPackage.open(ins);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(page);
        ins.close();
        excelcell();
        return BGList;
    }

    //根据Excel单元格的列号来读取相应的数据----内部方法
    private void parseCellBill(Cell cell,Bill billtemple){
        switch (cell.getColumnIndex()){
            case 0:
                java.util.Date date = cell.getDateCellValue();
                result = sdf.format(date);
                billtemple.setBill_date(StringtoDate(result));
                break;
            case 1:
                billtemple.setBill_comments(cell.getRichStringCellValue().getString());
                break;
            case 2:
                billtemple.setBill_cost((float) cell.getNumericCellValue());
                break;
            case 3:
                billtemple.setBill_level((int) cell.getNumericCellValue());
                break;
        }
    }

    //将相应的String类型转化成日期类型----内部方法
    public Date StringtoDate(String sDate){
        Date dDate= Date.valueOf(sDate);
        return dDate;
    }

    //读取页面单元格内容到BGList列表中----内部方法
    private List excelcell(){
        itrow=excelsheet.rowIterator();
        itrow.next();
        itrow.remove();
        while (itrow.hasNext()) {
                billtemp = new Bill();
                rowtemp = itrow.next();
                for (Cell cell : rowtemp) {
                    parseCellBill(cell, billtemp);
                }
                BGList.add(billtemp);
            }
            return BGList;
    }


}
