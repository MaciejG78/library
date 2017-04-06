package pl.com.bottega.library.model;

import java.io.Serializable;

/**
 * Created by maciek on 03.04.2017.
 */
public class BookNumber implements Serializable {

    private String number;

    BookNumber() {}

    public BookNumber(String number) {
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookNumber)) return false;

        BookNumber that = (BookNumber) o;

        return number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}
