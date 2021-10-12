package com.onesignal;

import android.net.TrafficStats;
import android.os.Build;
import com.onesignal.OneSignal;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class OneSignalRestClient {
    private static int getThreadTimeout(int i) {
        return i + 5000;
    }

    /* access modifiers changed from: package-private */
    public static abstract class ResponseHandler {
        /* access modifiers changed from: package-private */
        public void onFailure(int i, String str, Throwable th) {
        }

        /* access modifiers changed from: package-private */
        public void onSuccess(String str) {
        }

        ResponseHandler() {
        }
    }

    public static void put(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            /* class com.onesignal.OneSignalRestClient.AnonymousClass1 */

            public void run() {
                OneSignalRestClient.makeRequest(str, "PUT", jSONObject, responseHandler, 120000, null);
            }
        }, "OS_REST_ASYNC_PUT").start();
    }

    public static void post(final String str, final JSONObject jSONObject, final ResponseHandler responseHandler) {
        new Thread(new Runnable() {
            /* class com.onesignal.OneSignalRestClient.AnonymousClass2 */

            public void run() {
                OneSignalRestClient.makeRequest(str, "POST", jSONObject, responseHandler, 120000, null);
            }
        }, "OS_REST_ASYNC_POST").start();
    }

    public static void get(final String str, final ResponseHandler responseHandler, final String str2) {
        new Thread(new Runnable() {
            /* class com.onesignal.OneSignalRestClient.AnonymousClass3 */

            public void run() {
                OneSignalRestClient.makeRequest(str, null, null, responseHandler, 60000, str2);
            }
        }, "OS_REST_ASYNC_GET").start();
    }

    public static void getSync(String str, ResponseHandler responseHandler, String str2) {
        makeRequest(str, null, null, responseHandler, 60000, str2);
    }

    public static void putSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "PUT", jSONObject, responseHandler, 120000, null);
    }

    public static void postSync(String str, JSONObject jSONObject, ResponseHandler responseHandler) {
        makeRequest(str, "POST", jSONObject, responseHandler, 120000, null);
    }

    /* access modifiers changed from: private */
    public static void makeRequest(final String str, final String str2, final JSONObject jSONObject, final ResponseHandler responseHandler, final int i, final String str3) {
        if (OSUtils.isRunningOnMainThread()) {
            throw new OneSignalNetworkCallException("Method: " + str2 + " was called from the Main Thread!");
        } else if (str2 == null || !OneSignal.shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {
            final Thread[] threadArr = new Thread[1];
            Thread thread = new Thread(new Runnable() {
                /* class com.onesignal.OneSignalRestClient.AnonymousClass4 */

                public void run() {
                    threadArr[0] = OneSignalRestClient.startHTTPConnection(str, str2, jSONObject, responseHandler, i, str3);
                }
            }, "OS_HTTPConnection");
            thread.start();
            try {
                thread.join((long) getThreadTimeout(i));
                if (thread.getState() != Thread.State.TERMINATED) {
                    thread.interrupt();
                }
                if (threadArr[0] != null) {
                    threadArr[0].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0250, code lost:
        if (r8 != null) goto L_0x0252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0252, code lost:
        r8.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02a8, code lost:
        if (r8 == null) goto L_0x02ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x02ab, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0266 A[Catch:{ all -> 0x02ac }] */
    public static Thread startHTTPConnection(String str, String str2, JSONObject jSONObject, ResponseHandler responseHandler, int i, String str3) {
        Thread thread;
        HttpURLConnection httpURLConnection;
        Throwable th;
        String headerField;
        if (Build.VERSION.SDK_INT >= 26) {
            TrafficStats.setThreadStatsTag(10000);
        }
        int i2 = -1;
        try {
            OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
            OneSignal.Log(log_level, "OneSignalRestClient: Making request to: https://api.onesignal.com/" + str);
            httpURLConnection = newHttpURLConnection(str);
            try {
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(i);
                httpURLConnection.setReadTimeout(i);
                httpURLConnection.setRequestProperty("SDK-Version", "onesignal/android/031502");
                httpURLConnection.setRequestProperty("Accept", "application/vnd.onesignal.v1+json");
                if (jSONObject != null) {
                    httpURLConnection.setDoInput(true);
                }
                if (str2 != null) {
                    httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection.setRequestMethod(str2);
                    httpURLConnection.setDoOutput(true);
                }
                if (jSONObject != null) {
                    String jSONObject2 = jSONObject.toString();
                    OneSignal.LOG_LEVEL log_level2 = OneSignal.LOG_LEVEL.DEBUG;
                    OneSignal.Log(log_level2, "OneSignalRestClient: " + str2 + " SEND JSON: " + jSONObject2);
                    byte[] bytes = jSONObject2.getBytes("UTF-8");
                    httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                    httpURLConnection.getOutputStream().write(bytes);
                }
                if (str3 != null) {
                    String str4 = OneSignalPrefs.PREFS_ONESIGNAL;
                    String string = OneSignalPrefs.getString(str4, "PREFS_OS_ETAG_PREFIX_" + str3, null);
                    if (string != null) {
                        httpURLConnection.setRequestProperty("if-none-match", string);
                        OneSignal.LOG_LEVEL log_level3 = OneSignal.LOG_LEVEL.DEBUG;
                        OneSignal.Log(log_level3, "OneSignalRestClient: Adding header if-none-match: " + string);
                    }
                }
                i2 = httpURLConnection.getResponseCode();
                try {
                    OneSignal.LOG_LEVEL log_level4 = OneSignal.LOG_LEVEL.VERBOSE;
                    OneSignal.Log(log_level4, "OneSignalRestClient: After con.getResponseCode to: https://api.onesignal.com/" + str);
                    String str5 = "";
                    String str6 = "GET";
                    if (i2 == 200 || i2 == 202) {
                        OneSignal.LOG_LEVEL log_level5 = OneSignal.LOG_LEVEL.DEBUG;
                        StringBuilder sb = new StringBuilder();
                        try {
                            sb.append("OneSignalRestClient: Successfully finished request to: https://api.onesignal.com/");
                            sb.append(str);
                            OneSignal.Log(log_level5, sb.toString());
                            Scanner scanner = new Scanner(httpURLConnection.getInputStream(), "UTF-8");
                            if (scanner.useDelimiter("\\A").hasNext()) {
                                str5 = scanner.next();
                            }
                            scanner.close();
                            OneSignal.LOG_LEVEL log_level6 = OneSignal.LOG_LEVEL.DEBUG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("OneSignalRestClient: ");
                            if (str2 != null) {
                                str6 = str2;
                            }
                            sb2.append(str6);
                            sb2.append(" RECEIVED JSON: ");
                            sb2.append(str5);
                            OneSignal.Log(log_level6, sb2.toString());
                            if (!(str3 == null || (headerField = httpURLConnection.getHeaderField("etag")) == null)) {
                                OneSignal.LOG_LEVEL log_level7 = OneSignal.LOG_LEVEL.DEBUG;
                                OneSignal.Log(log_level7, "OneSignalRestClient: Response has etag of " + headerField + " so caching the response.");
                                String str7 = OneSignalPrefs.PREFS_ONESIGNAL;
                                OneSignalPrefs.saveString(str7, "PREFS_OS_ETAG_PREFIX_" + str3, headerField);
                                String str8 = OneSignalPrefs.PREFS_ONESIGNAL;
                                OneSignalPrefs.saveString(str8, "PREFS_OS_HTTP_CACHE_PREFIX_" + str3, str5);
                            }
                            thread = callResponseHandlerOnSuccess(responseHandler, str5);
                        } catch (Throwable th2) {
                            th = th2;
                            i2 = i2;
                            try {
                                if (!(th instanceof ConnectException)) {
                                }
                                OneSignal.LOG_LEVEL log_level8 = OneSignal.LOG_LEVEL.INFO;
                                OneSignal.Log(log_level8, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + th.getClass().getName());
                                thread = callResponseHandlerOnFailure(responseHandler, i2, null, th);
                            } catch (Throwable th3) {
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th3;
                            }
                        }
                    } else if (i2 != 304) {
                        OneSignal.LOG_LEVEL log_level9 = OneSignal.LOG_LEVEL.DEBUG;
                        OneSignal.Log(log_level9, "OneSignalRestClient: Failed request to: https://api.onesignal.com/" + str);
                        InputStream errorStream = httpURLConnection.getErrorStream();
                        if (errorStream == null) {
                            errorStream = httpURLConnection.getInputStream();
                        }
                        if (errorStream != null) {
                            Scanner scanner2 = new Scanner(errorStream, "UTF-8");
                            if (scanner2.useDelimiter("\\A").hasNext()) {
                                str5 = scanner2.next();
                            }
                            scanner2.close();
                            OneSignal.LOG_LEVEL log_level10 = OneSignal.LOG_LEVEL.WARN;
                            OneSignal.Log(log_level10, "OneSignalRestClient: " + str2 + " RECEIVED JSON: " + str5);
                        } else {
                            OneSignal.LOG_LEVEL log_level11 = OneSignal.LOG_LEVEL.WARN;
                            OneSignal.Log(log_level11, "OneSignalRestClient: " + str2 + " HTTP Code: " + i2 + " No response body!");
                            str5 = null;
                        }
                        thread = callResponseHandlerOnFailure(responseHandler, i2, str5, null);
                    } else {
                        String str9 = OneSignalPrefs.PREFS_ONESIGNAL;
                        String string2 = OneSignalPrefs.getString(str9, "PREFS_OS_HTTP_CACHE_PREFIX_" + str3, null);
                        OneSignal.LOG_LEVEL log_level12 = OneSignal.LOG_LEVEL.DEBUG;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("OneSignalRestClient: ");
                        if (str2 != null) {
                            str6 = str2;
                        }
                        sb3.append(str6);
                        sb3.append(" - Using Cached response due to 304: ");
                        sb3.append(string2);
                        OneSignal.Log(log_level12, sb3.toString());
                        thread = callResponseHandlerOnSuccess(responseHandler, string2);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    if (!(th instanceof ConnectException)) {
                    }
                    OneSignal.LOG_LEVEL log_level82 = OneSignal.LOG_LEVEL.INFO;
                    OneSignal.Log(log_level82, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + th.getClass().getName());
                    thread = callResponseHandlerOnFailure(responseHandler, i2, null, th);
                }
            } catch (Throwable th5) {
                th = th5;
                if (!(th instanceof ConnectException)) {
                }
                OneSignal.LOG_LEVEL log_level822 = OneSignal.LOG_LEVEL.INFO;
                OneSignal.Log(log_level822, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + th.getClass().getName());
                thread = callResponseHandlerOnFailure(responseHandler, i2, null, th);
            }
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            if (!(th instanceof ConnectException)) {
                if (!(th instanceof UnknownHostException)) {
                    OneSignal.LOG_LEVEL log_level13 = OneSignal.LOG_LEVEL.WARN;
                    OneSignal.Log(log_level13, "OneSignalRestClient: " + str2 + " Error thrown from network stack. ", th);
                    thread = callResponseHandlerOnFailure(responseHandler, i2, null, th);
                }
            }
            OneSignal.LOG_LEVEL log_level8222 = OneSignal.LOG_LEVEL.INFO;
            OneSignal.Log(log_level8222, "OneSignalRestClient: Could not send last request, device is offline. Throwable: " + th.getClass().getName());
            thread = callResponseHandlerOnFailure(responseHandler, i2, null, th);
        }
    }

    private static Thread callResponseHandlerOnSuccess(final ResponseHandler responseHandler, final String str) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() {
            /* class com.onesignal.OneSignalRestClient.AnonymousClass5 */

            public void run() {
                responseHandler.onSuccess(str);
            }
        }, "OS_REST_SUCCESS_CALLBACK");
        thread.start();
        return thread;
    }

    private static Thread callResponseHandlerOnFailure(final ResponseHandler responseHandler, final int i, final String str, final Throwable th) {
        if (responseHandler == null) {
            return null;
        }
        Thread thread = new Thread(new Runnable() {
            /* class com.onesignal.OneSignalRestClient.AnonymousClass6 */

            public void run() {
                responseHandler.onFailure(i, str, th);
            }
        }, "OS_REST_FAILURE_CALLBACK");
        thread.start();
        return thread;
    }

    private static HttpURLConnection newHttpURLConnection(String str) throws IOException {
        return (HttpURLConnection) new URL("https://api.onesignal.com/" + str).openConnection();
    }

    /* access modifiers changed from: private */
    public static class OneSignalNetworkCallException extends RuntimeException {
        public OneSignalNetworkCallException(String str) {
            super(str);
        }
    }
}
