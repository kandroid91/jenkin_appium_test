package com.asiapay.payyobusiness.login;

import android.content.Context;

import com.asiapay.payyobusiness.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Model.OnFinishedListener {
    private LoginContract.View loginView;
    private LoginContract.Model loginModel;
    private Context mContext;

    LoginPresenter(Context context, LoginContract.View loginView) {
        this.loginView = loginView;
        this.mContext = context;
        this.loginModel = new LoginModel(mContext);
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onLoginButtonClick(User user) {
        if (user.getUserName().equals("")) {
            loginView.showInvalidInputMessage("etUsername", "Kindly enter user name");
        } else if (user.getPassword().equals("")) {
            loginView.showInvalidInputMessage("etPassword", "Kindly enter password");
        } else if (!user.getUserName().equals("kandroid") && !user.getPassword().equals("kandroid")) {
            loginView.showInvalidInputMessage("", "Kindly enter valid username and password");
        } else {
            loginView.showProgress();
            //presenter pass him self to the model
            loginModel.validateLogin(this, user);
        }
    }

    @Override
    public void onOTPButtonClick(User user) {
        if (user.getOtp().length() < 4) {
            loginView.showInvalidInputMessage("OTP", "Kindly enter OTP");
        } else {
            loginView.showProgress();
            loginModel.validateOTP(this, user);
        }
    }

    @Override
    public void onFinished(User user) {
        if (loginView != null) {
            loginView.hideProgress();
        }
        if (user.getOtp() != null) {
            loginView.launchActivity();
        } else if (user.getUserName() != null) {
            loginView.launchActivity();
        } else {
            String message = "";
            loginView.showAPIErrorMessage(message);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (loginView != null) {
            loginView.hideProgress();
        }
        loginView.showAPIErrorMessage(t.getMessage());
    }


    @Override
    public void onForgotPasswordButtonClick(User user) {
        loginModel.forgotPassword(this, user);
    }
}
