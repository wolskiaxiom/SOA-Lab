package entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "borrowing")
public class Borrowing  implements Serializable {

    @EmbeddedId
    private ReaderBookId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idReader")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idBook")
    private Book book;

    @Column(name = "borrowingdate")
    private Date borrowingDate;

    @Column(name = "returningdate")
    private Date returningDate;

    public Borrowing() {}

    public Borrowing(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.id = new ReaderBookId(reader.getIdReader(), book.getIdBook());
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Borrowing that = (Borrowing) o;

        return Objects.equals(book,that.book)&&Objects.equals(reader, that.reader);

    }

    public int hashCode() {
        return Objects.hash(reader,book);
    }

}
