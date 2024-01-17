package com.example.ns_individualproj2;

import java.util.ArrayList;

public class cartsignleton {

    private static cartsignleton INSTANCE;
    private String name;


    private Double price;
private ArrayList<products> ProductList;

    public ArrayList<products> getProductList() {
        return ProductList;
    }

    public void setProductList(ArrayList<products> productList) {
        ProductList = productList;
    }

    private cartsignleton() {
    }

    public static cartsignleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new cartsignleton();
        }

        return INSTANCE;


    }

    public static cartsignleton getINSTANCE() {
        return INSTANCE;
    }

    public static void setINSTANCE(cartsignleton INSTANCE) {
        cartsignleton.INSTANCE = INSTANCE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}