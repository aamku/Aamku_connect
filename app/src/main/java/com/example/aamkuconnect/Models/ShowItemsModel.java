package com.example.aamkuconnect.Models;

public class ShowItemsModel {

    String pname,psku,ppages,pmrp,pinner,pouter;

    public ShowItemsModel(){

    }

    public ShowItemsModel(String pname,String psku,String ppages,String pmrp,String pinner,String pouter){

        this.pname = pname;
        this.psku = psku;
        this.ppages = ppages;
        this.pmrp = pmrp;
        this.pinner = pinner;
        this.pouter = pouter;

    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPsku() {
        return psku;
    }

    public void setPsku(String psku) {
        this.psku = psku;
    }

    public String getPpages() {
        return ppages;
    }

    public void setPpages(String ppages) {
        this.ppages = ppages;
    }

    public String getPmrp() {
        return pmrp;
    }

    public void setPmrp(String pmrp) {
        this.pmrp = pmrp;
    }

    public String getPinner() {
        return pinner;
    }

    public void setPinner(String pinner) {
        this.pinner = pinner;
    }

    public String getPouter() {
        return pouter;
    }

    public void setPouter(String pouter) {
        this.pouter = pouter;
    }
}
