package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.play.core.internal.u;
import com.google.android.play.core.tasks.i;
import java.util.List;

class ag<T> extends u {

    /* renamed from: a  reason: collision with root package name */
    final i<T> f67a;
    final /* synthetic */ an b;

    ag(an anVar, i<T> iVar) {
        this.b = anVar;
        this.f67a = iVar;
    }

    ag(an anVar, i iVar, byte[] bArr) {
        this(anVar, iVar);
    }

    ag(an anVar, i iVar, char[] cArr) {
        this(anVar, iVar);
    }

    ag(an anVar, i iVar, int[] iArr) {
        this(anVar, iVar);
    }

    @Override // com.google.android.play.core.internal.v
    public void b(int i, Bundle bundle) {
        an.o(this.b).b();
        an.p().d("onStartDownload(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.v
    public void c(List<Bundle> list) {
        an.o(this.b).b();
        an.p().d("onGetSessionStates", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.v
    public void d(Bundle bundle, Bundle bundle2) {
        an.u(this.b).b();
        an.p().d("onKeepAlive(%b)", Boolean.valueOf(bundle.getBoolean("keep_alive")));
    }

    @Override // com.google.android.play.core.internal.v
    public void e(Bundle bundle, Bundle bundle2) throws RemoteException {
        an.o(this.b).b();
        an.p().d("onGetChunkFileDescriptor", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.v
    public void f(Bundle bundle, Bundle bundle2) {
        an.o(this.b).b();
        an.p().d("onRequestDownloadInfo()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.v
    public void g(Bundle bundle) {
        an.o(this.b).b();
        int i = bundle.getInt("error_code");
        an.p().b("onError(%d)", Integer.valueOf(i));
        this.f67a.d(new AssetPackException(i));
    }

    @Override // com.google.android.play.core.internal.v
    public final void h(int i) {
        an.o(this.b).b();
        an.p().d("onCancelDownload(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.v
    public void i() {
        an.o(this.b).b();
        an.p().d("onCancelDownloads()", new Object[0]);
    }

    @Override // com.google.android.play.core.internal.v
    public final void j(int i) {
        an.o(this.b).b();
        an.p().d("onGetSession(%d)", Integer.valueOf(i));
    }

    @Override // com.google.android.play.core.internal.v
    public void k(Bundle bundle) {
        an.o(this.b).b();
        an.p().d("onNotifyChunkTransferred(%s, %s, %d, session=%d)", bundle.getString("module_name"), bundle.getString("slice_id"), Integer.valueOf(bundle.getInt("chunk_number")), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.v
    public void l(Bundle bundle) {
        an.o(this.b).b();
        an.p().d("onNotifyModuleCompleted(%s, sessionId=%d)", bundle.getString("module_name"), Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.v
    public void m(Bundle bundle) {
        an.o(this.b).b();
        an.p().d("onNotifySessionFailed(%d)", Integer.valueOf(bundle.getInt("session_id")));
    }

    @Override // com.google.android.play.core.internal.v
    public void n() {
        an.o(this.b).b();
        an.p().d("onRemoveModule()", new Object[0]);
    }
}
