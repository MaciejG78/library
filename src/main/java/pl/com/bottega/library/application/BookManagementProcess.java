package pl.com.bottega.library.application;

import pl.com.bottega.library.model.Book;
import pl.com.bottega.library.model.BookNumber;
import pl.com.bottega.library.model.Command.ChangeBookCommand;
import pl.com.bottega.library.model.Command.CreateBookCommand;

import java.util.List;

/**
 * Created by maciek on 05.04.2017.
 */
public interface BookManagementProcess {

    BookNumber create(CreateBookCommand cmd);

    void change(ChangeBookCommand cmd, BookNumber number);

    void remove(BookNumber number);

    List<Book> search(BookQuery bookQuery);

    BookList listAllBooks();

    BookInfo show(BookNumber number);
}
