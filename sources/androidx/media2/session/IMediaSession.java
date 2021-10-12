package androidx.media2.session;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import androidx.media2.session.IMediaController;
import androidx.versionedparcelable.ParcelImpl;
import java.util.ArrayList;
import java.util.List;

public interface IMediaSession extends IInterface {
    void addPlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException;

    void adjustVolume(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void connect(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void deselectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void fastForward(IMediaController iMediaController, int i) throws RemoteException;

    void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException;

    void getItem(IMediaController iMediaController, int i, String str) throws RemoteException;

    void getLibraryRoot(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException;

    void onControllerResult(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void onCustomCommand(IMediaController iMediaController, int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException;

    void pause(IMediaController iMediaController, int i) throws RemoteException;

    void play(IMediaController iMediaController, int i) throws RemoteException;

    void playFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void playFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void playFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException;

    void prepare(IMediaController iMediaController, int i) throws RemoteException;

    void prepareFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void prepareFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException;

    void prepareFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException;

    void release(IMediaController iMediaController, int i) throws RemoteException;

    void removePlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void replacePlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException;

    void rewind(IMediaController iMediaController, int i) throws RemoteException;

    void search(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException;

    void selectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    void setMediaItem(IMediaController iMediaController, int i, String str) throws RemoteException;

    void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException;

    void setPlaylist(IMediaController iMediaController, int i, List<String> list, ParcelImpl parcelImpl) throws RemoteException;

    void setRating(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setShuffleMode(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void setSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException;

    void setVolumeTo(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException;

    void skipBackward(IMediaController iMediaController, int i) throws RemoteException;

    void skipForward(IMediaController iMediaController, int i) throws RemoteException;

    void skipToNextItem(IMediaController iMediaController, int i) throws RemoteException;

    void skipToPlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException;

    void skipToPreviousItem(IMediaController iMediaController, int i) throws RemoteException;

    void subscribe(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException;

    void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException;

    void updatePlaylistMetadata(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException;

    public static abstract class Stub extends Binder implements IMediaSession {
        public IBinder asBinder() {
            return this;
        }

        public static IMediaSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("androidx.media2.session.IMediaSession");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMediaSession)) {
                return new Proxy(iBinder);
            }
            return (IMediaSession) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                ParcelImpl parcelImpl = null;
                ParcelImpl parcelImpl2 = null;
                ParcelImpl parcelImpl3 = null;
                Surface surface = null;
                ParcelImpl parcelImpl4 = null;
                ParcelImpl parcelImpl5 = null;
                ParcelImpl parcelImpl6 = null;
                ParcelImpl parcelImpl7 = null;
                ParcelImpl parcelImpl8 = null;
                ParcelImpl parcelImpl9 = null;
                ParcelImpl parcelImpl10 = null;
                ParcelImpl parcelImpl11 = null;
                ParcelImpl parcelImpl12 = null;
                Bundle bundle = null;
                Bundle bundle2 = null;
                Bundle bundle3 = null;
                Bundle bundle4 = null;
                Bundle bundle5 = null;
                Bundle bundle6 = null;
                Bundle bundle7 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        connect(asInterface, readInt, parcelImpl);
                        return true;
                    case 2:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        release(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 3:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        setVolumeTo(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 4:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        adjustVolume(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        return true;
                    case 5:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        play(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 6:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        pause(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 7:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        prepare(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 8:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        fastForward(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 9:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        rewind(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 10:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        skipForward(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 11:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        skipBackward(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 12:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        seekTo(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readLong());
                        return true;
                    case 13:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface2 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt2 = parcel.readInt();
                        ParcelImpl createFromParcel = parcel.readInt() != 0 ? ParcelImpl.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            bundle7 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        onCustomCommand(asInterface2, readInt2, createFromParcel, bundle7);
                        return true;
                    case 14:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface3 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt3 = parcel.readInt();
                        Uri uri = parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            bundle6 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        prepareFromUri(asInterface3, readInt3, uri, bundle6);
                        return true;
                    case 15:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface4 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt4 = parcel.readInt();
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle5 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        prepareFromSearch(asInterface4, readInt4, readString, bundle5);
                        return true;
                    case 16:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface5 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt5 = parcel.readInt();
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle4 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        prepareFromMediaId(asInterface5, readInt5, readString2, bundle4);
                        return true;
                    case 17:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface6 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt6 = parcel.readInt();
                        Uri uri2 = parcel.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(parcel) : null;
                        if (parcel.readInt() != 0) {
                            bundle3 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        playFromUri(asInterface6, readInt6, uri2, bundle3);
                        return true;
                    case 18:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface7 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt7 = parcel.readInt();
                        String readString3 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        playFromSearch(asInterface7, readInt7, readString3, bundle2);
                        return true;
                    case 19:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface8 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt8 = parcel.readInt();
                        String readString4 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        playFromMediaId(asInterface8, readInt8, readString4, bundle);
                        return true;
                    case 20:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface9 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt9 = parcel.readInt();
                        String readString5 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            parcelImpl12 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        setRating(asInterface9, readInt9, readString5, parcelImpl12);
                        return true;
                    case 21:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        setPlaybackSpeed(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readFloat());
                        return true;
                    case 22:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface10 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt10 = parcel.readInt();
                        ArrayList<String> createStringArrayList = parcel.createStringArrayList();
                        if (parcel.readInt() != 0) {
                            parcelImpl11 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        setPlaylist(asInterface10, readInt10, createStringArrayList, parcelImpl11);
                        return true;
                    case 23:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        setMediaItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                        return true;
                    case 24:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface11 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt11 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl10 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        updatePlaylistMetadata(asInterface11, readInt11, parcelImpl10);
                        return true;
                    case 25:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        addPlaylistItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString());
                        return true;
                    case 26:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        removePlaylistItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        return true;
                    case 27:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        replacePlaylistItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt(), parcel.readString());
                        return true;
                    case 28:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        skipToPlaylistItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        return true;
                    case 29:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        skipToPreviousItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 30:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        skipToNextItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        return true;
                    case 31:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        setRepeatMode(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        return true;
                    case 32:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        setShuffleMode(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        return true;
                    case 33:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface12 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt12 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl9 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        onControllerResult(asInterface12, readInt12, parcelImpl9);
                        return true;
                    case 34:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface13 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt13 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl8 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        getLibraryRoot(asInterface13, readInt13, parcelImpl8);
                        return true;
                    case 35:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        getItem(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                        return true;
                    case 36:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface14 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt14 = parcel.readInt();
                        String readString6 = parcel.readString();
                        int readInt15 = parcel.readInt();
                        int readInt16 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl7 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        getChildren(asInterface14, readInt14, readString6, readInt15, readInt16, parcelImpl7);
                        return true;
                    case 37:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface15 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt17 = parcel.readInt();
                        String readString7 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            parcelImpl6 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        search(asInterface15, readInt17, readString7, parcelImpl6);
                        return true;
                    case 38:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface16 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt18 = parcel.readInt();
                        String readString8 = parcel.readString();
                        int readInt19 = parcel.readInt();
                        int readInt20 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl5 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        getSearchResult(asInterface16, readInt18, readString8, readInt19, readInt20, parcelImpl5);
                        return true;
                    case 39:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface17 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt21 = parcel.readInt();
                        String readString9 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            parcelImpl4 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        subscribe(asInterface17, readInt21, readString9, parcelImpl4);
                        return true;
                    case 40:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        unsubscribe(IMediaController.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readString());
                        return true;
                    case 41:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface18 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt22 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
                        }
                        setSurface(asInterface18, readInt22, surface);
                        return true;
                    case 42:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface19 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt23 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl3 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        selectTrack(asInterface19, readInt23, parcelImpl3);
                        return true;
                    case 43:
                        parcel.enforceInterface("androidx.media2.session.IMediaSession");
                        IMediaController asInterface20 = IMediaController.Stub.asInterface(parcel.readStrongBinder());
                        int readInt24 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            parcelImpl2 = ParcelImpl.CREATOR.createFromParcel(parcel);
                        }
                        deselectTrack(asInterface20, readInt24, parcelImpl2);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("androidx.media2.session.IMediaSession");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IMediaSession {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.media2.session.IMediaSession
            public void connect(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(1, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void release(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setVolumeTo(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(3, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void adjustVolume(IMediaController iMediaController, int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void play(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(5, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void pause(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(6, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void prepare(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void fastForward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(8, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void rewind(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(9, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void skipForward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(10, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void skipBackward(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void seekTo(IMediaController iMediaController, int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    this.mRemote.transact(12, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void onCustomCommand(IMediaController iMediaController, int i, ParcelImpl parcelImpl, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
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
                    this.mRemote.transact(13, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void prepareFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(14, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void prepareFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(15, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void prepareFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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

            @Override // androidx.media2.session.IMediaSession
            public void playFromUri(IMediaController iMediaController, int i, Uri uri, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(17, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void playFromSearch(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(18, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void playFromMediaId(IMediaController iMediaController, int i, String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(19, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setRating(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
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

            @Override // androidx.media2.session.IMediaSession
            public void setPlaybackSpeed(IMediaController iMediaController, int i, float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeFloat(f);
                    this.mRemote.transact(21, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setPlaylist(IMediaController iMediaController, int i, List<String> list, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeStringList(list);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(22, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setMediaItem(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(23, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void updatePlaylistMetadata(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
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

            @Override // androidx.media2.session.IMediaSession
            public void addPlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(25, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void removePlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(26, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void replacePlaylistItem(IMediaController iMediaController, int i, int i2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeString(str);
                    this.mRemote.transact(27, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void skipToPlaylistItem(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(28, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void skipToPreviousItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(29, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void skipToNextItem(IMediaController iMediaController, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    this.mRemote.transact(30, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setRepeatMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(31, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setShuffleMode(IMediaController iMediaController, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(32, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void setSurface(IMediaController iMediaController, int i, Surface surface) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (surface != null) {
                        obtain.writeInt(1);
                        surface.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(41, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void selectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(42, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void deselectTrack(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(43, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void onControllerResult(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(33, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void getLibraryRoot(IMediaController iMediaController, int i, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void getItem(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(35, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void getChildren(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(36, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void search(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(37, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void getSearchResult(IMediaController iMediaController, int i, String str, int i2, int i3, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(38, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void subscribe(IMediaController iMediaController, int i, String str, ParcelImpl parcelImpl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    if (parcelImpl != null) {
                        obtain.writeInt(1);
                        parcelImpl.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // androidx.media2.session.IMediaSession
            public void unsubscribe(IMediaController iMediaController, int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("androidx.media2.session.IMediaSession");
                    obtain.writeStrongBinder(iMediaController != null ? iMediaController.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    this.mRemote.transact(40, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }
    }
}
