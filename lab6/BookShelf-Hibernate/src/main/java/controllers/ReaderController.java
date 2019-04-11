package controllers;

import datasources.DatabaseService;
import entities.Author;
import entities.Book;
import entities.Reader;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReaderController {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addReader(Reader reader){
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        entityManager.getTransaction().commit();
    }

    public static List<Reader> readAllReaders(){
        TypedQuery<Reader> queryBooks = (TypedQuery<Reader>) entityManager.createQuery("select r from Reader r", Reader.class);
        return queryBooks.getResultList();
    }


    public static List<Book> readAllBorrowedBooksByReader(Reader reader){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Book b, Borrowing bor where bor.reader.idReader = :idReader and bor.book.idBook = b.idBook", Book.class);
        return query.setParameter("idReader", reader.getIdReader()).getResultList();
    }

    public static void deleteReader(long idreader){
        entityManager.getTransaction().begin();
        Reader reader = entityManager.find(Reader.class, idreader);
        entityManager.remove(reader);
        entityManager.getTransaction().commit();
    }

    public static void updateReader(Reader reader){
        entityManager.getTransaction().begin();
        Reader edited = entityManager.find(Reader.class, reader.getIdReader());
        edited.setFirstName(reader.getFirstName());
        edited.setLastName(reader.getLastName());
        edited.setBooks(reader.getBooks());
        entityManager.getTransaction().commit();
    }
}
