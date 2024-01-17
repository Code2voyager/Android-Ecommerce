package com.example.ns_individualproj2;

public class cartItems {

    String product_name;
    String product_image;
    Double product_price;
    String product_quantity;
    String PID;

    private String product_size;

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public cartItems(String pid, String product_name, String product_image,String product_size, Double product_price, String product_quantity) {
       this.PID = pid;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.product_size = product_size;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }
}
