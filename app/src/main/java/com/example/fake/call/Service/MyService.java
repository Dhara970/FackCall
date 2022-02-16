package com.example.fake.call.Service;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
//import com.onesignal.OneSignalDbContract;
import com.example.fake.call.Activities.Setting_Act;
import com.example.fake.call.Call_Screens.Call_Now_Huawei_Mate;
import com.example.fake.call.Call_Screens.Call_Screen_A51;
import com.example.fake.call.Call_Screens.Call_Screen_Blurry_Dark;
import com.example.fake.call.Call_Screens.Call_now_Samsung_S5;
import com.example.fake.call.Call_Screens.Call_screen_HTC_1;
import com.example.fake.call.Call_Screens.Dark_blue;
import com.example.fake.call.Call_Screens.Ice_White;
import com.example.fake.call.Call_Screens.Mid_red;
import com.example.fake.call.MainActivity;
import com.example.fake.call.R;
import com.example.fake.call.Utils;
import com.example.fake.call.Utils_.Shared;

/* loaded from: classes2.dex */
public class MyService extends Service {
    static final boolean $assertionsDisabled = false;
    public static String CHANNEL_ID = "321";
    Context context;
    Boolean is_call_active = false;
    SharedPreferences mysharedPreference;
    CountDownTimer yourCountDownTimer;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.context = this;
        this.mysharedPreference = PreferenceManager.getDefaultSharedPreferences(this);
        if (Build.VERSION.SDK_INT >= 26) {
           // startMyOwnForeground();
        }
        this.yourCountDownTimer = new CountDownTimer((long) this.mysharedPreference.getInt("milliseconds", 10000), 1000) { // from class: com.softdroid.fake.call.Service.MyService.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                long j2 = j / 1000;
                long j3 = j2 / 60;
                int i = (int) (j2 % 60);
                if (Setting_Act.schedule_call_txtview_remaining != null) {
                    Setting_Act.schedule_call_txtview_remaining.setText("Remaining time for incoming fake call is (" + j3 + " : " + i + ")");
                }
                if (MainActivity.schedule_call_txtview_remaining != null) {
                    MainActivity.schedule_call_txtview_remaining.setText("Incoming fake call in (" + j3 + " : " + i + ")");
                }
                if (Setting_Act.cancel_shedule_call != null) {
                    Setting_Act.cancel_shedule_call.setVisibility(View.VISIBLE);
                }
                if (MainActivity.cancel_shedule_call != null) {
                    MainActivity.cancel_shedule_call.setVisibility(View.VISIBLE);
                }
                if (MainActivity.schedule_call_image != null) {
                    MainActivity.schedule_call_image.setVisibility(View.GONE);
                }
                if (MainActivity.shedule_arrow != null) {
                    MainActivity.shedule_arrow.setVisibility(View.GONE);
                }
                MyService.this.is_call_active = Shared.getInstance().getBooleanValueFromPreference(MyService.this.context.getResources().getString(R.string.call_is_active), false, MyService.this.context);
            }

