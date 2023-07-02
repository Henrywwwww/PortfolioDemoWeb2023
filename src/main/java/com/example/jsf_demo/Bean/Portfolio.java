package com.example.jsf_demo.Bean;

public class Portfolio {

    private String account;
    private String name;
    private String gender;
    private String age;
    private String university;
    private String major;
    private String IDnumber;

    private String content;

    public Portfolio(){

    }
    public Portfolio(String account,String name,String gender,String age,String university,String major ,String IDnumber,String content){
        this.account=account;
        this.name=name;
        this.gender=gender;
        this.age=age;
        this.university=university;
        this.major=major;
        this.IDnumber=IDnumber;
        this.content=content;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
