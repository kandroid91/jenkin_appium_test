package com.asiapay.payyobusiness.generateqrcode;

public class QRAmountPresenter implements QRAmountContract.Presenter {
    private QRAmountContract.View scanCodeView;

    QRAmountPresenter(QRAmountContract.View scanCodeView) {
        this.scanCodeView = scanCodeView;
    }

    void onScanCodeClick(QRCodePresent scanCode) {
        if (scanCode.getEnterAmount().equals("")) {
            scanCodeView.showInvalidInputMessage("edtTxtEnterAmt", "Kindly enter Amount");
        } else {
            scanCodeView.showQRcode();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onFragmentsButtonClick(QRCodePresent scanCode) {

    }
}
