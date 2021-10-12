package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
public class MetadataBackendRegistry implements BackendRegistry {
    private final BackendFactoryProvider backendFactoryProvider;
    private final Map<String, TransportBackend> backends;
    private final CreationContextFactory creationContextFactory;

    MetadataBackendRegistry(Context context, CreationContextFactory creationContextFactory2) {
        this(new BackendFactoryProvider(context), creationContextFactory2);
    }

    MetadataBackendRegistry(BackendFactoryProvider backendFactoryProvider2, CreationContextFactory creationContextFactory2) {
        this.backends = new HashMap();
        this.backendFactoryProvider = backendFactoryProvider2;
        this.creationContextFactory = creationContextFactory2;
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRegistry
    public synchronized TransportBackend get(String str) {
        if (this.backends.containsKey(str)) {
            return this.backends.get(str);
        }
        BackendFactory backendFactory = this.backendFactoryProvider.get(str);
        if (backendFactory == null) {
            return null;
        }
        TransportBackend create = backendFactory.create(this.creationContextFactory.create(str));
        this.backends.put(str, create);
        return create;
    }

    /* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
    static class BackendFactoryProvider {
        private final Context applicationContext;
        private Map<String, String> backendProviders = null;

        BackendFactoryProvider(Context context) {
            this.applicationContext = context;
        }

        /* access modifiers changed from: package-private */
        public BackendFactory get(String str) {
            String str2 = getBackendProviders().get(str);
            if (str2 == null) {
                return null;
            }
            try {
                return (BackendFactory) Class.forName(str2).asSubclass(BackendFactory.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (ClassNotFoundException e) {
                Log.w("BackendRegistry", String.format("Class %s is not found.", str2), e);
                return null;
            } catch (IllegalAccessException e2) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", str2), e2);
                return null;
            } catch (InstantiationException e3) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", str2), e3);
                return null;
            } catch (NoSuchMethodException e4) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", str2), e4);
                return null;
            } catch (InvocationTargetException e5) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", str2), e5);
                return null;
            }
        }

        private Map<String, String> getBackendProviders() {
            if (this.backendProviders == null) {
                this.backendProviders = discover(this.applicationContext);
            }
            return this.backendProviders;
        }

        private Map<String, String> discover(Context context) {
            Bundle metadata = getMetadata(context);
            if (metadata == null) {
                Log.w("BackendRegistry", "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.emptyMap();
            }
            HashMap hashMap = new HashMap();
            for (String str : metadata.keySet()) {
                Object obj = metadata.get(str);
                if ((obj instanceof String) && str.startsWith("backend:")) {
                    for (String str2 : ((String) obj).split(",", -1)) {
                        String trim = str2.trim();
                        if (!trim.isEmpty()) {
                            hashMap.put(trim, str.substring(8));
                        }
                    }
                }
            }
            return hashMap;
        }

        private static Bundle getMetadata(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("BackendRegistry", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, TransportBackendDiscovery.class), 128);
                if (serviceInfo != null) {
                    return serviceInfo.metaData;
                }
                Log.w("BackendRegistry", "TransportBackendDiscovery has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("BackendRegistry", "Application info not found.");
                return null;
            }
        }
    }
}
