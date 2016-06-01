package com.stock.utils;

import com.stock.pojos.TradeInfo;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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

    private List<TradeInfo> BGList=new ArrayList<TradeInfo>();
    private List mysqlList;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");;
    private String result;
    private InputStream ins;
    private XSSFWorkbook wb;
    private Sheet excelsheet;
    private GetDate getDate=new GetDate();
    private TradeInfo tradeInfo;

    //批量将mysqlList中的元素插入到数据库中,需结合readExcel方法的进行
    public void insertList(String file)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        ExecuteMySql ems=new ExecuteMySql();
        mysqlList= readExcel(file);
        try{
            ExecuteMySql.getCWLDao().insertTradeList(mysqlList);
            ems.sessionCommit();
        }
        finally {
            ems.CloseMysql();
        }
    }

    //打开excel文件,将文件中的相应页面读取到List列表中
    public  List readExcel(String file) throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        ins = new FileInputStream(new File(file));
        OPCPackage pkg = OPCPackage.open(ins);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(0);
        ins.close();
        return  excelcell(excelsheet);
    }

    //直接打开流文件来获取excel,是servlet中采取的方法,但是没有实例化ExceltoMysql,而是使用了静态方法
    public void insertListStream(InputStream stream)throws IOException, EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        OPCPackage pkg = OPCPackage.open(stream);
        wb = new XSSFWorkbook(pkg);
        excelsheet = wb.getSheetAt(0);
        if(stream!=null)
        {
            stream.close();
        }
        mysqlList=excelcell(excelsheet);
        try {
            ExecuteMySql.getCWLDao().insertTradeList(mysqlList);
            ExecuteMySql.getSession().commit();
        }
        finally {
            ExecuteMySql.CloseMysql();
        }
    }

    //根据Excel单元格的列号来读取相应的数据
    private void parseCellBill(Cell cell, TradeInfo tradeInfo){
        switch (cell.getColumnIndex()){
            case 0:
                tradeInfo.setTradeTime(getDate.CellDate((int)cell.getNumericCellValue()));
                break;
            case 1:
                tradeInfo.setSerialNumber((int) cell.getNumericCellValue());
                break;
            case 2:
                tradeInfo.setZiJinID((int) cell.getNumericCellValue());
                break;
            case 3:
                tradeInfo.setStockID((int) cell.getNumericCellValue());
                break;
            case 4:
                tradeInfo.setStockName(cell.getRichStringCellValue().getString());
                break;
            case 5:
                tradeInfo.setAction(cell.getRichStringCellValue().getString());
                break;
            case 9:
                tradeInfo.setTradeMoney((float) cell.getNumericCellValue());
                break;
            case 10:
                tradeInfo.setTradeQuantity((float) cell.getNumericCellValue());
                break;
        }
    }

    //遍历一个excel页面中的每个cell
    private List excelcell(Sheet excelsheet){
        Iterator<Row> itrow=excelsheet.rowIterator();
        itrow.next();
        itrow.remove();
        while(itrow.hasNext()){
            tradeInfo = new TradeInfo();
            Row rowtemp=itrow.next();
            for(Cell cell:rowtemp){
                parseCellBill(cell,tradeInfo);
            }
            BGList.add(tradeInfo);
        }
        return BGList;

    }


}
