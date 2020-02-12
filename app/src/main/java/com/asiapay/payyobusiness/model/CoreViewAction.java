package com.asiapay.payyobusiness.model;

import com.asiapay.payyobusiness.home.HomeFragmentPresenter;

public interface CoreViewAction {
    void initUI();

    void addEventListener();

    void showProgress();

    void hideProgress();

    void hideKeyboard();


}
