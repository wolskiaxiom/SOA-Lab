package app;

import controllers.AuthorController;
import controllers.BorrowingController;
import controllers.ReaderController;
import datasources.DatabaseService;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab6_jpa");
//        EntityManager em = entityManagerFactory.createEntityManager();
        EntityManager em = DatabaseService.getInstance().getEntityManager();

        try{
//            em.getTransaction().begin();
//
//
//            Author mickiewicz= new Author("Adam", "Mickiewicz");
//            em.persist(mickiewicz);
//
//            Book potop = new Book("Dziady");
//            Book  panTadeusz= new Book("Pan Tadeusz");
//            potop.setAuthor(mickiewicz);
//            panTadeusz.setAuthor(mickiewicz);
//            mickiewicz.getBooks().add(potop);
//            mickiewicz.getBooks().add(panTadeusz);
//
//
//
//            Reader lukasz = new Reader("Lukasz", "Bojler");
//            Reader bojler = new Reader("Bojler", "Lukasz");
//
//
//            Borrowing lukaszPotop = new Borrowing(lukasz, potop);
//            lukaszPotop.setBorrowingDate(new Date());
//            lukaszPotop.setReturningDate(new Date());
////            lukaszPotop.
////            lukasz.getBooks().add(lukaszPotop);
//
//
//
//            em.persist(potop);
//            em.persist(panTadeusz);
//            em.persist(lukasz);
//            em.persist(lukaszPotop);
//            em.getTransaction().commit();
//
//            mickiewicz.setLastName("niemickiewicz");
//
//            AuthorController.updateAuthor(mickiewicz);
//
//            System.out.println(AuthorController.readAllAuthorBooks(mickiewicz));
//            System.out.println(ReaderController.readAllBorrowedBooksByReader(lukasz));
//
////            lukaszPotop.setBook(panTadeusz);
//            lukaszPotop.setReturningDate(new Date(2001, Calendar.AUGUST, 22));
//            BorrowingController.updateBorrowing(lukaszPotop);
//            System.out.println(ReaderController.readAllBorrowedBooksByReader(lukasz));
//            TypedQuery<Object []> query = (TypedQuery<Object[]>)
//                    em.createNamedQuery("getAllAuthorsFirstAndLastNamesWithTheirBooks");
//
//            List<Object []> result = query.getResultList();
//
//            for(Object[] o : result){
//                System.out.println("Imię autora: "+o[0]);
//                System.out.println("Nazwisko autora: "+o[1]);
//                System.out.println("Tytuł książki "+o[2]);
//            }
;

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
