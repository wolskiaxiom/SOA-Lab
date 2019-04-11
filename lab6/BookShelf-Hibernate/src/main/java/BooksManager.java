import datasources.DatabaseService;
import entities.Book;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
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

    public List<Book> getBooks() {
        return databaseServiceInstance.readBooks();
    }

    public void deleteBookWithId(long idbook){
        databaseServiceInstance.delete(idbook);
    }
    public void updateBook(Book book){
//        databaseServiceInstance.updateBook(book);

    }

}
