package com.asiapay.payyobusiness.generateqrcode;

import com.asiapay.payyobusiness.model.CoreViewAction;

public interface QRAmountContract {
    interface Model {
        interface OnFinishedListener {
            void onFinished(QRCodePresent user);

            void onFailure(Throwable t);
        }

        void generateQRValues(OnFinishedListener onFinishedListener, QRCodePresent generateScanCode);
    }


    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void apiResponse(QRCodePresent generateScanCode);

        void onResponseFailure(Throwable throwable);

        void showQRcode();
    }

    interface Presenter {
        void onDestroy();
        void onFragmentsButtonClick(QRCodePresent generateScanCode);
    }

}
