package pl.agh.kis.soa.datasource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseService {
    private static final DatabaseService ourInstance = new DatabaseService();
    private final EntityManager entityManager;
    public static DatabaseService getInstance() {
        return ourInstance;
    }

    private DatabaseService() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab8_rest");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
