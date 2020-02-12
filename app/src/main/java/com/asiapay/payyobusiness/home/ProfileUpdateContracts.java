package com.asiapay.payyobusiness.home;

import android.graphics.Bitmap;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.User;

import java.io.FileInputStream;

public class ProfileUpdateContracts {
    public interface Model {

        interface OnFinishedListener {
            void onFinished(User updateProfile);

            void onFailure(Throwable t);
        }

        void userProfile(OnFinishedListener onFinishedListener, User updateProfile);

    }

    interface View extends CoreViewAction {
        void showInvalidInputMessage(String forComponent, String message);

        void showAPIErrorMessage(String message);

        void UploadSuccess(String strPath);

    }

    interface Presenter {
        void onDestroy();

    }
}
