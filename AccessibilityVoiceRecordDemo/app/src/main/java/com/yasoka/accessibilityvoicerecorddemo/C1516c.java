package com.yasoka.accessibilityvoicerecorddemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.catalinagroup.callrecorder.database.c */
public class C1516c {

    /* renamed from: a */
    private SharedPreferences f4793a;

    public C1516c(Context context, String str) {
        this.f4793a = context.getSharedPreferences(str, 0);
    }

    /* renamed from: a */
    public boolean mo6386a(String str) {
        return this.f4793a.contains(str);
    }

    /* renamed from: b */
    public void mo6390b(String str, String str2) {
        Editor edit = this.f4793a.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    /* renamed from: c */
    public void mo6393c(String str) {
        Editor edit = this.f4793a.edit();
        edit.remove(str);
        edit.apply();
    }

    @SuppressLint({"ApplySharedPref"})
    /* renamed from: d */
    public void mo6394d(String str) {
        Editor edit = this.f4793a.edit();
        edit.remove(str);
        edit.commit();
    }

    /* renamed from: a */
    public void mo6385a(String str, Set<String> set) {
        Editor edit = this.f4793a.edit();
        edit.remove(str);
        edit.apply();
        edit.putStringSet(str, set);
        edit.apply();
    }

    public C1516c(Context context) {
        this.f4793a = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /* renamed from: c */
    private boolean m7150c(String str, Map<String, ?> map) {
        try {
            Object readObject = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str, 0))).readObject();
            if (readObject instanceof Map) {
                map.putAll((Map) readObject);
            }
            return true;
        } catch (IOException | ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: d */
    private boolean m7151d(String str, Map<String, String> map) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                String optString = jSONObject.optString(str2, null);
                if (optString != null) {
                    map.put(str2, optString);
                }
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* renamed from: b */
    public void mo6389b(String str, long j) {
        Editor edit = this.f4793a.edit();
        edit.putLong(str, j);
        edit.apply();
    }

    /* renamed from: a */
    public String mo6382a(String str, String str2) {
        return this.f4793a.getString(str, str2);
    }

    /* renamed from: b */
    public void mo6392b(String str, boolean z) {
        Editor edit = this.f4793a.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    /* renamed from: a */
    public boolean mo6387a(String str, boolean z) {
        return this.f4793a.getBoolean(str, z);
    }

    /* renamed from: b */
    public void mo6391b(String str, Map<String, String> map) {
        Editor edit = this.f4793a.edit();
        edit.putString(str, m7149a(map));
        edit.apply();
    }

    /* renamed from: a */
    public long mo6381a(String str, long j) {
        SharedPreferences sharedPreferences = this.f4793a;
        try {
            return sharedPreferences.getLong(str, j);
        } catch (ClassCastException unused) {
            return (long) sharedPreferences.getInt(str, (int) j);
        }
    }

    /* renamed from: a */
    public float mo6380a(String str, float f) {
        return this.f4793a.getFloat(str, f);
    }

    /* renamed from: b */
    public Set<String> mo6388b(String str) {
        return this.f4793a.getStringSet(str, new HashSet());
    }

    /* renamed from: a */
    public void mo6384a(String str, Map<String, String> map) {
        String string = this.f4793a.getString(str, null);
        if (string != null && !m7151d(string, map) && m7150c(string, map)) {
            mo6391b(str, map);
        }
    }

    /* renamed from: a */
    public Set<String> mo6383a() {
        return this.f4793a.getAll().keySet();
    }

    /* renamed from: a */
    private String m7149a(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            try {
                jSONObject.put(str, map.get(str));
            } catch (JSONException unused) {
            }
        }
        return jSONObject.toString();
    }
}
