package pl.com.bottega.library.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.library.application.*;
import pl.com.bottega.library.model.*;
import pl.com.bottega.library.model.Command.ChangeBookCommand;
import pl.com.bottega.library.model.Command.CreateBookCommand;

import java.util.List;

/**
 * Created by maciek on 05.04.2017.
 */
@Transactional
public class StandardBookManagementProcess implements BookManagementProcess {

    private BookRepository bookRepository;
    private NumberGenerator numberGenerator;

    public StandardBookManagementProcess(NumberGenerator numberGenerator,
                                         BookRepository bookRepository) {
        this.numberGenerator = numberGenerator;
        this.bookRepository = bookRepository;
    }

    @Override
    public BookNumber create(CreateBookCommand cmd) {
        Book book = new Book(cmd, numberGenerator.generate());
        bookRepository.put(book);
        return book.getNumber();
    }

    @Override
    public void change(ChangeBookCommand cmd, BookNumber number) {
        Book book = bookRepository.get(number);
        book.change(cmd);
    }

    //TODO sprawdzić czy nie jest wypożyczona
    @Override
    public void remove(BookNumber number) {
        Book book = bookRepository.get(number);
        if(book.isAvailable())
            bookRepository.remove(number);
        else
            throw new BookRemoveException(number.getNumber());
    }

    @Override
    public List<Book> search(BookQuery bookQuery){
        return bookRepository.search(bookQuery);
    }

    @Override
    public BookList listAllBooks(){
        BookList bookList = new BookList();
        List<Book> books = bookRepository.getAllBooks();
        bookList.setBooks(books);
        Long availableBooksCount = bookRepository.countAvailableBooks();
        bookList.setAvailableBookCount(availableBooksCount);
        bookList.setLentBookCount(books.size()-availableBooksCount);
        return bookList;
    }

    @Override
    public BookInfo show(BookNumber number){
        BookInfo bookInfo = new BookInfo();
        Book book = bookRepository.get(number);
        bookInfo.setBook(book);
        return bookInfo;
    }

}
