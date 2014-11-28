package org.geeklub.smartlib.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vass on 2014/11/28.
 */
public class SummaryBook implements Parcelable {

  public String book_kind;
  public String book_detail_url;
  public String book_name;
  public String book_author;
  public String book_status;
  public String favour;
  public String book_pic;
  public String isLike;
  public String created_at;
  public String return_at;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.book_kind);
    dest.writeString(this.book_detail_url);
    dest.writeString(this.book_name);
    dest.writeString(this.book_author);
    dest.writeString(this.book_status);
    dest.writeString(this.favour);
    dest.writeString(this.book_pic);
    dest.writeString(this.isLike);
    dest.writeString(this.created_at);
    dest.writeString(this.return_at);
  }

  public SummaryBook() {
  }

  private SummaryBook(Parcel in) {
    this.book_kind = in.readString();
    this.book_detail_url = in.readString();
    this.book_name = in.readString();
    this.book_author = in.readString();
    this.book_status = in.readString();
    this.favour = in.readString();
    this.book_pic = in.readString();
    this.isLike = in.readString();
    this.created_at = in.readString();
    this.return_at = in.readString();
  }

  public static final Parcelable.Creator<SummaryBook> CREATOR =
      new Parcelable.Creator<SummaryBook>() {
        public SummaryBook createFromParcel(Parcel source) {
          return new SummaryBook(source);
        }

        public SummaryBook[] newArray(int size) {
          return new SummaryBook[size];
        }
      };
}
