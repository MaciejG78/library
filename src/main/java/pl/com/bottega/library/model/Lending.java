package pl.com.bottega.library.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by maciek on 01.04.2017.
 */
@Entity
public class Lending {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDateTime lendDate;
    private LocalDateTime returnDate;

    Lending(){}

    public Lending(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void lending(){
        if (isLended()){
            throw new BookLendingException("Book isn't available at this moment");
        } else {
            lendDate = LocalDateTime.now();
        }
    }

    public void giveBack(){
        returnDate = LocalDateTime.now();
    }

    public boolean isLended() {
        return getReturnDate() != null;
    }

    public Long getLendingId() {
        return id;
    }

    public void setLendingId(Long id) {
        this.id = id;
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

    public LocalDateTime getLendDate() {
        return lendDate;
    }

    public void setLendDate(LocalDateTime lendDate) {
        this.lendDate = lendDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
