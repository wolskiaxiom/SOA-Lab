package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReaderBookId implements Serializable {


    @Column(name = "idreader")
    private long idReader;

    @Column(name = "idbook")
    private long idBook;

    public ReaderBookId(){}

    public ReaderBookId(long idReader, long idBook) {
        this.idReader = idReader;
        this.idBook = idBook;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass())return false;

        ReaderBookId that = (ReaderBookId) o;

        return Objects.equals(idReader, that.idReader) && Objects.equals(idBook, that.idBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReader, idBook);
    }

    public long getIdReader() {
        return idReader;
    }

    public void setIdReader(long idAuthor) {
        this.idReader = idAuthor;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }
}
