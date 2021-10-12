package org.altbeacon.beacon.distance;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.altbeacon.beacon.logging.LogManager;

public class DistanceConfigFetcher {
    protected Exception mException;
    protected String mResponse;
    private int mResponseCode = -1;
    private String mUrlString;
    private String mUserAgentString;

    public DistanceConfigFetcher(String str, String str2) {
        this.mUrlString = str;
        this.mUserAgentString = str2;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseString() {
        return this.mResponse;
    }

    public Exception getException() {
        return this.mException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b6 A[SYNTHETIC, Splitter:B:39:0x00b6] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00b2 A[ADDED_TO_REGION, EDGE_INSN: B:48:0x00b2->B:37:0x00b2 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    public void request() {
        URL url;
        int i;
        HttpURLConnection httpURLConnection;
        SecurityException e;
        FileNotFoundException e2;
        IOException e3;
        this.mResponse = null;
        String str = this.mUrlString;
        StringBuilder sb = new StringBuilder();
        HttpURLConnection httpURLConnection2 = null;
        int i2 = 0;
        while (true) {
            if (i2 != 0) {
                LogManager.d("DistanceConfigFetcher", "Following redirect from %s to %s", this.mUrlString, httpURLConnection2.getHeaderField("Location"));
                str = httpURLConnection2.getHeaderField("Location");
            }
            i2++;
            this.mResponseCode = -1;
            try {
                url = new URL(str);
            } catch (Exception e4) {
                LogManager.e("DistanceConfigFetcher", "Can't construct URL from: %s", this.mUrlString);
                this.mException = e4;
                url = null;
            }
            if (url == null) {
                LogManager.d("DistanceConfigFetcher", "URL is null.  Cannot make request", new Object[0]);
            } else {
                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    try {
                        httpURLConnection.addRequestProperty("User-Agent", this.mUserAgentString);
                        this.mResponseCode = httpURLConnection.getResponseCode();
                        LogManager.d("DistanceConfigFetcher", "response code is %s", Integer.valueOf(httpURLConnection.getResponseCode()));
                    } catch (SecurityException e5) {
                        e = e5;
                    } catch (FileNotFoundException e6) {
                        e2 = e6;
                        LogManager.w(e2, "DistanceConfigFetcher", "No data exists at \"+urlString", new Object[0]);
                        this.mException = e2;
                        httpURLConnection2 = httpURLConnection;
                        if (i2 >= 10) {
                            break;
                        }
                        break;
                        if (this.mException != null) {
                        }
                    } catch (IOException e7) {
                        e3 = e7;
                        LogManager.w(e3, "DistanceConfigFetcher", "Can't reach server", new Object[0]);
                        this.mException = e3;
                        httpURLConnection2 = httpURLConnection;
                        if (i2 >= 10) {
                        }
                        if (this.mException != null) {
                        }
                    }
                } catch (SecurityException e8) {
                    httpURLConnection = httpURLConnection2;
                    e = e8;
                    LogManager.w(e, "DistanceConfigFetcher", "Can't reach sever.  Have you added android.permission.INTERNET to your manifest?", new Object[0]);
                    this.mException = e;
                    httpURLConnection2 = httpURLConnection;
                    if (i2 >= 10) {
                    }
                    if (this.mException != null) {
                    }
                } catch (FileNotFoundException e9) {
                    httpURLConnection = httpURLConnection2;
                    e2 = e9;
                    LogManager.w(e2, "DistanceConfigFetcher", "No data exists at \"+urlString", new Object[0]);
                    this.mException = e2;
                    httpURLConnection2 = httpURLConnection;
                    if (i2 >= 10) {
                    }
                    if (this.mException != null) {
                    }
                } catch (IOException e10) {
                    httpURLConnection = httpURLConnection2;
                    e3 = e10;
                    LogManager.w(e3, "DistanceConfigFetcher", "Can't reach server", new Object[0]);
                    this.mException = e3;
                    httpURLConnection2 = httpURLConnection;
                    if (i2 >= 10) {
                    }
                    if (this.mException != null) {
                    }
                }
                httpURLConnection2 = httpURLConnection;
            }
            if (i2 >= 10 || !((i = this.mResponseCode) == 302 || i == 301 || i == 303)) {
            }
        }
        if (this.mException != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        bufferedReader.close();
                        this.mResponse = sb.toString();
                        return;
                    }
                }
            } catch (Exception e11) {
                this.mException = e11;
                LogManager.w(e11, "DistanceConfigFetcher", "error reading beacon data", new Object[0]);
            }
        }
    }
}
