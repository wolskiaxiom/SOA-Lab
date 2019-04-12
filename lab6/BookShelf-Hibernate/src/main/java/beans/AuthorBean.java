package beans;

import controllers.AuthorController;
import entities.Author;
import entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "authorBean")
@ApplicationScoped
public class AuthorBean {
    private Author authorEntity = new Author();
//    private Author updatedAuthor = new Author();
    private long idUpdatedAuthor ;
    private String updatedFirstName;
    private String updatedListName;
    private List<Book> updatedBooks;

    public AuthorBean() {
    }


    public String saveAuthor(){
        AuthorController.addAuthor(authorEntity);
        return "index";
    }

    public List<Author> getAuthors(){
        return AuthorController.readAllAuthors();
    }

    public String deleteAuthor(long idAuthor){
        AuthorController.deleteAuthor(idAuthor);
        return "index.xhtml";
    }

    public Author getAuthorEntity() {
        return authorEntity;
    }



    public String goToUpdateAuthor(Author author){
//        setIdUpdatedAuthor(author.getIdAuthor());
//        updatedAuthor.setFirstName(author.getFirstName());
//        updatedAuthor.setLastName(author.getLastName());
//        updatedAuthor.setIdAuthor(author.getIdAuthor());
//        updatedAuthor.setBooks(author.getBooks());

        idUpdatedAuthor = author.getIdAuthor();
        updatedFirstName = author.getFirstName();
        updatedListName = author.getLastName();
        updatedBooks = author.getBooks();

        return "update_author";
    }
    public String updateAuthor(){
        System.out.println(idUpdatedAuthor+"fsdaasdf");
//        System.out.println("updated " + updatedAuthor.getIdAuthor());
//        System.out.println("sad" + updatedAuthor.getFirstName());

        AuthorController.updateAuthor(new Author(updatedFirstName, updatedListName, updatedBooks), idUpdatedAuthor);
        return "index";
    }

    public void setAuthorEntity(Author authorEntity) {
        this.authorEntity = authorEntity;
    }

//    public Author getUpdatedAuthor() {
//        return updatedAuthor;
//    }
//
//    public void setUpdatedAuthor(Author updatedAuthor) {
//        this.updatedAuthor = updatedAuthor;
//    }

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
