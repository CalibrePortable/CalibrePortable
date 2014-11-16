package org.geeklub.smartlib.module.type;

/**
 * Created by Vass on 2014/11/3.
 */
public enum NormalUserFunctions {
  library("Library"), borrow("Borrow");

  private String mDisplayName;

  NormalUserFunctions(String displayName) {
    mDisplayName = displayName;
  }

  public String getDisplayName() {
    return mDisplayName;
  }
}
