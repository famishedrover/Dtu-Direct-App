package com.example.android.dtuguide;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NetworkChangeReciever extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {


        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final android.net.NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if( MyApplication.isActivityVisible() == true ) {
        if (!wifi.isConnected() && !mobile.isConnected()) {


            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.mipmap.ic_stat_action_account_child)
                            .setContentTitle("NO INTERNET CONNECTION FOUND")
                            .setContentText("Please check your Mobile Data or Wifi Connection and Try Again!");

            int mNotificationId = 001;
// Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(alarmSound);

            mNotifyMgr.notify(mNotificationId, mBuilder.build());

            Toast.makeText(context, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
            Log.d("Network NOT Available ", "Flag No 1");
        }

    }

    }


}

