package org.geeklub.smartlib.beans;

import java.util.List;

/**
 * Created by Vass on 2014/11/4.
 */
public class Book {

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

  @Override public String toString() {
    return "Book{" +
        "book_name='" + book_name + '\'' +
        ", book_author='" + book_author + '\'' +
        ", book_type='" + book_type + '\'' +
        ", book_info='" + book_info + '\'' +
        ", book_price='" + book_price + '\'' +
        ", book_status='" + book_status + '\'' +
        ", favour='" + favour + '\'' +
        '}';
  }

  public static class Response {
    /**
     * 书本列表
     */
    private List<Book> apps;
    /**
     * 状态
     */
    private int status;

    public List<Book> getApps() {
      return apps;
    }

    public void setApps(List<Book> apps) {
      this.apps = apps;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    @Override public String toString() {
      return "Response{" +
          "apps=" + apps +
          ", status=" + status +
          '}';
    }
  }
}
