package pl.com.bottega.library.model.Command;

/**
 * Created by maciek on 06.04.2017.
 */
public class LendingBookCommand {
    private String number;
    private String firstName;
    private String lastName;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
