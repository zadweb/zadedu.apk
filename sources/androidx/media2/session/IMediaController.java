package androidx.media2.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.media2.common.ParcelImplListSlice;
import androidx.versionedparcelable.ParcelImpl;
import java.util.List;

public interface IMediaController extends IInterface {
    void onAllowedCommandsChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onBufferingStateChanged(int i, ParcelImpl parcelImpl, int i2, long j, long j2, long j3) throws RemoteException;

    void onChildrenChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException;

    void onConnected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onCurrentMediaItemChanged(int i, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException;

    void onCustomCommand(int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException;

    void onDisconnected(int i) throws RemoteException;

    void onLibraryResult(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onPlaybackCompleted(int i) throws RemoteException;

    void onPlaybackInfoChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException;

    void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException;

    void onPlaylistChanged(int i, ParcelImplListSlice parcelImplListSlice, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException;

    void onPlaylistMetadataChanged(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onSearchResultChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException;

    void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException;

    void onSessionResult(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onSetCustomLayout(int i, List<ParcelImpl> list) throws RemoteException;

    void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

    void onSubtitleData(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3) throws RemoteException;

    void onTrackDeselected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onTrackInfoChanged(int i, List<ParcelImpl> list, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3, ParcelImpl parcelImpl4) throws RemoteException;

    void onTrackSelected(int i, ParcelImpl parcelImpl) throws RemoteException;

    void onVideoSizeChanged(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaController {
        public IBinder asBinder() {
            return this;
        }

        public static IMediaController asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.media2.session.IMediaController");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaController)) {
                return new Proxy(iBinder);
            }
            return (IMediaController) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                ParcelImpl parcelImpl = null;
                ParcelImpl parcelImpl2 = null;
                ParcelImpl parcelImpl3 = null;
                ParcelImpl parcelImpl4 = null;
                ParcelImpl parcelImpl5 = null;
                ParcelImpl parcelImpl6 = null;
                ParcelImpl parcelImpl7 = null;
                ParcelImpl parcelImpl8 = null;
                ParcelImpl parcelImpl9 = null;
                Bundle bundle = null;
                ParcelImpl parcelImpl10 = null;
                ParcelImpl parcelImpl11 = null;
                ParcelImpl parcelImpl12 = null;
                ParcelImpl parcelImpl13 = null;
                ParcelImpl parcelImpl14 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onCurrentMediaItemChanged(readInt, parcelImpl, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 2:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onPlayerStateChanged(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt());
                        return true;
                    case 3:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onPlaybackSpeedChanged(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readFloat());
                        return true;
                    case 4:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt2 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl14 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onBufferingStateChanged(readInt2, parcelImpl14, parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readLong());
                        return true;
                    case 5:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onPlaylistChanged(parcel.readInt(), parcel.readInt() != 0 ? ParcelImplListSlice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 6:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt3 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl13 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onPlaylistMetadataChanged(readInt3, parcelImpl13);
                        return true;
                    case 7:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt4 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl12 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onPlaybackInfoChanged(readInt4, parcelImpl12);
                        return true;
                    case 8:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onRepeatModeChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 9:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onShuffleModeChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 10:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onPlaybackCompleted(parcel.readInt());
                        return true;
                    case 11:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onSeekCompleted(parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readLong());
                        return true;
                    case 12:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt5 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl11 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onConnected(readInt5, parcelImpl11);
                        return true;
                    case 13:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onDisconnected(parcel.readInt());
                        return true;
                    case 14:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onSetCustomLayout(parcel.readInt(), parcel.createTypedArrayList(ParcelImpl.CREATOR));
                        return true;
                    case 15:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt6 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl10 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onAllowedCommandsChanged(readInt6, parcelImpl10);
                        return true;
                    case 16:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt7 = parcel.readInt();
                        ParcelImpl createFromParcel = parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onCustomCommand(readInt7, createFromParcel, bundle);
                        return true;
                    case 17:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt8 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl9 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onSessionResult(readInt8, parcelImpl9);
                        return true;
                    case 18:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt9 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl8 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onLibraryResult(readInt9, parcelImpl8);
                        return true;
                    case 19:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt10 = parcel.readInt();
                        String readString = parcel.readString();
                        int readInt11 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl7 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onChildrenChanged(readInt10, readString, readInt11, parcelImpl7);
                        return true;
                    case 20:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt12 = parcel.readInt();
                        String readString2 = parcel.readString();
                        int readInt13 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl6 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onSearchResultChanged(readInt12, readString2, readInt13, parcelImpl6);
                        return true;
                    case 21:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt14 = parcel.readInt();
                        ParcelImpl createFromParcel2 = parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            parcelImpl5 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onVideoSizeChanged(readInt14, createFromParcel2, parcelImpl5);
                        return true;
                    case 22:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        onTrackInfoChanged(parcel.readInt(), parcel.createTypedArrayList(ParcelImpl.CREATOR), parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null);
                        return true;
                    case 23:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt15 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl4 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onTrackSelected(readInt15, parcelImpl4);
                        return true;
                    case 24:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt16 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl3 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onTrackDeselected(readInt16, parcelImpl3);
                        return true;
                    case 25:
                        parcel.enforceInterface("androidx.media2.session.IMediaController");
                        int readInt17 = parcel.readInt();
                        ParcelImpl createFromParcel3 = parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null;
                        ParcelImpl createFromParcel4 = parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            parcelImpl2 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onSubtitleData(readInt17, createFromParcel3, createFromParcel4, parcelImpl2);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("androidx.media2.session.IMediaController");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IMediaController {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.media2.session.IMediaController
            public void onCurrentMediaItemChanged(int i, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeInt(i2);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeFloat(f);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onBufferingStateChanged(int i, ParcelImpl parcelImpl, int i2, long j, long j2, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeLong(j3);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlaylistChanged(int i, ParcelImplListSlice parcelImplListSlice, ParcelImpl parcelImpl, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImplListSlice != null) {
                        obtain.writeInt(1);
                        parcelImplListSlice.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlaylistMetadataChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlaybackInfoChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.mRemote.transact(8, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    this.mRemote.transact(9, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onPlaybackCompleted(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeLong(j3);
                    this.mRemote.transact(11, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onVideoSizeChanged(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(21, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onSubtitleData(int i, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl3 != null) {
                        obtain.writeInt(1);
                        parcelImpl3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(25, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onConnected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(12, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onDisconnected(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    this.mRemote.transact(13, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onSetCustomLayout(int i, List<ParcelImpl> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    this.mRemote.transact(14, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onAllowedCommandsChanged(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onCustomCommand(int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(16, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onSessionResult(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onLibraryResult(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onTrackInfoChanged(int i, List<ParcelImpl> list, ParcelImpl parcelImpl, ParcelImpl parcelImpl2, ParcelImpl parcelImpl3, ParcelImpl parcelImpl4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl2 != null) {
                        obtain.writeInt(1);
                        parcelImpl2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl3 != null) {
                        obtain.writeInt(1);
                        parcelImpl3.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (parcelImpl4 != null) {
                        obtain.writeInt(1);
                        parcelImpl4.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onTrackSelected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(23, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onTrackDeselected(int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(24, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onChildrenChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaController
            public void onSearchResultChanged(int i, String str, int i2, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaController");
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(20, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
