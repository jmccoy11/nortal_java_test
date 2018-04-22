package com.nortal.expenditure.model;

import java.util.Date;

public class Expenditure {
    private Date date;
    private String supplier;
    private String type;
    // TODO need more...



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
