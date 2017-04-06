package pl.com.bottega.library.model;

import pl.com.bottega.library.model.Command.ChangeBookCommand;
import pl.com.bottega.library.model.Command.CreateBookCommand;
import pl.com.bottega.library.model.Command.LendingBookCommand;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Book {

    @EmbeddedId
    private BookNumber number;

    private String title;
    private String author;
    private int year;
    private boolean available;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bookNumber")
    private Set<Lending> lendings;

    Book() {
    }

    public Book(CreateBookCommand cmd, BookNumber number) {
        this.number = number;
        this.title = cmd.getTitle();
        this.author = cmd.getAuthor();
        this.year = cmd.getYear();
        this.available = true;
        this.lendings = new HashSet<>();
    }

    public void change(ChangeBookCommand cmd) {
        this.title = cmd.getTitle();
        this.author = cmd.getAuthor();
        this.year = cmd.getYear();
    }

    public void lend(LendingBookCommand cmd) {
        if (available == true) {
            Lending lending = new Lending(cmd.getFirstName(), cmd.getLastName());
            available = false;
            lending.lending();
        }
    }

    public void giveBack(BookNumber bookNumber) {
        Lending lending = getLending(bookNumber);
        available = true;
        lending.giveBack();
    }

    public Lending getLending(BookNumber bookNumber) {
        for (Lending lending : lendings) {
            if (lending.getReturnDate() == null)
                return lending;
        }
        throw new BookLendingException(bookNumber.getNumber());
    }

    public void setNumber(BookNumber number) {
        this.number = number;
    }

    public BookNumber getNumber() {
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
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
