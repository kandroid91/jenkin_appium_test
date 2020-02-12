package com.asiapay.payyobusiness.home;

import com.asiapay.payyobusiness.model.Notification;

import java.util.ArrayList;

public class NotificationModel implements NotificationContracts.Model {
    @Override
    public void notification(final OnFinishedListener onFinishedListener) {
        /*ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TransactionDetails> notificationCall = apiService.notification(notification);
        notificationCall.enqueue(new Callback<TransactionDetails>() {
            @Override
            public void onResponse(Call<TransactionDetails> call, Response<TransactionDetails> response) {

            }

            @Override
            public void onFailure(Call<TransactionDetails> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/
        ArrayList<Notification> notificationList = new ArrayList<>();
        Notification notification = new Notification();
        notification.setMessage("Food Panda Rider");
        //history.setDate((float) 20.00);
        notification.setDate("2019-12-05 10:02:32");
        notification.setMessage("Money received");
        notificationList.add(notification);
        onFinishedListener.onFinished(notificationList);
    }
}
