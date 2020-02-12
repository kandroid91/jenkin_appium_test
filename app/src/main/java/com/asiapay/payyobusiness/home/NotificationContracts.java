package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.CoreViewAction;
import com.asiapay.payyobusiness.model.Notification;

import java.util.ArrayList;

public class NotificationContracts {
    public interface Model {
        interface OnFinishedListener {
            void onFinished(ArrayList<Notification> notification);

            void onFailure(Throwable t);
        }

        void notification(OnFinishedListener onFinishedListener);
    }

    public interface View extends CoreViewAction {
        void apiResponseSuccess(ArrayList<Notification> notification);

        void onResponseFailure(Throwable throwable);

    }

    public interface Presenter {
        void onDestroy();

        void onCallRetrofitApi(Notification notification);

    }
}
