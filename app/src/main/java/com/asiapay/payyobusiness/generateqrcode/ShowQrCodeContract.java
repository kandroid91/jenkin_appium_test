package com.asiapay.payyobusiness.generateqrcode;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.asiapay.payyobusiness.model.CoreViewAction;

public interface ShowQrCodeContract {
    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void onResponseFailure(Throwable throwable);

        void showQRCode(Bitmap bitmap);
    }


    interface Presenter {
        void generateQR(DisplayMetrics displayMetrics, String data);

        void onDestroy();
    }
}
