package entities;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ReaderBookId implements Serializable {

    private Book book;
    private Reader reader;

    @ManyToOne
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o==null || getClass() != o.getClass())return false;

        ReaderBookId that = (ReaderBookId) o;

        if(reader != null? !reader.equals(that.reader) : that.reader !=null) return false;
        if(book != null? !book.equals(that.book): that.book != null) return  false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (reader != null ? reader.hashCode() :0);
        result = 31 * result + (book!=null ? book.hashCode() :0);
        return result;
    }

}
