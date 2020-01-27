package com.example.aamkuconnect.Models;

import com.example.aamkuconnect.AddExcel;

public class AddExcelSalesModel {

    String saleName,salePhone;

    public AddExcelSalesModel(){

    }

    public AddExcelSalesModel(String saleName,String salePhone){

        this.saleName = saleName;
        this.salePhone = salePhone;

    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone;
    }
}
