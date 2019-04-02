import java.io.Serializable;

public class Book implements Serializable {
    private Double idBook;
    private String title;
    private String author;
    private String type;
    private double price;
    private double originPrice;
    private String currency;
    private Integer numberOfPages;
    private Boolean ifChecked;

    public Book(String title, String author, String type, double originPrice, String currency, Integer numberOfPages) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.originPrice = originPrice;
        this.currency = currency;
        this.numberOfPages = numberOfPages;
        this.ifChecked = false;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Boolean getIfChecked() {
        return ifChecked;
    }

    public void setIfChecked(Boolean ifChecked) {
        this.ifChecked = ifChecked;
    }


}