package com.hackdelhi.dyingtocode.wallettracker;

/**
 * Created by ishaan on 26/7/15.
 */
public class Person {

    private String phone_no;
    private String pass;
    private String name;
    private String mailid;

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String name) {
        this.phone_no = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String country) {
        this.pass = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String country) {
        this.name = country;
    }

    public String getMailid() {
        return mailid;
    }

    public void setMailid(String country) {
        this.mailid = country;
    }


    @Override
    public String toString() {
        return "Person [phone_no:" + phone_no + ",password :" + pass + ",name:" + name + ",email:" + mailid +"]";
    }
}