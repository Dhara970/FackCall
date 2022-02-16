package com.example.fake.call.Utils_;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class Shake implements SensorEventListener {
    private static final int ACCELERATION_THRESHOLD = 23;
    private Sensor accelerometer;
    private final Listener listener;
    private final SampleQueue queue = new SampleQueue();
    private SensorManager sensorManager;


    public interface Listener {
        void hearShake();
    }

    public abstract void hearShake();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public Shake(Listener listener) {
        this.listener = listener;
    }

    public boolean start(SensorManager sensorManager) {
        if (this.accelerometer != null) {
            return true;
        }
        Sensor defaultSensor = sensorManager.getDefaultSensor(1);
        this.accelerometer = defaultSensor;
        if (defaultSensor != null) {
            this.sensorManager = sensorManager;
            sensorManager.registerListener(this, defaultSensor, 0);
        }
        if (this.accelerometer != null) {
            return true;
        }
        return false;
    }

    public void stop() {
        Sensor sensor = this.accelerometer;
        if (sensor != null) {
            this.sensorManager.unregisterListener(this, sensor);
            this.sensorManager = null;
            this.accelerometer = null;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        boolean isAccelerating = isAccelerating(sensorEvent);
        this.queue.add(sensorEvent.timestamp, isAccelerating);
        if (this.queue.isShaking()) {
            this.queue.clear();
            this.listener.hearShake();
        }
    }

    private boolean isAccelerating(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        if (((double) ((f * f) + (f2 * f2) + (f3 * f3))) > 529.0d) {
            return true;
        }
        return false;
    }


    static class SampleQueue {
        private static final long MAX_WINDOW_SIZE = 500000000;
        private static final int MIN_QUEUE_SIZE = 4;
        private static final long MIN_WINDOW_SIZE = 250000000;
        private int acceleratingCount;
        private Sample newest;
        private Sample oldest;
        private final SamplePool pool = new SamplePool();
        private int sampleCount;

        SampleQueue() {
        }

        void add(long j, boolean z) {
            purge(j - MAX_WINDOW_SIZE);
            Sample acquire = this.pool.acquire();
            acquire.timestamp = j;
            acquire.accelerating = z;
            acquire.next = null;
            Sample sample = this.newest;
            if (sample != null) {
                sample.next = acquire;
            }
            this.newest = acquire;
            if (this.oldest == null) {
                this.oldest = acquire;
            }
            this.sampleCount++;
            if (z) {
                this.acceleratingCount++;
            }
        }

        void clear() {
            while (true) {
                Sample sample = this.oldest;
                if (sample != null) {
                    this.oldest = sample.next;
                    this.pool.release(sample);
                } else {
                    this.newest = null;
                    this.sampleCount = 0;
                    this.acceleratingCount = 0;
                    return;
                }
            }
        }

        void purge(long j) {
            Sample sample;
            while (this.sampleCount >= 4 && (sample = this.oldest) != null && j - sample.timestamp > 0) {
                Sample sample2 = this.oldest;
                if (sample2.accelerating) {
                    this.acceleratingCount--;
                }
                this.sampleCount--;
                Sample sample3 = sample2.next;
                this.oldest = sample3;
                if (sample3 == null) {
                    this.newest = null;
                }
                this.pool.release(sample2);
            }
        }

        List<Sample> asList() {
            ArrayList arrayList = new ArrayList();
            for (Sample sample = this.oldest; sample != null; sample = sample.next) {
                arrayList.add(sample);
            }
            return arrayList;
        }

        boolean isShaking() {
            Sample sample = this.newest;
            if (!(sample == null || this.oldest == null || sample.timestamp - this.oldest.timestamp < MIN_WINDOW_SIZE)) {
                int i = this.acceleratingCount;
                int i2 = this.sampleCount;
                if (i >= (i2 >> 1) + (i2 >> 2)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */

    public static class Sample {
        boolean accelerating;
        Sample next;
        long timestamp;

        Sample() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */

    public static class SamplePool {
        private Sample head;

        SamplePool() {
        }

        Sample acquire() {
            Sample sample = this.head;
            if (sample == null) {
                return new Sample();
            }
            this.head = sample.next;
            return sample;
        }

        void release(Sample sample) {
            sample.next = this.head;
            this.head = sample;
        }
    }
}
