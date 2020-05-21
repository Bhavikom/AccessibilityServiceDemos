package com.yasoka.accessibilityvoicerecorddemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.graphics.PixelFormat;
import android.media.MediaRecorder;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class VoiceAccessibilityService extends AccessibilityService {
    FrameLayout mLayout;
    MediaRecorder recorder;
    public Recording f5485c;
    private boolean f5489g = false;

    private boolean f5490h = false;
    public VoiceAccessibilityService() {
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent != null) {
            if (accessibilityEvent.getEventType() == 2048) {
                //Recording recording = this.f5485c;
                if (f5485c.getCallInfo() == null) {
                    AccessibilityNodeInfo source = accessibilityEvent.getSource();
                    if (source != null) {
                        m8151a((CallRecording) (ActivityCallRecording) this.f5485c, source);
                    }
                }
                Recording recording2 = this.f5485c;
                if ((recording2 instanceof PhoneRecording) && ((PhoneRecording) recording2).getCallInfo().f5518b == null) {
                    TelephonyManager telephonyManager = (TelephonyManager) getSystemService(PhoneRecording.kName);
                    if (telephonyManager == null || telephonyManager.getCallState() == 2) {
                        AccessibilityNodeInfo source2 = accessibilityEvent.getSource();
                        if (source2 != null) {
                            m8151a((CallRecording) (PhoneRecording) this.f5485c, source2);
                        }
                    }
                }
            }
            /*if (!(accessibilityEvent.getEventType() != 32 || accessibilityEvent.getPackageName() == null || accessibilityEvent.getClassName() == null)) {
                try {
                    ComponentName componentName = new ComponentName(accessibilityEvent.getPackageName().toString(), accessibilityEvent.getClassName().toString());
                    if (getPackageManager().getActivityInfo(componentName, 0) != null) {
                        m8154a(componentName.flattenToShortString());
                    }
                } catch (NameNotFoundException | Exception unused) {
                }
            }*/
        }
    }
    private void m8151a(CallRecording callRecording, AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            HashSet hashSet = new HashSet();
            hashSet.add(accessibilityNodeInfo);
            if (((callRecording.getCallInfo() == null) || (C1801j.m8078d() && callRecording.getCallInfo().f5518b == null))
                    && callRecording.tryGetCallInfo(accessibilityNodeInfo, hashSet)) {
                m8152a(callRecording, false);
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ((AccessibilityNodeInfo) it.next()).recycle();
            }
        }
    }
    private void m8152a(CallRecording callRecording, boolean z) {
        if (callRecording instanceof ActivityCallRecording) {
            this.f5489g = z;
        }
        if (callRecording instanceof PhoneRecording) {
            this.f5490h = z && C1801j.m8078d();
        }
        m8164d();
    }
    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.TOP;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.action_bar, mLayout);
        wm.addView(mLayout, lp);


        configureStartRecording();
        configureStopRecording();
    }

    private void configureStartRecording() {
        Button startRecordingButton = mLayout.findViewById(R.id.btn_startrecord);
        startRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recorder = new MediaRecorder();
                File audiofile = null;
                String out = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss").format(new Date());
                File sampleDir = new File(getExternalFilesDir(null), "/TestRecordingDasa1");
                if (!sampleDir.exists()) {
                    sampleDir.mkdirs();
                }
                String file_name = "Record";
                try {
                    audiofile = File.createTempFile(file_name, ".amr", sampleDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                recorder.setOutputFile(audiofile.getAbsolutePath());
                try {
                    recorder.prepare();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //recorder.start(getApplicationContext());
                recorder.start();

                //Log.i(MainActivity.LOG_PREFIX, String.format("Recording started. Saving to path: '%s'", audiofile.getAbsolutePath()));

            }
        });
    }

    private void configureStopRecording() {
        Button button = (Button) mLayout.findViewById(R.id.btn_stoprecord);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaRecorder recorder = new MediaRecorder();
                if(recorder != null) {
                    recorder.stop();
                }
            }
        });
    }
    private void m8164d() {
        int i = 0;
        int i2 = (this.f5490h || C1833t.m8130a(this.f5487e, true)) ? 32 : 0;
        if (this.f5490h || this.f5489g) {
            i2 |= 2048;
            if (Build.VERSION.SDK_INT >= 18) {
                i = 16;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                i |= 64;
            }
        }
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.eventTypes = i2;
        accessibilityServiceInfo.flags = i;
        accessibilityServiceInfo.feedbackType = 16;
        accessibilityServiceInfo.notificationTimeout = 100;
        setServiceInfo(accessibilityServiceInfo);
    }

}