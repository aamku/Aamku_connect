package com.example.aamkuconnect.Models;

public class AllRetailerModel {

    String my_retailer_name,retailer_date;

   public AllRetailerModel(){

    }

    public AllRetailerModel(String my_retailer_name,String retailer_date){

        this.my_retailer_name = my_retailer_name;
        this.retailer_date = retailer_date;
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
}
