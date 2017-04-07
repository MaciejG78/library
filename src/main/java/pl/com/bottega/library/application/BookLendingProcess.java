package pl.com.bottega.library.application;

import pl.com.bottega.library.model.BookNumber;
import pl.com.bottega.library.model.Command.LendingBookCommand;

/**
 * Created by maciek on 03.04.2017.
 */
public interface BookLendingProcess {

    void lend(LendingBookCommand cmd);

    void giveBack(BookNumber bookNumber);
}
