package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.QrScan;
import com.asiapay.payyobusiness.network.ApiClient;
import com.asiapay.payyobusiness.network.ApiInterface;

public class ScanResultModel implements ScanResultContracts.Model {

    @Override
    public void onScanQrApiCall(final OnFinishedListener onFinishedListener, QrScan qrScan) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
       // Call<QrScan> call = apiService.scanQrApi(qrScan);
       /* call.enqueue(new Callback<QrScan>() {
            @Override
            public void onResponse(Call<QrScan> call, Response<QrScan> response) {*/
                QrScan user = new QrScan();
                user.setAmount("Success");
                onFinishedListener.onFinished(user);
           // }

          /*  @Override
            public void onFailure(Call<QrScan> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
    }
}
