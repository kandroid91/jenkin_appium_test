package com.asiapay.payyobusiness.generateqrcode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QRCodePresent {

    @SerializedName("Amount")
    @Expose
    private String enterAmount;

    public String getEnterAmount() {
        return enterAmount;
    }

    public void setEnterAmount(String enterAmount) {
        this.enterAmount = enterAmount;
    }
}
