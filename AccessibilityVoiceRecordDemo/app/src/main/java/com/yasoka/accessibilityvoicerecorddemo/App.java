package com.yasoka.accessibilityvoicerecorddemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.AppTask;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Service;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;

/*import com.catalinagroup.callrecorder.database.C1515b;
import com.catalinagroup.callrecorder.database.C1519f;
import com.catalinagroup.callrecorder.p114e.C1530a;
import com.catalinagroup.callrecorder.p115f.C1533a;
import com.catalinagroup.callrecorder.p118i.C1582b;
import com.catalinagroup.callrecorder.p123j.C1776b;
import com.catalinagroup.callrecorder.p123j.C1780d;
import com.catalinagroup.callrecorder.p123j.C1788g;
import com.catalinagroup.callrecorder.p123j.C1801j;
import com.catalinagroup.callrecorder.p123j.C1832s;
import com.catalinagroup.callrecorder.service.AnyCallListenerService;
import com.catalinagroup.callrecorder.service.overlay.C1869a;
import com.catalinagroup.callrecorder.service.recorders.AndroidAudioRecord;
import com.catalinagroup.callrecorder.service.recordings.ActivityCallRecording;
import com.catalinagroup.callrecorder.service.recordings.PhoneRecording;*/

//import p000a.p049p.C0319b;

public class App extends Application {

    /* renamed from: c */
    private C1780d<C1533a> f4542c = null;

    /* renamed from: d */
    private C1780d<C1515b> f4543d = null;

    /* renamed from: e */
    private boolean f4544e = false;

    /* renamed from: com.catalinagroup.callrecorder.App$a */
    class C1410a extends C1780d<C1533a> {
        C1410a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C1533a m6753a() {
            return new C1533a(App.this);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.App$b */
    class C1411b extends C1780d<C1515b> {
        C1411b() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C1515b m6755a() {
            return C1519f.m7204a((Context) App.this);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.App$c */
    private class C1412c implements ActivityLifecycleCallbacks {
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f4547c;

        /* renamed from: d */
        private Handler f4548d;

        /* renamed from: com.catalinagroup.callrecorder.App$c$a */
        class C1413a implements Runnable {
            C1413a() {
            }

            public void run() {
                if (C1412c.this.f4547c == 0 && C1776b.m7985b(App.this) && VERSION.SDK_INT >= 21) {
                    ActivityManager activityManager = (ActivityManager) App.this.getSystemService("activity");
                    if (activityManager != null) {
                        for (AppTask finishAndRemoveTask : activityManager.getAppTasks()) {
                            finishAndRemoveTask.finishAndRemoveTask();
                        }
                    }
                }
            }
        }

        private C1412c() {
            this.f4547c = 0;
            this.f4548d = new Handler();
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityPaused(Activity activity) {
        }

        public void onActivityResumed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
            this.f4547c++;
            this.f4548d.removeCallbacksAndMessages(null);
        }

        public void onActivityStopped(Activity activity) {
            this.f4547c = Math.max(this.f4547c - 1, 0);
            if (this.f4547c == 0) {
                this.f4548d.postDelayed(new C1413a(), 10000);
            }
        }

        /* synthetic */ C1412c(App app, C1410a aVar) {
            this();
        }
    }

    /* renamed from: a */
    public static void m6748a(Context context, boolean z) {
        App a = m6747a(context);
        if (a != null) {
            a.f4544e = z;
        }
    }

    /* renamed from: b */
    public static C1533a m6749b(Context context) {
        return (C1533a) m6747a(context).f4542c.mo6920b();
    }

    /* renamed from: c */
    public static C1515b m6750c(Context context) {
        App a = m6747a(context);
        if (a != null) {
            return (C1515b) a.f4543d.mo6920b();
        }
        return null;
    }

    /* renamed from: d */
    public static boolean m6751d(Context context) {
        App a = m6747a(context);
        return a != null && a.f4544e;
    }

    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new C1412c(this, null));
        AndroidAudioRecord.m8235b(this);
        C1788g.m8013c(this);
        C1414a.m6759a((Context) this);
        C1507c.m7113a((Context) this);
        this.f4542c = new C1410a();
        this.f4542c.mo6921c();
        this.f4543d = new C1411b();
        this.f4543d.mo6921c();
        C1516c cVar = new C1516c(this);
        long b = C1832s.m8128b(this);
        String str = "";
        String str2 = "currentDeviceModelInfo";
        String a = cVar.mo6382a(str2, str);
        String str3 = "currentVersionCode";
        if (str.equals(a)) {
            cVar.mo6389b(str3, b);
        } else if (cVar.mo6381a(str3, 0) == 0) {
            cVar.mo6389b(str3, 165);
        }
        boolean z = false;
        String b2 = C1801j.m8067b();
        if (!a.equals(b2)) {
            z = true;
            cVar.mo6390b(str2, b2);
        }
        long a2 = cVar.mo6381a(str3, 0);
        if (b != a2) {
            cVar.mo6389b(str3, b);
        }
        CallRecording.migrate(this, cVar);
        PhoneRecording.migrate(this, cVar, z, a2);
        ActivityCallRecording.migrate(this, cVar, z, a2);
        AnyCallListenerService.m8150a(cVar);
        C1869a.m8220b(cVar);
        C1582b.m7491a(cVar);
        C1530a.m7251b(this);
    }

    public void onTerminate() {
        super.onTerminate();
        C1530a.m7247a(this);
    }

    /* renamed from: a */
    public static App m6747a(Context context) {
        Application application = context instanceof Activity ? ((Activity) context).getApplication() : context instanceof Service ? ((Service) context).getApplication() : context instanceof Application ? (Application) context : null;
        if (application == null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
            }
        }
        if (application instanceof App) {
            return (App) application;
        }
        return null;
    }
}
