package com.example.fake.call.Service;

import android.app.NotificationChannel;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
//import com.onesignal.OneSignalDbContract;
import com.example.fake.call.Broadcast_Reciever.Broadcast_Reciever_for_Screen_off;
import com.example.fake.call.R;

/* loaded from: classes2.dex */
public class Screen_off_service extends Service {
    static final boolean $assertionsDisabled = false;
    public static String CHANNEL_ID = "123";
    Context context;
    BroadcastReceiver mReceiver;
    private SharedPreferences sharedPreferences;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        Broadcast_Reciever_for_Screen_off broadcast_Reciever_for_Screen_off = new Broadcast_Reciever_for_Screen_off();
        this.mReceiver = broadcast_Reciever_for_Screen_off;
        registerReceiver(broadcast_Reciever_for_Screen_off, intentFilter);
        super.onCreate();
        this.context = this;
        if (Build.VERSION.SDK_INT >= 26) {
         //   startMyOwnForeground();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String packageName = this.context.getPackageName();
        NotificationChannel notificationChannel = new NotificationChannel(packageName, getResources().getResourceName(R.string.app_name), 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
      //  ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
        startForeground(4, new NotificationCompat.Builder(this, packageName).setOngoing(true).setSmallIcon(R.mipmap.ic_launcher_noti).setContentTitle(getResources().getString(R.string.app_name) + "(Power button detector) is running").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
    }
}
