package com.stock.dao;

import com.stock.pojos.TradeInfo;

import java.util.List;

/**
 * Created by Kazaf on 16/5/23.
 */
public interface TradeInfoDAO {

    public TradeInfo getTradeInfo(int SerialNumber);
    public void insertTradeInfo(TradeInfo tradeInfo);
    public List<TradeInfo> getALLTradeInfo();
    public void insertTradeList(List<TradeInfo> Tradeinfolist);
}
