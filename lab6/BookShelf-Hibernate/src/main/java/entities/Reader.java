package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "reader")
    private List<Borrowing> books = new ArrayList<>();


    public Reader() {}

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Reader(String firstName, String lastName, List<Borrowing> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
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

    public List<Borrowing> getBooks() {
        return books;
    }

    public void setBooks(List<Borrowing> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Reader post = (Reader) o;
        return Objects.equals(firstName, post.firstName) && Objects.equals(lastName, post.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName,lastName);
    }
}
