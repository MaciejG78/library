package pl.com.bottega.library.model.Command;

import java.time.LocalDateTime;

/**
 * Created by maciek on 01.04.2017.
 */
public class ChangeBookCommand {
    private static final int MIN_BOOK_YEAR = 500;

    private String number;
    private String title;
    private String author;
    private int year;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
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