package datasources;

import controllers.BookController;
import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private static final DatabaseService ourInstance = new DatabaseService();
    private final EntityManager entityManager;
    public static DatabaseService getInstance() {
        return ourInstance;
    }

    private DatabaseService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-BookShelf");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void addBook(Book book){
        BookController.addBook(book);
    }

    public List<Book> readBooks(){
        return BookController.readAllBooks();
    }

    public void delete(long idbook){
        BookController.deleteBook(idbook);
    }

    public void updateBook(Book book){
        BookController.updateBook(book);
    }

}
