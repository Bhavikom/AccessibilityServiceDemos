package com.yasoka.accessibilityvoicerecorddemo;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;

/* renamed from: com.catalinagroup.callrecorder.h.e */
public class C1577e {

    /* renamed from: com.catalinagroup.callrecorder.h.e$b */
    public static class C1579b {

        /* renamed from: a */
        public final String f4927a;

        /* renamed from: b */
        public final boolean f4928b;

        private C1579b(String str, boolean z) {
            this.f4927a = str;
            this.f4928b = z;
        }
    }

    /* renamed from: a */
    public static C1580f m7456a(Context context, String str) {
        String b = m7458b(context);
        if (!b.isEmpty() && C1566a.m7379a(context, Uri.parse(b))) {
            return C1566a.m7377a(context, Uri.parse(b), str);
        }
        if (C1568c.m7416s()) {
            return C1568c.m7414a(context, str);
        }
        return C1567b.m7397r();
    }

    /* renamed from: b */
    public static String m7458b(Context context) {
        return new C1516c(context).mo6382a("targetContentUri", "");
    }

    /* renamed from: c */
    public static boolean m7459c(Context context) {
        String b = m7458b(context);
        return !b.isEmpty() && !C1566a.m7379a(context, Uri.parse(b));
    }

    /* renamed from: d */
    public static void m7460d(Context context) {
        String b = m7458b(context);
        if (!b.isEmpty()) {
            C1566a.m7380b(context, Uri.parse(b));
        } else {
            C1568c.m7417t();
        }
    }

    /* renamed from: a */
    public static C1579b m7455a(Context context) {
        C1580f a = m7456a(context, "");
        return new C1579b(a.mo6564i().getPath(), a.mo6557a());
    }

    /* renamed from: a */
    public static void m7457a(Context context, Uri uri) {
        C1516c cVar = new C1516c(context);
        String str = "targetContentUri";
        if (uri != null) {
            if (VERSION.SDK_INT >= 21) {
                context.getContentResolver().takePersistableUriPermission(uri, 3);
            }
            cVar.mo6390b(str, uri.toString());
        } else {
            cVar.mo6393c(str);
        }
        m7460d(context);
    }
}
