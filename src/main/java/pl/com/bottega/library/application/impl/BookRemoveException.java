package pl.com.bottega.library.application.impl;

/**
 * Created by maciek on 06.04.2017.
 */
public class BookRemoveException extends RuntimeException {
    public BookRemoveException(String number) {
        super(String.format("Book with number %s is unavailable (probably isn't returned)", number));
    }
}
