package com.example.fake.call;

import android.os.CountDownTimer;

/* loaded from: classes2.dex */
public class Utils {
    public static boolean fake_call_actvie = false;
    public static int millisecs;
    public static String path;

    public void counter(int i) {
        new CountDownTimer((long) i, 1000) { // from class: com.softdroid.fake.call.Utils.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        }.start();
    }
}
