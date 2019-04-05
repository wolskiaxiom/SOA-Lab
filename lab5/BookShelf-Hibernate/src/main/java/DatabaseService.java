import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Vector;

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
}
