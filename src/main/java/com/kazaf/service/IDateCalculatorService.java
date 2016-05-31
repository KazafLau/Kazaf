package com.kazaf.service;

import java.util.Date;

/**
 * Created by Kazaf on 16/5/20.
 */
public interface IDateCalculatorService {

    void setCosumedays(int cosumedays);

    Date getFirstday();

    Date getLastday();

    int getCosumedays();

    int getDays();

    Date getDay();

}
