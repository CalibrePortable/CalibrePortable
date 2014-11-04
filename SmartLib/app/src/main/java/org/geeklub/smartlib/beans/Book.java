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

  private class Response {
    /**
     * 书本列表
     */
    private List<Book> apps;
    /**
     * 状态
     */
    private int status;
  }
}
