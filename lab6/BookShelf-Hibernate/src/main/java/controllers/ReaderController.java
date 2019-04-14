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
import java.util.Calendar;
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

    public static List<Author> getAllReadersWhoBorrowedAuthorsBookAtSpecificTime(long idAuthor, Date borrowingDate, Date returningDate){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reader> query = cb.createQuery(Reader.class);
//        Root<Borrowing> borrowingRoot = query.from(Borrowing.class);
//        Root<Book> bookRoot = query.from(Book.class);
//        Root<Author> authorRoot = query.from(Author.class);
        Root<Reader> readerRoot = query.from(Reader.class);
//        Root<Borrowing> borrowingRoot = query.from(Borrowing.class);
//        Join<Reader,Borrowing> borrowingJoin = readerRoot.join("idReader");
//        Join<Borrowing, Book> borrowingBookJoin = borrowingJoin.join("books");
//        Join<Book, Author> bookAuthorJoin = borrowingBookJoin.join("author");
//        List<Predicate> conditions = new ArrayList<>();
//        conditions.add(cb.equal(bookAuthorJoin.get("idauthor"), idAuthor));

//        TypedQuery<Reader> readerTypedQuery = entityManager.createQuery(query
//                .select(readerRoot));
//                .where(conditions.toArray(new Predicate[]{})));

        CriteriaQuery<Author> criteriaQuery = cb.createQuery(Author.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Join<Book,Reader> bookReaderJoin = bookRoot.join("readers");
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(cb.lessThanOrEqualTo(bookReaderJoin.get("borrowingDate"),new Date(1999, Calendar.AUGUST,1)));
        TypedQuery<Author> authorTypedQuery = entityManager.createQuery(criteriaQuery
                    .select(bookRoot.get("author")).where(conditions.toArray(new Predicate[]{})));

//        Join<Author, Book> authorBookJoin = authorRoot.join("idAuthor");

//        return readerTypedQuery.getResultList();

        return authorTypedQuery.getResultList();

    }
}
