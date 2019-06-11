package pl.agh.soa.datasource.database;



import pl.agh.soa.datasource.entities.UserDetails;

import javax.persistence.EntityManager;

public class UserDetailsManager {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static UserDetails getUserDetails(String login){
        return entityManager.find(UserDetails.class, login);
    }
}
