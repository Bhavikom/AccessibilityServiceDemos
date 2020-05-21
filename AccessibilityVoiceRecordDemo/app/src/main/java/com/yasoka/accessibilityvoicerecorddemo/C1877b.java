package com.yasoka.accessibilityvoicerecorddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;

import com.yasoka.cubecallrecord.App;
import com.yasoka.cubecallrecord.p117h.C1577e;
import com.yasoka.cubecallrecord.p123j.C1777c;
import com.yasoka.cubecallrecord.p123j.C1827q;
/*import com.catalinagroup.callrecorder.App;
import com.catalinagroup.callrecorder.database.C1516c;
import com.catalinagroup.callrecorder.p115f.C1533a.C1538e;
import com.catalinagroup.callrecorder.p117h.C1577e;
import com.catalinagroup.callrecorder.p123j.C1777c;
import com.catalinagroup.callrecorder.p123j.C1827q;*/

/* renamed from: com.catalinagroup.callrecorder.service.recorders.b */
public abstract class C1877b {

    /* renamed from: e */
    private static final String[] f5571e = {"amr", "mp4-lo", "mp4", "mp4-hq"};

    /* renamed from: f */
    private static final String[] f5572f;

    /* renamed from: g */
    private static final int[] f5573g = {0, 1, 2, 3};

    /* renamed from: h */
    private static final String f5574h = f5571e[0];

    /* renamed from: a */
    private C1882e f5575a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f5576b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Context f5577c;

    /* renamed from: d */
    private AsyncTask<Void, Void, Boolean> f5578d;

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$a */
    class C1878a implements C1538e {

        /* renamed from: a */
        final /* synthetic */ Context f5579a;

        C1878a(Context context) {
            this.f5579a = context;
        }

        public final void onFailure() {
            C1877b.this.f5576b = 0;
        }

        public final void onSuccess(boolean z) {
            C1877b.this.f5576b = C1877b.m8253b(this.f5579a);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$b */
    class C1879b extends AsyncTask<Void, Void, Boolean> {

        /* renamed from: a */
        final /* synthetic */ int f5581a;

        /* renamed from: b */
        final /* synthetic */ int f5582b;

        /* renamed from: c */
        final /* synthetic */ byte f5583c;

        /* renamed from: d */
        final /* synthetic */ String f5584d;

        /* renamed from: e */
        final /* synthetic */ C1883f f5585e;

        C1879b(int i, int i2, byte b, String str, C1883f fVar) {
            this.f5581a = i;
            this.f5582b = i2;
            this.f5583c = b;
            this.f5584d = str;
            this.f5585e = fVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Boolean doInBackground(Void... voidArr) {
            C1577e.m7460d(C1877b.this.f5577c);
            return Boolean.valueOf(C1877b.this.mo7013a(this.f5581a, this.f5582b, this.f5583c, this.f5584d));
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            C1883f fVar = this.f5585e;
            if (fVar != null) {
                fVar.onStartResult(bool.booleanValue());
            }
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$c */
    class C1880c extends AsyncTask<Void, Void, Void> {

        /* renamed from: a */
        final /* synthetic */ AsyncTask f5587a;

        /* renamed from: b */
        final /* synthetic */ Runnable f5588b;

        C1880c(AsyncTask asyncTask, Runnable runnable) {
            this.f5587a = asyncTask;
            this.f5588b = runnable;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            try {
                this.f5587a.get();
            } catch (Exception unused) {
            }
            C1877b.this.mo7014d();
            return null;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            Runnable runnable = this.f5588b;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$d */
    class C1881d implements Runnable {

        /* renamed from: c */
        final /* synthetic */ C1882e f5590c;

        /* renamed from: d */
        final /* synthetic */ int f5591d;

        /* renamed from: e */
        final /* synthetic */ int f5592e;

        C1881d(C1882e eVar, int i, int i2) {
            this.f5590c = eVar;
            this.f5591d = i;
            this.f5592e = i2;
        }

        public void run() {
            this.f5590c.onError(C1877b.this, this.f5591d, this.f5592e);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$e */
    public interface C1882e {
        void onError(C1877b bVar, int i, int i2);
    }

    /* renamed from: com.catalinagroup.callrecorder.service.recorders.b$f */
    public interface C1883f {
        void onStartResult(boolean z);
    }

    static {
        String str = ".m4a";
        f5572f = new String[]{".amr", str, str, str, ".mp4"};
    }

    protected C1877b(Context context) {
        this.f5577c = context;
        App.m6749b(context).mo6454a((C1538e) new C1878a(context));
    }

    /* renamed from: a */
    public static int m8248a(int i) {
        return i & 255;
    }

    /* renamed from: b */
    public static boolean m8254b(int i) {
        return (i & 256) != 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo7013a(int i, int i2, byte b, String str);

    /* renamed from: b */
    public String mo7022b() {
        return f5572f[this.f5576b];
    }

    /* renamed from: c */
    public int mo7023c() {
        return this.f5576b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract void mo7014d();

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static int m8253b(Context context) {
        if (VERSION.SDK_INT >= 18) {
            int a = C1777c.m7990a(f5571e, new C1516c(context).mo6382a("recorderAudioFormat", f5574h));
            if (a >= 0) {
                return f5573g[a];
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m8252a(String str) {
        String lowerCase = str.toLowerCase();
        for (String endsWith : f5572f) {
            if (lowerCase.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void mo7020a(C1882e eVar) {
        this.f5575a = eVar;
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: a */
    public final void mo7019a(int i, int i2, byte b, String str, C1883f fVar) {
        if (this.f5578d == null) {
            C1879b bVar = new C1879b(i, i2, b, str, fVar);
            this.f5578d = bVar.executeOnExecutor(C1827q.f5466a, new Void[0]);
            return;
        }
        throw new AssertionError();
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: a */
    public final void mo7021a(Runnable runnable) {
        AsyncTask<Void, Void, Boolean> asyncTask = this.f5578d;
        if (asyncTask != null) {
            this.f5578d = null;
            new C1880c(asyncTask, runnable).executeOnExecutor(C1827q.f5466a, new Void[0]);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Context mo7017a() {
        return this.f5577c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7018a(int i, int i2) {
        C1882e eVar = this.f5575a;
        if (eVar != null) {
            new Handler(mo7017a().getMainLooper()).post(new C1881d(eVar, i, i2));
        }
    }
}
