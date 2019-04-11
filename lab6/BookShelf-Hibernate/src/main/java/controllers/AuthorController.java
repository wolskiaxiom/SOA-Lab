package controllers;

import datasources.DatabaseService;
import entities.Author;
import entities.Book;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
        entityManager.getTransaction().begin();
        Author edited = entityManager.find(Author.class, author.getIdAuthor());
        edited.setFirstName(author.getFirstName());
        edited.setLastName(author.getLastName());
        edited.setBooks(author.getBooks());
        entityManager.getTransaction().commit();
    }
}
