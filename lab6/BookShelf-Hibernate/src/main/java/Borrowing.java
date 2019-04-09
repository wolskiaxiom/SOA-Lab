import entities.Book;
import entities.Reader;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBorrowing;

    @Embedded
    private Reader reader;
    @Embedded
    private Book book;

    private Date borrowingDate;
    private Date rentingDate;

}
