package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.Notification;

import java.util.ArrayList;

public class NotificationFragmentPresenter implements NotificationContracts.Presenter, NotificationContracts.Model.OnFinishedListener {
    private NotificationContracts.View notificationView;
    private NotificationContracts.Model notificationModel;

    public NotificationFragmentPresenter(NotificationContracts.View notificationFragment, NotificationFragment notificationFragment1) {
        this.notificationView = notificationFragment;
        this.notificationModel = new NotificationModel();
    }

    @Override
    public void onFinished(ArrayList<Notification> notification) {
        if (notificationView != null) {
            notificationView.hideProgress();
        }
        notificationView.apiResponseSuccess(notification);
    }

    @Override
    public void onFailure(Throwable t) {
        if (notificationView != null) {
            notificationView.hideProgress();
        }
        notificationView.onResponseFailure(t);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCallRetrofitApi(Notification notification) {
        notificationModel.notification(this);
    }
}
