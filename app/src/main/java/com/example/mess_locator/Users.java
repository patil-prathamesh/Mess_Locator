package com.example.mess_locator;

public class Users {
    String messName,address;
    public Users () {

    }
    public Users(String messName,String address) {
        this.messName = messName;
        this.address = address;
    }
    public String getMessName() {
        return messName;
    }
    public void setMessName(String messName) {
        this.messName = messName;
    }
    public String getaddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
