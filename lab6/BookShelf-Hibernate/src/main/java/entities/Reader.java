package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reader")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreader")
    private long idReader;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;

    @OneToMany(mappedBy = "pk.reader")
    private List<Borrowing> borrowings = new ArrayList<>();


    public Reader() {
    }

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reader(String firstName, String lastName, List<Borrowing> borrowings) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowings = borrowings;
    }

    public long getIdReader() {
        return idReader;
    }

    public void setIdReader(long idReader) {
        this.idReader = idReader;
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

    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }
}
