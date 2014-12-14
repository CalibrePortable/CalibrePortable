package org.geeklub.smartlib4admin.module.type;

/**
 * Created by Vass on 2014/12/14.
 */
public enum  ScanCategory {
    ShapeCode("ShapeCode"), QRCode("QRCode");

    private String mDisplayName;

    ScanCategory(String displayName) {
        mDisplayName = displayName;
    }

    public String getDisplayName() {
        return mDisplayName;
    }
}
