package entities;

import javax.persistence.*;

//@Entity
//@Embeddable
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idReader;

    private String firstName;
    private String lastName;



}
