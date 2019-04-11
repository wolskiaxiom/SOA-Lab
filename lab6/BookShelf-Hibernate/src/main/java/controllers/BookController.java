package controllers;


import datasources.DatabaseService;
import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookController {

    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addBook(Book book){
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public static List<Book> readAllBooks(){
        TypedQuery<Book> queryBooks = (TypedQuery<Book>) entityManager.createNamedQuery("getAllBooks", Book.class);
        return queryBooks.getResultList();
    }


    public static List<Object> readAllBooksWithAuthors(){
        TypedQuery<Object> queryBooks = (TypedQuery<Object>) entityManager.createNamedQuery("getAllBooksWithAuthors");
        return queryBooks.getResultList();
    }

    public static void deleteBook(long idbook){
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, idbook);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
    }

    public static void updateBook(Book book){
        entityManager.getTransaction().begin();
        Book edited = entityManager.find(Book.class, book.getIdBook());
        edited.setAuthor(book.getAuthor());
        edited.setReaders(book.getReaders());
        edited.setTitle(book.getTitle());
        entityManager.getTransaction().commit();
    }
}
