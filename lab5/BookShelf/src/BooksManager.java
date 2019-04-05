import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class BooksManager {



    public static final Vector<Book> books
            = new Vector<>(Arrays.asList());
    public BooksManager(){}

    public static Vector<Book> getBooks() {
        return books;
    }

}
