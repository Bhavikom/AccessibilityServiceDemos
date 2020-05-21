package com.yasoka.accessibilityvoicerecorddemo;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.catalinagroup.callrecorder.p123j.C1780d;
import com.catalinagroup.callrecorder.p123j.C1801j;
import com.facebook.appevents.C2638g;
import com.google.android.gms.analytics.C3705d;
import com.google.android.gms.analytics.C3708e;
import com.google.android.gms.analytics.C3713j;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

/* renamed from: com.catalinagroup.callrecorder.a */
public class C1414a {

    /* renamed from: d */
    private static volatile C1780d<C1414a> f4551d;

    /* renamed from: a */
    //private FirebaseAnalytics f4552a;

    /* renamed from: b */
    private C3713j f4553b;

    /* renamed from: c */
    private C2638g f4554c;

    /* renamed from: com.catalinagroup.callrecorder.a$a */
    static class C1415a extends C1780d<C1414a> {

        /* renamed from: b */
        final /* synthetic */ Context f4555b;

        C1415a(Context context) {
            this.f4555b = context;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public C1414a m6768a() {
            return new C1414a(this.f4555b, null);
        }
    }

    /* renamed from: com.catalinagroup.callrecorder.a$b */
    public enum C1416b {
        CustomPowerManagementNotFound("ca_cpm_not_found", "settings", "custom_power_management_not_found"),
        PhoneRecordVoiceCallSoftSamsungDefaultSet("ca_phone_vcall_s_init_samsung", "settings", "phone_record_vcall_soft_initial_set_samsung_2"),
        PhoneRecordVoiceCallInitialSet("ca_phone_vcall_h_init", "settings", "phone_record_vcall_initial_set_2"),
        PhoneRecordVoiceCallSoftInitialSet("ca_phone_vcall_s_init", "settings", "phone_record_vcall_soft_initial_set_2"),
        PhoneRecordVoiceCommSoftInitialSet("ca_phone_vcomm_s_init", "settings", "phone_record_vcomm_soft_initial_set_2"),
        PhoneRecordVoiceRecognInitialSet("ca_phone_vrec_init", "settings", "phone_record_vrec_initial_set_2"),
        PhoneRecordVoiceRecognSoftInitialSet("ca_phone_vrec_s_init", "settings", "phone_record_vrec_soft_initial_set_2"),
        PhoneRecordVoiceCallApproved("ca_phone_vcall_h_approved", "settings", "phone_record_vcall_approved_v2"),
        PhoneRecordVoiceCallSoftApproved("ca_phone_vcall_s_approved", "settings", "phone_record_vcall_soft_approved_v2"),
        PhoneRecordVoiceCommSoftApproved("ca_phone_vcomm_s_approved", "settings", "phone_record_vcomm_soft_approved_v2"),
        PhoneRecordEnhanceLoudnessApproved("ca_phone_ell_approved", "settings", "phone_record_ell_approved_v2"),
        VoIPRecordVoiceCallInitialSet("ca_voip_vcall_h_init", "settings", "voip_record_vcall_initial_set_2"),
        VoIPRecordVoiceCallSoftInitialSet("ca_voip_vcall_s_init", "settings", "voip_record_vcall_soft_initial_set_2"),
        VoIPRecordVoiceCommSoftInitialSet("ca_voip_vcomm_s_init", "settings", "voip_record_vcomm_soft_initial_set_2"),
        VoIPRecordForceInCallInitialSet("ca_voip_fic_init", "settings", "voip_record_force_incall_initial_set_2"),
        VoIPRecordVoiceCallApproved("ca_voip_vcall_h_approved", "settings", "voip_record_vcall_approved_2"),
        VoIPRecordVoiceCallSoftApproved("ca_voip_vcall_s_approved", "settings", "voip_record_vcall_soft_approved_2"),
        VoIPRecordVoiceCommSoftApproved("ca_voip_vcomm_s_approved", "settings", "voip_record_vcomm_soft_approved_2"),
        VoIPRecordEnhanceLoudnessApproved("ca_voip_ell_approved", "settings", "voip_record_ell_approved_v2"),
        VoIPRecordForceInCallApproved("ca_voip_fic_approved", "settings", "voip_record_force_incall_approved_2"),
        VoIPPositive("ca_voip_positive", "settings", "voip_record_positive_v2"),
        SoftRecordNegative("ca_soft_negative", "settings", "soft_record_negative_v2"),
        GeoAllowed("ca_geo_allowed", "settings", "geo_allowed"),
        DataCollectionAllowed("ca_data_allowed", "settings", "data_allowed"),
        ApplicationLaunch("ca_app_launch", "app", "launch"),
        ShowRecordsList("ca_show_records_list", "records_list", "open"),
        SubscriptionPurchase("ca_iab_subscription_purchase", "iab", "subscription_purchase"),
        SubscriptionPurchase2b1m("ca_iab_subscription_purchase_2b1m", "iab", "subscription_purchase_2b1m"),
        SubscriptionPurchase12b1y("ca_iab_subscription_purchase_12b1y", "iab", "subscription_purchase_12b1y"),
        FirstRecordDone("ca_first_record", "record", "first_create"),
        NativeAdShow("ca_show_native_ad", "native_ad", "show"),
        InterstitialAdShow("ca_show_interstitial_ad", "interstitial_ad", "show"),
        TutorialComplete("ca_tutorial_complete", "tutorial", "complete"),
        YearSubPromoShown("ca_iab_ysp_shown", "iab", "ysp_shown"),
        YearSubPromoApproved("ca_iab_ysp_approved", "iab", "ysp_approved"),
        YearSubPromoPurchased("ca_iab_ysp_puchased", "iab", "ysp_purchased"),
        YearSubPromoDeclined("ca_iab_ysp_declined", "iab", "ysp_declined");
        
