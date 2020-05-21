package com.yasoka.accessibilityvoicerecorddemo;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;

//import com.catalinagroup.callrecorder.p123j.C1790h;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

//import p000a.p037k.p038a.C0278a;

/* renamed from: com.catalinagroup.callrecorder.h.a */
class C1566a extends C1580f {

    /* renamed from: c */
    private final Context f4906c;

    /* renamed from: d */
    private C0278a f4907d;

    /* renamed from: e */
    private C0278a f4908e;

    /* renamed from: f */
    private String f4909f;

    C1566a(Context context, C0278a aVar) {
        this.f4907d = aVar;
        this.f4906c = context;
    }

    /* renamed from: a */
    public boolean mo6557a() {
        C0278a aVar = this.f4907d;
        return aVar != null && aVar.mo1144a();
    }

    /* renamed from: b */
    public void mo6558b() {
        if (this.f4907d == null) {
            this.f4907d = this.f4908e.mo1142a(this.f4909f);
            this.f4908e = null;
            this.f4909f = null;
        }
    }

    /* renamed from: c */
    public boolean mo6560c() {
        C0278a aVar = this.f4907d;
        return aVar == null || aVar.mo1146b();
    }

    /* renamed from: d */
    public boolean mo6561d() {
        C0278a aVar = this.f4907d;
        return aVar != null && aVar.mo1147c();
    }

    /* renamed from: f */
    public String mo6562f() {
        C0278a aVar = this.f4907d;
        return aVar == null ? this.f4909f : aVar.mo1149d();
    }

    /* renamed from: h */
    public String mo6563h() {
        C0278a aVar = this.f4907d;
        if (aVar != null) {
            return m7378a(aVar);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m7378a(this.f4908e));
        sb.append(File.separator);
        sb.append(this.f4909f);
        return sb.toString();
    }

    /* renamed from: i */
    public Uri mo6564i() {
        C0278a aVar = this.f4907d;
        if (aVar != null) {
            return aVar.mo1151f();
        }
        return null;
    }

    /* renamed from: j */
    public boolean mo6565j() {
        C0278a aVar = this.f4907d;
        return aVar != null && aVar.mo1152g();
    }

    /* renamed from: k */
    public boolean mo6566k() {
        C0278a aVar = this.f4907d;
        return aVar != null && aVar.mo1153h();
    }

    /* renamed from: l */
    public long mo6567l() {
        C0278a aVar = this.f4907d;
        if (aVar == null) {
            return 0;
        }
        return aVar.mo1154i();
    }

    /* renamed from: m */
    public long mo6568m() {
        C0278a aVar = this.f4907d;
        if (aVar == null) {
            return 0;
        }
        return aVar.mo1155j();
    }

    /* renamed from: n */
    public C1580f[] mo6569n() {
        LinkedList linkedList = new LinkedList();
        C0278a aVar = this.f4907d;
        if (aVar != null) {
            C0278a[] k = aVar.mo1156k();
            if (k != null) {
                for (C0278a aVar2 : k) {
                    linkedList.add(new C1566a(this.f4906c, aVar2));
                }
            }
        }
        return (C1580f[]) linkedList.toArray(new C1580f[linkedList.size()]);
    }

    /* renamed from: o */
    public InputStream mo6570o() {
        if (this.f4907d != null) {
            return this.f4906c.getContentResolver().openInputStream(this.f4907d.mo1151f());
        }
        throw new FileNotFoundException();
    }

    /* renamed from: a */
    public C1580f mo6555a(String str) {
        C0278a aVar = this.f4907d;
        if (aVar == null) {
            return new C1567b();
        }
        C0278a b = aVar.mo1145b(str);
        return b == null ? new C1566a(this.f4906c, this.f4907d, str) : new C1566a(this.f4906c, b);
    }

    /* renamed from: c */
    public void mo6559c(String str) {
        C0278a aVar = this.f4907d;
        if (aVar != null) {
            C0278a e = aVar.mo1150e();
            if (!this.f4907d.mo1148c(str) && e != null) {
                C0278a b = e.mo1145b(str);
                if (b != null) {
                    this.f4907d = b;
                }
            }
        }
    }

    C1566a(Context context, C0278a aVar, String str) {
        this.f4908e = aVar;
        this.f4909f = str;
        this.f4906c = context;
    }

    /* renamed from: b */
    static void m7380b(Context context, Uri uri) {
        C0278a a = C0278a.m1303a(context, uri);
        String str = "CubeCallRecorder";
        C0278a b = a.mo1145b(str);
        if (b == null) {
            b = a.mo1142a(str);
        }
        if (b != null) {
            String str2 = "All";
            C0278a b2 = b.mo1145b(str2);
            if (b2 == null) {
                b2 = b.mo1142a(str2);
            }
            if (b2 != null) {
                String str3 = ".nomedia";
                if (b2.mo1145b(str3) == null) {
                    b2.mo1143a((String) null, str3);
                }
            }
        }
    }

    /* renamed from: a */
    public OutputStream mo6556a(boolean z) {
        if (this.f4907d == null) {
            String a = C1790h.m8019a(this.f4909f, false);
            this.f4907d = this.f4908e.mo1143a(!a.isEmpty() ? MimeTypeMap.getSingleton().getMimeTypeFromExtension(a) : "application/octet-stream", this.f4909f);
            if (this.f4907d != null) {
                this.f4908e = null;
                this.f4909f = null;
            }
        }
        ContentResolver contentResolver = this.f4906c.getContentResolver();
        if (contentResolver != null) {
            C0278a aVar = this.f4907d;
            if (aVar != null) {
                return contentResolver.openOutputStream(aVar.mo1151f(), z ? "wa" : "w");
            }
        }
        throw new FileNotFoundException();
    }

    /* renamed from: a */
    static C1566a m7377a(Context context, Uri uri, String str) {
        String str2;
        C0278a a = C0278a.m1303a(context, uri);
        StringBuilder sb = new StringBuilder();
        sb.append("CubeCallRecorder");
        if (str.isEmpty()) {
            str2 = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(File.separator);
            sb2.append(str);
            str2 = sb2.toString();
        }
        sb.append(str2);
        String[] split = sb.toString().split(File.separator);
        if (split.length == 0 || a == null) {
            return new C1566a(context, a);
        }
        for (int i = 0; i < split.length - 1; i++) {
            C0278a b = a.mo1145b(split[i]);
            if (b == null) {
                a = a.mo1142a(split[i]);
                a.mo1143a((String) null, ".nomedia");
            } else {
                a = b;
            }
        }
        String str3 = split[split.length - 1];
        C0278a b2 = a.mo1145b(str3);
        if (b2 == null) {
            return new C1566a(context, a, str3);
        }
        return new C1566a(context, b2);
    }

    /* renamed from: a */
    static boolean m7379a(Context context, Uri uri) {
        C0278a a = C0278a.m1303a(context, uri);
        boolean z = (a == null || !a.mo1144a() || a.mo1145b("CubeCallRecorder") == null) ? false : true;
        if (z) {
            m7380b(context, uri);
        }
        return z;
    }

    /* renamed from: a */
    private static String m7378a(C0278a aVar) {
        String path = aVar.mo1151f().getPath();
        int lastIndexOf = path.lastIndexOf("CubeCallRecorder");
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 16 + 1) : path;
    }
}
