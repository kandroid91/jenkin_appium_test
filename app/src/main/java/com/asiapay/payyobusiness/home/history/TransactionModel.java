package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.Transaction;

public class TransactionModel implements TransactionDetailsContracts.Model {
    @Override
    public void transactionDetails(final OnFinishedListener onFinishedListener, Transaction transaction) {
        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDetails> detailsCall = apiService.transactionDetails(details);
        detailsCall.enqueue(new Callback<TransactionDetails>() {
            @Override
            public void onResponse(Call<TransactionDetails> call, Response<TransactionDetails> response) {
                TransactionDetails detail = response.body();
                onFinishedListener.onFinished(detail);
            }

            @Override
            public void onFailure(Call<TransactionDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
    }
}