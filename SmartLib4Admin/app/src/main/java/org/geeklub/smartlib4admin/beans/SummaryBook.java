package org.geeklub.smartlib4admin.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vass on 2014/11/4.
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.book_kind);
        dest.writeString(this.book_detail_url);
        dest.writeString(this.book_name);
        dest.writeString(this.book_author);
        dest.writeString(this.book_status);
        dest.writeString(this.favour);
        dest.writeString(this.book_pic);
        dest.writeString(this.isLike);
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

    @Override
    public String toString() {
        return "SummaryBook{" +
                "book_kind='" + book_kind + '\'' +
                ", book_detail_url='" + book_detail_url + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_status='" + book_status + '\'' +
                ", favour='" + favour + '\'' +
                ", book_pic='" + book_pic + '\'' +
                ", isLike='" + isLike + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof SummaryBook) && ((SummaryBook) o).book_kind.equals(book_kind);
    }
}
