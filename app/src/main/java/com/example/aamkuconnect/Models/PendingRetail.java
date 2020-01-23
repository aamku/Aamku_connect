package com.example.aamkuconnect.Models;

public class PendingRetail {

    String pendingName,pendingGst,pendingPhone;

    public PendingRetail(){

    }

    public PendingRetail(String pendingName,String pendingGst,String pendingPhone){

        this.pendingName = pendingName;
        this.pendingGst =  pendingGst;
        this.pendingPhone =  pendingPhone;

    }

    public String getPendingName() {
        return pendingName;
    }

    public void setPendingName(String pendingName) {
        this.pendingName = pendingName;
    }

    public String getPendingGst() {
        return pendingGst;
    }

    public void setPendingGst(String pendingGst) {
        this.pendingGst = pendingGst;
    }

    public String getPendingPhone() {
        return pendingPhone;
    }

    public void setPendingPhone(String pendingPhone) {
        this.pendingPhone = pendingPhone;
    }
}
