package entities;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//@RequestScoped
//@ManagedBean
@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "getAllBooks",
                query = "Select b from Book b"),
        @NamedQuery(name = "getAllBooksWithAuthors",
        query = "SELECT b, a.firstName, a.lastName from Book b, Author a")
})
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbook")
    private long idBook;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "idauthor", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Borrowing> readers = new ArrayList<>();



    public Book(){}

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, Author author, List<Borrowing> borrowings) {
        this.title = title;
        this.author = author;
        this.readers = borrowings;
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Borrowing> getReaders() {
        return readers;
    }

    public void setReaders(List<Borrowing> readers) {
        this.readers = readers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o ;
        return Objects.equals(book.title, title);
    }
}