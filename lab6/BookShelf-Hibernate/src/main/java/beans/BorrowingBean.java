package beans;

import controllers.BookController;
import controllers.BorrowingController;
import controllers.ReaderController;
import entities.Book;
import entities.Borrowing;
import entities.Reader;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "borrowingBean",eager = true)
@ApplicationScoped
public class BorrowingBean {

    private Borrowing borrowing;

    private long readerId;
    private long bookId;
    private Date borrowingDate;
    private Date returningDate;

    private List<SelectItem> availableBook;
    private List<SelectItem> availableReader;

    @PostConstruct
    public void init(){
        availableBook = new ArrayList<>();
        availableReader = new ArrayList<>();

        for(Reader reader: ReaderController.readAllReaders()){
            availableReader.add(new SelectItem(reader.getIdReader(), reader.getLastName()+" "+reader.getFirstName()));
        }

        for (Book book : BookController.readAllBooks()){
            availableBook.add(new SelectItem(book.getIdBook(), book.getTitle()+ " of " + book.getAuthor().getLastName()));
        }
    }


    public void addBorrowing(){
        Reader reader = ReaderController.getReaderById(readerId);
        Book book = BookController.getBookById(bookId);
        Borrowing  borrowing = new Borrowing(reader,book);
        borrowing.setBorrowingDate(borrowingDate);
        borrowing.setReturningDate(returningDate);
        BorrowingController.addBorrowing(borrowing);
    }

    public List<SelectItem> getAvailableBook() {
        return availableBook;
    }

    public void setAvailableBook(List<SelectItem> availableBook) {
        this.availableBook = availableBook;
    }

    public List<SelectItem> getAvailableReader() {
        return availableReader;
    }

    public void setAvailableReader(List<SelectItem> availableReader) {
        this.availableReader = availableReader;
    }

    public Borrowing getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Borrowing borrowing) {
        this.borrowing = borrowing;
    }

    public long getReaderId() {
        return readerId;
    }

    public void setReaderId(long readerId) {
        this.readerId = readerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(Date borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public Date getReturningDate() {
        return returningDate;
    }

    public void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }
}
