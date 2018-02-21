package com.example.listausarios.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.listausarios.MainActivity;
import com.example.listausarios.R;

/**
 * Created by mamorky on 20/02/18.
 */

public class NotifyAddUsuario extends BroadcastReceiver {
    public static final int NOTIFICATION = 1;
    public static final String NAME_ACTION = "com.example.listausuarios.ADD_USER";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"pepito");
        builder.setContentTitle(intent.getExtras().getString("ll")).setContentInfo(intent.getExtras().getString("ll")).setSmallIcon(R.drawable.ic_launcher_foreground).
                setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher_foreground));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NotifyAddUsuario.NOTIFICATION,builder.build());
    }
}