        /* access modifiers changed from: private */

        /* renamed from: c */
        public final String f4594c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public final String f4595d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public final String f4596e;

        private C1416b(String str, String str2, String str3) {
            this.f4594c = str;
            this.f4595d = str2;
            this.f4596e = str3;
        }
    }

    /* synthetic */ C1414a(Context context, C1415a aVar) {
        this(context);
    }

    /* renamed from: a */
    public static void m6759a(Context context) {
        if (f4551d == null) {
            f4551d = new C1415a(context);
            f4551d.mo6921c();
        }
    }

    private C1414a(Context context) {
        /*if (!FirebaseApp.m32347a(context).isEmpty()) {
            this.f4552a = FirebaseAnalytics.getInstance(context);
            this.f4552a.mo21147a(true);
        }*/
        this.f4553b = C3705d.m15663a(context).mo11689a((int) R.xml.global_tracker);
        this.f4553b.mo11730a(true);
        this.f4553b.mo11726a(10.0d);
        App a = App.m6747a(context);
        if (a != null) {
            C2638g.m10289a((Application) a);
            this.f4554c = C2638g.m10294b(a);
        }
        m6763a(C1416b.ApplicationLaunch, C1801j.m8074c(context), (Integer) null);
    }

    /* renamed from: a */
    private void m6766a(String str, String str2, String str3, String str4, Integer num) {
        FirebaseAnalytics firebaseAnalytics = this.f4552a;
        if (!(firebaseAnalytics == null || str == null)) {
            firebaseAnalytics.mo21146a(str, m6757a(str4, num, true));
        }
        C2638g gVar = this.f4554c;
        if (!(gVar == null || str == null)) {
            gVar.mo8744a(str, m6757a(str4, num, false));
        }
        if (this.f4553b != null && str2 != null && str3 != null) {
            C3708e eVar = new C3708e();
            eVar.mo11712b(str2);
            eVar.mo11711a(str3);
            if (str4 != null) {
                eVar.mo11713c(str4);
            }
            if (num != null) {
                eVar.mo11710a((long) num.intValue());
            }
            this.f4553b.mo11729a(eVar.mo11717a());
        }
    }

    /* renamed from: a */
    private void m6763a(C1416b bVar, String str, Integer num) {
        m6766a(bVar.f4594c, bVar.f4595d, bVar.f4596e, str, num);
    }

    /* renamed from: a */
    public static void m6760a(C1416b bVar) {
        C1414a aVar = f4551d == null ? null : (C1414a) f4551d.mo6920b();
        if (aVar != null) {
            aVar.m6763a(bVar, (String) null, (Integer) null);
        }
    }

    /* renamed from: a */
    public static void m6762a(C1416b bVar, String str) {
        C1414a aVar = f4551d == null ? null : (C1414a) f4551d.mo6920b();
        if (aVar != null) {
            aVar.m6763a(bVar, str, (Integer) null);
        }
    }

    /* renamed from: a */
    public static void m6764a(String str, String str2) {
        C1414a aVar = f4551d == null ? null : (C1414a) f4551d.mo6920b();
        if (aVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ca_create_");
            sb.append(str);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("record_");
            sb3.append(str);
            aVar.m6766a(sb2, sb3.toString(), "create", str2, null);
        }
    }

    /* renamed from: a */
    public static void m6765a(String str, String str2, int i) {
        C1414a aVar = f4551d == null ? null : (C1414a) f4551d.mo6920b();
        if (aVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("ca_recorded_");
            sb.append(str);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append("recorded_");
            sb3.append(str);
            aVar.m6766a(sb2, sb3.toString(), "create", str2, Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public static void m6761a(C1416b bVar, int i, int i2, String str) {
        C1414a aVar = f4551d == null ? null : (C1414a) f4551d.mo6920b();
        if (aVar != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("_b");
            sb.append(i);
            sb.append("_t");
            sb.append(i2);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(bVar.f4594c);
            sb3.append(sb2);
            String sb4 = sb3.toString();
            String b = bVar.f4595d;
            StringBuilder sb5 = new StringBuilder();
            sb5.append(bVar.f4596e);
            sb5.append(sb2);
            aVar.m6766a(sb4, b, sb5.toString(), str, null);
        }
    }

    /* renamed from: a */
    private Bundle m6757a(String str, Integer num, boolean z) {
        if (str == null && num == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        if (str != null) {
            if (z) {
                str = m6758a(str);
            }
            bundle.putString("label", str);
        }
        if (num != null) {
            bundle.putInt("value", num.intValue());
        }
        return bundle;
    }

    /* renamed from: a */
    private static String m6758a(String str) {
        return str.replace("|", "_ _");
    }
}
