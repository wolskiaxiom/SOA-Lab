import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class DatabaseService {
    private static final DatabaseService ourInstance = new DatabaseService();
    private EntityManager entityManager;
    public static DatabaseService getInstance() {
        return ourInstance;
    }

    private DatabaseService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-BookShelf");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void addBook(Book book){
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public ArrayList<Book> readBooks(){
        return (ArrayList<Book>) entityManager.createQuery("Select t from " + Book.class.getSimpleName()+" t").getResultList();
    }

    public void delete(long idbook){
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, idbook);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

//    public void updateBook(Book book){
//        Book edited = entityManager.find(Book.class, book.getIdBook());
//
//        entityManager.getTransaction().begin();
//        entityManager.getTransaction().commit();
//    }
}
