package com.kazaf.service.impl;


import com.kazaf.service.IBillService;
import com.kazaf.utils.ExceltoMySql;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Kazaf on 16/5/17.
 */
@Service("BillService")
public class BillServiceImpl implements IBillService {



    @Resource
    private ExceltoMySql etm;

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
}
