package com.msword.pojo;

/**
 * Created by Kazaf on 16/5/30.
 */
public class Customer {

    private String customername;
    private String customerid;
    private String customertel;
    private String customerphone;
    private String customeremail;

    public Customer(String customername, String customerid, String customertel, String customerphone, String customeremail) {
        this.customername = customername;
        this.customerid = customerid;
        this.customertel = customertel;
        this.customerphone = customerphone;
        this.customeremail = customeremail;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomertel() {
        return customertel;
    }

    public void setCustomertel(String customertel) {
        this.customertel = customertel;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getCustomeremail() {
        return customeremail;
    }

    public void setCustomeremail(String customeremail) {
        this.customeremail = customeremail;
    }
}
