package pl.com.bottega.library.application;

import pl.com.bottega.library.model.Book;

import java.util.List;

/**
 * Created by maciek on 05.04.2017.
 */
public class BookList {

    private List<Book> books;
    private Long availableBookCount;
    private Long lentBookCount;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getAvailableBookCount() {
        return availableBookCount;
    }

    public void setAvailableBookCount(Long availableBookCount) {
        this.availableBookCount = availableBookCount;
    }

    public Long getLentBookCount() {
        return lentBookCount;
    }

    public void setLentBookCount(Long lentBookCount) {
        this.lentBookCount = lentBookCount;
    }
}
