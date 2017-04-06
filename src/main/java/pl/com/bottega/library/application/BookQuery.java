package pl.com.bottega.library.application;

/**
 * Created by maciek on 01.04.2017.
 */
public class BookQuery {

    private String title;
    private String author;
    private int year;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }
}
