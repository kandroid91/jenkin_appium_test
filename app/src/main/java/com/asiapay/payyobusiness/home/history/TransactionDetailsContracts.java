package com.asiapay.payyobusiness.home.history;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.Transaction;

public class TransactionDetailsContracts {

    public interface Model {
        interface OnFinishedListener {
            void onFinished(Transaction transaction);

            void onFailure(Throwable t);
        }

        void transactionDetails(OnFinishedListener onFinishedListener, Transaction transaction);
    }

    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void apiResponse(Transaction transaction);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void onCallRetrofitApi(Transaction transaction);

    }

}
