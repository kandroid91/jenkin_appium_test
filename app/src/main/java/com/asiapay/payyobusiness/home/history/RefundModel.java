package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.Refund;

public class RefundModel implements RefundContracts.Model {
    @Override
    public void transferPayment(OnFinishedListener onFinishedListener, Refund refund) {
        Refund refund1 = new Refund();
        refund1.setAmount("$ 500 HKD successfully withdrawn from wallet to your HSBC Bnak Account ");
        onFinishedListener.onFinished(refund1);
    }
}
