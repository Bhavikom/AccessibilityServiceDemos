package com.yasoka.accessibilityvoicerecorddemo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.Toast;

/*import androidx.appcompat.app.C0506d;
import androidx.appcompat.app.C0506d.C0507a;
import androidx.core.app.C0777h.C0780c;
import androidx.core.content.C0797a;*/
import androidx.fragment.app.Fragment;

/*import com.catalinagroup.callrecorder.database.C1516c;
import com.catalinagroup.callrecorder.p115f.C1533a;
import com.catalinagroup.callrecorder.p115f.C1533a.C1538e;
import com.catalinagroup.callrecorder.p123j.C1811l.C1813b;
import com.catalinagroup.callrecorder.p126ui.activities.SplashScreenActivity;
import com.catalinagroup.callrecorder.p126ui.activities.tutorial.TutorialPremium;
import com.catalinagroup.callrecorder.service.recordings.PhoneRecording;
import com.catalinagroup.callrecorder.service.recordings.Recording.OnStartResult;
import com.facebook.ads.AdError;
import com.yasoka.cubecallrecord.p116g.C1550a;*/

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/* renamed from: com.catalinagroup.callrecorder.j.j */
public class C1801j {

    /* renamed from: a */
    private static NotificationChannel f5410a;

    /* renamed from: b */
    private static NotificationChannel f5411b;

    /* renamed from: c */
    private static String f5412c;

    /* renamed from: com.catalinagroup.callrecorder.j.j$a */
    static class C1802a implements OnClickListener {

        /* renamed from: c */
        final /* synthetic */ boolean f5413c;

        /* renamed from: d */
        final /* synthetic */ C1550a[] f5414d;

        /* renamed from: e */
        final /* synthetic */ Context f5415e;

        /* renamed from: f */
        final /* synthetic */ C1808c f5416f;

        /* renamed from: g */
        final /* synthetic */ Runnable f5417g;

        /* renamed from: com.catalinagroup.callrecorder.j.j$a$a */
        class C1803a implements OnClickListener {
            C1803a() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                C1802a aVar = C1802a.this;
                C1801j.m8070b(aVar.f5415e, aVar.f5414d, aVar.f5416f, aVar.f5417g);
            }
        }

        /* renamed from: com.catalinagroup.callrecorder.j.j$a$b */
        class C1804b implements OnClickListener {

            /* renamed from: c */
            final /* synthetic */ ArrayList f5419c;

            C1804b(ArrayList arrayList) {
                this.f5419c = arrayList;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = this.f5419c;
                C1550a[] aVarArr = (C1550a[]) arrayList.toArray(new C1550a[arrayList.size()]);
                C1802a aVar = C1802a.this;
                C1801j.m8070b(aVar.f5415e, aVarArr, aVar.f5416f, aVar.f5417g);
            }
        }

