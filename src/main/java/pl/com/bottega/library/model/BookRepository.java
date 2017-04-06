package pl.com.bottega.library.model;

import pl.com.bottega.library.application.BookQuery;
import pl.com.bottega.library.infrastructure.BookNotFoundException;
import pl.com.bottega.library.model.Book;
import pl.com.bottega.library.model.BookNumber;

import java.util.List;

/**
 * Created by maciek on 02.04.2017.
 */
public interface BookRepository {
    void put(Book book);

    Book get(BookNumber number) throws BookNotFoundException;

    void remove(BookNumber number);

    List<Book> search(BookQuery bookQuery);

    List<Book> getAllBooks();

    Long countAvailableBooks();

}
