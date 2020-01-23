package com.example.aamkuconnect.Models;

public class AllRetailerModel {

    String my_retailer_name,retailer_date,retailer_status;

   public AllRetailerModel(){

    }

    public AllRetailerModel(String my_retailer_name,String retailer_date,String retailer_status){

        this.my_retailer_name = my_retailer_name;
        this.retailer_date = retailer_date;
        this.retailer_status = retailer_status;
    }

    public String getMy_retailer_name() {
        return my_retailer_name;
    }

    public void setMy_retailer_name(String my_retailer_name) {
        this.my_retailer_name = my_retailer_name;
    }

    public String getRetailer_date() {
        return retailer_date;
    }

    public void setRetailer_date(String retailer_date) {
        this.retailer_date = retailer_date;
    }

    public String getRetailer_status() {
        return retailer_status;
    }

    public void setRetailer_status(String retailer_status) {
        this.retailer_status = retailer_status;
    }
}