            @SuppressLint("WrongConstant")
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (!Utils.fake_call_actvie && !MyService.this.is_call_active.booleanValue()) {
                    switch (MyService.this.mysharedPreference.getInt(MyService.this.context.getResources().getString(R.string.call_screen_position), 1)) {
                        case 0:
                            Utils.fake_call_actvie = true;
                            Intent intent = new Intent(MyService.this.context, Call_Screen_Blurry_Dark.class);
                            intent.setFlags(268435456);
                            MyService.this.context.startActivity(intent);
                            break;
                        case 1:
                            Utils.fake_call_actvie = true;
                            Intent intent2 = new Intent(MyService.this.context, Dark_blue.class);
                            intent2.setFlags(268435456);
                            MyService.this.context.startActivity(intent2);
                            break;
                        case 2:
                            Utils.fake_call_actvie = true;
                            Intent intent3 = new Intent(MyService.this.context, Call_Screen_A51.class);
                            intent3.setFlags(268435456);
                            MyService.this.context.startActivity(intent3);
                            break;
                        case 3:
                            Utils.fake_call_actvie = true;
                            Intent intent4 = new Intent(MyService.this.context, Call_screen_HTC_1.class);
                            intent4.setFlags(268435456);
                            MyService.this.context.startActivity(intent4);
                            break;
                        case 4:
                            Utils.fake_call_actvie = true;
                            Intent intent5 = new Intent(MyService.this.context, Call_now_Samsung_S5.class);
                            intent5.setFlags(268435456);
                            MyService.this.context.startActivity(intent5);
                            break;
                        case 5:
                            Utils.fake_call_actvie = true;
                            Intent intent6 = new Intent(MyService.this.context, Call_Now_Huawei_Mate.class);
                            intent6.setFlags(268435456);
                            MyService.this.context.startActivity(intent6);
                            break;
                        case 6:
                            Utils.fake_call_actvie = true;
                            Intent intent7 = new Intent(MyService.this.context, Ice_White.class);
                            intent7.setFlags(268435456);
                            MyService.this.context.startActivity(intent7);
                            break;
                        case 7:
                            Utils.fake_call_actvie = true;
                            Intent intent8 = new Intent(MyService.this.context, Mid_red.class);
                            intent8.setFlags(268435456);
                            MyService.this.context.startActivity(intent8);
                            break;
                    }
                }
                if (Setting_Act.schedule_call_txtview_remaining != null) {
                    Setting_Act.schedule_call_txtview_remaining.setText("");
                }
                if (MainActivity.schedule_call_txtview_remaining != null) {
                    MainActivity.schedule_call_txtview_remaining.setText(MyService.this.context.getResources().getString(R.string.schedule_call));
                }
                if (Setting_Act.cancel_shedule_call != null) {
                    Setting_Act.cancel_shedule_call.setVisibility(View.INVISIBLE);
                }
                if (MainActivity.cancel_shedule_call != null) {
                    MainActivity.cancel_shedule_call.setVisibility(View.GONE);
                }
                if (MainActivity.shedule_arrow != null) {
                    MainActivity.shedule_arrow.setVisibility(View.VISIBLE);
                }
                if (MainActivity.schedule_call_image != null) {
                    MainActivity.schedule_call_image.setVisibility(View.VISIBLE);
                }
                MyService.this.stopSelf();
            }
        }.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String packageName = this.context.getPackageName();
        NotificationChannel notificationChannel = new NotificationChannel(packageName, getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_NONE);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
      //  ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
        //((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).createNotificationChannel(notificationChannel);
        startForeground(2, new NotificationCompat.Builder(this, packageName).setOngoing(true).setSmallIcon(R.mipmap.ic_launcher_noti).setContentTitle(getResources().getString(R.string.app_name) + " (Schedule call) is running").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.yourCountDownTimer.cancel();
        if (Setting_Act.schedule_call_txtview_remaining != null) {
            Setting_Act.schedule_call_txtview_remaining.setText("");
        }
        if (MainActivity.schedule_call_txtview_remaining != null) {
            MainActivity.schedule_call_txtview_remaining.setText(this.context.getResources().getString(R.string.schedule_call));
        }
        if (Setting_Act.cancel_shedule_call != null) {
            Setting_Act.cancel_shedule_call.setVisibility(View.INVISIBLE);
        }
        if (MainActivity.cancel_shedule_call != null) {
            MainActivity.cancel_shedule_call.setVisibility(View.GONE);
        }
        if (MainActivity.shedule_arrow != null) {
            MainActivity.shedule_arrow.setVisibility(View.VISIBLE);
        }
        if (MainActivity.schedule_call_image != null) {
            MainActivity.schedule_call_image.setVisibility(View.VISIBLE);
        }
    }
}
