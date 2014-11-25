package org.geeklub.smartlib4admin.module.type;

/**
 * Created by Vass on 2014/11/25.
 */
public enum  Category {
  library("Library"), lend("Lend");

  private String mDisplayName;

  Category(String displayName) {
    mDisplayName = displayName;
  }

  public String getDisplayName() {
    return mDisplayName;
  }
}
