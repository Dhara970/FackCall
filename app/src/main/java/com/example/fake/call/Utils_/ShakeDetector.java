package com.example.fake.call.Utils_;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/* loaded from: classes2.dex */
public class ShakeDetector implements SensorEventListener {
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7f;
    private OnShakeListener mListener;
    private int mShakeCount;
    private long mShakeTimestamp;


    public interface OnShakeListener {
        void onShake(int i);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        this.mListener = onShakeListener;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.mListener != null) {
            float f = sensorEvent.values[0];
            float f2 = f / 9.80665f;
            float f3 = sensorEvent.values[1] / 9.80665f;
            float f4 = sensorEvent.values[2] / 9.80665f;
            if (((float) Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f4 * f4)))) > SHAKE_THRESHOLD_GRAVITY) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = this.mShakeTimestamp;
                if (500 + j <= currentTimeMillis) {
                    if (j + 3000 < currentTimeMillis) {
                        this.mShakeCount = 0;
                    }
                    this.mShakeTimestamp = currentTimeMillis;
                    int i = this.mShakeCount + 1;
                    this.mShakeCount = i;
                    this.mListener.onShake(i);
                }
            }
        }
    }
}
