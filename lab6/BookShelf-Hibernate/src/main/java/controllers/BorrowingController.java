package controllers;

import datasources.DatabaseService;
import entities.Book;
import entities.Borrowing;
import entities.Reader;
import entities.ReaderBookId;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class BorrowingController {

    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addBorrowing(Borrowing borrowing){
        entityManager.getTransaction().begin();
        entityManager.persist(borrowing);
        entityManager.getTransaction().commit();
    }

    public static void addBorrowing(Reader reader, Book book, Date borrowingDate, Date returningDate){
        Borrowing newBorrowing = new Borrowing(reader,book);
        newBorrowing.setBorrowingDate(borrowingDate);
        newBorrowing.setReturningDate(returningDate);
        entityManager.getTransaction().begin();
        entityManager.persist(returningDate);
        entityManager.getTransaction().commit();
    }

    public static List<Borrowing> readAllBorrowings(){
        TypedQuery<Borrowing> queryBooks = (TypedQuery<Borrowing>) entityManager.createQuery("select borrowing from Borrowing borrowing", Borrowing.class);
        return queryBooks.getResultList();
    }


//    public static List<Book> readAllBorrowingsOfConcreteBook(Book book){
//        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Book b, Borrowing bor where bor.reader.idReader = :idReader and bor.book.idBook = b.idBook", Book.class);
//        return query.setParameter("idReader", reader.getIdReader()).getResultList();
//    }

    public static void deleteBorrowing(ReaderBookId id){
        entityManager.getTransaction().begin();
        Borrowing borrowing1 = entityManager.find(Borrowing.class, id);
        entityManager.remove(borrowing1);
        entityManager.getTransaction().commit();
    }

    public static void updateBorrowing(Borrowing borrowing){
        entityManager.getTransaction().begin();
        Borrowing edited = entityManager.find(Borrowing.class, borrowing.getId());
        edited.setBorrowingDate(borrowing.getBorrowingDate());
        edited.setReturningDate(borrowing.getReturningDate());
        entityManager.getTransaction().commit();
    }

}
