package com.nortal.expenditure.model;

import java.util.Date;

public class Expenditure {
    private Date date;
    private String supplier;
    private String type;
    private String product;
    private Integer units;
    private Double unitPrice;
    private Double tax;

    public Expenditure(Date date, String supplier, String type, String product, Integer units,
                       Double unitPrice, Double tax) {
        this.date = date;
        this.supplier = supplier;
        this.type = type;
        this.product = product;
        this.units = units;
        this.unitPrice = unitPrice;
        this.tax = tax;
    }

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

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Expenditure{" +
                "date=" + date +
                ", supplier='" + supplier + '\'' +
                ", type='" + type + '\'' +
                ", product='" + product + '\'' +
                ", units='" + units + '\'' +
                ", unitPrice=" + unitPrice +
                ", tax=" + tax +
                '}';
    }
}
