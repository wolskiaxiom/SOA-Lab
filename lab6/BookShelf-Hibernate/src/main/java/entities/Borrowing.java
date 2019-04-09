package entities;

import entities.Book;
import entities.Reader;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "borrowing")
@AssociationOverrides({
        @AssociationOverride(name = "pk.reader", joinColumns = @JoinColumn(name = "idreader")),
        @AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "idbook"))
})
public class Borrowing  implements Serializable {

    @EmbeddedId
    private ReaderBookId pk = new ReaderBookId();

    @Column(name = "borrowingdate")
    private Date borrowingDate;

    @Column(name = "returningdate")
    private Date returningDate;


    public ReaderBookId getPk(){
        return pk;
    }

    public void setPk(ReaderBookId pk){
        this.pk = pk;
    }

    @Transient
    public Reader getReader(){
        return getPk().getReader();
    }

    public void setReader(Reader reader){
        getPk().setReader(reader);
    }

    @Transient
    public Book getBook(){
        return getPk().getBook();
    }

    public void setBook(Book book){
        getPk().setBook(book);
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getRentingDate() {
        return returningDate;
    }

    public void setRentingDate(Date rentingDate) {
        this.returningDate = rentingDate;
    }


    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Borrowing that = (Borrowing) o;

        if (getPk() != null ? !getPk().equals(that.getPk())
                : that.getPk() != null)
            return false;

        return true;
    }

    public int hashCode() {
        return (getPk() != null ? getPk().hashCode() : 0);
    }

}
