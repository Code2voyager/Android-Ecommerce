package com.example.ns_individualproj2;

public class orders {


    String Email;
    String Full_Name;
    String Phone_No;
    String Email_Id;
    String Address;
    String Zip_Code;
    String Country;
    String Payment;
    String Card_Name;
    String Card_No;
    String CCV;
    String Expiry;


    public orders(String email, String full_Name, String phone_No, String email_Id, String address, String zip_Code, String country, String payment, String card_Name, String card_No, String ccv,String expiry) {
        Email = email;
        Full_Name = full_Name;
        Phone_No = phone_No;
        Email_Id = email_Id;
        Address = address;
        Zip_Code = zip_Code;
        Country = country;
        Payment = payment;
        Card_Name = card_Name;
        Card_No = card_No;
        CCV = ccv;
        Expiry = expiry;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getPhone_No() {
        return Phone_No;
    }

    public void setPhone_No(String phone_No) {
        Phone_No = phone_No;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZip_Code() {
        return Zip_Code;
    }

    public void setZip_Code(String zip_Code) {
        Zip_Code = zip_Code;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    public String getCard_Name() {
        return Card_Name;
    }

    public void setCard_Name(String card_Name) {
        Card_Name = card_Name;
    }

    public String getCard_No() {
        return Card_No;
    }

    public void setCard_No(String card_No) {
        Card_No = card_No;
    }

    public String getCCV() {
        return CCV;
    }

    public void setCCV(String CCV) {
        this.CCV = CCV;
    }

    public String getExpiry() {
        return Expiry;
    }

    public void setExpiry(String expiry) {
        Expiry = expiry;
    }
}
