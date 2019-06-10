package pl.agh.soa.dashboard.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseService {
    private static final DatabaseService ourInstance = new DatabaseService();
    private final EntityManager entityManager;
    public static DatabaseService getInstance(){return ourInstance;}

    private DatabaseService(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("project_parking");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
