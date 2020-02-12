package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.home.HomeFragmentContracts;
import com.asiapay.payyobusiness.home.HomeFragmentModel;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class TwentyEightDayFragmentPresenter implements HomeFragmentContracts.Presenter, HomeFragmentContracts.Model.OnFinishedListener{
    private HomeFragmentContracts.View twentyEightDayView;
    private HomeFragmentContracts.Model twentyEightDayModel;
    public TwentyEightDayFragmentPresenter(HomeFragmentContracts.View twentyEightDayFragment, TwentyEightDayFragment twentyEightDayFragment1) {
        this.twentyEightDayView=twentyEightDayFragment;
        this.twentyEightDayModel=new HomeFragmentModel();
    }

    @Override
    public void onFinished(ArrayList<Transaction> transactionArrayList) {
        if (twentyEightDayView != null){
            twentyEightDayView.hideProgress();
        }
        twentyEightDayView.apiResponseSuccess(transactionArrayList);
    }

    @Override
    public void onFailure(Throwable t) {
        if (twentyEightDayView != null){
            twentyEightDayView.hideProgress();
        }
        twentyEightDayView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getTransactionList(Transaction transactionTwenttEightDay) {
        twentyEightDayModel.getTransactionList(this, transactionTwenttEightDay);
    }
}
