package com.kazaf.service;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Kazaf on 16/4/5.
 */
@Component
public class BMRCalculatorService {


    enum Calorie{
        PROTEIN("蛋白质",4),FAT("脂肪",9),CHO("碳水化合物",4);
        private String name;
        private  double cal;
        Calorie(String name, float cal) {
            this.name = name;
            this.cal = cal;
        }

        public double getCal() {
            return cal;
        }

        public void setCal(float cal) {
            this.cal = cal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private BigDecimal weight;
    private  BigDecimal height;
    private  int age;
    private BigDecimal BMR;
    private BigDecimal CalPDIn;//每日应摄入热量
    private BigDecimal CalPDOut;//每日消耗热量

    public BMRCalculatorService() {
    }

    public BMRCalculatorService(BigDecimal weight, BigDecimal height, int age) {
        this.weight = weight;
        this.height = height;
        this.age = age;

        BMR=(new BigDecimal("66").add((new BigDecimal("13.7").multiply(weight)).add(new BigDecimal("5").multiply(height)))).subtract(new BigDecimal(6.8 * age));
        CalPDOut=BMR.multiply(new BigDecimal("1.55"));
        CalPDIn=CalPDOut.multiply(new BigDecimal("1.1"));
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getBMR() {
        return BMR;
    }

    public void setBMR(BigDecimal BMR) {
        this.BMR = BMR;
    }

    public void calculator(){

        System.out.println("每日正常的基础代谢率为:"+BMR.doubleValue()+"KCa");
        System.out.println("其中新陈代谢所消耗热量为;"+BMR.multiply(new BigDecimal("0.6")).doubleValue()+"KCa"+" 消化食物所消耗热量为:"+BMR.multiply(new BigDecimal("0.1")).doubleValue()+"KCa"+" 日常活动为:"+BMR.multiply(new BigDecimal("0.3")).doubleValue()+"KCa");
        System.out.println("每日应消耗热量:"+CalPDOut.doubleValue()+"KCa");
        System.out.println("每日应摄入热量:"+CalPDIn.doubleValue()+"KCa");
        //System.out.println("其中新陈代谢所消耗热量为;"+BMR*0.6+" 消化食物所消耗热量为:"+BMR*0.1+" 日产活动为:"+BMR*0.3);

   }

    public void diet(){

        System.out.println("摄入热量成份--");
        System.out.println(" 蛋白质:"+(CalPDIn.doubleValue()*0.2)/Calorie.PROTEIN.cal+"g");
        System.out.println(" 脂肪:"+(CalPDIn.doubleValue()*0.2)/Calorie.FAT.cal+"g");
        System.out.println(" 碳水:"+(CalPDIn.doubleValue()*0.6)/Calorie.CHO.cal+"g");

    }
}
