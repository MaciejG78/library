package pl.com.bottega.library.acceptance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.library.application.*;
import pl.com.bottega.library.infrastructure.BookNotFoundException;
import pl.com.bottega.library.model.Book;
import pl.com.bottega.library.model.BookRepository;
import pl.com.bottega.library.model.BookNumber;
import pl.com.bottega.library.model.Command.ChangeBookCommand;
import pl.com.bottega.library.model.Command.CreateBookCommand;
import pl.com.bottega.library.model.Command.LendingBookCommand;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by maciek on 02.04.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Rollback(false)
public class BookManagementProcessTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookManagementProcess bookManagementProcess;

    @Autowired
    private BookLendingProcess bookLendingProcess;

    @Test
    @Transactional
    public void shouldCreateBook(){
        BookNumber bookNumber = createBook();
        assertThat(bookRepository.get(bookNumber).getTitle()).isEqualTo("Java");
        assertThat(bookRepository.get(bookNumber).getYear()).isEqualTo(2009);
        assertThat(bookRepository.get(bookNumber).getAuthor()).isEqualTo("Horstmann Cornell");
    }

    @Test
    @Transactional
    public void shouldChangeBook(){
        BookNumber bookNumber = createBook();

        changeBook(bookNumber);

        assertThat(bookRepository.get(bookNumber).getTitle()).isEqualTo("Java 2");
        assertThat(bookRepository.get(bookNumber).getYear()).isEqualTo(2012);
        assertThat(bookRepository.get(bookNumber).getAuthor()).isEqualTo("Horsti Cornell");
    }

    @Test(expected = BookNotFoundException.class)
    public void shouldDeleteBook(){
        BookNumber bookNumber = createBook();

        bookManagementProcess.remove(bookNumber);

        bookRepository.get(bookNumber);
    }

    @Test
    @Transactional
    public void shouldListAllBooks() {
        createBook();
        createBook();

        BookList bookList = bookManagementProcess.listAllBooks();

        assertThat(bookList.getBooks().size()).isEqualTo(2);
        assertThat(bookList.getBooks().get(0).getTitle()).isEqualTo("Java");
        assertThat(bookList.getBooks().get(1).getTitle()).isEqualTo("Java");
        assertThat(bookList.getAvailableBookCount()).isEqualTo(2);
        assertThat(bookList.getLentBookCount()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void shouldShowAvailableBookInfo() {
        BookNumber number = createBook();

        BookInfo bookInfo = bookManagementProcess.show(number);

        assertThat(bookInfo.getBook().getNumber()).isEqualTo(number);
        assertThat(bookInfo.getBook().isAvailable()).isEqualTo(true);
    }

    @Test
    @Transactional
    public void shouldShouldShowLentBookInfo() {
        BookNumber number = createBook();
        LendingBookCommand cmd = new LendingBookCommand();
        cmd.setFirstName("Tolek");
        cmd.setLastName("Banan");
        cmd.setNumber(number.getNumber());
        bookLendingProcess.lend(cmd);

        BookInfo bookInfo = bookManagementProcess.show(number);

        assertThat(bookInfo.getBook().getNumber()).isEqualTo(number);
        assertThat(bookInfo.getBook().isAvailable()).isEqualTo(false);
    }

    @Test
    @Transactional
    public void shouldSearchByTitle() {
        createBook();
        BookNumber bookNumber = createBook();
        changeBook(bookNumber);
        BookQuery query = new BookQuery();
        query.setTitle("Java 2");

        List<Book> books = bookManagementProcess.search(query);

        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getTitle()).contains("Java 2");
    }

    @Test
    @Transactional
    public void shouldSearchByAuthor() {
        createBook();
        createBook();
        BookQuery query = new BookQuery();
        query.setAuthor("Horstmann Cornell");

        List<Book> books = bookManagementProcess.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getAuthor()).contains("Horstmann Cornell");
        assertThat(books.get(1).getAuthor()).contains("Horstmann Cornell");
    }

    @Test
    @Transactional
    public void shouldSearchByYear() {
        createBook();
        createBook();
        BookQuery query = new BookQuery();
        query.setYear(2009);

        List<Book> books = bookManagementProcess.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getYear()).isEqualTo(2009);
    }

    @Test
    @Transactional
    public void shouldSearchByAllCriteria() {
        createBook();
        createBook();
        BookNumber bookNumber = createBook();
        changeBook(bookNumber);
        BookQuery query = new BookQuery();
        query.setTitle("Java");
        query.setAuthor("Horstmann Cornell");
        query.setYear(2009);

        List<Book> books = bookManagementProcess.search(query);

        assertThat(books.size()).isEqualTo(2);
        assertThat(books.get(0).getTitle()).contains("Java");
        assertThat(books.get(0).getAuthor()).contains("Horstmann Cornell");
        assertThat(books.get(0).getYear()).isEqualTo(2009);
    }

    private BookNumber createBook() {
        CreateBookCommand cmd = new CreateBookCommand();
        cmd.setTitle("Java");
        cmd.setAuthor("Horstmann Cornell");
        cmd.setYear(2009);
        return bookManagementProcess.create(cmd);
    }

    private void changeBook(BookNumber bookNumber) {
        ChangeBookCommand cmd = new ChangeBookCommand();
        cmd.setTitle("Java 2");
        cmd.setAuthor("Horsti Cornell");
        cmd.setYear(2012);
        bookManagementProcess.change(cmd, bookNumber);
    }

}
