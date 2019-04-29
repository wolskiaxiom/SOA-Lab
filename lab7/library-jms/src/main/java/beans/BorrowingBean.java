package beans;

import beans.jmsbeans.PublisherBean;
import controllers.BookController;
import controllers.BorrowingController;
import controllers.ReaderController;
import entities.Book;
import entities.Borrowing;
import entities.Reader;
import entities.ReaderBookId;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "borrowingBean")
@ApplicationScoped
public class BorrowingBean {

    @EJB(lookup = "java:module/PublisherBean")
    private PublisherBean publisherBean;

    private Borrowing borrowing;

    private long readerId;
    private long bookId;
    private Date borrowingDate;
    private Date returningDate;

    private List<Borrowing> borrowings;

    private List<SelectItem> availableBook;
    private List<SelectItem> availableReader;

    private Borrowing updatedBorrowing;

    @PostConstruct
    public void init(){
        availableBook = new ArrayList<>();
        availableReader = new ArrayList<>();
        borrowings = new ArrayList<>(BorrowingController.readAllBorrowings());
        for(Reader reader: ReaderController.readAllReaders()){
            availableReader.add(new SelectItem(reader.getIdReader(), reader.getLastName()+" "+reader.getFirstName()));
        }

        for (Book book : BookController.readAllBooks()){
            availableBook.add(new SelectItem(book.getIdBook(), book.getTitle()+ " of " + book.getAuthor().getLastName()));
        }
    }


    public String addBorrowing(){
        Reader reader = ReaderController.getReaderById(readerId);
        Book book = BookController.getBookById(bookId);
        Borrowing  borrowing = new Borrowing(reader,book);
        borrowing.setBorrowingDate(borrowingDate);
        borrowing.setReturningDate(returningDate);
        BorrowingController.addBorrowing(borrowing);
        return "success";
    }

    public String deleteBorrowing(long idReader, long idBook){
        BorrowingController.deleteBorrowing(new ReaderBookId(idReader, idBook));
        return "success";
    }

    public String editBorrowing(Borrowing borrowing, Reader reader, Book book){
        System.out.println(borrowing.getReader().getIdReader());
        System.out.println(borrowing.getBook().getIdBook());
        System.out.println(borrowing.getBorrowingDate());
        System.out.println(borrowing.getReturningDate());
        updatedBorrowing = new Borrowing(reader, book);
        updatedBorrowing.setBorrowingDate(borrowing.getBorrowingDate());
        updatedBorrowing.setReturningDate(borrowing.getReturningDate());
        System.out.println(bookId);
//        publisherBean.publishNews("book_available", bookId, " is now available for you!");
        return "success";
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

    public List<Borrowing> getBorrowings() {
        borrowings = BorrowingController.readAllBorrowings();
        return borrowings;
    }

    public String updateBorrowing(long bookId){
        System.out.println(bookId);
        publisherBean.publishNews("book_available", bookId, " is now available for you!");
        return "success";
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    public void setReturningDate(Date returningDate) {
        this.returningDate = returningDate;
    }

    public Borrowing getUpdatedBorrowing() {
        return updatedBorrowing;
    }

    public void setUpdatedBorrowing(Borrowing updatedBorrowing) {
        this.updatedBorrowing = updatedBorrowing;
    }
}
