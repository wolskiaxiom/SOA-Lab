package entities;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@RequestScoped
//@ManagedBean
@Entity
@Table(name = "book")
//@Embeddable
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

    @OneToMany(mappedBy = "pk.book")
    private List<Borrowing> borrowings = new ArrayList<>();



    public Book(){
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(String title, Author author, List<Borrowing> borrowings) {
        this.title = title;
        this.author = author;
        this.borrowings = borrowings;
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
}