package com.stock.pojos;

import java.sql.Date;

/**
 * Created by Kazaf on 16/5/23.
 */
public class TradeInfo {

    private int ZiJinID;
    private int StockID;
    private String StockName;
    private float  TradeMoney;
    private float  TradeQuantity;
    private Date    TradeTime;

    public int getZiJinID() {
        return ZiJinID;
    }

    public void setZiJinID(int ziJinID) {
        ZiJinID = ziJinID;
    }

    public int getStockID() {
        return StockID;
    }

    public void setStockID(int stockID) {
        StockID = stockID;
    }

    public String getStockName() {
        return StockName;
    }

    public void setStockName(String stockName) {
        StockName = stockName;
    }

    public float getTradeMoney() {
        return TradeMoney;
    }

    public void setTradeMoney(float tradeMoney) {
        TradeMoney = tradeMoney;
    }

    public float getTradeQuantity() {
        return TradeQuantity;
    }

    public void setTradeQuantity(float tradeQuantity) {
        TradeQuantity = tradeQuantity;
    }

    public Date getTradeTime() {
        return TradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        TradeTime = tradeTime;
    }

    public int getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }

    private int SerialNumber;
    private String  Action;
}
