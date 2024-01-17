package com.example.ns_individualproj2;


import android.os.Parcel;

public class products  {

    private String PID;


    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public products(String PID, String name, String imageurl, String description,Double price, String quantity) {
        this.PID = PID;
        this.name = name;
        this.imageurl = imageurl;
        this.description = description;
        this.quantity = quantity;
        this.price = price;

    }

    private String name;


    private String imageurl;

    private String description;

    private String quantity;
    private Double price;

    protected products(Parcel in) {
        name = in.readString();
        imageurl = in.readString();
        description = in.readString();
        quantity = in.readString();
        if (in.readByte() == 0) {
            price = null;
        } else {
            price = in.readDouble();
        }
    }


    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}


