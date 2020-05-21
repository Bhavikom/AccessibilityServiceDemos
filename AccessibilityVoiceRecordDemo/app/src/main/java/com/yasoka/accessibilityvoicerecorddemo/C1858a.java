package com.yasoka.accessibilityvoicerecorddemo;

import android.text.TextUtils;

/* renamed from: com.catalinagroup.callrecorder.service.a */
public class C1858a {

    /* renamed from: a */
    public final C1859a f5517a;

    /* renamed from: b */
    public final String f5518b;

    /* renamed from: com.catalinagroup.callrecorder.service.a$a */
    public enum C1859a {
        Incoming,
        Outgoing,
        Unknown
    }

    public C1858a(C1859a aVar, String str) {
        this.f5517a = aVar;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.f5518b = str;
    }

    public C1858a(String str) {
        this.f5517a = C1859a.Unknown;
        this.f5518b = str;
    }
}
