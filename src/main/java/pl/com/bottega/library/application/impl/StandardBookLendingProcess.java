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
        Book book = bookRepository.get(new BookNumber(cmd.getNumber()));
        book.lend(cmd);
    }
}
