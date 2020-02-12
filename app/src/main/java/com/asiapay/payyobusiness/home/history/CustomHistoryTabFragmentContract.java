package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.CustomTransactionDate;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class CustomHistoryTabFragmentContract {

    public interface Model {


        interface OnFinishedListener {
            void onFinished(ArrayList<Transaction> transaction);

            void onFailure(Throwable t);
        }

        void customDate(OnFinishedListener onFinishedListener, CustomTransactionDate transactionDate);
    }

    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void apiResponse(ArrayList<Transaction> transactionDate);

        void showAPIErrorMessage(String message);
    }

    interface Presenter {
        void onDestroy();


        void getCustomList(CustomTransactionDate date);
    }

}
