package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.BaseGmsClient;

@zzadh
public final class zzaee extends BaseGmsClient<zzaen> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzaee(Context context, Looper looper, BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        super(context.getApplicationContext() != null ? context.getApplicationContext() : context, looper, 8, baseConnectionCallbacks, baseOnConnectionFailedListener, null);
    }

    /* Return type fixed from 'android.os.IInterface' to match base method */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ zzaen createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.request.IAdRequestService");
        return queryLocalInterface instanceof zzaen ? (zzaen) queryLocalInterface : new zzaep(iBinder);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.ads.service.START";
    }

    public final zzaen zzob() throws DeadObjectException {
        return (zzaen) super.getService();
    }
}
