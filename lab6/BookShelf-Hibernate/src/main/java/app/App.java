package app;

import entities.Author;
import entities.Book;
import entities.Borrowing;
import entities.Reader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab6_jpa");
        EntityManager em = entityManagerFactory.createEntityManager();

        try{
            em.getTransaction().begin();


            Author mickiewicz= new Author("Adam", "Mickiewicz");
            em.persist(mickiewicz);

            Book potop = new Book("Dziady");
            Book  panTadeusz= new Book("Pan Tadeusz");
            potop.setAuthor(mickiewicz);
            panTadeusz.setAuthor(mickiewicz);
            mickiewicz.getBooks().add(potop);
            mickiewicz.getBooks().add(panTadeusz);


            Reader lukasz = new Reader("Lukasz", "Bojler");
            Reader bojler = new Reader("Bojler", "Lukasz");


            Borrowing lukaszPotop = new Borrowing(lukasz, potop);
            lukaszPotop.setBorrowingDate(new Date());
            lukaszPotop.setReturningDate(new Date());
//            lukaszPotop.
//            lukasz.getBooks().add(lukaszPotop);



            em.persist(potop);
            em.persist(panTadeusz);
            em.persist(lukasz);
            em.persist(lukaszPotop);
            em.getTransaction().commit();





//            TypedQuery<Object []> query = (TypedQuery<Object[]>)
//                    em.createNamedQuery("getAllAuthorsFirstAndLastNamesWithTheirBooks");
//
//            List<Object []> result = query.getResultList();
//
//            for(Object[] o : result){
//                System.out.println("Imię autora: "+o[0]);
//                System.out.println("Nazwisko autora: "+o[1]);
//                System.out.println("Tytuł książki "+o[2]);
//            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
