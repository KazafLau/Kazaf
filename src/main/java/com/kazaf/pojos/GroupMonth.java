package com.kazaf.pojos;

import java.sql.Date;

/**
 * Created by kazaf on 16-6-24.
 */
public class GroupMonth {

    private float necessary;
    private float unnecessary;
    private float totall;
    private String monthdate;

    public float getNecessary() {
        return necessary;
    }

    public void setNecessary(float necessary) {
        this.necessary = necessary;
    }

    public float getUnnecessary() {
        return unnecessary;
    }

    public void setUnnecessary(float unnecessary) {
        this.unnecessary = unnecessary;
    }

    public float getTotall() {
        return totall;
    }

    public void setTotall(float totall) {
        this.totall = totall;
    }

    public String getMonthdate() {
        return monthdate;
    }

    public void setMonthdate(String monthdate) {
        this.monthdate = monthdate;
    }
}