        C1802a(boolean z, C1550a[] aVarArr, Context context, C1808c cVar, Runnable runnable) {
            this.f5413c = z;
            this.f5414d = aVarArr;
            this.f5415e = context;
            this.f5416f = cVar;
            this.f5417g = runnable;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            C1550a[] aVarArr;
            if (!this.f5413c && this.f5414d.length > 1) {
                ArrayList arrayList = new ArrayList();
                for (C1550a aVar : this.f5414d) {
                    if (!aVar.mo6522w()) {
                        arrayList.add(aVar);
                    }
                }
                if (arrayList.size() != this.f5414d.length) {
                    C0507a aVar2 = new C0507a(this.f5415e);
                    aVar2.mo1822a(true);
                    aVar2.mo1832c(R.string.text_title_remove_records);
                    aVar2.mo1827b((int) R.string.text_confirm_remove_starred);
                    aVar2.mo1816a(C1831r.m8124a(this.f5415e, R.drawable.ic_warning_black_24dp, R.color.colorAccent));
                    aVar2.mo1812a((int) R.string.btn_no, (OnClickListener) new C1804b(arrayList));
                    aVar2.mo1828b((int) R.string.btn_yes, (OnClickListener) new C1803a());
                    aVar2.mo1833c(R.string.btn_cancel, null);
                    aVar2.mo1834c();
                    return;
                }
            }
            C1801j.m8070b(this.f5415e, this.f5414d, this.f5416f, this.f5417g);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.j$b */
    static class C1805b implements C1538e {

        /* renamed from: a */
        final /* synthetic */ C0507a f5421a;

        /* renamed from: b */
        final /* synthetic */ C1550a f5422b;

        /* renamed from: c */
        final /* synthetic */ EditText f5423c;

        /* renamed from: d */
        final /* synthetic */ Runnable f5424d;

        /* renamed from: e */
        final /* synthetic */ boolean f5425e;

        /* renamed from: f */
        final /* synthetic */ Context f5426f;

        /* renamed from: com.catalinagroup.callrecorder.j.j$b$a */
        class C1806a implements OnClickListener {
            C1806a() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                C1805b bVar = C1805b.this;
                bVar.f5422b.mo6501c(bVar.f5423c.getText().toString());
                Runnable runnable = C1805b.this.f5424d;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }

        /* renamed from: com.catalinagroup.callrecorder.j.j$b$b */
        class C1807b implements OnClickListener {
            C1807b() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                C1805b bVar = C1805b.this;
                if (!bVar.f5425e) {
                    Context context = bVar.f5426f;
                    if (context instanceof Activity) {
                        TutorialPremium.m8410a((Activity) context);
                    }
                }
            }
        }

        C1805b(C0507a aVar, C1550a aVar2, EditText editText, Runnable runnable, boolean z, Context context) {
            this.f5421a = aVar;
            this.f5422b = aVar2;
            this.f5423c = editText;
            this.f5424d = runnable;
            this.f5425e = z;
            this.f5426f = context;
        }

        public final void onFailure() {
            this.f5421a.mo1833c(R.string.btn_buy_premium, new C1807b());
        }

        public final void onSuccess(boolean z) {
            this.f5421a.mo1833c(R.string.btn_ok, new C1806a());
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.j.j$c */
    public interface C1808c {
        /* renamed from: a */
        void mo6677a(C1550a[] aVarArr);
    }

    /* renamed from: a */
    private static String m8050a(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public static boolean m8064a() {
        return true;
    }

    /* renamed from: b */
    public static String m8068b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (VERSION.SDK_INT >= 21) {
            return PhoneNumberUtils.normalizeNumber(str);
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int digit = Character.digit(charAt, 10);
            if (digit != -1) {
                sb.append(digit);
            } else if (sb.length() == 0 && charAt == '+') {
                sb.append(charAt);
            } else if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                return m8068b(PhoneNumberUtils.convertKeypadLettersToDigits(str));
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static C1813b m8073c(Context context, String str) {
        return new C1813b(context, str);
    }

    /* renamed from: d */
    public static void m8077d(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            launchIntentForPackage.addFlags(268468224);
            context.startActivity(launchIntentForPackage);
        } catch (Exception unused) {
        }
    }

    /* renamed from: e */
    public static synchronized String m8079e(Context context) {
        synchronized (C1801j.class) {
            if (VERSION.SDK_INT >= 26) {
                if (f5411b == null) {
                    f5411b = new NotificationChannel("cidCubeACRError", context.getString(R.string.app_name_short), 2);
                    f5411b.setShowBadge(false);
                    f5411b.setLockscreenVisibility(1);
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(f5411b);
                    }
                }
                String id = f5411b.getId();
                return id;
            }
            String str = "";
            return str;
        }
    }

    /* renamed from: f */
    public static boolean m8080f(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* renamed from: g */
    public static boolean m8081g(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PhoneRecording.kName);
        return (telephonyManager == null || telephonyManager.getPhoneType() == 0) ? false : true;
    }

    /* renamed from: h */
    public static boolean m8082h(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    /* renamed from: i */
    public static boolean m8083i(Context context) {
        return ((AudioManager) context.getSystemService("audio")).isWiredHeadsetOn();
    }

    /* renamed from: j */
    public static void m8084j(Context context) {
        try {
            Intent intent = new Intent(context, SplashScreenActivity.class);
            intent.addFlags(268468224);
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    /* renamed from: k */
    public static void m8085k(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        if (vibrator != null && vibrator.hasVibrator()) {
            if (VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, -1));
            } else {
                vibrator.vibrate(50);
            }
        }
    }

    /* renamed from: a */
    public static String m8051a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("-");
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: c */
    public static boolean m8075c() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled() || (defaultAdapter.getProfileConnectionState(1) != 2 && defaultAdapter.getProfileConnectionState(2) != 2)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m8065a(Context context, C1516c cVar, String str, String str2, String str3, String str4) {
        if (m8048a(context, cVar.mo6388b(str), str3, str4) != null || (str2 != null && cVar.mo6387a(str2, false) && PhoneRecording.kName.equals(str3) && C1811l.m8106d(context, str4))) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public static synchronized String m8076d(Context context) {
        synchronized (C1801j.class) {
            if (VERSION.SDK_INT >= 26) {
                if (f5410a == null) {
                    f5410a = new NotificationChannel("cidCubeACR", context.getString(R.string.app_name_short), 2);
                    f5410a.setShowBadge(false);
                    f5410a.setLockscreenVisibility(1);
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(f5410a);
                    }
                }
                String id = f5410a.getId();
                return id;
            }
            String str = "";
            return str;
        }
    }

    /* renamed from: c */
    public static String m8074c(Context context) {
        String str;
        try {
            str = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        } catch (IllegalArgumentException unused) {
            str = null;
        }
        return m8050a(str);
    }

    /* renamed from: a */
    public static void m8056a(Context context, C1516c cVar, String str, String str2, String str3, boolean z) {
        boolean z2;
        Set b = cVar.mo6388b(str);
        String a = m8048a(context, b, str2, str3);
        if (!z || a != null) {
            z2 = false;
        } else {
            b.add(m8051a(str2, str3));
            z2 = true;
        }
        if (!z && a != null) {
            b.remove(a);
            z2 = true;
        }
        if (z2) {
            cVar.mo6385a(str, b);
        }
    }

    /* renamed from: a */
    private static String m8048a(Context context, Set<String> set, String str, String str2) {
        String str3 = PhoneRecording.kName;
        if (str3.equals(str)) {
            for (String str4 : set) {
                if (str4.startsWith(str3) && PhoneNumberUtils.compare(context, str2, str4.substring(6))) {
                    return str4;
                }
            }
            return null;
        }
        String a = m8051a(str, str2);
        for (String str5 : set) {
            if (str5.equals(a)) {
                return str5;
            }
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m8072b(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m8071b(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if ("LGE".equalsIgnoreCase(android.os.Build.MANUFACTURER) == false) goto L_0x001e;
     */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m8078d() {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 19
            if (r0 <= r1) goto L_0x0027
            r1 = 21
            if (r0 != r1) goto L_0x001e
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "HTC"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 != 0) goto L_0x0027
            java.lang.String r0 = android.os.Build.MANUFACTURER
            java.lang.String r1 = "LGE"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 != 0) goto L_0x0027
        L_0x001e:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            r0 = 0
            goto L_0x0028
        L_0x0027:
            r0 = 1
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.catalinagroup.callrecorder.p123j.C1801j.m8078d():boolean");
    }

    /* renamed from: b */
    public static void m8069b(Activity activity, String str) {
        if (str != null) {
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.withAppendedPath(Contacts.CONTENT_URI, str));
                activity.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m8070b(Context context, C1550a[] aVarArr, C1808c cVar, Runnable runnable) {
        if (cVar != null) {
            cVar.mo6677a(aVarArr);
        }
        C1550a.m7306a(context, aVarArr, true, runnable);
    }

    /* renamed from: a */
    public static void m8058a(Context context, Recording recording, OnStartResult onStartResult) {
        if (C0797a.m3538a(context, "android.permission.RECORD_AUDIO") == 0 && C0797a.m3538a(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            recording.start(onStartResult);
            return;
        }
        Toast.makeText(context, R.string.enable_permission_audiorecord_writesdcard, 1).show();
        if (onStartResult != null) {
            onStartResult.onStartResult(false);
        }
    }

    /* renamed from: b */
    public static String m8067b() {
        if (f5412c == null) {
            StringBuilder sb = new StringBuilder();
            sb.append(m8050a(Build.MODEL));
            String str = "|";
            sb.append(str);
            sb.append(m8050a(Build.PRODUCT));
            sb.append(str);
            sb.append(m8050a(VERSION.RELEASE));
            f5412c = sb.toString();
        }
        return f5412c;
    }

    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m8043a(String r1, boolean r2) {
        /*
            int r0 = r1.hashCode()
            switch(r0) {
                case -1417823607: goto L_0x00f5;
                case -1360467711: goto L_0x00ea;
                case -1081572806: goto L_0x00df;
                case -810843126: goto L_0x00d4;
                case -791770330: goto L_0x00c9;
                case 97325: goto L_0x00be;
                case 97545: goto L_0x00b3;
                case 104395: goto L_0x00a8;
                case 108103: goto L_0x009e;
                case 3202587: goto L_0x0092;
                case 3321844: goto L_0x0086;
                case 3444122: goto L_0x007a;
                case 4944471: goto L_0x006f;
                case 93926699: goto L_0x0063;
                case 101812419: goto L_0x0057;
                case 106642798: goto L_0x004c;
                case 109512406: goto L_0x0041;
                case 109518736: goto L_0x0036;
                case 110127177: goto L_0x002a;
                case 112200956: goto L_0x001f;
                case 497130182: goto L_0x0014;
                case 1934780818: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0100
        L_0x0009:
            java.lang.String r0 = "whatsapp"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 6
            goto L_0x0101
        L_0x0014:
            java.lang.String r0 = "facebook"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 7
            goto L_0x0101
        L_0x001f:
            java.lang.String r0 = "viber"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 5
            goto L_0x0101
        L_0x002a:
            java.lang.String r0 = "tango"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 13
            goto L_0x0101
        L_0x0036:
            java.lang.String r0 = "slack"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 4
            goto L_0x0101
        L_0x0041:
            java.lang.String r0 = "skype"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 3
            goto L_0x0101
        L_0x004c:
            java.lang.String r0 = "phone"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 0
            goto L_0x0101
        L_0x0057:
            java.lang.String r0 = "kakao"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 9
            goto L_0x0101
        L_0x0063:
            java.lang.String r0 = "botim"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 20
            goto L_0x0101
        L_0x006f:
            java.lang.String r0 = "hangouts"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 2
            goto L_0x0101
        L_0x007a:
            java.lang.String r0 = "plus"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 17
            goto L_0x0101
        L_0x0086:
            java.lang.String r0 = "line"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 10
            goto L_0x0101
        L_0x0092:
            java.lang.String r0 = "hike"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 21
            goto L_0x0101
        L_0x009e:
            java.lang.String r0 = "mic"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 1
            goto L_0x0101
        L_0x00a8:
            java.lang.String r0 = "imo"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 8
            goto L_0x0101
        L_0x00b3:
            java.lang.String r0 = "bip"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 19
            goto L_0x0101
        L_0x00be:
            java.lang.String r0 = "bbm"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 12
            goto L_0x0101
        L_0x00c9:
            java.lang.String r0 = "wechat"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 14
            goto L_0x0101
        L_0x00d4:
            java.lang.String r0 = "vonage"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 16
            goto L_0x0101
        L_0x00df:
            java.lang.String r0 = "mailru"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 15
            goto L_0x0101
        L_0x00ea:
            java.lang.String r0 = "telegram"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 11
            goto L_0x0101
        L_0x00f5:
            java.lang.String r0 = "textnow"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x0100
            r1 = 18
            goto L_0x0101
        L_0x0100:
            r1 = -1
        L_0x0101:
            switch(r1) {
                case 0: goto L_0x0166;
                case 1: goto L_0x015c;
                case 2: goto L_0x0158;
                case 3: goto L_0x0154;
                case 4: goto L_0x0150;
                case 5: goto L_0x014c;
                case 6: goto L_0x0148;
                case 7: goto L_0x0144;
                case 8: goto L_0x0140;
                case 9: goto L_0x013c;
                case 10: goto L_0x0138;
                case 11: goto L_0x0134;
                case 12: goto L_0x0130;
                case 13: goto L_0x012c;
                case 14: goto L_0x0128;
                case 15: goto L_0x0124;
                case 16: goto L_0x0120;
                case 17: goto L_0x011c;
                case 18: goto L_0x0118;
                case 19: goto L_0x0113;
                case 20: goto L_0x010e;
                case 21: goto L_0x0109;
                default: goto L_0x0104;
            }
        L_0x0104:
            r1 = 2131231026(0x7f080132, float:1.8078121E38)
            goto L_0x016f
        L_0x0109:
            r1 = 2131230957(0x7f0800ed, float:1.8077981E38)
            goto L_0x016f
        L_0x010e:
            r1 = 2131230917(0x7f0800c5, float:1.80779E38)
            goto L_0x016f
        L_0x0113:
            r1 = 2131230916(0x7f0800c4, float:1.8077898E38)
            goto L_0x016f
        L_0x0118:
            r1 = 2131231023(0x7f08012f, float:1.8078115E38)
            goto L_0x016f
        L_0x011c:
            r1 = 2131230999(0x7f080117, float:1.8078067E38)
            goto L_0x016f
        L_0x0120:
            r1 = 2131231029(0x7f080135, float:1.8078127E38)
            goto L_0x016f
        L_0x0124:
            r1 = 2131230971(0x7f0800fb, float:1.807801E38)
            goto L_0x016f
        L_0x0128:
            r1 = 2131231032(0x7f080138, float:1.8078134E38)
            goto L_0x016f
        L_0x012c:
            r1 = 2131231021(0x7f08012d, float:1.8078111E38)
            goto L_0x016f
        L_0x0130:
            r1 = 2131230915(0x7f0800c3, float:1.8077896E38)
            goto L_0x016f
        L_0x0134:
            r1 = 2131231022(0x7f08012e, float:1.8078113E38)
            goto L_0x016f
        L_0x0138:
            r1 = 2131230968(0x7f0800f8, float:1.8078004E38)
            goto L_0x016f
        L_0x013c:
            r1 = 2131230966(0x7f0800f6, float:1.8078E38)
            goto L_0x016f
        L_0x0140:
            r1 = 2131230965(0x7f0800f5, float:1.8077998E38)
            goto L_0x016f
        L_0x0144:
            r1 = 2131230950(0x7f0800e6, float:1.8077967E38)
            goto L_0x016f
        L_0x0148:
            r1 = 2131231033(0x7f080139, float:1.8078136E38)
            goto L_0x016f
        L_0x014c:
            r1 = 2131231027(0x7f080133, float:1.8078123E38)
            goto L_0x016f
        L_0x0150:
            r1 = 2131231017(0x7f080129, float:1.8078103E38)
            goto L_0x016f
        L_0x0154:
            r1 = 2131231016(0x7f080128, float:1.8078101E38)
            goto L_0x016f
        L_0x0158:
            r1 = 2131230955(0x7f0800eb, float:1.8077977E38)
            goto L_0x016f
        L_0x015c:
            if (r2 == 0) goto L_0x0162
            r1 = 2131230978(0x7f080102, float:1.8078024E38)
            goto L_0x016f
        L_0x0162:
            r1 = 2131230977(0x7f080101, float:1.8078022E38)
            goto L_0x016f
        L_0x0166:
            if (r2 == 0) goto L_0x016c
            r1 = 2131230996(0x7f080114, float:1.807806E38)
            goto L_0x016f
        L_0x016c:
            r1 = 2131230995(0x7f080113, float:1.8078059E38)
        L_0x016f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.catalinagroup.callrecorder.p123j.C1801j.m8043a(java.lang.String, boolean):int");
    }

    /* renamed from: b */
    public static long m8066b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
        } catch (NameNotFoundException unused) {
            return 0;
        }
    }

    /* renamed from: a */
    private static String m8047a(Context context, String str) {
        String[] stringArray = context.getResources().getStringArray(R.array.audioSourceNames);
        String[] stringArray2 = context.getResources().getStringArray(R.array.audioSourceValues);
        for (int i = 0; i < stringArray2.length; i++) {
            if (str.equals(stringArray2[i])) {
                return stringArray[i];
            }
        }
        return "<invalid>";
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.lang.Boolean] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static String m8046a(Context r13, com.catalinagroup.callrecorder.database.C1516c r14) {
        /*
            java.text.SimpleDateFormat r0 = new java.text.SimpleDateFormat
            java.util.Locale r1 = java.util.Locale.US
            java.lang.String r2 = "yyyy-MM-dd"
            r0.<init>(r2, r1)
            com.catalinagroup.callrecorder.f.a r1 = com.catalinagroup.callrecorder.App.m6749b(r13)
            com.catalinagroup.callrecorder.f.a$g r1 = r1.mo6456b()
            java.lang.String r2 = "unavailable"
            if (r1 != 0) goto L_0x0018
            java.lang.String r1 = "none"
            goto L_0x0046
        L_0x0018:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = ""
            r3.append(r4)
            java.lang.String r4 = r1.f4839a
            r3.append(r4)
            java.lang.String r4 = " / "
            r3.append(r4)
            long r4 = r1.f4840b
            r6 = 0
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 != 0) goto L_0x0036
            r1 = r2
            goto L_0x003f
        L_0x0036:
            java.util.Date r1 = new java.util.Date
            r1.<init>(r4)
            java.lang.String r1 = r0.format(r1)
        L_0x003f:
            r3.append(r1)
            java.lang.String r1 = r3.toString()
        L_0x0046:
            java.util.Map r3 = com.catalinagroup.callrecorder.backup.BackupService.m6781a(r14)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0057:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x0081
            java.lang.Object r5 = r3.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.String r6 = "    "
            r4.append(r6)
            java.lang.Object r6 = r5.getKey()
            r4.append(r6)
            java.lang.String r6 = ": "
            r4.append(r6)
            java.lang.Object r5 = r5.getValue()
            r4.append(r5)
            java.lang.String r5 = "\n"
            r4.append(r5)
            goto L_0x0057
        L_0x0081:
            r3 = 1
            java.lang.String r5 = "autorecord"
            boolean r5 = r14.mo6387a(r5, r3)
            if (r5 == 0) goto L_0x008d
            java.lang.String r6 = "Unknown numbers excluded: "
            goto L_0x008f
        L_0x008d:
            java.lang.String r6 = "Unknown numbers autorecorded: "
        L_0x008f:
            r7 = 0
            if (r5 == 0) goto L_0x0099
            java.lang.String r8 = "excludedCalleesUnknown"
            boolean r8 = r14.mo6387a(r8, r7)
            goto L_0x009f
        L_0x0099:
            java.lang.String r8 = "autorecordCalleesUnknown"
            boolean r8 = r14.mo6387a(r8, r7)
        L_0x009f:
            java.util.Date r9 = new java.util.Date
            long r10 = m8066b(r13)
            r9.<init>(r10)
            java.lang.String r0 = r0.format(r9)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Manufacturer: "
            r9.append(r10)
            java.lang.String r10 = android.os.Build.MANUFACTURER
            r9.append(r10)
            r10 = 10
            r9.append(r10)
            java.lang.String r11 = "Device model: "
            r9.append(r11)
            java.lang.String r11 = m8067b()
            r9.append(r11)
            r9.append(r10)
            java.lang.String r11 = "Hardware: "
            r9.append(r11)
            java.lang.String r11 = android.os.Build.HARDWARE
            r9.append(r11)
            r9.append(r10)
            java.lang.String r11 = "Cube ACR version: "
            r9.append(r11)
            java.lang.String r11 = com.catalinagroup.callrecorder.p123j.C1832s.m8127a(r13)
            r9.append(r11)
            r9.append(r10)
            java.lang.String r11 = "Cube ACR package name: "
            r9.append(r11)
            java.lang.String r11 = r13.getPackageName()
            r9.append(r11)
            r9.append(r10)
            java.lang.String r11 = "UID: "
            r9.append(r11)
            java.lang.String r11 = m8049a(r14)
            r9.append(r11)
            r9.append(r10)
            java.lang.String r11 = "Install date: "
            r9.append(r11)
            if (r0 != 0) goto L_0x0111
            r0 = r2
        L_0x0111:
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Cube is enabled: "
            r9.append(r0)
            boolean r0 = com.catalinagroup.callrecorder.service.recordings.CallRecording.isEnabled(r14)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Don't record: ("
            r9.append(r0)
            java.util.HashSet r0 = com.catalinagroup.callrecorder.service.recordings.CallRecording.getServicesToSkip(r14)
            java.lang.String r11 = ","
            java.lang.String r0 = android.text.TextUtils.join(r11, r0)
            r9.append(r0)
            java.lang.String r0 = ")"
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Battery optimization is disabled: "
            r9.append(r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r11 = 23
            if (r0 < r11) goto L_0x0153
            boolean r0 = com.catalinagroup.callrecorder.p123j.C1809k.m8092b(r13)
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)
        L_0x0153:
            r9.append(r2)
            r9.append(r10)
            java.lang.String r0 = "Premium state: "
            r9.append(r0)
            r9.append(r1)
            r9.append(r10)
            java.lang.String r0 = "Back-up systems:\n"
            r9.append(r0)
            java.lang.String r0 = r4.toString()
            r9.append(r0)
            java.lang.String r0 = "Backup via cellular data: "
            r9.append(r0)
            java.lang.String r0 = "enableCellularAutoBackup"
            boolean r0 = r14.mo6387a(r0, r7)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Delete after backup: "
            r9.append(r0)
            java.lang.String r0 = "removeLocalAfterBackup"
            boolean r0 = r14.mo6387a(r0, r7)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "User-defined storage path: "
            r9.append(r0)
            java.lang.String r0 = com.catalinagroup.callrecorder.p117h.C1577e.m7458b(r13)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r0 = r0 ^ r3
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Automatic cleanup timeout (days): "
            r9.append(r0)
            long r0 = com.catalinagroup.callrecorder.backup.C1440a.m6830a(r14)
            r11 = 86400000(0x5265c00, double:4.2687272E-316)
            long r0 = r0 / r11
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Autorecord is enabled: "
            r9.append(r0)
            r9.append(r5)
            r9.append(r10)
            r9.append(r6)
            r9.append(r8)
            r9.append(r10)
            java.lang.String r0 = "Recording widget is visible: "
            r9.append(r0)
            java.lang.String r0 = "hideWidget"
            boolean r0 = r14.mo6387a(r0, r7)
            r0 = r0 ^ r3
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Phone recording audio source: "
            r9.append(r0)
            java.lang.Integer r0 = com.catalinagroup.callrecorder.service.recordings.PhoneRecording.kAudioSourcePrefDefaultValue
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "callsAudioSource"
            java.lang.String r0 = r14.mo6382a(r1, r0)
            java.lang.String r0 = m8047a(r13, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Phone auto-recording delay: "
            r9.append(r0)
            r0 = 1082130432(0x40800000, float:4.0)
            java.lang.String r1 = "phoneAutoRecordingDelay"
            float r0 = r14.mo6380a(r1, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "Phone calls clarity level: "
            r9.append(r0)
            java.lang.Integer r0 = com.catalinagroup.callrecorder.service.recordings.PhoneRecording.kEnhanceLoudnessLevelPrefDefaultValue
            int r0 = r0.intValue()
            long r0 = (long) r0
            java.lang.String r2 = "callsEnhanceAudioLevel"
            long r0 = r14.mo6381a(r2, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP check passed: "
            r9.append(r0)
            boolean r0 = com.catalinagroup.callrecorder.p123j.C1833t.m8130a(r14, r7)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP check ignored: "
            r9.append(r0)
            boolean r0 = com.catalinagroup.callrecorder.p123j.C1833t.m8130a(r14, r3)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP recording audio source: "
            r9.append(r0)
            java.lang.Integer r0 = com.catalinagroup.callrecorder.service.recordings.ActivityCallRecording.kAudioSourcePrefDefaultValue
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "voipCallsAudioSource"
            java.lang.String r0 = r14.mo6382a(r1, r0)
            java.lang.String r0 = m8047a(r13, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP auto-recording delay: "
            r9.append(r0)
            r0 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r1 = "voipAutoRecordingDelay"
            float r0 = r14.mo6380a(r1, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP calls clarity level: "
            r9.append(r0)
            java.lang.Integer r0 = com.catalinagroup.callrecorder.service.recordings.ActivityCallRecording.kEnhanceLoudnessLevelPrefDefaultValue
            int r0 = r0.intValue()
            long r0 = (long) r0
            java.lang.String r2 = "voipCallsEnhanceAudioLevel"
            long r0 = r14.mo6381a(r2, r0)
            r9.append(r0)
            r9.append(r10)
            java.lang.String r0 = "VoIP force in-call: "
            r9.append(r0)
            java.lang.String r0 = "voipForceInCallMode"
            boolean r14 = r14.mo6387a(r0, r7)
            r9.append(r14)
            r9.append(r10)
            java.lang.String r14 = "Accessibility service enabled: "
            r9.append(r14)
            boolean r13 = com.catalinagroup.callrecorder.service.AnyCallListenerService.m8158b(r13)
            r9.append(r13)
            r9.append(r10)
            java.lang.String r13 = r9.toString()
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.catalinagroup.callrecorder.p123j.C1801j.m8046a(android.content.Context, com.catalinagroup.callrecorder.database.c):java.lang.String");
    }

    /* renamed from: a */
    public static String m8049a(C1516c cVar) {
        String str = "userId";
        String a = cVar.mo6382a(str, "");
        if (!a.isEmpty()) {
            return a;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(UUID.randomUUID().toString());
        sb.append("}");
        String sb2 = sb.toString();
        cVar.mo6390b(str, sb2);
        return sb2;
    }

    /* renamed from: a */
    public static void m8053a(Activity activity, String str) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("tel:");
            sb.append(str);
            activity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(sb.toString())));
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m8054a(Activity activity, String str, String str2) {
        if (str == null) {
            try {
                Intent intent = new Intent("android.intent.action.INSERT");
                intent.setType("vnd.android.cursor.dir/contact");
                intent.putExtra("finishActivityOnSaveCompleted", true);
                intent.putExtra(PhoneRecording.kName, str2);
                activity.startActivityForResult(intent, 5052);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|11|(1:13)|14|16) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r4 = new java.lang.StringBuilder();
        r4.append("\n\n--\n");
        r4.append(r2);
        r1.putExtra(r3, r4.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003c, code lost:
        r3 = "android.intent.extra.TEXT";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0088 A[Catch:{ Exception -> 0x0090 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m8055a(Context r6) {
        /*
            com.catalinagroup.callrecorder.database.c r0 = new com.catalinagroup.callrecorder.database.c
            r0.<init>(r6)
            android.content.Intent r1 = new android.content.Intent     // Catch:{ Exception -> 0x0090 }
            java.lang.String r2 = "android.intent.action.SENDTO"
            java.lang.String r3 = "mailto:"
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x0090 }
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r2 = ""
            java.lang.String r2 = m8046a(r6, r0)     // Catch:{ Exception -> 0x0018 }
        L_0x0018:
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x003c }
            java.io.File r4 = r6.getExternalCacheDir()     // Catch:{ Exception -> 0x003c }
            java.lang.String r5 = "system_info.txt"
            r3.<init>(r4, r5)     // Catch:{ Exception -> 0x003c }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x003c }
            r4.<init>(r3)     // Catch:{ Exception -> 0x003c }
            byte[] r5 = r2.getBytes()     // Catch:{ Exception -> 0x003c }
            r4.write(r5)     // Catch:{ Exception -> 0x003c }
            r4.close()     // Catch:{ Exception -> 0x003c }
            java.lang.String r4 = "android.intent.extra.STREAM"
            android.net.Uri r3 = android.net.Uri.fromFile(r3)     // Catch:{ Exception -> 0x003c }
            r1.putExtra(r4, r3)     // Catch:{ Exception -> 0x003c }
            goto L_0x0052
        L_0x003c:
            java.lang.String r3 = "android.intent.extra.TEXT"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0090 }
            r4.<init>()     // Catch:{ Exception -> 0x0090 }
            java.lang.String r5 = "\n\n--\n"
            r4.append(r5)     // Catch:{ Exception -> 0x0090 }
            r4.append(r2)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0090 }
            r1.putExtra(r3, r2)     // Catch:{ Exception -> 0x0090 }
        L_0x0052:
            java.lang.String r2 = "android.intent.extra.EMAIL"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x0090 }
            r4 = 0
            java.lang.String r5 = "support@cubeacr.app"
            r3[r4] = r5     // Catch:{ Exception -> 0x0090 }
            r1.putExtra(r2, r3)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r2 = "android.intent.extra.SUBJECT"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0090 }
            r3.<init>()     // Catch:{ Exception -> 0x0090 }
            java.lang.String r4 = "Cube ACR Feedback - "
            r3.append(r4)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r0 = m8049a(r0)     // Catch:{ Exception -> 0x0090 }
            r3.append(r0)     // Catch:{ Exception -> 0x0090 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0090 }
            r1.putExtra(r2, r0)     // Catch:{ Exception -> 0x0090 }
            r0 = 2131755506(0x7f1001f2, float:1.9141893E38)
            java.lang.String r0 = r6.getString(r0)     // Catch:{ Exception -> 0x0090 }
            android.content.Intent r0 = android.content.Intent.createChooser(r1, r0)     // Catch:{ Exception -> 0x0090 }
            boolean r1 = r6 instanceof android.app.Activity     // Catch:{ Exception -> 0x0090 }
            if (r1 != 0) goto L_0x008d
            r1 = 268435456(0x10000000, float:2.5243549E-29)
            r0.addFlags(r1)     // Catch:{ Exception -> 0x0090 }
        L_0x008d:
            r6.startActivity(r0)     // Catch:{ Exception -> 0x0090 }
        L_0x0090:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.catalinagroup.callrecorder.p123j.C1801j.m8055a(android.content.Context):void");
    }

    /* renamed from: a */
    public static void m8059a(Context context, C1550a[] aVarArr) {
        String str;
        if (aVarArr.length != 0) {
            Intent intent = new Intent();
            String str2 = null;
            String str3 = "android.intent.extra.STREAM";
            if (aVarArr.length == 1) {
                intent.setAction("android.intent.action.SEND");
                C1580f l = aVarArr[0].mo6511l();
                str = C1790h.m8019a(l.mo6587e(), false);
                intent.putExtra(str3, l.mo6564i());
            } else {
                intent.setAction("android.intent.action.SEND_MULTIPLE");
                ArrayList arrayList = new ArrayList();
                String a = C1790h.m8019a(aVarArr[0].mo6511l().mo6587e(), false);
                String str4 = a;
                for (C1550a aVar : aVarArr) {
                    arrayList.add(aVar.mo6511l().mo6564i());
                    if (str4 != null && !str4.equalsIgnoreCase(C1790h.m8019a(aVar.mo6511l().mo6587e(), false))) {
                        str4 = null;
                    }
                }
                intent.putParcelableArrayListExtra(str3, arrayList);
                str = str4;
            }
            if (str != null) {
                str2 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
            }
            if (str2 == null) {
                str2 = "audio/*";
            }
            intent.setType(str2);
            intent.addFlags(1);
            try {
                context.startActivity(Intent.createChooser(intent, context.getString(R.string.text_share_menu)));
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static Notification m8044a(Context context, int i, int i2, int i3, boolean z) {
        C0780c cVar = new C0780c(context, m8076d(context));
        if (z || !C1776b.m7985b(context)) {
            if (i2 != -1) {
                cVar.mo3590b(context.getText(i2));
            }
            if (i3 != -1) {
                cVar.mo3584a(context.getText(i3));
            }
            if (i != -1) {
                cVar.mo3595d(i);
            }
        } else {
            cVar.mo3595d((int) R.drawable.ic_empty);
            cVar.mo3592c(-2);
        }
        return cVar.mo3576a();
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public static void m8061a(Context context, C1550a[] aVarArr, boolean z, C1808c cVar, Runnable runnable) {
        String str;
        if (aVarArr.length > 1) {
            str = context.getString(R.string.text_remove_records_confirmation_fmt, new Object[]{Integer.valueOf(aVarArr.length)});
        } else {
            str = context.getString(R.string.text_remove_record_confirmation);
        }
        C0507a aVar = new C0507a(context);
        aVar.mo1822a(true);
        aVar.mo1832c(R.string.text_title_remove_records);
        aVar.mo1820a((CharSequence) str);
        aVar.mo1816a(C1831r.m8124a(context, R.drawable.ic_warning_black_24dp, R.color.colorAccent));
        C1802a aVar2 = new C1802a(z, aVarArr, context, cVar, runnable);
        aVar.mo1833c(R.string.btn_yes, aVar2);
        aVar.mo1812a((int) R.string.btn_cancel, (OnClickListener) null);
        C0506d a = aVar.mo1825a();
        if (z && a.getWindow() != null) {
            a.getWindow().setType(VERSION.SDK_INT >= 26 ? 2038 : AdError.INTERNAL_ERROR_2003);
        }
        a.show();
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    public static void m8057a(Context context, C1550a aVar, boolean z, Runnable runnable) {
        View inflate = View.inflate(context, R.layout.dlg_comment, null);
        EditText editText = (EditText) inflate.findViewById(R.id.value);
        editText.setText(aVar.mo6500c());
        C0507a aVar2 = new C0507a(context);
        aVar2.mo1832c(R.string.title_record_comment);
        aVar2.mo1829b(inflate);
        aVar2.mo1822a(false);
        aVar2.mo1812a((int) R.string.btn_cancel, (OnClickListener) null);
        C1533a b = App.m6749b(context);
        C1805b bVar = new C1805b(aVar2, aVar, editText, runnable, z, context);
        b.mo6454a((C1538e) bVar);
        C0506d a = aVar2.mo1825a();
        if (z && a.getWindow() != null) {
            a.getWindow().setType(VERSION.SDK_INT >= 26 ? 2038 : AdError.INTERNAL_ERROR_2003);
        }
        a.show();
    }

    /* renamed from: a */
    public static void m8063a(Fragment fragment, int i) {
        fragment.mo3931a(new Intent("android.intent.action.PICK", Phone.CONTENT_URI), i);
    }

    /* renamed from: a */
    public static String m8045a(Context context, Intent intent) {
        try {
            Cursor query = context.getContentResolver().query(intent.getData(), null, null, null, null);
            if (query != null) {
                query.moveToFirst();
                String string = query.getString(query.getColumnIndex("data1"));
                query.close();
                return string;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: a */
    public static void m8062a(View view) {
        ObjectAnimator.ofFloat(view, "translationX", new float[]{-20.0f, 20.0f, -20.0f, 20.0f, -20.0f, 15.0f, -15.0f, 6.0f, -6.0f, 0.0f}).setDuration(500).start();
    }

    /* renamed from: a */
    public static void m8052a(long j) {
        try {
            Thread.sleep(j);
        } catch (Exception unused) {
        }
    }
}
