package com.example.aamkuconnect.Models;

public class PendingRetail {

    String name,gst,phone;

    public PendingRetail(){

    }

    public PendingRetail(String name,String gst,String phone){

        this.name = name;
        this.gst =  gst;
        this.phone =  phone;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
