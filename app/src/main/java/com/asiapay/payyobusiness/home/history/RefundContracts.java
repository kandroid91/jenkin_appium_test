package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.Refund;

public interface RefundContracts {
    interface Model {


        interface OnFinishedListener {
            void onFinished(Refund refund);

            void onFailure(Throwable t);
        }

        void transferPayment(OnFinishedListener onFinishedListener, Refund refund);
    }

    interface View extends CoreViewAction {

        void showInvalidInputMessage(String etEnterAmount, String please_enter_amount);

        void showAPIMessage(String srtMessage);

        void showSuccessView(Refund refund);
    }

    interface Presenter {
        void onDestroy();


        void checkEnterAmount(Refund refund);


    }
}