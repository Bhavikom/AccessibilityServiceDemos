package com.yasoka.accessibilityvoicerecorddemo;

import android.net.Uri;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.catalinagroup.callrecorder.h.f */
public abstract class C1580f {

    /* renamed from: a */
    private String f4929a = null;

    /* renamed from: b */
    private String f4930b = null;

    /* renamed from: a */
    public abstract C1580f mo6555a(String str);

    /* renamed from: a */
    public abstract OutputStream mo6556a(boolean z);

    /* renamed from: a */
    public abstract boolean mo6557a();

    /* renamed from: b */
    public abstract void mo6558b();

    /* renamed from: b */
    public void mo6586b(String str) {
        this.f4929a = null;
        this.f4930b = null;
        mo6559c(str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public abstract void mo6559c(String str);

    /* renamed from: c */
    public abstract boolean mo6560c();

    /* renamed from: d */
    public abstract boolean mo6561d();

    /* renamed from: e */
    public String mo6587e() {
        if (this.f4929a == null) {
            this.f4929a = mo6562f();
        }
        return this.f4929a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public abstract String mo6562f();

    /* renamed from: g */
    public String mo6588g() {
        if (this.f4930b == null) {
            this.f4930b = mo6563h();
        }
        return this.f4930b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public abstract String mo6563h();

    /* renamed from: i */
    public abstract Uri mo6564i();

    /* renamed from: j */
    public abstract boolean mo6565j();

    /* renamed from: k */
    public abstract boolean mo6566k();

    /* renamed from: l */
    public abstract long mo6567l();

    /* renamed from: m */
    public abstract long mo6568m();

    /* renamed from: n */
    public abstract C1580f[] mo6569n();

    /* renamed from: o */
    public abstract InputStream mo6570o();

    /* renamed from: p */
    public OutputStream mo6589p() {
        return mo6556a(false);
    }

    /* renamed from: q */
    public void mo6590q() {
        try {
            OutputStream a = mo6556a(true);
            if (a instanceof FileOutputStream) {
                FileOutputStream fileOutputStream = (FileOutputStream) a;
                long m = mo6568m();
                fileOutputStream.write(0);
                fileOutputStream.getChannel().truncate(m).close();
            }
            a.close();
        } catch (IOException unused) {
        }
    }
}
