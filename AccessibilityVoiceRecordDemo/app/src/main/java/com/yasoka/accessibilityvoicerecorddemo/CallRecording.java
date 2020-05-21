package com.yasoka.accessibilityvoicerecorddemo;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;

/*import com.catalinagroup.callrecorder.App;
import com.catalinagroup.callrecorder.C1414a;
import com.catalinagroup.callrecorder.C1414a.C1416b;
import com.catalinagroup.callrecorder.backup.BackupService;
import com.catalinagroup.callrecorder.database.C1516c;
import com.catalinagroup.callrecorder.p115f.C1533a.C1538e;
import com.catalinagroup.callrecorder.p123j.C1782e;
import com.catalinagroup.callrecorder.p123j.C1790h;
import com.catalinagroup.callrecorder.p123j.C1801j;
import com.catalinagroup.callrecorder.p123j.C1827q;
import com.catalinagroup.callrecorder.p123j.C1833t;
import com.catalinagroup.callrecorder.service.C1858a;
import com.catalinagroup.callrecorder.service.C1858a.C1859a;*/

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public abstract class CallRecording extends Recording {
    public static final String kAutoRecordCalleesPrefName = "autorecordCallees";
    public static final boolean kAutoRecordPrefDefaultValue = true;
    public static final String kAutoRecordPrefName = "autorecord";
    public static final String kAutoRecordUnknownCalleesPrefName = "autorecordCalleesUnknown";
    public static final String kAutoStarCalleesPrefName = "autoStarCallees";
    /* access modifiers changed from: private */
    public static final String kDontSaveShortCallsTimedPrefDefaultValue = Integer.toString(0);
    public static final String kDontSaveShortCallsTimedPrefName = "dontSaveShortCallsTimed";
    private static final boolean kEnabledPrefDefaultValue = true;
    private static final String kEnabledPrefName = "callRecordingEnabled";
    public static final String kExcludedCalleesPrefName = "excludedCallees";
    public static final String kExcludedUnknownCalleesPrefName = "excludedCalleesUnknown";
    public static final int kRecordedCallsCounterPrefDefaultValue = 0;
    public static final String kRecordedCallsCounterPrefName = "recordedCallsCounter";
    private static final String kServicesDelimiter = ",";
    private static final String kServicesNotToRecordPrefName = "servicesNotToRecord";
    private AudioManagerStateBackup ammb_ = null;
    private C1858a callInfo_;

    protected class AudioManagerStateBackup {
        private Boolean doRestoreForceInCall_ = null;
        private Handler poster_;
        private Boolean savedSpeakerphoneOn_ = null;
        private Integer savedVolume_ = null;

        AudioManagerStateBackup(boolean z, boolean z2, boolean z3) {
            final AudioManager audioManager = (AudioManager) CallRecording.this.getContext().getSystemService(Context.AUDIO_SERVICE);
            if (z2) {
                this.savedVolume_ = Integer.valueOf(audioManager.getStreamVolume(0));
            }
            if (z) {
                this.savedSpeakerphoneOn_ = Boolean.valueOf(audioManager.isSpeakerphoneOn());
            }
            if (z3) {
                this.doRestoreForceInCall_ = Boolean.valueOf(true);
                C1827q.f5466a.execute(new Runnable(CallRecording.this) {
                    public void run() {
                        audioManager.setParameters("INCALL_RECORDING_MODE=ON");
                        audioManager.setParameters("VOICE_RECORDING_MODE=ON");
                    }
                });
            }
            this.poster_ = new Handler();
            Handler handler = this.poster_;
            final CallRecording callRecording = CallRecording.this;
            final boolean z4 = z;
            final boolean z5 = z2;
            C19002 r1 = new Runnable() {
                public void run() {
                    try {
                        if (z4) {
                            audioManager.setSpeakerphoneOn(true);
                        }
                        if (z5) {
                            audioManager.setStreamVolume(0, audioManager.getStreamMaxVolume(0), 1);
                        }
                    } catch (Exception unused) {
                    }
                }
            };
            handler.post(r1);
        }

        public void restore() {
            final AudioManager audioManager = (AudioManager) CallRecording.this.getContext().getSystemService("audio");
            Handler handler = this.poster_;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            Boolean bool = this.savedSpeakerphoneOn_;
            if (bool != null) {
                audioManager.setSpeakerphoneOn(bool.booleanValue());
            }
            Integer num = this.savedVolume_;
            if (num != null) {
                audioManager.setStreamVolume(0, num.intValue(), 1);
            }
            Boolean bool2 = this.doRestoreForceInCall_;
            if (bool2 != null && bool2.booleanValue()) {
                C1827q.f5466a.execute(new Runnable() {
                    public void run() {
                        audioManager.setParameters("INCALL_RECORDING_MODE=OFF");
                        audioManager.setParameters("VOICE_RECORDING_MODE=OFF");
                    }
                });
            }
        }
    }

    public interface Listener extends com.catalinagroup.callrecorder.service.recordings.Recording.Listener {
        void onCallInfo(CallRecording callRecording);
    }

    protected CallRecording(String str, C1858a aVar, Context context) {
        super(str, context);
        this.callInfo_ = aVar;
    }

    public static String generateBaseFileName(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str3)) {
            return str2;
        }
        String a = C1790h.m8018a(str3);
        try {
            int a2 = BackupService.m6778a(str, str4);
            while (a.getBytes("UTF-8").length > a2) {
                a = a.substring(0, a.length() - 1);
            }
        } catch (Exception unused) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append("_");
        sb.append(a);
        return sb.toString();
    }

    public static HashSet<String> getServicesToSkip(C1516c cVar) {
        HashSet<String> hashSet = new HashSet<>();
        if (!C1833t.m8130a(cVar, true)) {
            return hashSet;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(cVar.mo6382a(kServicesNotToRecordPrefName, ""), ",");
        while (stringTokenizer.hasMoreTokens()) {
            hashSet.add(stringTokenizer.nextToken());
        }
        return hashSet;
    }

    public static boolean isEnabled(C1516c cVar) {
        return cVar.mo6387a(kEnabledPrefName, true);
    }

    public static void migrate(Context context, C1516c cVar) {
        String str = "dontSaveShortCalls";
        if (cVar.mo6387a(str, false)) {
            cVar.mo6393c(str);
            cVar.mo6390b(kDontSaveShortCallsTimedPrefName, Integer.toString(5000));
        }
    }

    public static void setEnabled(C1516c cVar, boolean z) {
        cVar.mo6392b(kEnabledPrefName, z);
    }

    public static void setServicesToSkip(C1516c cVar, HashSet<String> hashSet) {
        cVar.mo6390b(kServicesNotToRecordPrefName, TextUtils.join(",", hashSet));
    }

    /* access modifiers changed from: protected */
    public String fillBaseFileName(String str, String str2) {
        String type = getType();
        C1858a aVar = this.callInfo_;
        return generateBaseFileName(type, str, aVar == null ? null : aVar.f5518b, str2);
    }

    /* access modifiers changed from: protected */
    public void fillProperties(final Map<String, String> map) {
        C1858a aVar = this.callInfo_;
        if (aVar != null) {
            String str = aVar.f5518b;
            if (str != null) {
                map.put("callee", str);
                if (C1801j.m8065a(getContext(), getCommonPreferences(), kAutoStarCalleesPrefName, (String) null, getType(), this.callInfo_.f5518b)) {
                    App.m6749b(getContext()).mo6454a((C1538e) new C1538e() {
                        public void onFailure() {
                        }

                        public void onSuccess(boolean z) {
                            map.put("starred", String.valueOf(true));
                        }
                    });
                }
            }
            C1859a aVar2 = this.callInfo_.f5517a;
            if (aVar2 != C1859a.Unknown) {
                map.put("direction", aVar2.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean forceInCallRecording() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public abstract int getAutoRecordingStartDelay();

    public C1858a getCallInfo() {
        return this.callInfo_;
    }

    public abstract boolean isInList(Set<String> set);

    /* access modifiers changed from: protected */
    public boolean needGainVolume() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean needTurnOnLoudspeaker();

    /* access modifiers changed from: protected */
    public void onAddListener(Recording.Listener listener) {
        super.onAddListener(listener);
        if (listener instanceof Listener) {
            ((Listener) listener).onCallInfo(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onNeedRestoreParams() {
        super.onNeedRestoreParams();
        AudioManagerStateBackup audioManagerStateBackup = this.ammb_;
        if (audioManagerStateBackup != null) {
            audioManagerStateBackup.restore();
            this.ammb_ = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onNeedSetParams() {
        super.onNeedSetParams();
        this.ammb_ = new AudioManagerStateBackup(needTurnOnLoudspeaker(), needGainVolume(), forceInCallRecording());
    }

    /* access modifiers changed from: protected */
    public void onStopped(String str, Map<String, String> map, boolean z) {
        super.onStopped(str, map, z);
        if (z) {
            C1516c commonPreferences = getCommonPreferences();
            String str2 = kRecordedCallsCounterPrefName;
            int a = (int) commonPreferences.mo6381a(str2, 0);
            if (a == 0) {
                C1414a.m6762a(C1416b.FirstRecordDone, C1801j.m8067b());
            }
            commonPreferences.mo6389b(str2, (long) (a + 1));
        }
    }

    /* access modifiers changed from: protected */
    public void setCallInfo(C1858a aVar) {
        this.callInfo_ = aVar;
        Iterator it = this.listeners_.iterator();
        while (it.hasNext()) {
            Recording.Listener listener = (Recording.Listener) it.next();
            if (listener instanceof Listener) {
                ((Listener) listener).onCallInfo(this);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean shouldDiscard(final long j) {
        final C1782e eVar = new C1782e(false);
        App.m6749b(getContext()).mo6454a((C1538e) new C1538e() {
            public final void onFailure() {
            }

            public final void onSuccess(boolean z) {
                String a = CallRecording.this.getCommonPreferences().mo6382a(CallRecording.kDontSaveShortCallsTimedPrefName, CallRecording.kDontSaveShortCallsTimedPrefDefaultValue);
                boolean z2 = false;
                Integer valueOf = Integer.valueOf(0);
                try {
                    valueOf = Integer.valueOf(Integer.parseInt(a));
                } catch (Exception unused) {
                }
                C1782e eVar = null;
                if (valueOf.intValue() > 0 && j < ((long) valueOf.intValue())) {
                    z2 = true;
                }
                eVar.f5382a = z2;
            }
        });
        return eVar.f5382a;
    }

    public abstract boolean tryGetCallInfo(AccessibilityNodeInfo accessibilityNodeInfo, Set<AccessibilityNodeInfo> set);
}
