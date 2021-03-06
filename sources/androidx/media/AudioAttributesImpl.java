package androidx.media;

import androidx.versionedparcelable.VersionedParcelable;

/* access modifiers changed from: package-private */
public interface AudioAttributesImpl extends VersionedParcelable {

    public interface Builder {
        AudioAttributesImpl build();

        Builder setContentType(int i);

        Builder setFlags(int i);

        Builder setLegacyStreamType(int i);

        Builder setUsage(int i);
    }

    int getContentType();

    int getFlags();

    int getUsage();

    int getVolumeControlStream();
}
