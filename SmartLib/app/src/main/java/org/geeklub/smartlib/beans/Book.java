package org.geeklub.smartlib.beans;

/**
 * Created by Vass on 2014/12/18.
 */
public class Book {

    public String book_id;

    public String book_name;

    public String book_author;

    public String book_status;

    public String book_pub;

    public String book_type;

    public String book_edit;

    public String book_price;

    public String book_pic;

    public String book_link;

    public String book_info;

    public String user_name;

    public String favour;

    public String create_at;

    public String return_at;


    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }

        if (otherObject == this) {
            return true;
        }

        if (getClass() != otherObject.getClass()) {
            return false;
        }

        Book book = (Book) otherObject;

        return book_id.equals(book.book_id) && create_at.equals(book.create_at);


    }
}
