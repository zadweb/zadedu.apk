package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.appnext.base.b.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0071, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0072, code lost:
        r17 = null;
        r18 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00d1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d2, code lost:
        r23 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d9, code lost:
        r23 = r5;
        r17 = null;
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00e1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e2, code lost:
        r18 = r1;
        r17 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e6, code lost:
        r2 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e9, code lost:
        r18 = r1;
        r17 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ef, code lost:
        r1 = r2.getStatusLine().getStatusCode();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00f9, code lost:
        if (r1 == 301) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00fe, code lost:
        com.android.volley.VolleyLog.e("Unexpected response code %d for %s", java.lang.Integer.valueOf(r1), r25.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0112, code lost:
        com.android.volley.VolleyLog.e("Request at %s has been redirected to %s", r25.getOriginUrl(), r25.getUrl());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0125, code lost:
        if (r17 != null) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0127, code lost:
        r0 = new com.android.volley.NetworkResponse(r1, r17, r18, false, android.os.SystemClock.elapsedRealtime() - r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0139, code lost:
        if (r1 == 401) goto L_0x0157;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0140, code lost:
        if (r1 == 301) goto L_0x014b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014a, code lost:
        throw new com.android.volley.ServerError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x014b, code lost:
        attemptRetryOnException("redirect", r25, new com.android.volley.RedirectError(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0157, code lost:
        attemptRetryOnException("auth", r25, new com.android.volley.AuthFailureError(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0168, code lost:
        throw new com.android.volley.NetworkError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x016e, code lost:
        throw new com.android.volley.NoConnectionError(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x016f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018a, code lost:
        throw new java.lang.RuntimeException("Bad URL " + r25.getUrl(), r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x018b, code lost:
        attemptRetryOnException("connection", r25, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0197, code lost:
        attemptRetryOnException("socket", r25, new com.android.volley.TimeoutError());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x016f A[ExcHandler: MalformedURLException (r0v2 'e' java.net.MalformedURLException A[CUSTOM_DECLARE]), Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:76:? A[ExcHandler: ConnectTimeoutException (unused org.apache.http.conn.ConnectTimeoutException), SYNTHETIC, Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:78:? A[ExcHandler: SocketTimeoutException (unused java.net.SocketTimeoutException), SYNTHETIC, Splitter:B:2:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0169 A[SYNTHETIC] */
    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
        HttpResponse performRequest;
        Map<String, String> map;
        byte[] entityToBytes;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            Map<String, String> emptyMap = Collections.emptyMap();
            HttpResponse httpResponse = null;
            try {
                HashMap hashMap = new HashMap();
                addCacheHeaders(hashMap, request.getCacheEntry());
                performRequest = this.mHttpStack.performRequest(request, hashMap);
                StatusLine statusLine = performRequest.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                Map<String, String> convertHeaders = convertHeaders(performRequest.getAllHeaders());
                if (statusCode == 304) {
                    Cache.Entry cacheEntry = request.getCacheEntry();
                    if (cacheEntry == null) {
                        return new NetworkResponse(304, null, convertHeaders, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                    }
                    cacheEntry.responseHeaders.putAll(convertHeaders);
                    return new NetworkResponse(304, cacheEntry.data, cacheEntry.responseHeaders, true, SystemClock.elapsedRealtime() - elapsedRealtime);
                }
                if (statusCode == 301 || statusCode == 302) {
                    request.setRedirectUrl(convertHeaders.get("Location"));
                }
                entityToBytes = performRequest.getEntity() != null ? entityToBytes(performRequest.getEntity()) : new byte[0];
                map = convertHeaders;
                logSlowRequests(SystemClock.elapsedRealtime() - elapsedRealtime, request, entityToBytes, statusLine);
                if (statusCode >= 200 && statusCode <= 299) {
                    return new NetworkResponse(statusCode, entityToBytes, map, false, SystemClock.elapsedRealtime() - elapsedRealtime);
                }
                throw new IOException();
            } catch (SocketTimeoutException unused) {
            } catch (ConnectTimeoutException unused2) {
            } catch (MalformedURLException e) {
            } catch (IOException e2) {
                IOException e3 = e2;
                httpResponse = performRequest;
                byte[] bArr = entityToBytes;
                Map<String, String> map2 = map;
                if (httpResponse == null) {
                }
            }
        }
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > ((long) SLOW_REQUEST_THRESHOLD_MS)) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry != null) {
            if (entry.etag != null) {
                map.put("If-None-Match", entry.etag);
            }
            if (entry.lastModified > 0) {
                map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
            }
        }
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws IOException, ServerError {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        try {
            InputStream content = httpEntity.getContent();
            if (content != null) {
                byte[] buf = this.mPool.getBuf(d.fb);
                while (true) {
                    int read = content.read(buf);
                    if (read == -1) {
                        break;
                    }
                    poolingByteArrayOutputStream.write(buf, 0, read);
                }
                byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
                try {
                    httpEntity.consumeContent();
                } catch (IOException unused) {
                    VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
                }
                this.mPool.returnBuf(buf);
                poolingByteArrayOutputStream.close();
                return byteArray;
            }
            throw new ServerError();
        } catch (Throwable th) {
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                VolleyLog.v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(null);
            poolingByteArrayOutputStream.close();
            throw th;
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}
