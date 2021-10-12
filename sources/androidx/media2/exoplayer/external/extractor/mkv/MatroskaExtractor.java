package androidx.media2.exoplayer.external.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import androidx.media2.exoplayer.external.C;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.audio.Ac3Util;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.extractor.ChunkIndex;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.ExtractorInput;
import androidx.media2.exoplayer.external.extractor.ExtractorOutput;
import androidx.media2.exoplayer.external.extractor.ExtractorsFactory;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.extractor.SeekMap;
import androidx.media2.exoplayer.external.extractor.TrackOutput;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.LongArray;
import androidx.media2.exoplayer.external.util.MimeTypes;
import androidx.media2.exoplayer.external.util.NalUnitUtil;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.AvcConfig;
import androidx.media2.exoplayer.external.video.ColorInfo;
import androidx.media2.exoplayer.external.video.HevcConfig;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MatroskaExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = MatroskaExtractor$$Lambda$0.$instance;
    private static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    /* access modifiers changed from: protected */
    public int getElementType(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case 215:
            case 231:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case 17026:
            case 21358:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public final void release() {
    }

    static final /* synthetic */ Extractor[] lambda$static$0$MatroskaExtractor() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (i & 1) != 0 ? false : true;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public final boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public final void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    @Override // androidx.media2.exoplayer.external.extractor.Extractor
    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == 160) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == 174) {
            this.currentTrack = new Track();
        } else if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == 19899) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == 20533) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == 21968) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == 408125543) {
            long j3 = this.segmentContentPosition;
            if (j3 == -1 || j3 == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
                return;
            }
            throw new ParserException("Multiple Segment elements not supported");
        } else if (i == 475249515) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i != 524531317 || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    /* access modifiers changed from: protected */
    public void endMasterElement(int i) throws ParserException {
        if (i != 160) {
            if (i == 174) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    Track track = this.currentTrack;
                    track.initializeOutput(this.extractorOutput, track.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i == 19899) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == 475249515) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (i != 25152) {
                if (i != 28032) {
                    if (i == 357149030) {
                        if (this.timecodeScale == -9223372036854775807L) {
                            this.timecodeScale = 1000000;
                        }
                        long j2 = this.durationTimecode;
                        if (j2 != -9223372036854775807L) {
                            this.durationUs = scaleTimecodeToUs(j2);
                        }
                    } else if (i != 374648427) {
                        if (i == 475249515 && !this.sentSeekMap) {
                            this.extractorOutput.seekMap(buildSeekMap());
                            this.sentSeekMap = true;
                        }
                    } else if (this.tracks.size() != 0) {
                        this.extractorOutput.endTracks();
                    } else {
                        throw new ParserException("No valid tracks were found");
                    }
                } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
            } else if (!this.currentTrack.hasContentEncryption) {
            } else {
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, "video/webm", this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput(this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void integerElement(int i, long j) throws ParserException {
        if (i != 20529) {
            if (i != 20530) {
                boolean z = false;
                switch (i) {
                    case 131:
                        this.currentTrack.type = (int) j;
                        return;
                    case 136:
                        Track track = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track.flagDefault = z;
                        return;
                    case 155:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case 159:
                        this.currentTrack.channelCount = (int) j;
                        return;
                    case 176:
                        this.currentTrack.width = (int) j;
                        return;
                    case 179:
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case 186:
                        this.currentTrack.height = (int) j;
                        return;
                    case 215:
                        this.currentTrack.number = (int) j;
                        return;
                    case 231:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case 241:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case 251:
                        this.sampleSeenReferenceBlock = true;
                        return;
                    case 16980:
                        if (j != 3) {
                            StringBuilder sb = new StringBuilder(50);
                            sb.append("ContentCompAlgo ");
                            sb.append(j);
                            sb.append(" not supported");
                            throw new ParserException(sb.toString());
                        }
                        return;
                    case 17029:
                        if (j < 1 || j > 2) {
                            StringBuilder sb2 = new StringBuilder(53);
                            sb2.append("DocTypeReadVersion ");
                            sb2.append(j);
                            sb2.append(" not supported");
                            throw new ParserException(sb2.toString());
                        }
                        return;
                    case 17143:
                        if (j != 1) {
                            StringBuilder sb3 = new StringBuilder(50);
                            sb3.append("EBMLReadVersion ");
                            sb3.append(j);
                            sb3.append(" not supported");
                            throw new ParserException(sb3.toString());
                        }
                        return;
                    case 18401:
                        if (j != 5) {
                            StringBuilder sb4 = new StringBuilder(49);
                            sb4.append("ContentEncAlgo ");
                            sb4.append(j);
                            sb4.append(" not supported");
                            throw new ParserException(sb4.toString());
                        }
                        return;
                    case 18408:
                        if (j != 1) {
                            StringBuilder sb5 = new StringBuilder(56);
                            sb5.append("AESSettingsCipherMode ");
                            sb5.append(j);
                            sb5.append(" not supported");
                            throw new ParserException(sb5.toString());
                        }
                        return;
                    case 21420:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case 21432:
                        int i2 = (int) j;
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case 21680:
                        this.currentTrack.displayWidth = (int) j;
                        return;
                    case 21682:
                        this.currentTrack.displayUnit = (int) j;
                        return;
                    case 21690:
                        this.currentTrack.displayHeight = (int) j;
                        return;
                    case 21930:
                        Track track2 = this.currentTrack;
                        if (j == 1) {
                            z = true;
                        }
                        track2.flagForced = z;
                        return;
                    case 22186:
                        this.currentTrack.codecDelayNs = j;
                        return;
                    case 22203:
                        this.currentTrack.seekPreRollNs = j;
                        return;
                    case 25188:
                        this.currentTrack.audioBitDepth = (int) j;
                        return;
                    case 30321:
                        int i3 = (int) j;
                        if (i3 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i3 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i3 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i3 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case 2352003:
                        this.currentTrack.defaultSampleDurationNs = (int) j;
                        return;
                    case 2807729:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case 21945:
                                int i4 = (int) j;
                                if (i4 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i4 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case 21946:
                                int i5 = (int) j;
                                if (i5 != 1) {
                                    if (i5 == 16) {
                                        this.currentTrack.colorTransfer = 6;
                                        return;
                                    } else if (i5 == 18) {
                                        this.currentTrack.colorTransfer = 7;
                                        return;
                                    } else if (!(i5 == 6 || i5 == 7)) {
                                        return;
                                    }
                                }
                                this.currentTrack.colorTransfer = 3;
                                return;
                            case 21947:
                                this.currentTrack.hasColorInfo = true;
                                int i6 = (int) j;
                                if (i6 == 1) {
                                    this.currentTrack.colorSpace = 1;
                                    return;
                                } else if (i6 == 9) {
                                    this.currentTrack.colorSpace = 6;
                                    return;
                                } else if (i6 == 4 || i6 == 5 || i6 == 6 || i6 == 7) {
                                    this.currentTrack.colorSpace = 2;
                                    return;
                                } else {
                                    return;
                                }
                            case 21948:
                                this.currentTrack.maxContentLuminance = (int) j;
                                return;
                            case 21949:
                                this.currentTrack.maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                StringBuilder sb6 = new StringBuilder(55);
                sb6.append("ContentEncodingScope ");
                sb6.append(j);
                sb6.append(" not supported");
                throw new ParserException(sb6.toString());
            }
        } else if (j != 0) {
            StringBuilder sb7 = new StringBuilder(55);
            sb7.append("ContentEncodingOrder ");
            sb7.append(j);
            sb7.append(" not supported");
            throw new ParserException(sb7.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void floatElement(int i, double d) throws ParserException {
        if (i == 181) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case 21970:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case 21971:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case 21972:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case 21973:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case 21974:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case 21975:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case 21976:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case 21977:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case 21978:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case 30323:
                            this.currentTrack.projectionPoseYaw = (float) d;
                            return;
                        case 30324:
                            this.currentTrack.projectionPosePitch = (float) d;
                            return;
                        case 30325:
                            this.currentTrack.projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: protected */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == 21358) {
                this.currentTrack.name = str;
            } else if (i == 2274716) {
                this.currentTrack.language = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new ParserException(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0202, code lost:
        throw new androidx.media2.exoplayer.external.ParserException("EBML lacing sample size out of range.");
     */
    public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i3;
        int i4;
        int[] iArr;
        int i5 = 0;
        int i6 = 1;
        if (i == 161 || i == 163) {
            if (this.blockState == 0) {
                this.blockTrackNumber = (int) this.varintReader.readUnsignedVarint(extractorInput, false, true, 8);
                this.blockTrackNumberLength = this.varintReader.getLastLength();
                this.blockDurationUs = -9223372036854775807L;
                this.blockState = 1;
                this.scratch.reset();
            }
            Track track = this.tracks.get(this.blockTrackNumber);
            if (track == null) {
                extractorInput.skipFully(i2 - this.blockTrackNumberLength);
                this.blockState = 0;
                return;
            }
            if (this.blockState == 1) {
                readScratch(extractorInput, 3);
                int i7 = (this.scratch.data[2] & 6) >> 1;
                byte b = 255;
                if (i7 == 0) {
                    this.blockLacingSampleCount = 1;
                    int[] ensureArrayCapacity = ensureArrayCapacity(this.blockLacingSampleSizes, 1);
                    this.blockLacingSampleSizes = ensureArrayCapacity;
                    ensureArrayCapacity[0] = (i2 - this.blockTrackNumberLength) - 3;
                } else if (i == 163) {
                    int i8 = 4;
                    readScratch(extractorInput, 4);
                    int i9 = (this.scratch.data[3] & 255) + 1;
                    this.blockLacingSampleCount = i9;
                    int[] ensureArrayCapacity2 = ensureArrayCapacity(this.blockLacingSampleSizes, i9);
                    this.blockLacingSampleSizes = ensureArrayCapacity2;
                    if (i7 == 2) {
                        int i10 = this.blockLacingSampleCount;
                        Arrays.fill(ensureArrayCapacity2, 0, i10, ((i2 - this.blockTrackNumberLength) - 4) / i10);
                    } else if (i7 == 1) {
                        int i11 = 0;
                        int i12 = 0;
                        while (true) {
                            i3 = this.blockLacingSampleCount;
                            if (i11 >= i3 - 1) {
                                break;
                            }
                            this.blockLacingSampleSizes[i11] = 0;
                            do {
                                i8++;
                                readScratch(extractorInput, i8);
                                i4 = this.scratch.data[i8 - 1] & 255;
                                iArr = this.blockLacingSampleSizes;
                                iArr[i11] = iArr[i11] + i4;
                            } while (i4 == 255);
                            i12 += iArr[i11];
                            i11++;
                        }
                        this.blockLacingSampleSizes[i3 - 1] = ((i2 - this.blockTrackNumberLength) - i8) - i12;
                    } else if (i7 == 3) {
                        int i13 = 0;
                        int i14 = 0;
                        while (true) {
                            int i15 = this.blockLacingSampleCount;
                            if (i13 >= i15 - 1) {
                                this.blockLacingSampleSizes[i15 - 1] = ((i2 - this.blockTrackNumberLength) - i8) - i14;
                                break;
                            }
                            this.blockLacingSampleSizes[i13] = i5;
                            i8++;
                            readScratch(extractorInput, i8);
                            int i16 = i8 - 1;
                            if (this.scratch.data[i16] != 0) {
                                long j = 0;
                                int i17 = 0;
                                while (true) {
                                    if (i17 >= 8) {
                                        break;
                                    }
                                    int i18 = i6 << (7 - i17);
                                    if ((this.scratch.data[i16] & i18) != 0) {
                                        i8 += i17;
                                        readScratch(extractorInput, i8);
                                        long j2 = (long) (this.scratch.data[i16] & b & (i18 ^ -1));
                                        int i19 = i16 + 1;
                                        while (true) {
                                            j = j2;
                                            if (i19 >= i8) {
                                                break;
                                            }
                                            j2 = (j << 8) | ((long) (this.scratch.data[i19] & b));
                                            i19++;
                                            b = 255;
                                        }
                                        if (i13 > 0) {
                                            j -= (1 << ((i17 * 7) + 6)) - 1;
                                        }
                                    } else {
                                        i17++;
                                        i6 = 1;
                                        b = 255;
                                    }
                                }
                                if (j >= -2147483648L && j <= 2147483647L) {
                                    int i20 = (int) j;
                                    int[] iArr2 = this.blockLacingSampleSizes;
                                    if (i13 != 0) {
                                        i20 += iArr2[i13 - 1];
                                    }
                                    iArr2[i13] = i20;
                                    i14 += this.blockLacingSampleSizes[i13];
                                    i13++;
                                    i5 = 0;
                                    i6 = 1;
                                    b = 255;
                                }
                            } else {
                                throw new ParserException("No valid varint length mask found");
                            }
                        }
                    } else {
                        StringBuilder sb = new StringBuilder(36);
                        sb.append("Unexpected lacing value: ");
                        sb.append(i7);
                        throw new ParserException(sb.toString());
                    }
                } else {
                    throw new ParserException("Lacing only supported in SimpleBlocks.");
                }
                this.blockTimeUs = this.clusterTimecodeUs + scaleTimecodeToUs((long) ((this.scratch.data[0] << 8) | (this.scratch.data[1] & 255)));
                this.blockFlags = ((this.scratch.data[2] & 8) == 8 ? RecyclerView.UNDEFINED_DURATION : 0) | ((track.type == 2 || (i == 163 && (this.scratch.data[2] & 128) == 128)) ? 1 : 0);
                this.blockState = 2;
                this.blockLacingSampleIndex = 0;
            }
            if (i == 163) {
                while (true) {
                    int i21 = this.blockLacingSampleIndex;
                    if (i21 < this.blockLacingSampleCount) {
                        writeSampleData(extractorInput, track, this.blockLacingSampleSizes[i21]);
                        commitSampleToOutput(track, this.blockTimeUs + ((long) ((this.blockLacingSampleIndex * track.defaultSampleDurationNs) / 1000)));
                        this.blockLacingSampleIndex++;
                    } else {
                        this.blockState = 0;
                        return;
                    }
                }
            } else {
                writeSampleData(extractorInput, track, this.blockLacingSampleSizes[0]);
            }
        } else if (i == 16981) {
            this.currentTrack.sampleStrippedBytes = new byte[i2];
            extractorInput.readFully(this.currentTrack.sampleStrippedBytes, 0, i2);
        } else if (i == 18402) {
            byte[] bArr = new byte[i2];
            extractorInput.readFully(bArr, 0, i2);
            this.currentTrack.cryptoData = new TrackOutput.CryptoData(1, bArr, 0, 0);
        } else if (i == 21419) {
            Arrays.fill(this.seekEntryIdBytes.data, (byte) 0);
            extractorInput.readFully(this.seekEntryIdBytes.data, 4 - i2, i2);
            this.seekEntryIdBytes.setPosition(0);
            this.seekEntryId = (int) this.seekEntryIdBytes.readUnsignedInt();
        } else if (i == 25506) {
            this.currentTrack.codecPrivate = new byte[i2];
            extractorInput.readFully(this.currentTrack.codecPrivate, 0, i2);
        } else if (i == 30322) {
            this.currentTrack.projectionData = new byte[i2];
            extractorInput.readFully(this.currentTrack.projectionData, 0, i2);
        } else {
            StringBuilder sb2 = new StringBuilder(26);
            sb2.append("Unexpected id: ");
            sb2.append(i);
            throw new ParserException(sb2.toString());
        }
    }

    private void commitSampleToOutput(Track track, long j) {
        if (track.trueHdSampleRechunker != null) {
            track.trueHdSampleRechunker.sampleMetadata(track, j);
        } else {
            if ("S_TEXT/UTF8".equals(track.codecId)) {
                commitSubtitleSample(track, "%02d:%02d:%02d,%03d", 19, 1000, SUBRIP_TIMECODE_EMPTY);
            } else if ("S_TEXT/ASS".equals(track.codecId)) {
                commitSubtitleSample(track, "%01d:%02d:%02d:%02d", 21, 10000, SSA_TIMECODE_EMPTY);
            }
            track.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        int i2;
        if ("S_TEXT/UTF8".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if ("S_TEXT/ASS".equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.data[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i3 = 0;
                            }
                            bArr[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.data, 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if (!"V_MPEG4/ISO/AVC".equals(track.codecId) && !"V_MPEGH/ISO/HEVC".equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, limit);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit) {
                        break;
                    }
                    readToOutput(extractorInput, trackOutput, limit - i9);
                }
            } else {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        readToTarget(extractorInput, bArr2, i11, i10);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining = i12 - readToOutput(extractorInput, trackOutput, i12);
                    }
                }
            }
            if ("A_VORBIS".equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4;
        if (j == -9223372036854775807L) {
            bArr4 = bArr2;
            bArr3 = bArr4;
        } else {
            int i2 = (int) (j / 3600000000L);
            long j3 = j - (((long) (i2 * 3600)) * 1000000);
            int i3 = (int) (j3 / 60000000);
            long j4 = j3 - (((long) (i3 * 60)) * 1000000);
            int i4 = (int) (j4 / 1000000);
            int i5 = (int) ((j4 - (((long) i4) * 1000000)) / j2);
            bArr4 = Util.getUtf8Bytes(String.format(Locale.US, str, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
            bArr3 = bArr2;
        }
        System.arraycopy(bArr4, 0, bArr, i, bArr3.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        LongArray longArray;
        LongArray longArray2;
        if (this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L || (longArray = this.cueTimesUs) == null || longArray.size() == 0 || (longArray2 = this.cueClusterPositions) == null || longArray2.size() != this.cueTimesUs.size()) {
            this.cueTimesUs = null;
            this.cueClusterPositions = null;
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = this.cueTimesUs.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jArr3[i2] = this.cueTimesUs.get(i2);
            jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
        }
        while (true) {
            int i3 = size - 1;
            if (i < i3) {
                int i4 = i + 1;
                iArr[i] = (int) (jArr[i4] - jArr[i]);
                jArr2[i] = jArr3[i4] - jArr3[i];
                i = i4;
            } else {
                iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                jArr2[i3] = this.durationUs - jArr3[i3];
                this.cueTimesUs = null;
                this.cueClusterPositions = null;
                return new ChunkIndex(iArr, jArr, jArr2, jArr3);
            }
        }
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean isCodecSupported(String str) {
        return "V_VP8".equals(str) || "V_VP9".equals(str) || "V_AV1".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_TEXT/ASS".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        @Override // androidx.media2.exoplayer.external.extractor.mkv.EbmlProcessor
        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    /* access modifiers changed from: private */
    public static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[10];
        private long timeUs;

        public void reset() {
            this.foundSyncframe = false;
        }

        public void startSample(ExtractorInput extractorInput, int i, int i2) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 10);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                } else {
                    return;
                }
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
        }

        public void sampleMetadata(Track track, long j) {
            if (this.foundSyncframe) {
                int i = this.sampleCount;
                this.sampleCount = i + 1;
                if (i == 0) {
                    this.timeUs = j;
                }
                if (this.sampleCount >= 16) {
                    track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                    this.sampleCount = 0;
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.foundSyncframe && this.sampleCount > 0) {
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class Track {
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        private String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public float projectionPosePitch;
        public float projectionPoseRoll;
        public float projectionPoseYaw;
        public int projectionType;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionType = -1;
            this.projectionPoseYaw = 0.0f;
            this.projectionPosePitch = 0.0f;
            this.projectionPoseRoll = 0.0f;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        public void initializeOutput(ExtractorOutput extractorOutput, int i) throws ParserException {
            char c;
            int i2;
            int i3;
            List<byte[]> list;
            int i4;
            Format format;
            int i5;
            int i6;
            int i7;
            int i8;
            String str;
            String str2;
            List<byte[]> list2;
            String str3;
            int pcmEncoding;
            String str4 = this.codecId;
            switch (str4.hashCode()) {
                case -2095576542:
                    if (str4.equals("V_MPEG4/ISO/AP")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -2095575984:
                    if (str4.equals("V_MPEG4/ISO/SP")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -1985379776:
                    if (str4.equals("A_MS/ACM")) {
                        c = 23;
                        break;
                    }
                    c = 65535;
                    break;
                case -1784763192:
                    if (str4.equals("A_TRUEHD")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case -1730367663:
                    if (str4.equals("A_VORBIS")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641358:
                    if (str4.equals("A_MPEG/L2")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case -1482641357:
                    if (str4.equals("A_MPEG/L3")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                case -1373388978:
                    if (str4.equals("V_MS/VFW/FOURCC")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -933872740:
                    if (str4.equals("S_DVBSUB")) {
                        c = 29;
                        break;
                    }
                    c = 65535;
                    break;
                case -538363189:
                    if (str4.equals("V_MPEG4/ISO/ASP")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -538363109:
                    if (str4.equals("V_MPEG4/ISO/AVC")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -425012669:
                    if (str4.equals("S_VOBSUB")) {
                        c = 27;
                        break;
                    }
                    c = 65535;
                    break;
                case -356037306:
                    if (str4.equals("A_DTS/LOSSLESS")) {
                        c = 21;
                        break;
                    }
                    c = 65535;
                    break;
                case 62923557:
                    if (str4.equals("A_AAC")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 62923603:
                    if (str4.equals("A_AC3")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case 62927045:
                    if (str4.equals("A_DTS")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case 82318131:
                    if (str4.equals("V_AV1")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338133:
                    if (str4.equals("V_VP8")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 82338134:
                    if (str4.equals("V_VP9")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 99146302:
                    if (str4.equals("S_HDMV/PGS")) {
                        c = 28;
                        break;
                    }
                    c = 65535;
                    break;
                case 444813526:
                    if (str4.equals("V_THEORA")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 542569478:
                    if (str4.equals("A_DTS/EXPRESS")) {
                        c = 20;
                        break;
                    }
                    c = 65535;
                    break;
                case 725957860:
                    if (str4.equals("A_PCM/INT/LIT")) {
                        c = 24;
                        break;
                    }
                    c = 65535;
                    break;
                case 738597099:
                    if (str4.equals("S_TEXT/ASS")) {
                        c = 26;
                        break;
                    }
                    c = 65535;
                    break;
                case 855502857:
                    if (str4.equals("V_MPEGH/ISO/HEVC")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1422270023:
                    if (str4.equals("S_TEXT/UTF8")) {
                        c = 25;
                        break;
                    }
                    c = 65535;
                    break;
                case 1809237540:
                    if (str4.equals("V_MPEG2")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950749482:
                    if (str4.equals("A_EAC3")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 1950789798:
                    if (str4.equals("A_FLAC")) {
                        c = 22;
                        break;
                    }
                    c = 65535;
                    break;
                case 1951062397:
                    if (str4.equals("A_OPUS")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            String str5 = "audio/x-unknown";
            switch (c) {
                case 0:
                    str = "video/x-vnd.on2.vp8";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 1:
                    str = "video/x-vnd.on2.vp9";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 2:
                    str = "video/av01";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 3:
                    str = "video/mpeg2";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 4:
                case 5:
                case 6:
                    byte[] bArr = this.codecPrivate;
                    list = bArr == null ? null : Collections.singletonList(bArr);
                    str2 = "video/mp4v-es";
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 7:
                    AvcConfig parse = AvcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list2 = parse.initializationData;
                    this.nalUnitLengthFieldLength = parse.nalUnitLengthFieldLength;
                    str2 = "video/avc";
                    list = list2;
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case '\b':
                    HevcConfig parse2 = HevcConfig.parse(new ParsableByteArray(this.codecPrivate));
                    list2 = parse2.initializationData;
                    this.nalUnitLengthFieldLength = parse2.nalUnitLengthFieldLength;
                    str2 = "video/hevc";
                    list = list2;
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case '\t':
                    Pair<String, List<byte[]>> parseFourCcPrivate = parseFourCcPrivate(new ParsableByteArray(this.codecPrivate));
                    str2 = (String) parseFourCcPrivate.first;
                    list = (List) parseFourCcPrivate.second;
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case '\n':
                    str = "video/x-unknown";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 11:
                    list = parseVorbisCodecPrivate(this.codecPrivate);
                    str5 = "audio/vorbis";
                    i3 = -1;
                    i2 = 8192;
                    break;
                case '\f':
                    list = new ArrayList<>(3);
                    list.add(this.codecPrivate);
                    list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.codecDelayNs).array());
                    list.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.seekPreRollNs).array());
                    str5 = "audio/opus";
                    i3 = -1;
                    i2 = 5760;
                    break;
                case '\r':
                    list = Collections.singletonList(this.codecPrivate);
                    str2 = "audio/mp4a-latm";
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 14:
                    str3 = "audio/mpeg-L2";
                    str5 = str3;
                    list = null;
                    i3 = -1;
                    i2 = 4096;
                    break;
                case 15:
                    str3 = "audio/mpeg";
                    str5 = str3;
                    list = null;
                    i3 = -1;
                    i2 = 4096;
                    break;
                case 16:
                    str = "audio/ac3";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 17:
                    str = "audio/eac3";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 18:
                    this.trueHdSampleRechunker = new TrueHdSampleRechunker();
                    str = "audio/true-hd";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 19:
                case 20:
                    str = "audio/vnd.dts";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 21:
                    str = "audio/vnd.dts.hd";
                    str5 = str;
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 22:
                    list = Collections.singletonList(this.codecPrivate);
                    str2 = "audio/flac";
                    str5 = str2;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 23:
                    if (parseMsAcmCodecPrivate(new ParsableByteArray(this.codecPrivate))) {
                        pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                        if (pcmEncoding == 0) {
                            int i9 = this.audioBitDepth;
                            StringBuilder sb = new StringBuilder(str5.length() + 60);
                            sb.append("Unsupported PCM bit depth: ");
                            sb.append(i9);
                            sb.append(". Setting mimeType to ");
                            sb.append(str5);
                            Log.w("MatroskaExtractor", sb.toString());
                        }
                        i3 = pcmEncoding;
                        str5 = "audio/raw";
                        list = null;
                        i2 = -1;
                        break;
                    } else {
                        Log.w("MatroskaExtractor", str5.length() != 0 ? "Non-PCM MS/ACM is unsupported. Setting mimeType to ".concat(str5) : new String("Non-PCM MS/ACM is unsupported. Setting mimeType to "));
                    }
                    list = null;
                    i3 = -1;
                    i2 = -1;
                case 24:
                    pcmEncoding = Util.getPcmEncoding(this.audioBitDepth);
                    if (pcmEncoding == 0) {
                        int i10 = this.audioBitDepth;
                        StringBuilder sb2 = new StringBuilder(str5.length() + 60);
                        sb2.append("Unsupported PCM bit depth: ");
                        sb2.append(i10);
                        sb2.append(". Setting mimeType to ");
                        sb2.append(str5);
                        Log.w("MatroskaExtractor", sb2.toString());
                        list = null;
                        i3 = -1;
                        i2 = -1;
                        break;
                    }
                    i3 = pcmEncoding;
                    str5 = "audio/raw";
                    list = null;
                    i2 = -1;
                case 25:
                    str5 = "application/x-subrip";
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 26:
                    str5 = "text/x-ssa";
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 27:
                    list = Collections.singletonList(this.codecPrivate);
                    str5 = "application/vobsub";
                    i3 = -1;
                    i2 = -1;
                    break;
                case 28:
                    str5 = "application/pgs";
                    list = null;
                    i3 = -1;
                    i2 = -1;
                    break;
                case 29:
                    byte[] bArr2 = this.codecPrivate;
                    list = Collections.singletonList(new byte[]{bArr2[0], bArr2[1], bArr2[2], bArr2[3]});
                    str5 = "application/dvbsubs";
                    i3 = -1;
                    i2 = -1;
                    break;
                default:
                    throw new ParserException("Unrecognized codec identifier.");
            }
            int i11 = (this.flagDefault ? 1 : 0) | 0 | (this.flagForced ? 2 : 0);
            if (MimeTypes.isAudio(str5)) {
                format = Format.createAudioSampleFormat(Integer.toString(i), str5, null, -1, i2, this.channelCount, this.sampleRate, i3, list, this.drmInitData, i11, this.language);
                i4 = 1;
            } else if (MimeTypes.isVideo(str5)) {
                if (this.displayUnit == 0) {
                    int i12 = this.displayWidth;
                    i5 = -1;
                    if (i12 == -1) {
                        i12 = this.width;
                    }
                    this.displayWidth = i12;
                    int i13 = this.displayHeight;
                    if (i13 == -1) {
                        i13 = this.height;
                    }
                    this.displayHeight = i13;
                } else {
                    i5 = -1;
                }
                int i14 = this.displayWidth;
                float f = (i14 == i5 || (i8 = this.displayHeight) == i5) ? -1.0f : ((float) (this.height * i14)) / ((float) (this.width * i8));
                ColorInfo colorInfo = this.hasColorInfo ? new ColorInfo(this.colorSpace, this.colorRange, this.colorTransfer, getHdrStaticInfo()) : null;
                if ("htc_video_rotA-000".equals(this.name)) {
                    i6 = 0;
                } else if ("htc_video_rotA-090".equals(this.name)) {
                    i6 = 90;
                } else if ("htc_video_rotA-180".equals(this.name)) {
                    i6 = 180;
                } else {
                    i6 = "htc_video_rotA-270".equals(this.name) ? 270 : -1;
                }
                if (this.projectionType == 0 && Float.compare(this.projectionPoseYaw, 0.0f) == 0 && Float.compare(this.projectionPosePitch, 0.0f) == 0) {
                    if (Float.compare(this.projectionPoseRoll, 0.0f) == 0) {
                        i7 = 0;
                    } else if (Float.compare(this.projectionPosePitch, 90.0f) == 0) {
                        i7 = 90;
                    } else if (Float.compare(this.projectionPosePitch, -180.0f) == 0 || Float.compare(this.projectionPosePitch, 180.0f) == 0) {
                        i7 = 180;
                    } else if (Float.compare(this.projectionPosePitch, -90.0f) == 0) {
                        i7 = 270;
                    }
                    format = Format.createVideoSampleFormat(Integer.toString(i), str5, null, -1, i2, this.width, this.height, -1.0f, list, i7, f, this.projectionData, this.stereoMode, colorInfo, this.drmInitData);
                    i4 = 2;
                }
                i7 = i6;
                format = Format.createVideoSampleFormat(Integer.toString(i), str5, null, -1, i2, this.width, this.height, -1.0f, list, i7, f, this.projectionData, this.stereoMode, colorInfo, this.drmInitData);
                i4 = 2;
            } else {
                if ("application/x-subrip".equals(str5)) {
                    format = Format.createTextSampleFormat(Integer.toString(i), str5, i11, this.language, this.drmInitData);
                } else if ("text/x-ssa".equals(str5)) {
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(MatroskaExtractor.SSA_DIALOGUE_FORMAT);
                    arrayList.add(this.codecPrivate);
                    format = Format.createTextSampleFormat(Integer.toString(i), str5, null, -1, i11, this.language, -1, this.drmInitData, Long.MAX_VALUE, arrayList);
                } else if ("application/vobsub".equals(str5) || "application/pgs".equals(str5) || "application/dvbsubs".equals(str5)) {
                    format = Format.createImageSampleFormat(Integer.toString(i), str5, null, -1, i11, list, this.language, this.drmInitData);
                } else {
                    throw new ParserException("Unexpected MIME type.");
                }
                i4 = 3;
            }
            TrackOutput track = extractorOutput.track(this.number, i4);
            this.output = track;
            track.format(format);
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put((byte) 0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>("video/divx", null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>("video/3gpp", null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] bArr = parsableByteArray.data;
                    for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                        if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                            return new Pair<>("video/wvc1", Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length)));
                        }
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>("video/x-unknown", null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    int i4 = i2 + bArr[i];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }
}
