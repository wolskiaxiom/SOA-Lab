package pl.agh.soa.dashboard.database;

import pl.agh.soa.dashboard.entity.UserDetails;

import javax.persistence.EntityManager;

public class UserDetailsManager {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static UserDetails getUserDetails(String login){
        return entityManager.find(UserDetails.class, login);
    }
}
