package androidx.media2.exoplayer.external.metadata;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.metadata.emsg.EventMessageDecoder;
import androidx.media2.exoplayer.external.metadata.icy.IcyDecoder;
import androidx.media2.exoplayer.external.metadata.id3.Id3Decoder;
import androidx.media2.exoplayer.external.metadata.scte35.SpliceInfoDecoder;

public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new MetadataDecoderFactory() {
        /* class androidx.media2.exoplayer.external.metadata.MetadataDecoderFactory.AnonymousClass1 */

        @Override // androidx.media2.exoplayer.external.metadata.MetadataDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return "application/id3".equals(str) || "application/x-emsg".equals(str) || "application/x-scte35".equals(str) || "application/x-icy".equals(str);
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // androidx.media2.exoplayer.external.metadata.MetadataDecoderFactory
        public MetadataDecoder createDecoder(Format format) {
            char c;
            String str = format.sampleMimeType;
            switch (str.hashCode()) {
                case -1348231605:
                    if (str.equals("application/x-icy")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -1248341703:
                    if (str.equals("application/id3")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1154383568:
                    if (str.equals("application/x-emsg")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1652648887:
                    if (str.equals("application/x-scte35")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                return new Id3Decoder();
            }
            if (c == 1) {
                return new EventMessageDecoder();
            }
            if (c == 2) {
                return new SpliceInfoDecoder();
            }
            if (c == 3) {
                return new IcyDecoder();
            }
            throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
        }
    };

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
