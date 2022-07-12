package com.example.meloday20.utils;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.example.meloday20.R;
import com.example.meloday20.home.Comment;
import com.example.meloday20.home.Like;
import com.example.meloday20.playlist.ParsePlaylist;
import com.example.meloday20.home.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class MyApplication extends Application {
    private static final String CHANNEL_ID = "myChannelId";

    @Override
    public void onCreate() {
        super.onCreate();
        ParseObject.registerSubclass(ParsePlaylist.class);
        ParseObject.registerSubclass(Post.class);
        ParseObject.registerSubclass(Like.class);
        ParseObject.registerSubclass(Comment.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());

        // configure notification channel
//        int importance = NotificationManager.IMPORTANCE_DEFAULT;
//        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "My Channel", importance);
//        channel.setDescription("Reminders");
//        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        mNotificationManager.createNotificationChannel(channel);



//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);
//
//            // Configure the notification channel.
//            notificationChannel.setDescription("Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }

    }
}