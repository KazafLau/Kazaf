package com.kazaf.service.impl;


import com.kazaf.pojos.Bill;
import com.kazaf.service.IBillService;
import com.kazaf.service.IDateCalculatorService;
import com.kazaf.utils.ExceltoMySql;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 * Created by Kazaf on 16/5/17.
 */
@Service("BillService")
public class BillServiceImpl implements IBillService {



    @Resource
    private ExceltoMySql etm;
    @Resource
    private IDateCalculatorService DateCalculatorService;

    @Override
    public void insertList(String file, int month) {
        try{
            if(etm==null) etm=new ExceltoMySql();
            etm.insertList(month,file);
        }
        catch (InvalidFormatException e){
           e.printStackTrace();
        }
        catch (EncryptedDocumentException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void insertListStream(int month, InputStream stream) {
        try{
            if(etm==null) etm = new ExceltoMySql();
            etm.insertListStream(month,stream);
        }
        catch (InvalidFormatException e){
            e.printStackTrace();
        }
        catch (EncryptedDocumentException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Bill> getBill(Date startdate,Date enddate) {


        return etm.getBillbyMonth(startdate,enddate);
    }
}
