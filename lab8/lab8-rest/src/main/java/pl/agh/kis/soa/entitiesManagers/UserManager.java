package pl.agh.kis.soa.entitiesManagers;

import pl.agh.kis.soa.datasource.DatabaseService;
import pl.agh.kis.soa.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserManager {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addUser(User user){
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static User getUser(long idUser){
        return entityManager.find(User.class, idUser);
    }

    public static List<User> readAllUsers(){
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createQuery("select user from User user", User.class);
        return query.getResultList();
    }

    public static void deleteUser(long idUser){
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, idUser);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    public static void updateUser(User user){
        entityManager.getTransaction().begin();
        User edited = entityManager.find(User.class, user.getUserId());
        edited.setName(user.getName());
        edited.setAge(user.getAge());
        edited.setAvatar(user.getAvatar());
        edited.setMovies(user.getMovies());
        entityManager.getTransaction().commit();
    }

}
