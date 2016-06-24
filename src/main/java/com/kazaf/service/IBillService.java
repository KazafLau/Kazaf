package com.kazaf.service;

import com.kazaf.pojos.Bill;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 * Bill上的数据操作
 * Created by Kazaf on 16/5/17.
 */
public interface IBillService {

    //批量将mysqlList中的元素插入到数据库中,需结合readExcel方法的进行
    void insertList(String file,int month);

    //直接打开流文件来获取excel,是servlet中采取的方法,但是没有实例化ExceltoMysql,而是使用了静态方法
    void insertListStream(int month,InputStream stream);

    //通过输入月份来查询当月的相关账单
    List<Bill> getBill(Date startdate,Date enddate);

}
