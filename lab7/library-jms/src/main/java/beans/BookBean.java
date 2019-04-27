package beans;

import controllers.AuthorController;
import controllers.BookController;
import datasources.DatabaseService;
import entities.Author;
import entities.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "bookBean",eager = true)
@ApplicationScoped
public class BookBean {
    Book bookEntity = new Book(new Author());
    Book editedBook= new Book();

    private List<SelectItem> availableAuthors;

    @PostConstruct
    public void init() {
        availableAuthors = new ArrayList<>();
        for(Author author: AuthorController.readAllAuthors()){
            availableAuthors.add(new SelectItem(author.getIdAuthor(), author.getLastName()));
        }
    }

    public String saveBook(){
        Author author = DatabaseService.getInstance().getEntityManager().find(Author.class, bookEntity.getAuthor().getIdAuthor());

        Book book = new Book(bookEntity.getTitle(), author);
        System.out.println(author.getLastName());
        BookController.addBook(bookEntity);
        return "index?faces-reditect=true";
    }


    public List<Book> getAllBooks(){
        return BookController.readAllBooks();
    }

    public String deleteBook(Book book){
        BookController.deleteBook(book.getIdBook());
        return "/index";
    }

    public String goToUpdateBook(Book book){
        editedBook = BookController.getBookById(book.getIdBook());
        return "/update_book?faces-reditect=true";
    }

    public String updateBook(){
        return  "/update_book?faces-reditect=true";
    }


    public Book getBookEntity() {
        return bookEntity;
    }

    public BookBean() {
    }


    public void setBookEntity(Book bookEntity) {
        this.bookEntity = bookEntity;
    }

    public List<SelectItem> getAvailableAuthors() {
        return availableAuthors;
    }

    public void setAvailableAuthors(List<SelectItem> availableAuthors) {
        this.availableAuthors = availableAuthors;
    }

    public Book getEditedBook() {
        return editedBook;
    }

    public void setEditedBook(Book editedBook) {
        this.editedBook = editedBook;
    }
}
