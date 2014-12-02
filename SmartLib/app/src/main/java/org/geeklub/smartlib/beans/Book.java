package org.geeklub.smartlib.beans;

/**
 * Created by Vass on 2014/11/4.
 */
public class Book {

  public String book_id;

  public String created_at;

  public String user_name;

  public String return_at;

  public String book_status;

  @Override public String toString() {
    return "Book{" +
        "book_id='" + book_id + '\'' +
        ", created_at='" + created_at + '\'' +
        ", user_name='" + user_name + '\'' +
        ", return_at='" + return_at + '\'' +
        '}';
  }

    @Override public boolean equals(Object o) {
        return o != null && (o instanceof Book) && ((Book) o).book_id.equals(book_id);
    }
}
