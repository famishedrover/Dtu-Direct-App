package com.angryziber.android.dtuguide;

import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    private  String Title = " " , Message = " ";



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
        Log.d(TAG, "From: " + remoteMessage.getFrom() + "Title : " + remoteMessage.getNotification().getTitle());

        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Title = remoteMessage.getNotification().getTitle();
        Message = remoteMessage.getNotification().getBody();

        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_stat_action_account_child)
                        .setLargeIcon(logoBitmap)
                        .setContentTitle(Title)
                        .setContentText(Message);
        int mNotificationId = 001;
// Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        mNotifyMgr.notify(mNotificationId, mBuilder.build());

    }
}
