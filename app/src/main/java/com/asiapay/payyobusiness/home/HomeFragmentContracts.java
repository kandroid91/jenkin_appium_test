package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.Transaction;

import java.util.ArrayList;

public class HomeFragmentContracts {
    public interface Model {
        interface OnFinishedListener {
            void onFinished(ArrayList<Transaction> transaction);

            void onFailure(Throwable t);
        }

        void getTransactionList(OnFinishedListener onFinishedListener, Transaction transaction);
    }

    public interface View extends CoreViewAction {
        void apiResponseSuccess(ArrayList<Transaction> transaction);

        void onResponseFailure(Throwable throwable);

    }

    public interface Presenter {
        void onDestroy();

        void getTransactionList(Transaction transaction);


    }
}
