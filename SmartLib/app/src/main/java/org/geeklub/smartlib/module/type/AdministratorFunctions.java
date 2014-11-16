package org.geeklub.smartlib.module.type;

/**
 * Created by Vass on 2014/11/15.
 */
public enum AdministratorFunctions {
  library("Library"), borrow("Lend");

  private String mDisplayName;

  AdministratorFunctions(String displayName) {
    mDisplayName = displayName;
  }

  public String getDisplayName() {
    return mDisplayName;
  }
}
