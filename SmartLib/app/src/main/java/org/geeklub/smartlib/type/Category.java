package org.geeklub.smartlib.type;

/**
 * Created by Vass on 2014/11/3.
 */
public enum Category {
  members("Member"), events("Events"), activiys("Activitys");

  private String mDisplayName;

  Category(String displayName) {
    mDisplayName = displayName;
  }

  public String getDisplayName() {
    return mDisplayName;
  }
}
