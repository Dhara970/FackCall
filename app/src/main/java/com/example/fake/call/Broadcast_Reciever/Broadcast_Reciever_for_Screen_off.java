package com.example.fake.call.Broadcast_Reciever;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
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
import java.util.List;

/* loaded from: classes2.dex */
public class Broadcast_Reciever_for_Screen_off extends BroadcastReceiver {
    Handler handler;
    private SharedPreferences.Editor prefEditor;
    private List<ActivityManager.RunningServiceInfo> serviceInfos;
    private SharedPreferences sharedPreferences;
    int i = 0;
    private int SPLASH_TIME_OUT = 4000;
    Boolean handler_is_running = false;

    @SuppressLint("WrongConstant")
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.handler = new Handler();
        try {
            if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
                SharedPreferences.Editor edit = this.sharedPreferences.edit();
                this.prefEditor = edit;
                edit.putBoolean("call_is_active", true);
                this.prefEditor.commit();
            }
        } catch ( Exception unused) {
        }
        try {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                if (!Utils.fake_call_actvie && this.i >= 3) {
                    switch (this.sharedPreferences.getInt(context.getResources().getString(R.string.call_screen_position), 1)) {
                        case 0:
                            Utils.fake_call_actvie = true;
                            Intent intent2 = new Intent(context, Call_Screen_Blurry_Dark.class);
                            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            context.startActivity(intent2);
                            break;
                        case 1:
                            Utils.fake_call_actvie = true;
                            Intent intent3 = new Intent(context, Dark_blue.class);
                            intent3.setFlags(268435456);
                            context.startActivity(intent3);
                            break;
                        case 2:
                            Utils.fake_call_actvie = true;
                            Intent intent4 = new Intent(context, Call_Screen_A51.class);
                            intent4.setFlags(268435456);
                            context.startActivity(intent4);
                            break;
                        case 3:
                            Utils.fake_call_actvie = true;
                            Intent intent5 = new Intent(context, Call_screen_HTC_1.class);
                            intent5.setFlags(268435456);
                            context.startActivity(intent5);
                            break;
                        case 4:
                            Utils.fake_call_actvie = true;
                            Intent intent6 = new Intent(context, Call_now_Samsung_S5.class);
                            intent6.setFlags(268435456);
                            context.startActivity(intent6);
                            break;
                        case 5:
                            Utils.fake_call_actvie = true;
                            Intent intent7 = new Intent(context, Call_Now_Huawei_Mate.class);
                            intent7.setFlags(268435456);
                            context.startActivity(intent7);
                            break;
                        case 6:
                            Utils.fake_call_actvie = true;
                            Intent intent8 = new Intent(context, Ice_White.class);
                            intent8.setFlags(268435456);
                            context.startActivity(intent8);
                            break;
                        case 7:
                            Utils.fake_call_actvie = true;
                            Intent intent9 = new Intent(context, Mid_red.class);
                            intent9.setFlags(268435456);
                            context.startActivity(intent9);
                            break;
                    }
                }
                this.i++;
            }
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                if (!Utils.fake_call_actvie && this.i >= 3) {
                    this.i = 0;
                    switch (this.sharedPreferences.getInt(context.getResources().getString(R.string.call_screen_position), 1)) {
                        case 0:
                            Utils.fake_call_actvie = true;
                            Intent intent10 = new Intent(context, Call_Screen_Blurry_Dark.class);
                            intent10.setFlags(268435456);
                            context.startActivity(intent10);
                            break;
                        case 1:
                            Utils.fake_call_actvie = true;
                            Intent intent11 = new Intent(context, Dark_blue.class);
                            intent11.setFlags(268435456);
                            context.startActivity(intent11);
                            break;
                        case 2:
                            Utils.fake_call_actvie = true;
                            Intent intent12 = new Intent(context, Call_Screen_A51.class);
                            intent12.setFlags(268435456);
                            context.startActivity(intent12);
                            break;
                        case 3:
                            Utils.fake_call_actvie = true;
                            Intent intent13 = new Intent(context, Call_screen_HTC_1.class);
                            intent13.setFlags(268435456);
                            context.startActivity(intent13);
                            break;
                        case 4:
                            Utils.fake_call_actvie = true;
                            Intent intent14 = new Intent(context, Call_now_Samsung_S5.class);
                            intent14.setFlags(268435456);
                            context.startActivity(intent14);
                            break;
                        case 5:
                            Utils.fake_call_actvie = true;
                            Intent intent15 = new Intent(context, Call_Now_Huawei_Mate.class);
                            intent15.setFlags(268435456);
                            context.startActivity(intent15);
                            break;
                        case 6:
                            Utils.fake_call_actvie = true;
                            Intent intent16 = new Intent(context, Ice_White.class);
                            intent16.setFlags(268435456);
                            context.startActivity(intent16);
                            break;
                        case 7:
                            Utils.fake_call_actvie = true;
                            Intent intent17 = new Intent(context, Mid_red.class);
                            intent17.setFlags(268435456);
                            context.startActivity(intent17);
                            break;
                    }
                }
                this.i++;
            }
            if (!this.handler_is_running.booleanValue()) {
                this.handler_is_running = true;
                this.handler.postDelayed(new Runnable() { // from class: com.softdroid.fake.call.Broadcast_Reciever.Broadcast_Reciever_for_Screen_off.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Broadcast_Reciever_for_Screen_off.this.i = 0;
                        Broadcast_Reciever_for_Screen_off.this.handler_is_running = false;
                    }
                }, (long) this.SPLASH_TIME_OUT);
            }
        } catch (Exception unused2) {
        }
    }
}
