package com.example.aamkuconnect.Models;

public class DealerModel {

    String dName,dPhone,dLocation,dPin,dAdr;

    public DealerModel(){

    }

    public DealerModel(String dName,String dPhone,String dLocation,String dPin,String dAdr){

        this.dName = dName;
        this.dPhone = dPhone;
        this.dLocation = dLocation;
        this.dPin = dPin;
        this.dAdr = dAdr;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdPhone() {
        return dPhone;
    }

    public void setdPhone(String dPhone) {
        this.dPhone = dPhone;
    }

    public String getdLocation() {
        return dLocation;
    }

    public void setdLocation(String dLocation) {
        this.dLocation = dLocation;
    }

    public String getdPin() {
        return dPin;
    }

    public void setdPin(String dPin) {
        this.dPin = dPin;
    }

    public String getdAdr() {
        return dAdr;
    }

    public void setdAdr(String dAdr) {
        this.dAdr = dAdr;
    }
}
