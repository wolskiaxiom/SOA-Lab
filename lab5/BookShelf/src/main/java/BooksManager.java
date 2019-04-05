import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Arrays;
import java.util.Vector;

@ApplicationScoped
@ManagedBean
public class BooksManager {

    DatabaseService databaseServiceInstance = DatabaseService.getInstance();

    public Vector<Book> books = new Vector<>();
    public BooksManager(){
        books.add(new Book("Ania z zielonego wwzgórza", "Adam", "Wiśniewski", "123-123-43-41-123", 1999, "Bajka", 44,"PLN")
        );
        books.add(new Book(123231,"asdf","asfd","sadfagasd", "asfdasdfasfd", 123, "Nauka", 123.3, 13, "USD", true));
    }

    public void setBooks(Vector<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        books.add(book);
        databaseServiceInstance.addBook(book);
    }

    public Vector<Book> getBooks() {
        return books;
    }

}
