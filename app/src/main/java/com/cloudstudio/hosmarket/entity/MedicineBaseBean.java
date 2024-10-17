package com.cloudstudio.hosmarket.entity;

/**
 * @ClassName MedicineBaseBean
 * @Author Create By matrix
 * @Date 2024/10/17 15:56
 */
public class MedicineBaseBean {
    private String medicine_code;
    private String medicine_name;
    private float medicine_price;
    private String medicine_time;
    private float medicine_retail;

    public String getMedicine_code() {
        return medicine_code;
    }

    public void setMedicine_code(String medicine_code) {
        this.medicine_code = medicine_code;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public float getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(float medicine_price) {
        this.medicine_price = medicine_price;
    }

    public String getMedicine_time() {
        return medicine_time;
    }

    public void setMedicine_time(String medicine_time) {
        this.medicine_time = medicine_time;
    }

    public float getMedicine_retail() {
        return medicine_retail;
    }

    public void setMedicine_retail(float medicine_retail) {
        this.medicine_retail = medicine_retail;
    }
}
