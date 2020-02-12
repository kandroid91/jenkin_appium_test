package com.asiapay.payyobusiness.generateqrcode;

import com.asiapay.payyobusiness.network.ApiClient;
import com.asiapay.payyobusiness.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QRAmountModel implements QRAmountContract.Model {

    @Override
    public void generateQRValues(final OnFinishedListener onFinishedListener, QRCodePresent generateScanCode) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<QRCodePresent> call = apiService.prepareQRAmountValue(generateScanCode);
        call.enqueue(new Callback<QRCodePresent>() {
            @Override
            public void onResponse(Call<QRCodePresent> call, Response<QRCodePresent> response) {
                QRCodePresent generateScanCode = response.body();
                onFinishedListener.onFinished(generateScanCode);
            }

            @Override
            public void onFailure(Call<QRCodePresent> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}