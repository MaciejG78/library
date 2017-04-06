package pl.com.bottega.library.model;

/**
 * Created by maciek on 03.04.2017.
 */
public class BookLendingException extends RuntimeException {
    public BookLendingException(String number) {
        super(String.format("Book %s is probably available to lend", number));
    }
}
