package org.geeklub.smartlib.beans;

import java.util.List;

/**
 * Created by Vass on 2014/11/28.
 */
public class BookDetailInfo {

  public BookIntroduction book_detail;

  public List<Book> book_list;

  public static class BookIntroduction {
    public String book_name;
    public String book_author;
    public String book_pub;
    public String book_type;
    public String book_edit;
    public String book_price;
    public String book_pic;
    public String book_link;
    public String book_info;
    public String favour;
  }
}
