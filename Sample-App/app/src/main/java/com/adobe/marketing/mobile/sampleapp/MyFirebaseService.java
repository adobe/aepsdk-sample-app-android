package com.adobe.marketing.mobile.sampleapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.adobe.marketing.mobile.Messaging;
import com.adobe.marketing.mobile.MessagingPushPayload;
import com.adobe.marketing.mobile.MobileCore;
import com.adobe.marketing.mobile.sampleapp.AppConstants;
import com.adobe.marketing.mobile.sampleapp.MainActivity;
import com.adobe.marketing.mobile.sampleapp.MenuActivity;
import com.adobe.marketing.mobile.sampleapp.R;
import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyFirebaseService extends FirebaseMessagingService {
    private final String CHANNEL_ID = "messaging_notification_channel";
    private final String CHANNEL_NAME = "Messaging Notifications Channel";

    public MyFirebaseService() {}

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        MobileCore.setPushIdentifier(s);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage);
    }

    private void sendNotification(RemoteMessage remoteMessage) {
        // Use the MessagingPushPayload object to extract the payload attributes for creating notification
        MessagingPushPayload payload = new MessagingPushPayload(remoteMessage);
        // Setting the channel
        String channelId = payload.getChannelId() == null ? CHANNEL_ID : payload.getChannelId();

        // Understanding whats the importance from priority
        int importance = getImportanceFromPriority(payload.getNotificationPriority());

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, CHANNEL_NAME, importance);
            notificationManager.createNotificationChannel(channel);
        }

        Map<String, String> data = remoteMessage.getData();

        String title = payload.getTitle();
        String body = payload.getBody();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(AppConstants.INTENT_TAB_KEY,  5); // 5 == Messaging tab
        intent.putExtra(AppConstants.INTENT_FROM_PUSH, true);

        Messaging.addPushTrackingDetails(intent, remoteMessage.getMessageId(), data);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder;
        notificationBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        String url = payload.getImageUrl();
        if (url!= null && !url.isEmpty()) {
            Future<Bitmap> bitmapTarget = Glide.with(this).asBitmap().load(url).submit();
            Bitmap image;
            try {
                image = bitmapTarget.get();
                notificationBuilder.setLargeIcon(image).setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image).bigLargeIcon(null));
            } catch (ExecutionException | InterruptedException e) {
                Log.d("MyFirebaseService", e.getMessage());
            }
        }

        if (payload.getActionButtons() != null) {
            List<MessagingPushPayload.ActionButton> buttons = payload.getActionButtons();
            for (int i = 0; i< buttons.size(); i++) {
                MessagingPushPayload.ActionButton obj = buttons.get(i);
                String buttonName = obj.getLabel();
                notificationBuilder.addAction(new NotificationCompat.Action(R.drawable.ic_assurance_active, buttonName, null));
            }
        }

        notificationManager.notify(new Random().nextInt(100), notificationBuilder.build());
    }

    private int getImportanceFromPriority(int priority) {
        switch (priority) {
            case NotificationCompat.PRIORITY_DEFAULT:
                return NotificationManager.IMPORTANCE_DEFAULT;
            case NotificationCompat.PRIORITY_MIN:
                return NotificationManager.IMPORTANCE_MIN;
            case NotificationCompat.PRIORITY_LOW:
                return NotificationManager.IMPORTANCE_LOW;
            case NotificationCompat.PRIORITY_HIGH:
                return NotificationManager.IMPORTANCE_HIGH;
            case NotificationCompat.PRIORITY_MAX:
                return NotificationManager.IMPORTANCE_MAX;
            default: return NotificationManager.IMPORTANCE_NONE;
        }
    }
}
