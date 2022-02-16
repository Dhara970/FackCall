package com.example.fake.call.Service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
//import com.onesignal.OneSignalDbContract;
import com.example.fake.call.Call_Screens.Call_Now_Huawei_Mate;
import com.example.fake.call.Call_Screens.Call_Screen_A51;
import com.example.fake.call.Call_Screens.Call_Screen_Blurry_Dark;
import com.example.fake.call.Call_Screens.Call_now_Samsung_S5;
import com.example.fake.call.Call_Screens.Call_screen_HTC_1;
import com.example.fake.call.Call_Screens.Dark_blue;
import com.example.fake.call.Call_Screens.Ice_White;
import com.example.fake.call.Call_Screens.Mid_red;
import com.example.fake.call.R;
import com.example.fake.call.Utils;
import com.example.fake.call.Utils_.Shake;

/* loaded from: classes2.dex */
public class Shake_Service extends Service implements Shake.Listener {
    static final boolean $assertionsDisabled = false;
    Context context;
    Shake sd;
    SharedPreferences sharedPreferences;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.context = this;
        @SuppressLint("WrongConstant") SensorManager sensorManager = (SensorManager) getSystemService("sensor");
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.sd = new Shake(this) { // from class: com.softdroid.fake.call.Service.Shake_Service.1
            @Override // com.softdroid.fake.call.Utils_.Shake
            public void hearShake() {
            }
        };
        if (Build.VERSION.SDK_INT >= 26) {
          //  startMyOwnForeground();
        }
        this.sd.start(sensorManager);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground() {

        NotificationChannel chan = new NotificationChannel(
                getApplicationContext().getPackageName(),
                "My Foreground Service",
                NotificationManager.IMPORTANCE_LOW);



        String packageName = this.context.getPackageName();
        NotificationChannel notificationChannel = new NotificationChannel(packageName, getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
      //  ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
       // ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
       startForeground(3, new NotificationCompat.Builder(this, packageName).setOngoing(true).setSmallIcon(R.mipmap.ic_launcher_noti).setContentTitle(getResources().getString(R.string.app_name) + " (Shake detector) is running").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            this.sd.stop();
        } catch ( Exception unused) {
        }
    }

    @SuppressLint("WrongConstant")
    @Override // com.softdroid.fake.call.Utils_.Shake.Listener
    public void hearShake() {
        if (!Utils.fake_call_actvie) {
            switch (this.sharedPreferences.getInt(getResources().getString(R.string.call_screen_position), 1)) {
                case 0:
                    Utils.fake_call_actvie = true;
                    Intent intent = new Intent(getApplicationContext(), Call_Screen_Blurry_Dark.class);
                    intent.setFlags(268435456);
                    getApplicationContext().startActivity(intent);
                    return;
                case 1:
                    Utils.fake_call_actvie = true;
                    Intent intent2 = new Intent(getApplicationContext(), Dark_blue.class);
                    intent2.setFlags(268435456);
                    getApplicationContext().startActivity(intent2);
                    return;
                case 2:
                    Utils.fake_call_actvie = true;
                    Intent intent3 = new Intent(getApplicationContext(), Call_Screen_A51.class);
                    intent3.setFlags(268435456);
                    getApplicationContext().startActivity(intent3);
                    return;
                case 3:
                    Utils.fake_call_actvie = true;
                    Intent intent4 = new Intent(getApplicationContext(), Call_screen_HTC_1.class);
                    intent4.setFlags(268435456);
                    getApplicationContext().startActivity(intent4);
                    return;
                case 4:
                    Utils.fake_call_actvie = true;
                    Intent intent5 = new Intent(getApplicationContext(), Call_now_Samsung_S5.class);
                    intent5.setFlags(268435456);
                    getApplicationContext().startActivity(intent5);
                    return;
                case 5:
                    Utils.fake_call_actvie = true;
                    Intent intent6 = new Intent(getApplicationContext(), Call_Now_Huawei_Mate.class);
                    intent6.setFlags(268435456);
                    getApplicationContext().startActivity(intent6);
                    return;
                case 6:
                    Utils.fake_call_actvie = true;
                    Intent intent7 = new Intent(getApplicationContext(), Ice_White.class);
                    intent7.setFlags(268435456);
                    getApplicationContext().startActivity(intent7);
                    return;
                case 7:
                    Utils.fake_call_actvie = true;
                    Intent intent8 = new Intent(getApplicationContext(), Mid_red.class);
                    intent8.setFlags(268435456);
                    getApplicationContext().startActivity(intent8);
                    return;
                default:
                    return;
            }
        }
    }
}
