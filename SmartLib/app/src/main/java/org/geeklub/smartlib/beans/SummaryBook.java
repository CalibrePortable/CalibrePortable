package org.geeklub.smartlib.beans;


/**
 * Created by Vass on 2014/11/4.
 */
public class SummaryBook {

    public String book_kind;

    public String book_detail_url;

    public String book_name;

    public String book_author;

    public String book_status;

    public String favour;

    public String book_pic;

    public String isLike;


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

        return ((SummaryBook) otherObject).book_kind.equals(book_kind);

    }
}
