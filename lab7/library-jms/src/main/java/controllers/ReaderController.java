package controllers;

import datasources.DatabaseService;
import entities.Author;
import entities.Book;
import entities.Borrowing;
import entities.Reader;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReaderController {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addReader(Reader reader){
        entityManager.getTransaction().begin();
        entityManager.persist(reader);
        entityManager.getTransaction().commit();
    }

    public static Reader getReaderById(long idreader){
        return entityManager.find(Reader.class, idreader);
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

    public static List<Reader> getAllReadersWhoReadAuthorsBookAtSpecificTime(long idAuthor, Date borrowingDate, Date returningDate){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reader> query = cb.createQuery(Reader.class);
        Root<Reader> readerRoot = query.from(Reader.class);
        Join<Reader,Book> readerBookJoin = readerRoot.join("books");
        Root<Book> bookRoot = query.from(Book.class);
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(cb.greaterThanOrEqualTo(readerBookJoin.get("borrowingDate"),borrowingDate));
        conditions.add(cb.lessThanOrEqualTo(readerBookJoin.get("returningDate"),returningDate));
        conditions.add(cb.equal(bookRoot.get("author").get("idAuthor"), idAuthor));
        TypedQuery<Reader> readerTypedQuery = entityManager.createQuery(query
                    .select(readerRoot)
                    .where(conditions.toArray(new Predicate[]{}))
                    .distinct(true)
        );
        return readerTypedQuery.getResultList();
    }

    public static List<Reader> getAllReadersWhoReadExactBook(long idBook){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reader> query = criteriaBuilder.createQuery(Reader.class);
        Root<Borrowing> borrowingRoot = query.from(Borrowing.class);
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(criteriaBuilder.equal(borrowingRoot.get("book").get("idBook"),idBook));
        TypedQuery<Reader> readerTypedQuery = entityManager.createQuery(query
                .select(borrowingRoot.get("reader"))
                .where(conditions.toArray(new Predicate[]{}))
                .distinct(true)
        );
        return readerTypedQuery.getResultList();
    }


}
