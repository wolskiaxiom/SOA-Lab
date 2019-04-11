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


    public static List<Book> readAllAuthorsBooks(Author author){
        return  (List<Book>) entityManager.createQuery("SELECT b from Author a, Book b where b.author=a").getResultList();

    }

    public static void deleteAuthor(long idauthor){
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, idauthor);
        entityManager.remove(author);
        entityManager.getTransaction().commit();
    }

    public static void updateBook(Author author){
        Author edited = entityManager.find(Author.class, author.getIdAuthor());
        edited.setFirstName(author.getFirstName());
        edited.setLastName(author.getLastName());
        edited.setBooks(author.getBooks());
    }
}
