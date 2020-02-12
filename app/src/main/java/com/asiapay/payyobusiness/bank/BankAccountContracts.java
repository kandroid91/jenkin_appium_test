package com.asiapay.payyobusiness.bank;

import com.asiapay.payyobusiness.model.Bank;
import com.asiapay.payyobusiness.model.CoreViewAction;

public interface BankAccountContracts {
    interface Model {
        interface OnFinishedListener {
            void onFinished(Bank bank);

            void onFailure(Throwable t);
        }

        void addBankDetails(OnFinishedListener onFinishedListener, Bank bank);

        void getBankList(OnFinishedListener onFinishedListener,Bank bank);

        void deleteExistingBankDetails(OnFinishedListener onFinishedListener,Bank bank);

    }

    interface View extends CoreViewAction {
        void showInvalidMessage(String forComponent, String message);

        /*void onResponseSuccess(Bank bank);

        void onResponseFailure(Throwable throwable);*/

        void showBankList(Bank bank);

        void showAddBank();

        void DeleteRecordMsg();
        void showAPIErrorMessage(String message);
    }

    interface Presenter {
        void onDestroy();

        void addBankButtonClick(Bank bank);

        void getBankList(Bank bank);
        void deleteBankDetails(Bank bank);

    }
}
