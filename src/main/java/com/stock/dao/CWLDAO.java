package com.stock.dao;

import com.stock.pojos.TradeInfo;

import java.util.List;

/**
 * Created by Kazaf on 16/5/23.
 */
public interface CWLDAO {

    public TradeInfo getCWL(int SerialNumber);
    public void insertCWL(TradeInfo tradeInfo);
    public List<TradeInfo> getALLCWL();

    public void insertTradeList(List<TradeInfo> Tradeinfolist);
}
