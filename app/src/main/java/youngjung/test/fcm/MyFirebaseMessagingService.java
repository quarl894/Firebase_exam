package youngjung.test.fcm;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import youngjung.test.MainActivity;
import youngjung.test.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Map<String, String> data = remoteMessage.getData();
        String title = "영수증 도착";
        String messagae = "의뢰한 영수증이 되착했습니다.";

        sendNotification(title, messagae);

// Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

// Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String title, String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logo))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createChannel(notificationManager);
//        mBuilder = new NotificationCompat.Builder(mActivity, "YOUR_TEXT_HERE").setSmallIcon(android.R.drawable.stat_sys_download).setColor
//                (ContextCompat.getColor(getApplicationContext(), R.color.purpleish_blue)).setContentTitle(title).setContentText(message);
//        notificationManager.notify(mFile.getId().hashCode(), mBuilder.build());
//
//        @TargetApi(26)
//        private void createChannel(NotificationManager notificationManager) {
//            String name = "FileDownload";
//            String description = "Notifications for download status";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//
//            NotificationChannel mChannel = new NotificationChannel(name, name, importance);
//            mChannel.setDescription(description);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.BLUE);
//            notificationManager.createNotificationChannel(mChannel);
//        }
    }
}