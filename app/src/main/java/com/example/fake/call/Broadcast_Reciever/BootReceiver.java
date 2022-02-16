package com.example.fake.call.Broadcast_Reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import com.example.fake.call.R;
import com.example.fake.call.Service.Screen_off_service;
import com.example.fake.call.Service.Shake_Service;

/* loaded from: classes2.dex */
public class BootReceiver extends BroadcastReceiver {
    private SharedPreferences sharedPreferences;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                this.sharedPreferences = defaultSharedPreferences;
                if (defaultSharedPreferences.getBoolean(context.getResources().getString(R.string.shake_pref), false)) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        context.startForegroundService(new Intent(context, Shake_Service.class));
                    } else {
                        context.startService(new Intent(context, Shake_Service.class));
                    }
                }
                if (!this.sharedPreferences.getBoolean(context.getResources().getString(R.string.triple_tap_pref), false)) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 26) {
                    context.startForegroundService(new Intent(context, Screen_off_service.class));
                } else {
                    context.startService(new Intent(context, Screen_off_service.class));
                }
            }
        } catch (NullPointerException unused) {
        }
    }
}
