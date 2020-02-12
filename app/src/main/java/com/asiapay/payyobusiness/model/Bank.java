package com.asiapay.payyobusiness.model;

public class Bank {

    int id;
   // String addBank;
    String bankName;
    String accountNo;
    String ifscCode;


    public Bank(int id, String bankName, String accountNo, String ifscCode) {
        this.id = id;
        this.bankName = bankName;
        this.accountNo = accountNo;
        this.ifscCode = ifscCode;
    }

    public Bank() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }


}
