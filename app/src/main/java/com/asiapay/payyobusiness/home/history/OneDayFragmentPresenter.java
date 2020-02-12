package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.home.HomeFragmentModel;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class OneDayFragmentPresenter implements HomeFragmentContracts.Presenter, HomeFragmentContracts.Model.OnFinishedListener {
    private HomeFragmentContracts.View firstDayView;
    private HomeFragmentContracts.Model firstDayModel;

    public OneDayFragmentPresenter(HomeFragmentContracts.View firstDayFragment, OneDayFragment firstDayFragment1) {
        this.firstDayView = firstDayFragment;
        this.firstDayModel=new HomeFragmentModel();
    }

    @Override
    public void onFinished(ArrayList<Transaction> transactionArrayList) {
        if (firstDayView != null){
            firstDayView.hideProgress();
        }
        firstDayView.apiResponseSuccess(transactionArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        if (firstDayView != null){
            firstDayView.hideProgress();
        }
        firstDayView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getTransactionList(Transaction transactionFirstDay) {
        firstDayModel.getTransactionList(this, transactionFirstDay);

    }
}
