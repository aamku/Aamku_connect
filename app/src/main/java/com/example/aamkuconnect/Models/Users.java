package com.example.aamkuconnect.Models;

public class Users {

    String name,role,phone,email,empid;

    public Users(){

    }

    public Users(String name,String role,String phone,String email,String empid){

        this.name = name;
        this.role = role;
        this.phone = phone;
        this.email = email;
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }
}
