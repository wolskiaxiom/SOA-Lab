import entities.Book;

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

    //to use in updating, no better idea
    //now i know....
    private long idBookE;
    private String titleE;
    private String authorFirstNameE;
    private String authorLastNameE;
    private String isbnE;
    private Integer issueYearE;
    private String typeE;
    private double priceE;
    private double originPriceE;
    private String currencyE;
    private Boolean ifCheckedE;



    private Vector<Book> chosenBooks = new Vector<>();

    private Double sumOfPrices;

    private Vector<Book> books;

    private BooksManager booksManager = new BooksManager();



    public Shelf() {}
    public  ArrayList<Book> getBooks()  {
        return booksManager.getBooks();
    }

}
