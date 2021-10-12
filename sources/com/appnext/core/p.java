package com.appnext.core;

import android.content.Context;
import android.os.AsyncTask;
import java.io.IOException;
import java.net.HttpRetryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class p {
    protected static final String hD = "https://cdn.appnext.com/tools/sdk/confign";
    protected HashMap<String, String> aR = null;
    protected HashMap<String, Object> hE = null;
    private ArrayList<a> hF;
    private int state = 0;

    public interface a {
        void b(HashMap<String, Object> hashMap);

        void error(String str);
    }

    /* access modifiers changed from: protected */
    public abstract String getUrl();

    /* access modifiers changed from: protected */
    public abstract HashMap<String, String> n();

    /* access modifiers changed from: protected */
    public abstract HashMap<String, String> o();

    public final synchronized void a(a aVar) {
        a((Context) null, aVar);
    }

    public final synchronized void a(Context context, a aVar) {
        if (this.hF == null) {
            this.hF = new ArrayList<>();
        }
        if (this.state != 2) {
            if (this.state == 0) {
                this.state = 1;
                String url = getUrl();
                if (context != null) {
                    q("pck", context.getPackageName());
                }
                q("vid", f.bi());
                new StringBuilder("start loading config from ").append(url);
                new b().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url, n());
            }
            if (aVar != null) {
                this.hF.add(aVar);
            }
        } else if (aVar != null) {
            aVar.b(this.hE);
        }
    }

    private String ad(String str) {
        HashMap<String, String> hashMap = this.aR;
        if (hashMap == null) {
            return str;
        }
        for (String str2 : hashMap.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            String str3 = "?";
            if (str.contains(str3)) {
                str3 = "&";
            }
            sb.append(str3);
            sb.append(str2);
            sb.append("=");
            sb.append(this.aR.get(str2));
            str = sb.toString();
        }
        return str;
    }

    public void a(HashMap<String, String> hashMap) {
        this.aR = hashMap;
    }

    public final void q(String str, String str2) {
        if (this.aR == null) {
            this.aR = new HashMap<>();
        }
        this.aR.put(str, str2);
    }

    public final void r(String str, String str2) {
        if (this.aR == null) {
            this.aR = new HashMap<>();
        }
        if (!this.aR.containsKey(str)) {
            this.aR.put(str, str2);
        }
    }

    public final HashMap<String, Object> bn() {
        return this.hE;
    }

    public final void s(String str, String str2) {
        if (this.hE == null) {
            this.hE = new HashMap<>();
        }
        this.hE.put(str, str2);
    }

    public final boolean isLoaded() {
        return this.state == 2;
    }

    public final String get(String str) {
        String value = getValue(str);
        if (value != null) {
            return value;
        }
        if (o().containsKey(str)) {
            return o().get(str);
        }
        return null;
    }

    public final String get(String str, String str2) {
        return getValue(str) == null ? str2 : getValue(str);
    }

    /* access modifiers changed from: private */
    public class b extends AsyncTask<Object, Void, String> {
        private b() {
        }

        /* Return type fixed from 'java.lang.Object' to match base method */
        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public final /* synthetic */ String doInBackground(Object[] objArr) {
            return a(objArr);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public final /* synthetic */ void onPostExecute(String str) {
            String str2 = str;
            super.onPostExecute(str2);
            if (str2 == null) {
                p.this.state = 0;
                p.a(p.this, "unknown error");
            } else if (str2.startsWith("error:")) {
                p.this.state = 0;
                p.a(p.this, str2.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = p.ae(str2);
                    if (p.this.hE == null) {
                        p.this.hE = ae;
                    } else {
                        p.this.hE.putAll(ae);
                    }
                    p.this.state = 2;
                    p.a(p.this, p.this.hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    p.this.state = 0;
                    p.a(p.this, "parsing error");
                }
            }
        }

        protected static String a(Object... objArr) {
            try {
                return f.a((String) objArr[0], (HashMap) objArr[1]);
            } catch (HttpRetryException e) {
                return "error: " + e.getReason();
            } catch (IOException unused) {
                return "error: network problem";
            } catch (Throwable unused2) {
                return "error: Internal error";
            }
        }

        /* access modifiers changed from: protected */
        public final void ag(String str) {
            super.onPostExecute(str);
            if (str == null) {
                p.this.state = 0;
                p.a(p.this, "unknown error");
            } else if (str.startsWith("error:")) {
                p.this.state = 0;
                p.a(p.this, str.substring(7));
            } else {
                try {
                    HashMap<String, Object> ae = p.ae(str);
                    if (p.this.hE == null) {
                        p.this.hE = ae;
                    } else {
                        p.this.hE.putAll(ae);
                    }
                    p.this.state = 2;
                    p.a(p.this, p.this.hE);
                } catch (Throwable th) {
                    new StringBuilder("error ").append(th.getMessage());
                    p.this.state = 0;
                    p.a(p.this, "parsing error");
                }
            }
        }
    }

    protected static HashMap<String, Object> ae(String str) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject jSONObject = new JSONObject(str);
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            String string = jSONObject.getString(next);
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                Iterator<String> keys2 = jSONObject2.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    String string2 = jSONObject2.getString(next2);
                    hashMap.put(next + "_" + next2, string2);
                }
            } catch (Throwable unused) {
                hashMap.put(next, string);
            }
        }
        return hashMap;
    }

    private void af(String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.hF).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            this.hF.clear();
        }
    }

    private void e(HashMap<String, Object> hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(this.hF).iterator();
            while (it.hasNext()) {
                ((a) it.next()).b(hashMap);
            }
            this.hF.clear();
        }
    }

    public final String getValue(String str) {
        HashMap<String, Object> hashMap = this.hE;
        if (hashMap != null && hashMap.containsKey(str)) {
            return (String) this.hE.get(str);
        }
        return null;
    }

    static /* synthetic */ void a(p pVar, String str) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.hF).iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar != null) {
                    aVar.error(str);
                }
            }
            pVar.hF.clear();
        }
    }

    static /* synthetic */ void a(p pVar, HashMap hashMap) {
        synchronized ("https://cdn.appnext.com/tools/sdk/confign") {
            Iterator it = new ArrayList(pVar.hF).iterator();
            while (it.hasNext()) {
                ((a) it.next()).b(hashMap);
            }
            pVar.hF.clear();
        }
    }
}
