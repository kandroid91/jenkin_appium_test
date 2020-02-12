package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CaseOut;
import com.asiapay.payyobusiness.model.CoreViewAction;

public interface CashoutContracts {
    interface Model {


        interface OnFinishedListener {
            void onFinished(CaseOut caseOut);

            void onFailure(Throwable t);
        }
        void transferPayment(OnFinishedListener onFinishedListener, CaseOut caseOut);
    }

    interface View extends CoreViewAction {

        void showInvalidInputMessage(String etEnterAmount, String please_enter_amount);

        void showLinkedAccount();

        void showAPIMessage(String srtMessage);

        void showSuccessView();
    }

    interface Presenter {
        void onDestroy();

        void  getCashOutList(CaseOut caseOut);
        void checkEnterAmount(CaseOut caseOut);

        void confirmPaymentTransfer(CaseOut caseOut);
    }

}
