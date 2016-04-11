package com.kazaf.service;

import com.kazaf.domain.BGinter;
import com.kazaf.domain.Bill;
import com.kazaf.domain.Gym;
import com.kazaf.plugins.ExecuteMySql;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazaf on 16/3/21.
 */
public class ExceltoMySql {




    private List<BGinter> BGList=new ArrayList<BGinter>();
    private List mysqlList;
    private SimpleDateFormat sdf;
    private String result;
    private InputStream ins;
    private OutputStream out;
    private Workbook wb;
    private Sheet excelsheet;


    //批量将mysqlList中的元素插入到数据库中
    public void insertList(String type,int month,String file)throws IOException,InvalidFormatException{
        month=month-1;
        try{
            mysqlList= readExcel(file, month, type);
            if(type.equals("gym")){
                ExecuteMySql.getCommonDao().insertGymList(mysqlList);
            }else{
                ExecuteMySql.getCommonDao().insertBillList(mysqlList);
            }
            ExecuteMySql.getSession().commit();
        }
        finally {
            ExecuteMySql.CloseMysql();
        }

    }

    //打开excel文件,将文件中的相应页面读取到List列表中
    public  List readExcel(String file, int page, String type) throws IOException, EncryptedDocumentException, InvalidFormatException {
        if (type.equals( "gym")) { page = page + 13;}

        ins = new FileInputStream(new File(file));
        wb=WorkbookFactory.create(ins);
        excelsheet = wb.getSheetAt(page);
        ins.close();

        int recordnum = excelsheet.getLastRowNum() + 1;
        Row excelrow = excelsheet.getRow(0);
        int columnum = excelrow.getLastCellNum();
        Cell excelcell;

        if (type .equals("bill"))
        {
            Bill billtemp;
            for (int i = 1; i < recordnum; i++) {
                billtemp = new Bill();
                excelrow = excelsheet.getRow(i);
                for (int j = 0; j < columnum; j++) {
                    excelcell = excelrow.getCell(j);
                    parseExceltoBill(excelcell, billtemp, j);
                }
                BGList.add(billtemp);
            }

        }
        else{
            Gym gymtemp;
            for (int i = 1; i < recordnum; i++) {
                gymtemp = new Gym();
                excelrow = excelsheet.getRow(i);
                for (int j = 0; j < columnum; j++) {
                    excelcell = excelrow.getCell(j);
                    parseExceltoGym(excelcell, gymtemp, j);
                }
                BGList.add(gymtemp);
                System.out.println();
            }
        }
        out = new FileOutputStream(file);
        wb.write(out);
        return BGList;
    }

    //读取Excel中Bill数据并判断格式提取
    private String parseExceltoBill(Cell cell, Bill billtemp, int j) {
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    java.util.Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                    billtemp.setBill_date(StringtoDate(result));
                    System.out.print("输出时间");
                    System.out.print("billtemp日期" + billtemp.getBill_date());
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    java.util.Date date = DateUtil.getJavaDate(value);
                    result = sdf.format(date);
                    System.out.print("输出日期");
                } else {
                    double value = cell.getNumericCellValue();
                    float tempvalue = (float) value;
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#");
                    }
                    result = format.format(value);
                    switch (j) {
                        case 2: {
                            billtemp.setBill_cost(tempvalue);
                            System.out.print("输出cost" + billtemp.getBill_cost());
                            break;
                        }
                        case 3: {
                            billtemp.setBill_level((int) tempvalue);
                            System.out.print("输出level" + billtemp.getBill_level());
                            break;
                        }
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                billtemp.setBill_comments(result);
                System.out.print("输出String");
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
                System.out.print("输出Blank");
            default:
                result = "";
                System.out.print("输出default");
                break;
        }
        return result;
    }

    //读取Excel中BGym数据并判断格式提取
    private String parseExceltoGym(Cell cell, Gym gymtemp, int j) {
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    java.util.Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                    gymtemp.setGym_date(StringtoDate(result));
                    System.out.print("输出时间");
                    System.out.print("billtemp日期" + gymtemp.getGym_date());
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                switch (j) {
                    case 1: {
                        gymtemp.setGym_item(result);
                        System.out.print("输出item" + gymtemp.getGym_item());
                        break;
                    }
                    case 2: {
                        gymtemp.setGym_comments(result);
                        System.out.print("输出comments" + gymtemp.getGym_comments());
                        break;
                    }
                }
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
                System.out.print("输出Blank");
                break;
            default:
                result = "";
                System.out.print("输出default");
                break;
        }
        return result;
    }

    //将相应的String类型转化成日期类型
    public Date StringtoDate(String sDate){
        Date dDate= Date.valueOf(sDate);
        return dDate;
    }


}
