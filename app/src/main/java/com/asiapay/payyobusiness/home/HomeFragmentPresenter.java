package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class HomeFragmentPresenter implements HomeFragmentContracts.Presenter, HomeFragmentContracts.Model.OnFinishedListener {
    private HomeFragmentContracts.View homeFragmentView;
    private HomeFragmentContracts.Model homeFragmentModel;

    HomeFragmentPresenter(HomeFragmentContracts.View homeView, HomeFragment context) {
        this.homeFragmentView = homeView;
        this.homeFragmentModel = new HomeFragmentModel();
    }

    @Override
    public void onFinished(ArrayList<Transaction> transaction) {
        if (homeFragmentView != null) {
            homeFragmentView.hideProgress();
        }
        homeFragmentView.apiResponseSuccess(transaction);
    }

    @Override
    public void onFailure(Throwable t) {
        if (homeFragmentView != null) {
            homeFragmentView.hideProgress();
        }
        homeFragmentView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void getTransactionList(Transaction transaction) {
        homeFragmentModel.getTransactionList(this, transaction);
    }
}
