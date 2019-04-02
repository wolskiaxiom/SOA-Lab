import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "shelf", eager = true)
@ApplicationScoped
public class Shelf implements Serializable {

    // all column visible
    private Boolean ifId = false;
    private Boolean ifTitle = true;
    private Boolean ifAuthor = true;
    private Boolean ifType = true;
    private Boolean ifPrice = true;
    private Boolean ifCurrency = true;
    private Boolean ifNumber = true;

    //
    private List<String> categories = Arrays.asList("Nauka", "Dramat", "Bajka", "Fantaza");
    private final Map<String, Double> currencies = Map.of("EUR",4.3,"USD",3.8,"PLN",1.0);
    private double minPrice=0;
    private double maxPrice=1000;

    private Vector<Book> chosenBooks = new Vector<>();

    private Double sumOfPrices;

    private Vector<Book> books;


    public String createChosenBooks(){
        chosenBooks.clear();
        sumOfPrices =0.0;
        for (Book book: books) {
            if(book.getIfChecked()){
                sumOfPrices += book.getPrice();
                chosenBooks.add(book);
            }
        }
        return "success";
    }


    private double toPLN(Book b){
        return b.getOriginPrice() * currencies.get(b.getCurrency());
    }

    public Shelf() {
        this.books = BooksManager.getBooks();
        for (Book book: this.books) {
            book.setPrice(toPLN(book));
        }
    }
    public  Vector<Book> getBooks() {
        Vector<Book> books = new Vector<>();
        for(Book book:this.books){
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice && categories.contains(book.getType())) {
                books.add(book);
            }
        }
        return books;
    }

    public Boolean getIfTitle() {
        return ifTitle;
    }

    public void setIfTitle(Boolean ifTitle) {
        this.ifTitle = ifTitle;
    }
    public Double getSumOfPrices() {
        return sumOfPrices;
    }

    public void setSumOfPrices(Double sumOfPrices) {
        this.sumOfPrices = sumOfPrices;
    }
    public Boolean getIfAuthor() {
        return ifAuthor;
    }

    public void setIfAuthor(Boolean ifAuthor) {
        this.ifAuthor = ifAuthor;
    }

    public Boolean getIfType() {
        return ifType;
    }

    public void setIfType(Boolean ifType) {
        this.ifType = ifType;
    }

    public Boolean getIfPrice() {
        return ifPrice;
    }

    public void setIfPrice(Boolean ifPrice) {
        this.ifPrice = ifPrice;
    }

    public Boolean getIfCurrency() {
        return ifCurrency;
    }

    public void setIfCurrency(Boolean ifCurrency) {
        this.ifCurrency = ifCurrency;
    }

    public Boolean getIfNumber() {
        return ifNumber;
    }

    public void setIfNumber(Boolean ifNumber) {
        this.ifNumber = ifNumber;
    }

    public Vector<Book> getChosenBooks() {
        return chosenBooks;
    }

    public void setChosenBooks(Vector<Book> chosenBooks) {
        this.chosenBooks = chosenBooks;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Map<String, Double> getCurrencies() {
        return currencies;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

}
