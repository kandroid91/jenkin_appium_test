package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.home.HomeFragmentModel;
import com.asiapay.payyobusiness.model.CustomTransactionDate;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class CustomHistoryTabFragmentPresenter implements HomeFragmentContracts.Presenter, HomeFragmentContracts.Model.OnFinishedListener {
    private HomeFragmentContracts.View customView;
    private HomeFragmentContracts.Model customViewtModel;

    public CustomHistoryTabFragmentPresenter(HomeFragmentContracts.View customFragment, CustomHistoryTabFragment context) {
        this.customView = customFragment;
        this.customViewtModel = new HomeFragmentModel();
    }

    @Override
    public void onFinished(ArrayList<Transaction> transactionArrayList) {
        if (customView != null){
            customView.hideProgress();
        }
        customView.apiResponseSuccess(transactionArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        if (customView != null){
            customView.hideProgress();
        }
        customView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
        customView = null;
    }

    @Override
    public void getTransactionList(Transaction transaction) {

        customViewtModel.getTransactionList(this, transaction);

    }


}
