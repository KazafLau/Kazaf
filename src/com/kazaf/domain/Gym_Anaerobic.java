package com.kazaf.domain;

import java.util.Date;

/**
 * Created by Kazaf on 16/4/4.
 */
public class Gym_Anaerobic implements BGinter {

    private Date gym_date;
    private String gym_option;
    private String gym_part;
    private float gym_weight;
    private int gym_rounds;
    private int gym_reps;
    private int gym_int;

    public Gym_Anaerobic() {
    }


    public Date getGym_date() {
        return gym_date;
    }

    public void setGym_date(Date gym_date) {
        this.gym_date = gym_date;
    }

    public String getGym_option() {
        return gym_option;
    }

    public void setGym_option(String gym_option) {
        this.gym_option = gym_option;
    }

    public String getGym_part() {
        return gym_part;
    }

    public void setGym_part(String gym_part) {
        this.gym_part = gym_part;
    }

    public float getGym_weight() {
        return gym_weight;
    }

    public void setGym_weight(float gym_weight) {
        this.gym_weight = gym_weight;
    }

    public int getGym_rounds() {
        return gym_rounds;
    }

    public void setGym_rounds(int gym_rounds) {
        this.gym_rounds = gym_rounds;
    }

    public int getGym_reps() {
        return gym_reps;
    }

    public void setGym_reps(int gym_reps) {
        this.gym_reps = gym_reps;
    }

    public int getGym_int() {
        return gym_int;
    }

    public void setGym_int(int gym_int) {
        this.gym_int = gym_int;
    }
}
