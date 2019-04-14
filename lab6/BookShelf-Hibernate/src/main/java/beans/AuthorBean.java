package beans;

import controllers.AuthorController;
import entities.Author;
import entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "authorBean",eager = true)
@ApplicationScoped
public class AuthorBean {
    private Author authorEntity = new Author();
    private long idUpdatedAuthor ;
    private String updatedFirstName;
    private String updatedListName;
    private List<Book> updatedBooks;
    private List<Author> authors = AuthorController.readAllAuthors();

    public AuthorBean() {
    }


    public String saveAuthor(){
        AuthorController.addAuthor(authorEntity);
        return "index?faces-reditect=true";
    }

    public List<Author> getAuthors(){
        authors= AuthorController.readAllAuthors();
        return authors;
    }

    public String deleteAuthor(long idAuthor){
        AuthorController.deleteAuthor(idAuthor);
        return "index.xhtml";
    }

    public Author getAuthorEntity() {
        return authorEntity;
    }



    public String goToUpdateAuthor(Author author){
        idUpdatedAuthor = author.getIdAuthor();
        updatedFirstName = author.getFirstName();
        updatedListName = author.getLastName();
        updatedBooks = author.getBooks();

        return "update_author";
    }
    public String updateAuthor(){
        AuthorController.updateAuthor(new Author(updatedFirstName, updatedListName, updatedBooks), idUpdatedAuthor);
        return "index";
    }

    public void setAuthorEntity(Author authorEntity) {
        this.authorEntity = authorEntity;
    }

    public long getIdUpdatedAuthor() {
        return idUpdatedAuthor;
    }

    public void setIdUpdatedAuthor(long idUpdatedAuthor) {
        this.idUpdatedAuthor = idUpdatedAuthor;
    }

    public void setIdUpdatedAuthor(Long idUpdatedAuthor) {
        this.idUpdatedAuthor = idUpdatedAuthor;
    }

    public String getUpdatedFirstName() {
        return updatedFirstName;
    }

    public void setUpdatedFirstName(String updatedFirstName) {
        this.updatedFirstName = updatedFirstName;
    }

    public String getUpdatedListName() {
        return updatedListName;
    }

    public void setUpdatedListName(String updatedListName) {
        this.updatedListName = updatedListName;
    }

    public List<Book> getUpdatedBooks() {
        return updatedBooks;
    }

    public void setUpdatedBooks(List<Book> updatedBooks) {
        this.updatedBooks = updatedBooks;
    }
}
