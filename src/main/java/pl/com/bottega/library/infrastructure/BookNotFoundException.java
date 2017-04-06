package pl.com.bottega.library.infrastructure;

/**
 * Created by maciek on 02.04.2017.
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String number) {
        super(String.format("Book with number %s doesn't exist", number));
    }
}
