package com.asiapay.payyobusiness.home;

import android.content.Context;

import com.asiapay.payyobusiness.model.QrScan;

public class ScanResultPresenter implements ScanResultContracts.Presenter, ScanResultContracts.Model.OnFinishedListener {
    private ScanResultContracts.View scanView;
    private ScanResultContracts.Model scanModel;
    private Context mContext;

    public ScanResultPresenter(ScanResultContracts.View scanResultActivity, ScanResultActivity scanResult) {
        this.scanView = scanResultActivity;
        this.mContext = scanResult;
        this.scanModel = new ScanResultModel();
    }

    @Override
    public void onFinished(QrScan qrScan) {
        if (scanView != null) {
            scanView.hideProgress();
        }
        if (qrScan.getAmount() != null) {
            scanView.launchAlert();
        }
        
    }

    @Override
    public void onFailure(Throwable t) {
        if (scanView != null) {
            scanView.hideProgress();
        }
        scanView.showAPIErrorMessage(t.getMessage());
    }

    @Override
    public void onDestroy() {
        scanView = null;
    }

    public void onScanButtonClick(QrScan qrScan) {
        scanView.showProgress();
        //presenter pass him self to the model
        scanModel.onScanQrApiCall(this, qrScan);
    }


}
