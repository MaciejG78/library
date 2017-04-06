package pl.com.bottega.library.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import pl.com.bottega.library.model.Command.ChangeBookCommand;
import pl.com.bottega.library.model.Command.CreateBookCommand;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciek on 01.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    @Test
    public void shouldBeAddedAfterCreate(){
        Book book = given().createBook();
        assertEquals(book.getAuthor(),"John Connel");
        assertEquals(book.getTitle(),"Life after life");
        assertEquals(book.getYear(),1980);
    }

    @Test
    public void shouldBeChangedAfterChange(){
        Book book = given().createBook();
        ChangeBookCommand changeBookCommand = new ChangeBookCommand();
        changeBookCommand.setTitle("Life after life");
        changeBookCommand.setAuthor("John Connel");
        changeBookCommand.setYear(1981);
        book.change(changeBookCommand);
        assertEquals(book.getAuthor(),"John Connel");
        assertEquals(book.getTitle(),"Life after life");
        assertEquals(book.getYear(),1981);
    }

    private BookAssembler given() {
        return new BookAssembler();
    }

    class BookAssembler {

        Book createBook() {
            CreateBookCommand cmd = new CreateBookCommand();
            NumberGenerator numberGenerator = new NumberGenerator();
            cmd.setTitle("Life after life");
            cmd.setAuthor("John Connel");
            cmd.setYear(1980);
            return new Book(cmd, numberGenerator.generate());
        }

    }

}
