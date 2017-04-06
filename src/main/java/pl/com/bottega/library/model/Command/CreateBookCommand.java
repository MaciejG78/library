package pl.com.bottega.library.model.Command;

import java.time.LocalDateTime;

/**
 * Created by maciek on 01.04.2017.
 */
public class CreateBookCommand {

    private static final int MIN_BOOK_YEAR = 500;

    private String title;
    private String author;
    private int year;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setYear(int year) {
        if (year >= MIN_BOOK_YEAR && year <= LocalDateTime.now().getYear())
            this.year = year;
        else
            throw new IllegalArgumentException(String.format("Year must between %d and %d", MIN_BOOK_YEAR, LocalDateTime.now().getYear()));
    }

    public int getYear() {
        return year;
    }
}
