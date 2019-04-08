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

    public String createChosenBooks(){
        this.chosenBooks.clear();
        this.sumOfPrices =0.0;
        for (Book book: this.getBooks()) {
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
    public  ArrayList<Book> getBooks()  {
        ArrayList<Book> booksFromManager = booksManager.getBooks();
        ArrayList<Book> books = new ArrayList<>();
        for(Book book:booksFromManager){
            book.setPrice(toPLN(book));
            System.out.println(book.getTitle());
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice && categories.contains(book.getType())) {
                books.add(book);
            }
        }
        return books;
    }

    public void deleteBook(Book book){
        booksManager.deleteBookWithId(book.getIdBook());
    }

    public String goToUpdatePage(Book book){
        idBookE=book.getIdBook();
        titleE=book.getTitle();
        authorFirstNameE=book.getAuthorFirstName();
        authorLastNameE=book.getAuthorLastName();
        isbnE=book.getIsbn();
        issueYearE=book.getIssueYear();
        typeE=book.getType();
        priceE=book.getPrice();
        originPriceE=book.getOriginPrice();
        currencyE=book.getCurrency();
        ifCheckedE=book.getIfChecked();
        return "update_book";
    }

    public String updateBook(){
        booksManager.updateBook(new Book(idBookE,titleE,authorFirstNameE,authorLastNameE,isbnE,issueYearE,typeE,priceE,originPriceE,currencyE,ifCheckedE));
        return "books";
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

    public long getIdBookE() {
        return idBookE;
    }

    public void setIdBookE(long idBookE) {
        this.idBookE = idBookE;
    }

    public String getTitleE() {
        return titleE;
    }

    public void setTitleE(String titleE) {
        this.titleE = titleE;
    }

    public String getAuthorFirstNameE() {
        return authorFirstNameE;
    }

    public void setAuthorFirstNameE(String authorFirstNameE) {
        this.authorFirstNameE = authorFirstNameE;
    }

    public String getAuthorLastNameE() {
        return authorLastNameE;
    }

    public void setAuthorLastNameE(String authorLastNameE) {
        this.authorLastNameE = authorLastNameE;
    }

    public String getIsbnE() {
        return isbnE;
    }

    public void setIsbnE(String isbnE) {
        this.isbnE = isbnE;
    }

    public Integer getIssueYearE() {
        return issueYearE;
    }

    public void setIssueYearE(Integer issueYearE) {
        this.issueYearE = issueYearE;
    }

    public String getTypeE() {
        return typeE;
    }

    public void setTypeE(String typeE) {
        this.typeE = typeE;
    }

    public double getPriceE() {
        return priceE;
    }

    public void setPriceE(double priceE) {
        this.priceE = priceE;
    }

    public double getOriginPriceE() {
        return originPriceE;
    }

    public void setOriginPriceE(double originPriceE) {
        this.originPriceE = originPriceE;
    }

    public String getCurrencyE() {
        return currencyE;
    }

    public void setCurrencyE(String currencyE) {
        this.currencyE = currencyE;
    }

    public Boolean getIfCheckedE() {
        return ifCheckedE;
    }

    public void setIfCheckedE(Boolean ifCheckedE) {
        this.ifCheckedE = ifCheckedE;
    }
}
