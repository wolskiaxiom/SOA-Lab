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

public class AuthorController {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addAuthor(Author author){
        entityManager.getTransaction().begin();
        entityManager.persist(author);
        entityManager.getTransaction().commit();
    }

    public static List<Author> readAllAuthors(){
        TypedQuery<Author> queryBooks = (TypedQuery<Author>) entityManager.createNamedQuery("getAllAuthors", Author.class);
        return queryBooks.getResultList();
    }


    public static List<Book> readAllAuthorBooks(Author author){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b from Author a, Book b where b member of a.books and a.idAuthor = :idAuthor", Book.class);

        return query.setParameter("idAuthor", author.getIdAuthor()).getResultList();

    }

    public static void deleteAuthor(long idauthor){
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, idauthor);
        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

    public static void updateAuthor(Author author){
        System.out.println(author.getIdAuthor());
        System.out.println(author.getFirstName());
        System.out.println(author.getLastName());
        System.out.println(author.getBooks());
    }

    public static void updateAuthor(Author author, long id){
        System.out.println(author.getIdAuthor());
        System.out.println(id);
        System.out.println(author.getFirstName());
        System.out.println(author.getLastName());
        System.out.println(author.getBooks());
    }

    public static List<Author> getAllAuthorsWhoseBookWasBorrowedAtSpecificTime(Date startTime, Date endTime){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = cb.createQuery(Author.class);

        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Join<Book,Reader> bookReaderJoin = bookRoot.join("readers");
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(cb.greaterThanOrEqualTo(bookReaderJoin.get("borrowingDate"),startTime));
        conditions.add(cb.lessThanOrEqualTo(bookReaderJoin.get("borrowingDate"),endTime));
        TypedQuery<Author> authorTypedQuery = entityManager.createQuery(criteriaQuery
                .select(bookRoot.get("author"))
                .where(conditions.toArray(new Predicate[]{}))
                .distinct(true)
        );
        return authorTypedQuery.getResultList();
    }
    public static String getMostPopularAuthorByReader(long idReader){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Object []> criteriaQuery = builder.createQuery(Object[].class);

        Root<Borrowing> borrowingRoot = criteriaQuery.from(Borrowing.class);
        Join<Borrowing, Book> borrowingBookJoin = borrowingRoot.join("book");
        criteriaQuery.multiselect(builder.count(borrowingRoot),
                borrowingRoot.get("book").get("author").get("firstName"),
                borrowingRoot.get("book").get("author").get("lastName"));
        List<Predicate> conditions = new ArrayList<>();
        conditions.add(builder.equal(borrowingRoot.get("reader").get("idReader"),idReader));
        criteriaQuery.where(conditions.toArray(new Predicate[]{}));

        criteriaQuery.groupBy(borrowingRoot.get("book").get("author").get("firstName"),
                borrowingRoot.get("book").get("author").get("lastName"));
        criteriaQuery.orderBy(builder.desc(builder.count(borrowingRoot)));
        TypedQuery<Object []> objectTypedQuery = (TypedQuery<Object []>) entityManager.createQuery(criteriaQuery);
        Object[] result = objectTypedQuery.getSingleResult();

        return " "+ result[1]+ " " +result[2];
    }
}
