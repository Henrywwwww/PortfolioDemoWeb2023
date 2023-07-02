package com.example.jsf_demo.Bean;

public class User {

    private String account;
    private String passwords;

    public User(){

    }

    public User(String account,String keywords){
        this.account=account;
        this.passwords =keywords;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }
}
