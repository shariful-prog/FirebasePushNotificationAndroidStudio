package com.example.pushnotificationdemo;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        getFirebaseMessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }


  public   void getFirebaseMessage(String title, String msg){
        String test ="Testing debug";
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID="Notice";

      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
          @SuppressLint("WrongConstant")
          NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                  "Salesroll Notification",NotificationManager.IMPORTANCE_MAX);

      notificationChannel.setDescription("SalesChannel");
      notificationChannel.enableLights(true);
      notificationChannel.setLightColor(Color.RED);
      notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
      notificationChannel.enableVibration(true);
      notificationManager.createNotificationChannel(notificationChannel);


      }


      NotificationCompat.Builder builder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
              .setSmallIcon(R.drawable.common_google_signin_btn_icon_light)
              .setContentTitle(title)
              .setContentText(msg)
              .setAutoCancel(true);
      notificationManager.notify(101,builder.build());

    }
}
