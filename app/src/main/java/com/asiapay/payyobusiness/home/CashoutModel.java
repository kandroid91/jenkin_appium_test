package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CaseOut;

public class CashoutModel implements CashoutContracts.Model {

    @Override
    public void transferPayment(final OnFinishedListener onFinishedListener, CaseOut caseOut) {

        // create our sqlite helper class
        // list all Banks
      /*  ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<CaseOut> call = apiService.getTransferPayment(caseOut);
        call.enqueue(new Callback<CaseOut>() {
            @Override
            public void onResponse(Call<CaseOut> call, Response<CaseOut> response) {
               // CaseOut out = response.body();*/
                CaseOut out = new CaseOut();
                out.setAmount("$ 500 HKD successfully withdrawn from wallet to your HSBC Bnak Account ");
                onFinishedListener.onFinished(out);
        /*    }

            @Override
            public void onFailure(Call<CaseOut> call, Throwable t) {

            }
        });*/
    }
}