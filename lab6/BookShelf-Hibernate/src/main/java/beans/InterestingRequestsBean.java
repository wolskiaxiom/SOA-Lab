package beans;

import controllers.AuthorController;
import controllers.BookController;
import controllers.ReaderController;
import entities.Author;
import entities.Book;
import entities.Reader;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "interestingBean", eager = true)
@RequestScoped
public class InterestingRequestsBean {

    private List<Reader> readers;
    private List<Author> authors;
    private List<Reader> readersWhoReadExactBook;
    private List<SelectItem> availableAuthors;
    private List<SelectItem> availableBooks;
    private List<SelectItem> availableReaders;
    private long idAuthor;
    private long idBook;
    private long idReader;
    private Date startTime;
    private Date endTime;
    private String mostPopularAuthor ="";

    @PostConstruct
    public void init() {
        readers = ReaderController.readAllReaders();
        readersWhoReadExactBook = ReaderController.readAllReaders();//to be clone from line above
        authors = AuthorController.readAllAuthors();
        availableAuthors = new ArrayList<>();
        for(Author author: authors){
            availableAuthors.add(
                    new SelectItem(author.getIdAuthor(), author.getLastName()+" " + author.getFirstName()));
        }
        availableBooks = new ArrayList<>();
        for(Book book: BookController.readAllBooks()){
            availableBooks.add(new SelectItem(book.getIdBook(), book.getTitle()));
        }
        availableReaders = new ArrayList<>();
        for(Reader reader: readers){
            availableReaders.add(
                    new SelectItem(reader.getIdReader(), reader.getFirstName() + " " + reader.getLastName()));
        }
    }

    public String allReadersWhoReadAuthorsBookAtSpecificTime(){
        readers = ReaderController.getAllReadersWhoReadAuthorsBookAtSpecificTime(idAuthor, startTime,endTime);
        return "success";
    }

    public void allAuthorsWhoseBookWasBorrowedAtSpecificTime(){
        authors = AuthorController.getAllAuthorsWhoseBookWasBorrowedAtSpecificTime(startTime, endTime);
    }

    public void readersWhoReadExactBook(){
        readersWhoReadExactBook = ReaderController.getAllReadersWhoReadExactBook(idBook);
    }
    public void getMostPopularAuthorByReader(){
        mostPopularAuthor = AuthorController.getMostPopularAuthorByReader(idReader);
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public void setReaders(List<Reader> readers) {
        this.readers = readers;
    }

    public List<SelectItem> getAvailableAuthors() {
        return availableAuthors;
    }

    public void setAvailableAuthors(List<SelectItem> availableAuthors) {
        this.availableAuthors = availableAuthors;
    }

    public long getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getMostPopularAuthor() {
        return mostPopularAuthor;
    }

    public void setMostPopularAuthor(String mostPopularAuthor) {
        this.mostPopularAuthor = mostPopularAuthor;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<SelectItem> getAvailableBooks() {
        return availableBooks;
    }

    public void setAvailableBooks(List<SelectItem> availableBooks) {
        this.availableBooks = availableBooks;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public List<Reader> getReadersWhoReadExactBook() {
        return readersWhoReadExactBook;
    }

    public void setReadersWhoReadExactBook(List<Reader> readersWhoReadExactBook) {
        this.readersWhoReadExactBook = readersWhoReadExactBook;
    }

    public List<SelectItem> getAvailableReaders() {
        return availableReaders;
    }

    public void setAvailableReaders(List<SelectItem> availableReaders) {
        this.availableReaders = availableReaders;
    }

    public long getIdReader() {
        return idReader;
    }

    public void setIdReader(long idReader) {
        this.idReader = idReader;
    }
}
