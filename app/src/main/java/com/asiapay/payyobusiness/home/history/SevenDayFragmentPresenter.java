package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.home.HomeFragmentModel;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class SevenDayFragmentPresenter implements HomeFragmentContracts.Presenter, HomeFragmentContracts.Model.OnFinishedListener {
    private HomeFragmentContracts.View sevenDayView;
    private HomeFragmentContracts.Model sevenDayModel;

    public SevenDayFragmentPresenter(HomeFragmentContracts.View sevenDayFragment, SevenDayFragment sevenDayFragment1) {
        this.sevenDayView=sevenDayFragment;
        this.sevenDayModel=new HomeFragmentModel();
    }

    @Override
    public void onFinished(ArrayList<Transaction> transactionArrayList) {
        if (sevenDayView != null){
            sevenDayView.hideProgress();
        }
        sevenDayView.apiResponseSuccess(transactionArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        if (sevenDayView != null){
            sevenDayView.hideProgress();
        }
        sevenDayView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getTransactionList(Transaction transactionSevenDay) {
        sevenDayModel.getTransactionList(this, transactionSevenDay);
    }
}
