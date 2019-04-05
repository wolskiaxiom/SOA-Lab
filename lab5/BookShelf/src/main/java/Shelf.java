import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.*;

@ManagedBean(name = "shelf", eager = true)
@ApplicationScoped
public class Shelf implements Serializable {
    //
    private List<String> categories = Arrays.asList("Nauka", "Dramat", "Bajka", "Fantaza");
    private final Map<String, Double> currencies = Map.of("EUR",4.3,"USD",3.8,"PLN",1.0);
    private double minPrice=0;
    private double maxPrice=1000;

    private Vector<Book> chosenBooks = new Vector<>();

    private Double sumOfPrices;

    private Vector<Book> books;

    private BooksManager booksManager = new BooksManager();

    public String createChosenBooks(){
        this.chosenBooks.clear();
        this.sumOfPrices =0.0;
        for (Book book: books) {
            if(book.getIfChecked()){
                this.sumOfPrices += book.getPrice();
                this.chosenBooks.add(book);
            }
        }
        return "success";
    }


    private double toPLN(Book b){
        return b.getOriginPrice() * currencies.get(b.getCurrency());
    }

    public Shelf() {}
    public  Vector<Book> getBooks()  {
        Vector<Book> booksFromManager = booksManager.getBooks();
        Vector<Book> books = new Vector<>();
        for(Book book:booksFromManager){
            book.setPrice(toPLN(book));
            System.out.println(book.getTitle());
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice && categories.contains(book.getType())) {
                books.add(book);
            }
        }
        return books;
    }

    public Double getSumOfPrices() {
        return sumOfPrices;
    }

    public void setSumOfPrices(Double sumOfPrices) {
        this.sumOfPrices = sumOfPrices;
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
