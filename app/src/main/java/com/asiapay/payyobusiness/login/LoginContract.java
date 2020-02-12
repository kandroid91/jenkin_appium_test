package com.asiapay.payyobusiness.login;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.User;

public interface LoginContract {
    interface Model {
        interface OnFinishedListener {
            void onFinished(User user);

            void onFailure(Throwable t);
        }

        void validateLogin(OnFinishedListener onFinishedListener, User user);

        void validateOTP(OnFinishedListener onFinishedListener, User user);

        void forgotPassword(OnFinishedListener onFinishedListener, User user);
    }

    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void launchActivity();

        void showAPIErrorMessage(String message);
        /*void apiResponse(User user);

        void onResponseFailure(Throwable throwable);*/
    }

    interface Presenter {
        void onDestroy();

        void onLoginButtonClick(User user);

        void onOTPButtonClick(User user);

        void onForgotPasswordButtonClick(User user);
    }
}
