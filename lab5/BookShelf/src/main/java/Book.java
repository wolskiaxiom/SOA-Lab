import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@RequestScoped
@ManagedBean
@Entity

public class Book implements Serializable {

    @Id
    @GeneratedValue
    private long idBook;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String isbn;
    private Integer issueYear;
    private String type;
    private double price;
    private double originPrice;
    private String currency;
    private Boolean ifChecked;

    public Book(){}

    public Book(String title, String authorFirstName, String authorLastName, String isbn, Integer issueYear, String type, double originPrice, String currency) {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.isbn = isbn;
        this.issueYear = issueYear;
        this.type = type;
        this.originPrice = originPrice;
        this.currency = currency;
        this.ifChecked = false;

    }

    public Book(long idBook, String title, String authorFirstName, String authorLastName, String isbn, Integer issueYear, String type, double price, double originPrice, String currency, Boolean ifChecked) {
        this.idBook = idBook;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.isbn = isbn;
        this.issueYear = issueYear;
        this.type = type;
        this.price = price;
        this.originPrice = originPrice;
        this.currency = currency;
        this.ifChecked = ifChecked;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(Integer issueYear) {
        this.issueYear = issueYear;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(double originPrice) {
        this.originPrice = originPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIfChecked() {
        return ifChecked;
    }

    public void setIfChecked(Boolean ifChecked) {
        this.ifChecked = ifChecked;
    }
}