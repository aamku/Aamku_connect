package com.example.aamkuconnect.Models;

public class AdminSeeRetailerModel {

    String adminseeName,adminseePhone,adminseeGst,adminseeState,adminseePin,adminseeAddress;

    public AdminSeeRetailerModel(){

    }

    public AdminSeeRetailerModel(String adminseeName,String adminseePhone,String adminseeGst,
                        String adminseeState,String adminseePin,String adminseeAddress){

        this.adminseeName = adminseeName;
        this.adminseePhone = adminseePhone;
        this.adminseeGst = adminseeGst;
        this.adminseeState =adminseeState;
        this.adminseePin = adminseePin;
        this.adminseeAddress = adminseeAddress;

    }

    public String getAdminseeName() {
        return adminseeName;
    }

    public void setAdminseeName(String adminseeName) {
        this.adminseeName = adminseeName;
    }

    public String getAdminseePhone() {
        return adminseePhone;
    }

    public void setAdminseePhone(String adminseePhone) {
        this.adminseePhone = adminseePhone;
    }

    public String getAdminseeGst() {
        return adminseeGst;
    }

    public void setAdminseeGst(String adminseeGst) {
        this.adminseeGst = adminseeGst;
    }

    public String getAdminseeState() {
        return adminseeState;
    }

    public void setAdminseeState(String adminseeState) {
        this.adminseeState = adminseeState;
    }

    public String getAdminseePin() {
        return adminseePin;
    }

    public void setAdminseePin(String adminseePin) {
        this.adminseePin = adminseePin;
    }

    public String getAdminseeAddress() {
        return adminseeAddress;
    }

    public void setAdminseeAddress(String adminseeAddress) {
        this.adminseeAddress = adminseeAddress;
    }
}
