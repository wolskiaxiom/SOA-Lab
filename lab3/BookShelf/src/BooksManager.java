import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class BooksManager {



    public static final Vector<Book> books
            = new Vector<>(Arrays.asList(
            new Book("Harry Potter 1", "J.K. Rowling", "Fantaza", 15, "USD", 345),
            new Book("O psie", "Adam", "Bajka", 11, "PLN", 123),
            new Book("O psach", "PWN", "Nauka", 44, "PLN", 3415),
            new Book("O psach, dramatyczne", "Dramaaaas", "Dramat", 22, "EUR", 3415),
            new Book("Harry Potter 2", "J.K. Rowling", "Fantaza", 36, "PLN", 3451),
            new Book("Harry Potter 3", "J.K. Rowling", "Fantaza", 322, "PLN", 3415)
    ));
    public BooksManager(){}

    public static Vector<Book> getBooks() {
        return books;
    }

}
