package org.altbeacon.beacon.service.scanner;

import android.bluetooth.le.ScanFilter;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.logging.LogManager;

public class ScanFilterUtils {

    /* access modifiers changed from: package-private */
    public class ScanFilterData {
        public byte[] filter;
        public int manufacturer;
        public byte[] mask;
        public Long serviceUuid = null;

        ScanFilterData() {
        }
    }

    public List<ScanFilter> createWildcardScanFilters() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ScanFilter.Builder().build());
        return arrayList;
    }

    public List<ScanFilterData> createScanFilterDataForBeaconParser(BeaconParser beaconParser) {
        ArrayList arrayList = new ArrayList();
        int[] hardwareAssistManufacturers = beaconParser.getHardwareAssistManufacturers();
        for (int i : hardwareAssistManufacturers) {
            Long serviceUuid = beaconParser.getServiceUuid();
            long longValue = beaconParser.getMatchingBeaconTypeCode().longValue();
            int matchingBeaconTypeCodeStartOffset = beaconParser.getMatchingBeaconTypeCodeStartOffset();
            int matchingBeaconTypeCodeEndOffset = beaconParser.getMatchingBeaconTypeCodeEndOffset();
            int i2 = (matchingBeaconTypeCodeEndOffset + 1) - 2;
            byte[] bArr = new byte[i2];
            byte[] bArr2 = new byte[i2];
            byte[] longToByteArray = BeaconParser.longToByteArray(longValue, (matchingBeaconTypeCodeEndOffset - matchingBeaconTypeCodeStartOffset) + 1);
            for (int i3 = 2; i3 <= matchingBeaconTypeCodeEndOffset; i3++) {
                int i4 = i3 - 2;
                if (i3 < matchingBeaconTypeCodeStartOffset) {
                    bArr[i4] = 0;
                    bArr2[i4] = 0;
                } else {
                    bArr[i4] = longToByteArray[i3 - matchingBeaconTypeCodeStartOffset];
                    bArr2[i4] = -1;
                }
            }
            ScanFilterData scanFilterData = new ScanFilterData();
            scanFilterData.manufacturer = i;
            scanFilterData.filter = bArr;
            scanFilterData.mask = bArr2;
            scanFilterData.serviceUuid = serviceUuid;
            arrayList.add(scanFilterData);
        }
        return arrayList;
    }

    public List<ScanFilter> createScanFiltersForBeaconParsers(List<BeaconParser> list) {
        ArrayList arrayList = new ArrayList();
        for (BeaconParser beaconParser : list) {
            for (ScanFilterData scanFilterData : createScanFilterDataForBeaconParser(beaconParser)) {
                ScanFilter.Builder builder = new ScanFilter.Builder();
                if (scanFilterData.serviceUuid != null) {
                    String format = String.format("0000%04X-0000-1000-8000-00805f9b34fb", scanFilterData.serviceUuid);
                    ParcelUuid fromString = ParcelUuid.fromString(format);
                    ParcelUuid fromString2 = ParcelUuid.fromString("FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF");
                    if (LogManager.isVerboseLoggingEnabled()) {
                        LogManager.d("ScanFilterUtils", "making scan filter for service: " + format + " " + fromString, new Object[0]);
                        LogManager.d("ScanFilterUtils", "making scan filter with service mask: " + "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF" + " " + fromString2, new Object[0]);
                    }
                    builder.setServiceUuid(fromString, fromString2);
                } else {
                    builder.setServiceUuid(null);
                    builder.setManufacturerData(scanFilterData.manufacturer, scanFilterData.filter, scanFilterData.mask);
                }
                ScanFilter build = builder.build();
                if (LogManager.isVerboseLoggingEnabled()) {
                    LogManager.d("ScanFilterUtils", "Set up a scan filter: " + build, new Object[0]);
                }
                arrayList.add(build);
            }
        }
        return arrayList;
    }
}
