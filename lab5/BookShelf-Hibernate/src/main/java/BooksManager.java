import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Vector;

@ApplicationScoped
@ManagedBean
public class BooksManager {

    DatabaseService databaseServiceInstance = DatabaseService.getInstance();

    public Vector<Book> books = new Vector<>();
    public BooksManager(){
    }

    public void setBooks(Vector<Book> books) {
        this.books = books;
    }

    public String addBook(Book book){
         databaseServiceInstance.addBook(book);
         return "success";
    }

    public ArrayList<Book> getBooks() {
        return databaseServiceInstance.readBooks();
    }


}
