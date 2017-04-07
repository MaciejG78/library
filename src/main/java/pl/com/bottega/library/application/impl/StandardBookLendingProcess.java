package pl.com.bottega.library.application.impl;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.library.application.BookLendingProcess;
import pl.com.bottega.library.model.BookRepository;
import pl.com.bottega.library.model.*;
import pl.com.bottega.library.model.Command.LendingBookCommand;

/**
 * Created by maciek on 02.04.2017.
 */
@Transactional
public class StandardBookLendingProcess implements BookLendingProcess{

    private BookRepository bookRepository;

    public StandardBookLendingProcess(BookRepository bookRepository){
       this. bookRepository = bookRepository;
    }

    @Override
    public void lend(LendingBookCommand cmd) {
        BookNumber bookNumber = new BookNumber(cmd.getNumber());
        Book book = bookRepository.get(bookNumber);
        book.lend(cmd);
    }

    @Override
    public void giveBack(BookNumber bookNumber) {
        Book book = bookRepository.get(bookNumber);
        book.giveBack(bookNumber);
    }
}
