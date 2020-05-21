package com.yasoka.accessibilityvoicerecorddemo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* renamed from: com.catalinagroup.callrecorder.j.p */
public class C1825p implements SensorEventListener {

    /* renamed from: a */
    private long f5460a;

    /* renamed from: b */
    private int f5461b;

    /* renamed from: c */
    private final SensorManager f5462c;

    /* renamed from: d */
    private final float f5463d;

    /* renamed from: e */
    private Sensor f5464e;

    /* renamed from: f */
    private C1826a f5465f;

    /* renamed from: com.catalinagroup.callrecorder.j.p$a */
    public interface C1826a {
        void onShake();
    }

    public C1825p(Context context) {
        this.f5462c = (SensorManager) context.getSystemService("sensor");
        this.f5463d = ((float) new C1516c(context).mo6381a("shakeDetectorThreshold", 25)) / 10.0f;
    }

    /* renamed from: a */
    public boolean mo6956a(C1826a aVar) {
        if (this.f5464e != null) {
            return true;
        }
        this.f5465f = aVar;
        this.f5464e = this.f5462c.getDefaultSensor(1);
        Sensor sensor = this.f5464e;
        boolean z = false;
        if (sensor != null) {
            this.f5462c.registerListener(this, sensor, 0);
        }
        if (this.f5464e != null) {
            z = true;
        }
        return z;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.f5465f != null) {
            float[] fArr = sensorEvent.values;
            float f = fArr[0];
            float f2 = f / 9.80665f;
            float f3 = fArr[1] / 9.80665f;
            float f4 = fArr[2] / 9.80665f;
            if (((float) Math.sqrt((double) ((f2 * f2) + (f3 * f3) + (f4 * f4)))) > this.f5463d) {
                long currentTimeMillis = System.currentTimeMillis();
                long j = this.f5460a;
                if (500 + j <= currentTimeMillis) {
                    if (j + 1000 < currentTimeMillis) {
                        this.f5461b = 0;
                    }
                    if (this.f5461b == 0) {
                        this.f5465f.onShake();
                    }
                    this.f5460a = currentTimeMillis;
                    this.f5461b++;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo6955a() {
        this.f5465f = null;
        Sensor sensor = this.f5464e;
        if (sensor != null) {
            this.f5462c.unregisterListener(this, sensor);
            this.f5464e = null;
        }
    }
}
