package org.altbeacon.beacon.distance;

import android.content.Context;
import android.os.AsyncTask;
import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.altbeacon.beacon.distance.ModelSpecificDistanceUpdater;
import org.altbeacon.beacon.logging.LogManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ModelSpecificDistanceCalculator implements DistanceCalculator {
    private Context mContext;
    private AndroidModel mDefaultModel;
    private DistanceCalculator mDistanceCalculator;
    private final ReentrantLock mLock;
    private AndroidModel mModel;
    Map<AndroidModel, DistanceCalculator> mModelMap;
    private String mRemoteUpdateUrlString;
    private AndroidModel mRequestedModel;

    public ModelSpecificDistanceCalculator(Context context, String str) {
        this(context, str, AndroidModel.forThisDevice());
    }

    public ModelSpecificDistanceCalculator(Context context, String str, AndroidModel androidModel) {
        this.mRemoteUpdateUrlString = null;
        this.mLock = new ReentrantLock();
        this.mRequestedModel = androidModel;
        this.mRemoteUpdateUrlString = str;
        this.mContext = context;
        loadModelMap();
        this.mDistanceCalculator = findCalculatorForModelWithLock(androidModel);
    }

    @Override // org.altbeacon.beacon.distance.DistanceCalculator
    public double calculateDistance(int i, double d) {
        DistanceCalculator distanceCalculator = this.mDistanceCalculator;
        if (distanceCalculator != null) {
            return distanceCalculator.calculateDistance(i, d);
        }
        LogManager.w("ModelSpecificDistanceCalculator", "distance calculator has not been set", new Object[0]);
        return -1.0d;
    }

    /* access modifiers changed from: package-private */
    public DistanceCalculator findCalculatorForModelWithLock(AndroidModel androidModel) {
        this.mLock.lock();
        try {
            return findCalculatorForModel(androidModel);
        } finally {
            this.mLock.unlock();
        }
    }

    private DistanceCalculator findCalculatorForModel(AndroidModel androidModel) {
        LogManager.d("ModelSpecificDistanceCalculator", "Finding best distance calculator for %s, %s, %s, %s", androidModel.getVersion(), androidModel.getBuildNumber(), androidModel.getModel(), androidModel.getManufacturer());
        Map<AndroidModel, DistanceCalculator> map = this.mModelMap;
        AndroidModel androidModel2 = null;
        if (map == null) {
            LogManager.d("ModelSpecificDistanceCalculator", "Cannot get distance calculator because modelMap was never initialized", new Object[0]);
            return null;
        }
        int i = 0;
        for (AndroidModel androidModel3 : map.keySet()) {
            if (androidModel3.matchScore(androidModel) > i) {
                i = androidModel3.matchScore(androidModel);
                androidModel2 = androidModel3;
            }
        }
        if (androidModel2 != null) {
            LogManager.d("ModelSpecificDistanceCalculator", "found a match with score %s", Integer.valueOf(i));
            LogManager.d("ModelSpecificDistanceCalculator", "Finding best distance calculator for %s, %s, %s, %s", androidModel2.getVersion(), androidModel2.getBuildNumber(), androidModel2.getModel(), androidModel2.getManufacturer());
            this.mModel = androidModel2;
        } else {
            this.mModel = this.mDefaultModel;
            LogManager.w("ModelSpecificDistanceCalculator", "Cannot find match for this device.  Using default", new Object[0]);
        }
        return this.mModelMap.get(this.mModel);
    }

    private void loadModelMap() {
        boolean z;
        if (this.mRemoteUpdateUrlString != null) {
            z = loadModelMapFromFile();
            if (!z) {
                requestModelMapFromWeb();
            }
        } else {
            z = false;
        }
        if (!z) {
            loadDefaultModelMap();
        }
        this.mDistanceCalculator = findCalculatorForModelWithLock(this.mRequestedModel);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:9|10|11|12|13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0043, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
        org.altbeacon.beacon.logging.LogManager.e(r1, "ModelSpecificDistanceCalculator", "Cannot update distance models from online database at %s with JSON: %s", r9.mRemoteUpdateUrlString, r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        return false;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0038 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003b */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A[SYNTHETIC, Splitter:B:36:0x0075] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007c A[SYNTHETIC, Splitter:B:40:0x007c] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0082 A[SYNTHETIC, Splitter:B:45:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0089 A[SYNTHETIC, Splitter:B:49:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0090 A[SYNTHETIC, Splitter:B:56:0x0090] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0097 A[SYNTHETIC, Splitter:B:60:0x0097] */
    private boolean loadModelMapFromFile() {
        FileInputStream fileInputStream;
        Throwable th;
        IOException e;
        File file = new File(this.mContext.getFilesDir(), "model-distance-calculations.json");
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                            sb.append("\n");
                        } else {
                            bufferedReader2.close();
                            fileInputStream.close();
                            buildModelMapWithLock(sb.toString());
                            return true;
                        }
                    } catch (FileNotFoundException unused) {
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                        }
                        if (fileInputStream != null) {
                        }
                        return false;
                    } catch (IOException e2) {
                        e = e2;
                        bufferedReader = bufferedReader2;
                        try {
                            LogManager.e(e, "ModelSpecificDistanceCalculator", "Cannot open distance model file %s", file);
                            if (bufferedReader != null) {
                            }
                            if (fileInputStream != null) {
                            }
                            return false;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedReader != null) {
                            }
                            if (fileInputStream != null) {
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                        }
                        if (fileInputStream != null) {
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException unused2) {
                if (bufferedReader != null) {
                }
                if (fileInputStream != null) {
                }
                return false;
            } catch (IOException e3) {
                e = e3;
                LogManager.e(e, "ModelSpecificDistanceCalculator", "Cannot open distance model file %s", file);
                if (bufferedReader != null) {
                }
                if (fileInputStream != null) {
                }
                return false;
            }
        } catch (FileNotFoundException unused3) {
            fileInputStream = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused4) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception unused5) {
                }
            }
            return false;
        } catch (IOException e4) {
            e = e4;
            fileInputStream = null;
            LogManager.e(e, "ModelSpecificDistanceCalculator", "Cannot open distance model file %s", file);
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused6) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception unused7) {
                }
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception unused8) {
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception unused9) {
                }
            }
            throw th;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean saveJson(String str) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.mContext.openFileOutput("model-distance-calculations.json", 0);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused) {
                }
            }
            LogManager.i("ModelSpecificDistanceCalculator", "Successfully saved new distance model file", new Object[0]);
            return true;
        } catch (Exception e) {
            LogManager.w(e, "ModelSpecificDistanceCalculator", "Cannot write updated distance model to local storage", new Object[0]);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused2) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception unused3) {
                }
            }
            throw th;
        }
    }

    private void requestModelMapFromWeb() {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            LogManager.w("ModelSpecificDistanceCalculator", "App has no android.permission.INTERNET permission.  Cannot check for distance model updates", new Object[0]);
        } else {
            new ModelSpecificDistanceUpdater(this.mContext, this.mRemoteUpdateUrlString, new ModelSpecificDistanceUpdater.CompletionHandler() {
                /* class org.altbeacon.beacon.distance.ModelSpecificDistanceCalculator.AnonymousClass1 */

                @Override // org.altbeacon.beacon.distance.ModelSpecificDistanceUpdater.CompletionHandler
                public void onComplete(String str, Exception exc, int i) {
                    if (exc != null) {
                        LogManager.w("ModelSpecificDistanceCalculator", "Cannot updated distance models from online database at %s", exc, ModelSpecificDistanceCalculator.this.mRemoteUpdateUrlString);
                    } else if (i != 200) {
                        LogManager.w("ModelSpecificDistanceCalculator", "Cannot updated distance models from online database at %s due to HTTP status code %s", ModelSpecificDistanceCalculator.this.mRemoteUpdateUrlString, Integer.valueOf(i));
                    } else {
                        LogManager.d("ModelSpecificDistanceCalculator", "Successfully downloaded distance models from online database", new Object[0]);
                        try {
                            ModelSpecificDistanceCalculator.this.buildModelMapWithLock(str);
                            if (ModelSpecificDistanceCalculator.this.saveJson(str)) {
                                ModelSpecificDistanceCalculator.this.loadModelMapFromFile();
                                ModelSpecificDistanceCalculator.this.mDistanceCalculator = ModelSpecificDistanceCalculator.this.findCalculatorForModelWithLock(ModelSpecificDistanceCalculator.this.mRequestedModel);
                                LogManager.i("ModelSpecificDistanceCalculator", "Successfully updated distance model with latest from online database", new Object[0]);
                            }
                        } catch (JSONException e) {
                            LogManager.w(e, "ModelSpecificDistanceCalculator", "Cannot parse json from downloaded distance model", new Object[0]);
                        }
                    }
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void buildModelMapWithLock(String str) throws JSONException {
        this.mLock.lock();
        try {
            buildModelMap(str);
        } finally {
            this.mLock.unlock();
        }
    }

    private void buildModelMap(String str) throws JSONException {
        HashMap hashMap = new HashMap();
        JSONArray jSONArray = new JSONObject(str).getJSONArray("models");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            boolean z = jSONObject.has(RewardedVideo.VIDEO_MODE_DEFAULT) ? jSONObject.getBoolean(RewardedVideo.VIDEO_MODE_DEFAULT) : false;
            Double valueOf = Double.valueOf(jSONObject.getDouble("coefficient1"));
            Double valueOf2 = Double.valueOf(jSONObject.getDouble("coefficient2"));
            Double valueOf3 = Double.valueOf(jSONObject.getDouble("coefficient3"));
            String string = jSONObject.getString("version");
            String string2 = jSONObject.getString("build_number");
            String string3 = jSONObject.getString("model");
            String string4 = jSONObject.getString("manufacturer");
            CurveFittedDistanceCalculator curveFittedDistanceCalculator = new CurveFittedDistanceCalculator(valueOf.doubleValue(), valueOf2.doubleValue(), valueOf3.doubleValue());
            AndroidModel androidModel = new AndroidModel(string, string2, string3, string4);
            hashMap.put(androidModel, curveFittedDistanceCalculator);
            if (z) {
                this.mDefaultModel = androidModel;
            }
        }
        this.mModelMap = hashMap;
    }

    private void loadDefaultModelMap() {
        try {
            buildModelMap(stringFromFilePath("model-distance-calculations.json"));
        } catch (Exception e) {
            this.mModelMap = new HashMap();
            LogManager.e(e, "ModelSpecificDistanceCalculator", "Cannot build model distance calculations", new Object[0]);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008f  */
    private String stringFromFilePath(String str) throws IOException {
        Throwable th;
        InputStream inputStream;
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            inputStream = ModelSpecificDistanceCalculator.class.getResourceAsStream("/" + str);
            if (inputStream == null) {
                try {
                    inputStream = getClass().getClassLoader().getResourceAsStream("/" + str);
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                    }
                    if (inputStream != null) {
                    }
                    throw th;
                }
            }
            if (inputStream != null) {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                try {
                    for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                        sb.append(readLine);
                        sb.append('\n');
                    }
                    bufferedReader2.close();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return sb.toString();
                } catch (Throwable th3) {
                    bufferedReader = bufferedReader2;
                    th = th3;
                    if (bufferedReader != null) {
                    }
                    if (inputStream != null) {
                    }
                    throw th;
                }
            } else {
                throw new RuntimeException("Cannot load resource at " + str);
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }
}
