package org.geeklub.smartlib4admin.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vass on 2014/12/1.
 */
public class Book implements Parcelable {
    public String book_id;
    public String book_name;
    public String book_status;
    public String user_name;
    public String favour;
    public String book_pic;
    public String created_at;
    public String return_at;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.book_id);
        dest.writeString(this.book_name);
        dest.writeString(this.book_status);
        dest.writeString(this.user_name);
        dest.writeString(this.favour);
        dest.writeString(this.book_pic);
        dest.writeString(this.created_at);
        dest.writeString(this.return_at);
    }

    public Book() {
    }

    private Book(Parcel in) {
        this.book_id = in.readString();
        this.book_name = in.readString();
        this.book_status = in.readString();
        this.user_name = in.readString();
        this.favour = in.readString();
        this.book_pic = in.readString();
        this.created_at = in.readString();
        this.return_at = in.readString();
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public String toString() {
        return "Book{" +
                "book_id='" + book_id + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_status='" + book_status + '\'' +
                ", user_name='" + user_name + '\'' +
                ", favour='" + favour + '\'' +
                ", book_pic='" + book_pic + '\'' +
                ", created_at='" + created_at + '\'' +
                ", return_at='" + return_at + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return o != null && (o instanceof Book) && ((Book) o).book_id.equals(book_id);
    }
}
