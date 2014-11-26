package org.geeklub.smartlib.module.type;

/**
 * Created by Vass on 2014/11/3.
 */
public enum Category {
  library("Library"), borrow("Borrow"),logout("Logout");

  private String mDisplayName;

  Category(String displayName) {
    mDisplayName = displayName;
  }

  public String getDisplayName() {
    return mDisplayName;
  }
}
