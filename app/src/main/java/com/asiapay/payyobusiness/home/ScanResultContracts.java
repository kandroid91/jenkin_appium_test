package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.QrScan;
public interface ScanResultContracts {

    interface Model {
        interface OnFinishedListener {
            void onFinished(QrScan qrScan);

            void onFailure(Throwable t);
        }

        void onScanQrApiCall(OnFinishedListener onFinishedListener, QrScan qrScan);


    }

    interface View extends CoreViewAction {

        void showAPIErrorMessage(String message);

        void launchAlert();
    }

    interface Presenter {
        void onDestroy();



    }

}
