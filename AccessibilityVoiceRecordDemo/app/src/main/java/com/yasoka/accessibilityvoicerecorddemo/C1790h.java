package com.yasoka.accessibilityvoicerecorddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.text.DecimalFormat;

/* renamed from: com.catalinagroup.callrecorder.j.h */
public class C1790h {

    /* renamed from: com.catalinagroup.callrecorder.j.h$a */
    static class C1791a extends AsyncTask<Void, Void, C1792b> {

        /* renamed from: a */
        final /* synthetic */ C1580f f5397a;

        /* renamed from: b */
        final /* synthetic */ C1794d f5398b;

        C1791a(C1580f fVar, C1794d dVar) {
            this.f5397a = fVar;
            this.f5398b = dVar;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C1792b doInBackground(Void... voidArr) {
            return C1790h.m8016a(this.f5397a);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(C1792b bVar) {
            super.onPostExecute(bVar);
            this.f5398b.mo6742a(bVar.f5399a, bVar.f5400b);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.h$b */
    public static class C1792b {

        /* renamed from: a */
        public long f5399a = 0;

        /* renamed from: b */
        public long f5400b = 0;

        C1792b() {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public void mo6932a(C1792b bVar) {
            this.f5399a += bVar.f5399a;
            this.f5400b += bVar.f5400b;
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.h$c */
    public static class C1793c {

        /* renamed from: a */
        public final String f5401a;

        /* renamed from: b */
        public final String f5402b;

        /* synthetic */ C1793c(String str, String str2, C1791a aVar) {
            this(str, str2);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f5402b);
            sb.append(File.separator);
            sb.append(this.f5401a);
            return sb.toString();
        }

        private C1793c(String str, String str2) {
            this.f5402b = str;
            this.f5401a = str2;
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.h$d */
    public interface C1794d {
        /* renamed from: a */
        void mo6742a(long j, long j2);
    }

    /* renamed from: a */
    public static void m8020a(Context context, String str) {
        C1577e.m7456a(context, str).mo6560c();
    }

    /* renamed from: b */
    public static String m8023b(String str) {
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : "";
    }

    /* renamed from: c */
    public static C1793c m8024c(String str) {
        String str2;
        int lastIndexOf = str.lastIndexOf(File.separator);
        if (lastIndexOf > 0) {
            str2 = str.substring(0, lastIndexOf);
            str = str.substring(lastIndexOf + 1, str.length());
        } else {
            str2 = null;
        }
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf2 > 0) {
            str = str.substring(0, lastIndexOf2);
        }
        return new C1793c(str2, str, null);
    }

    /* renamed from: d */
    public static String m8025d(String str) {
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf > 0 ? str.substring(lastIndexOf + 1, str.length()) : str;
    }

    /* renamed from: e */
    public static String m8026e(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
    }

    /* renamed from: a */
    public static void m8021a(Context context, String str, String str2) {
        C1577e.m7456a(context, str).mo6586b(str2);
    }

    /* renamed from: a */
    public static String m8019a(String str, boolean z) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf <= 0) {
            return "";
        }
        if (!z) {
            lastIndexOf++;
        }
        return str.substring(lastIndexOf, str.length());
    }

    /* renamed from: a */
    public static String m8018a(String str) {
        return str.replaceAll("[|?*<\":>+\\[\\]/']", "_");
    }

    /* renamed from: a */
    public static String m8017a(long j) {
        if (j <= 0) {
            return "0 B";
        }
        String[] strArr = {"B", "kB", "MB", "GB", "TB"};
        double d = (double) j;
        int log10 = (int) (Math.log10(d) / Math.log10(1024.0d));
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        double pow = Math.pow(1024.0d, (double) log10);
        Double.isNaN(d);
        sb.append(decimalFormat.format(d / pow));
        sb.append(OAuth.SCOPE_DELIMITER);
        sb.append(strArr[log10]);
        return sb.toString();
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: a */
    public static void m8022a(C1580f fVar, C1794d dVar) {
        new C1791a(fVar, dVar).executeOnExecutor(C1827q.f5467b, new Void[0]);
    }

    /* renamed from: a */
    public static C1792b m8016a(C1580f fVar) {
        C1580f[] n;
        C1792b bVar = new C1792b();
        try {
            if (fVar.mo6565j()) {
                for (C1580f fVar2 : fVar.mo6569n()) {
                    if (fVar2.mo6565j()) {
                        bVar.mo6932a(m8016a(fVar2));
                    } else {
                        bVar.f5400b++;
                        bVar.f5399a += fVar2.mo6568m();
                    }
                }
            } else {
                bVar.f5400b++;
                bVar.f5399a += fVar.mo6568m();
            }
        } catch (Exception unused) {
        }
        return bVar;
    }
}
