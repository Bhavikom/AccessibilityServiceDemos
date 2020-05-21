package com.yasoka.accessibilityvoicerecorddemo;

import android.content.Context;
import android.location.Location;
import android.widget.Toast;

/*import com.catalinagroup.callrecorder.App;
import com.catalinagroup.callrecorder.C1414a;
import com.catalinagroup.callrecorder.database.C1516c;
import com.catalinagroup.callrecorder.p115f.C1533a.C1538e;
import com.catalinagroup.callrecorder.p123j.C1790h;
import com.catalinagroup.callrecorder.p123j.C1795i;
import com.catalinagroup.callrecorder.p123j.C1795i.C1798b;
import com.catalinagroup.callrecorder.p123j.C1795i.C1800c;
import com.catalinagroup.callrecorder.p123j.C1801j;
import com.catalinagroup.callrecorder.p123j.C1825p;
import com.catalinagroup.callrecorder.p123j.C1825p.C1826a;
import com.catalinagroup.callrecorder.p123j.C1827q;
import com.catalinagroup.callrecorder.service.recorders.C1874a;
import com.catalinagroup.callrecorder.service.recorders.C1877b;
import com.catalinagroup.callrecorder.service.recorders.C1877b.C1882e;
import com.catalinagroup.callrecorder.service.recorders.C1877b.C1883f;
import com.catalinagroup.callrecorder.service.recorders.C1884c;
import com.google.android.gms.maps.model.LatLng;
import com.mopub.mobileads.VastIconXmlManager;*/

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class Recording {
    String str = null;

    public static final String kDateTimeFormat = "yyyyMMdd-HHmmss";
    private static final String kGeoTaggingEnabledPrefName = "recordingGeoTagging";
    public static final String kGeoTaggingUnintrusivePrefName = "recordingGeoUnintrusive";
    public static final boolean kShakeMarkEnabledPrefDefaultValue = true;
    public static final String kShakeMarkEnabledPrefName = "recordingShakeMarkEnabled";
    private static final boolean kShakeMarkVibratePrefDefaultValue = true;
    public static final String kShakeMarkVibratePrefName = "recordingShakeMarkVibrate";
    /* access modifiers changed from: private */
    public final C1516c commonPrefs_;
    /* access modifiers changed from: private */
    public final Context ctx_;
    protected ArrayList<Listener> listeners_;
    /* access modifiers changed from: private */
    public volatile C1877b recorder_;
    /* access modifiers changed from: private */
    public C1825p shakeDetector_;
    /* access modifiers changed from: private */
    public String startAddress_ = null;
    private Location startLocation_ = null;
    /* access modifiers changed from: private */
    public String startRelativeFilePath_;
    /* access modifiers changed from: private */
    public long startTime_ = 0;
    private long stopTime_ = 0;
    /* access modifiers changed from: private */
    public ArrayList<Long> timelapseMarks_ = new ArrayList<>();
    private final String type_;
    private boolean wasStarted_ = false;
    /* access modifiers changed from: private */
    public boolean wasStoppedSuccessfully_ = false;

    public interface Listener {
        void onBeforeStart(Recording recording);

        void onBeforeStop(Recording recording);

        void onError(Recording recording);

        void onStarted(Recording recording);

        void onStopped(Recording recording, String str, Map<String, String> map, boolean z);
    }

    public interface OnStartResult {
        void onStartResult(boolean z);
    }

    private class ShakeListener implements C1825p.C1826a {
        private ShakeListener() {
        }

        public void onShake() {
            if (Recording.this.recorder_ != null) {
                Recording.this.timelapseMarks_.add(Long.valueOf(Math.max((System.currentTimeMillis() - Recording.this.startTime_) - 200, 0)));
                Toast.makeText(Recording.this.ctx_, R.string.text_timelapse_mark_added, Toast.LENGTH_LONG).show();
                if (Recording.this.commonPrefs_.mo6387a(Recording.kShakeMarkVibratePrefName, true)) {
                    C1801j.m8085k(Recording.this.ctx_);
                }
            }
        }
    }

    protected Recording(String str, Context context) {
        this.type_ = str;
        this.ctx_ = context;
        this.listeners_ = new ArrayList<>();
        this.commonPrefs_ = new C1516c(context);
        if (this.commonPrefs_.mo6387a(kShakeMarkEnabledPrefName, true)) {
            App.m6749b(context).mo6454a((C1538e) new C1538e() {
                public final void onFailure() {
                }

                public final void onSuccess(boolean z) {
                    Recording recording = Recording.this;
                    recording.shakeDetector_ = new C1825p(recording.ctx_);
                }
            });
        }
    }

    public static String generateBaseFileName(String str, long j) {
        String format = new SimpleDateFormat(kDateTimeFormat).format(Long.valueOf(j));
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_");
        sb.append(format);
        return sb.toString();
    }

    private String getBaseFileName(long j, String str) {
        return fillBaseFileName(generateBaseFileName(this.type_, j), str);
    }

    private Map<String, String> getProperties() {
        HashMap hashMap = new HashMap();
        hashMap.put(VastIconXmlManager.DURATION, String.valueOf(this.stopTime_ - this.startTime_));
        if (this.startLocation_ != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(this.startLocation_.getLatitude()));
            sb.append(";");
            sb.append(String.valueOf(this.startLocation_.getLongitude()));
            hashMap.put("loc", sb.toString());
            String str = this.startAddress_;
            if (str != null) {
                hashMap.put("addr", str);
            }
        }
        if (!this.timelapseMarks_.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            boolean z = true;
            Iterator it = this.timelapseMarks_.iterator();
            while (it.hasNext()) {
                Long l = (Long) it.next();
                if (!z) {
                    sb2.append(OAuth.SCOPE_DELIMITER);
                }
                sb2.append(l.toString());
                z = false;
            }
            hashMap.put("tlm", sb2.toString());
        }
        fillProperties(hashMap);
        return hashMap;
    }

    /* access modifiers changed from: private */
    public String getRelativePathBase(long j, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("All");
        sb.append(File.separator);
        sb.append(getBaseFileName(j, str));
        return sb.toString();
    }

    public static boolean isGeoTaggingEnabled(C1516c cVar) {
        return cVar.mo6387a(kGeoTaggingEnabledPrefName, false);
    }

    public static void setGeoTaggingEnabled(C1516c cVar, boolean z) {
        cVar.mo6392b(kGeoTaggingEnabledPrefName, z);
    }

    /* access modifiers changed from: private */
    public void updateLocation(Location location) {
        if (location != null) {
            this.startLocation_ = location;
            C1795i.m8037a(getContext(), new LatLng(location.getLatitude(), location.getLongitude()), (C1800c) new C1800c() {
                public void onAddressResolved(LatLng latLng, String str) {
                    if (Recording.this.inProgress()) {
                        Recording.this.startAddress_ = str;
                    }
                }
            });
        }
    }

    public void addListener(Listener listener) {
        this.listeners_.add(listener);
        onAddListener(listener);
    }

    /* access modifiers changed from: protected */
    public abstract byte enhanceLoudnessLevel();

    /* access modifiers changed from: protected */
    public abstract String fillBaseFileName(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void fillProperties(Map<String, String> map);

    /* access modifiers changed from: protected */
    public C1516c getCommonPreferences() {
        return this.commonPrefs_;
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.ctx_;
    }

    public String getType() {
        return this.type_;
    }

    public boolean inProgress() {
        return this.recorder_ != null;
    }

    /* access modifiers changed from: protected */
    public void onAddListener(Listener listener) {
    }

    /* access modifiers changed from: protected */
    public void onNeedRestoreParams() {
    }

    /* access modifiers changed from: protected */
    public void onNeedSetParams() {
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    public void onStopped(String str, Map<String, String> map, boolean z) {
    }

    /* access modifiers changed from: protected */
    public abstract int preferredAudioMode();

    /* access modifiers changed from: protected */
    public abstract int preferredAudioSource();

    public void release() {
        this.listeners_.clear();
    }

    /* access modifiers changed from: protected */
    public boolean shouldDiscard(long j) {
        return false;
    }

    public final void start(final OnStartResult onStartResult) {
        if (this.recorder_ == null) {
            this.wasStarted_ = true;
            this.wasStoppedSuccessfully_ = false;
            long currentTimeMillis = System.currentTimeMillis();
            this.stopTime_ = currentTimeMillis;
            this.startTime_ = currentTimeMillis;
            this.startLocation_ = null;
            this.startAddress_ = null;
            this.timelapseMarks_.clear();
            int preferredAudioSource = preferredAudioSource();
            int a = C1877b.m8248a(preferredAudioSource);
            onNeedSetParams();
            Iterator it = new ArrayList(this.listeners_).iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onBeforeStart(this);
            }
            C1877b cVar = C1877b.m8254b(preferredAudioSource) ? new C1884c(this.ctx_) : new C1874a(this.ctx_);
            String b = cVar.mo7022b();
            StringBuilder sb = new StringBuilder();
            sb.append(getRelativePathBase(this.startTime_, b));
            sb.append(b);
            this.startRelativeFilePath_ = sb.toString();
            cVar.mo7020a((C1882e) new C1882e() {
                public void onError(C1877b bVar, int i, int i2) {
                    if (!Recording.this.wasStoppedSuccessfully_) {
                        Iterator it = new ArrayList(Recording.this.listeners_).iterator();
                        while (it.hasNext()) {
                            ((Listener) it.next()).onError(Recording.this);
                        }
                        if (Recording.this.startRelativeFilePath_ != null) {
                            final String access$300 = Recording.this.startRelativeFilePath_;
                            C1827q.f5467b.execute(new Runnable() {
                                public void run() {
                                    C1790h.m8020a(Recording.this.ctx_, access$300);
                                }
                            });
                        }
                    }
                    Recording.this.onNeedRestoreParams();
                }
            });
            this.recorder_ = cVar;
            cVar.mo7019a(a, preferredAudioMode(), enhanceLoudnessLevel(), this.startRelativeFilePath_, new C1883f() {
                public void onStartResult(boolean z) {
                    C19061 r0 = null;
                    if (z) {
                        Iterator it = new ArrayList(Recording.this.listeners_).iterator();
                        while (it.hasNext()) {
                            ((Listener) it.next()).onStarted(Recording.this);
                        }
                        C1414a.m6764a(Recording.this.getType(), C1801j.m8067b());
                        Recording.this.onStarted();
                        if (Recording.this.shakeDetector_ != null) {
                            Recording.this.shakeDetector_.mo6956a(new ShakeListener());
                        }
                        if (Recording.isGeoTaggingEnabled(Recording.this.getCommonPreferences())) {
                            Context access$100 = Recording.this.ctx_;
                            if (!Recording.this.getCommonPreferences().mo6387a(Recording.kGeoTaggingUnintrusivePrefName, false)) {
                                r0 = new C1798b() {
                                    public void onLocationUpdated(C1798b bVar, Location location) {
                                        if (Recording.this.inProgress()) {
                                            Recording.this.updateLocation(location);
                                        } else {
                                            bVar.disconnect();
                                        }
                                    }
                                };
                            }
                            Recording.this.updateLocation(C1795i.m8034a(access$100, (C1798b) r0));
                        }
                    } else {
                        Recording.this.recorder_ = null;
                    }
                    OnStartResult onStartResult = onStartResult;
                    if (onStartResult != null) {
                        onStartResult.onStartResult(z);
                    }
                }
            });
        }
    }
    public final void stop() {
        if (this.recorder_ != null) {
            C1825p pVar = this.shakeDetector_;
            if (pVar != null) {
                pVar.mo6955a();
            }
            this.stopTime_ = System.currentTimeMillis();
            this.wasStoppedSuccessfully_ = true;
            C1877b bVar = this.recorder_;
            final long j = this.startTime_;
            final long j2 = this.stopTime_ - j;
            final String str = this.startRelativeFilePath_;
            final Map properties = getProperties();
            final ArrayList arrayList = new ArrayList(this.listeners_);
            final String b = bVar.mo7022b();
            Iterator it = new ArrayList(arrayList).iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onBeforeStop(this);
            }
            Runnable r1 = new Runnable() {
                public void run() {
                    Recording.this.onNeedRestoreParams();
                    C1414a.m6765a(Recording.this.getType(), C1801j.m8067b(), (int) (((j2 + 60000) - 1) / 60000));

                    boolean z = !Recording.this.shouldDiscard(j2);
                    if (!z) {
                        C1827q.f5467b.execute(new Runnable() {
                            public void run() {
                                C1790h.m8020a(Recording.this.ctx_, str);
                            }
                        });
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(Recording.this.getRelativePathBase(j, b));
                        sb.append(b);
                        String sb2 = sb.toString();
                        if (!str.equals(sb2)) {
                            C1790h.m8021a(Recording.this.ctx_, str, C1790h.m8025d(sb2));
                            str = sb2;
                        }
                    }
                    Iterator it = new ArrayList(arrayList).iterator();
                    while (it.hasNext()) {
                        ((Listener) it.next()).onStopped(Recording.this, str, properties, z);
                    }
                    Recording.this.onStopped(str, properties, z);
                }
            };
            bVar.mo7021a((Runnable) r1);
            this.recorder_ = null;
        }
    }

    public boolean wasStarted() {
        return this.wasStarted_;
    }
}
