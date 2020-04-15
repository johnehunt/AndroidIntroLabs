package com.jjh.android.games;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class GameNotificationService extends IntentService {

    private static final String CHANNEL_ID = "com.jjh.android.games";
    private static final String TAG = "GameNotificationService";

    private NotificationManager notificationManager;

    public GameNotificationService() {
        super(GameNotificationService.class.getSimpleName());
        Log.d(TAG, "constructor()");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        createNotificationChannel();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent() - starting");

        Notification notification = new NotificationCompat.Builder(GameNotificationService.this, CHANNEL_ID)
                .setContentTitle("GameNotificationService")
                .setContentText("This is a notification from the game!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setChannelId(CHANNEL_ID)
                .setNumber(10)
                .build();

        int notificationID = 101;
        notificationManager.notify(notificationID, notification);
        Log.d(TAG, "onHandleIntent() - stopping");
    }

    private void createNotificationChannel() {
        Log.d(TAG, "createNotificationChannel()");

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "createNotificationChannel() - setting up channel");

            String name = "Notify Demo Info";
            String description = "EXAMPLE NOTIFICATION CHANNEL";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 40, 300, 200, 400});
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel);
        }
    }
}
