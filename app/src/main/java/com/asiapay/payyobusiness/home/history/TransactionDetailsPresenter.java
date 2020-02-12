package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.Transaction;

public class TransactionDetailsPresenter implements TransactionDetailsContracts.Presenter, TransactionDetailsContracts.Model.OnFinishedListener {
    private TransactionDetailsContracts.View tranxDetailsView;
    private TransactionDetailsContracts.Model tranxDetailsViewModel;

    public TransactionDetailsPresenter(TransactionDetailsContracts.View views, TransactionDetailsActivity transactionDetailsFragment1) {
        this.tranxDetailsView = views;
        this.tranxDetailsViewModel = new TransactionModel();
    }

    @Override
    public void onFinished(Transaction transaction) {
        if (tranxDetailsView != null) {
            tranxDetailsView.hideProgress();
        }
        tranxDetailsView.apiResponse(transaction);
    }

    @Override
    public void onFailure(Throwable t) {

        if (tranxDetailsView != null) {
            tranxDetailsView.hideProgress();
        }
        tranxDetailsView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCallRetrofitApi(Transaction transaction) {
        tranxDetailsViewModel.transactionDetails(this, transaction);
    }


}
