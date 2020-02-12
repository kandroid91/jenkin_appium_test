package com.asiapay.payyobusiness.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.asiapay.payyobusiness.R;
import com.asiapay.payyobusiness.home.HomeActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

public class FCMMessageReceiverService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        //Log.d(TAG, "onMessageReceived: Called ");
        //Log.d(TAG, "onMessageReceived: Message received from: " + remoteMessage.getFrom());
        String strTitle = "Notification Title";
        String strBody = "Testing App";
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        if (remoteMessage.getNotification() != null) {
            showNotification(this, strTitle, strBody, intent);
        }
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        //Log.d(TAG, "onMessageDeleted: Called ");
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        //Log.d(TAG, "onNewToken: Called ");
    }


    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);


        notificationManager.notify(notificationId, mBuilder.build());
    }
}