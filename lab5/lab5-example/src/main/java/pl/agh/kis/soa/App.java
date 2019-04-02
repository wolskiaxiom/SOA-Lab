package pl.agh.kis.soa;

import pl.agh.kis.soa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello JPA!" );
        EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("JPA-SOA-LAB");
        EntityManager em = entityManagerFactory.createEntityManager();

        try {
            Student s1 = new Student("Anna", "Kowalska", new Date());
            Student s2 = new Student("Jan", "Kowalski", new Date());
            Student s3 = new Student("Piotr", "Nowak", new Date());

            em.getTransaction().begin();
            em.persist(s1);
            em.persist(s2);
            em.persist(s3);

            em.getTransaction().commit();

            System.out.println("Check your db");
        }catch (Exception e){
            System.err.println("Error" + e);
        }


    }
}
