package org.geeklub.smartlib4admin.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vass on 2014/11/4.
 */
public class Book implements Parcelable {
  /**
   * 书本Id
   */
  private String book_id;

  /**
   * 书名
   */
  private String book_name;
  /**
   * 作者
   */
  private String book_author;
  /**
   * 类型
   */
  private String book_type;
  /**
   * 信息
   */
  private String book_info;
  /**
   * 价格
   */
  private String book_price;
  /**
   * 状态
   */
  private String book_status;
  /**
   * 点赞数
   */
  private String favour;
  /**
   * 图片地址
   */
  private String book_pic;

  /**
   * 是否已经点赞
   */

  private int isLike;

  /**
   * 借出时间
   */
  private String created_at;
  /**
   * 归还时间
   */
  private String return_at;

  public Book() {
  }

  public String getBook_id() {
    return book_id;
  }

  public void setBook_id(String book_id) {
    this.book_id = book_id;
  }

  public String getBook_name() {
    return book_name;
  }

  public void setBook_name(String book_name) {
    this.book_name = book_name;
  }

  public String getBook_author() {
    return book_author;
  }

  public void setBook_author(String book_author) {
    this.book_author = book_author;
  }

  public String getBook_type() {
    return book_type;
  }

  public void setBook_type(String book_type) {
    this.book_type = book_type;
  }

  public String getBook_info() {
    return book_info;
  }

  public void setBook_info(String book_info) {
    this.book_info = book_info;
  }

  public String getBook_price() {
    return book_price;
  }

  public void setBook_price(String book_price) {
    this.book_price = book_price;
  }

  public String getBook_status() {
    return book_status;
  }

  public void setBook_status(String book_status) {
    this.book_status = book_status;
  }

  public String getFavour() {
    return favour;
  }

  public void setFavour(String favour) {
    this.favour = favour;
  }

  public String getBook_pic() {
    return book_pic;
  }

  public void setBook_pic(String book_pic) {
    this.book_pic = book_pic;
  }

  public int getIsLike() {
    return isLike;
  }

  public void setIsLike(int isLike) {
    this.isLike = isLike;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getReturn_at() {
    return return_at;
  }

  public void setReturn_at(String return_at) {
    this.return_at = return_at;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.book_id);
    dest.writeString(this.book_name);
    dest.writeString(this.book_author);
    dest.writeString(this.book_type);
    dest.writeString(this.book_info);
    dest.writeString(this.book_price);
    dest.writeString(this.book_status);
    dest.writeString(this.favour);
    dest.writeString(this.book_pic);
    dest.writeInt(this.isLike);
    dest.writeString(this.created_at);
    dest.writeString(this.return_at);
  }

  private Book(Parcel in) {
    this.book_id = in.readString();
    this.book_name = in.readString();
    this.book_author = in.readString();
    this.book_type = in.readString();
    this.book_info = in.readString();
    this.book_price = in.readString();
    this.book_status = in.readString();
    this.favour = in.readString();
    this.book_pic = in.readString();
    this.isLike = in.readInt();
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

  @Override public String toString() {
    return "Book{" +
        "book_id='" + book_id + '\'' +
        ", book_name='" + book_name + '\'' +
        ", book_author='" + book_author + '\'' +
        ", book_type='" + book_type + '\'' +
        ", book_info='" + book_info + '\'' +
        ", book_price='" + book_price + '\'' +
        ", book_status='" + book_status + '\'' +
        ", favour='" + favour + '\'' +
        ", book_pic='" + book_pic + '\'' +
        ", isLike=" + isLike +
        ", created_at='" + created_at + '\'' +
        ", return_at='" + return_at + '\'' +
        '}';
  }
}