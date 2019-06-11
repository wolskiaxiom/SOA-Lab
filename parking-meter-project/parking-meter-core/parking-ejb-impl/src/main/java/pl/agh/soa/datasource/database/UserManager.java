package pl.agh.soa.datasource.database;




import pl.agh.soa.datasource.entities.User;

import javax.persistence.EntityManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserManager {
    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static User getUser(String login){
        return entityManager.find(User.class, login);
    }

    public static void updateUser(User user){
        entityManager.getTransaction().begin();
        User updatedUser = entityManager.find(User.class, user.getLogin());
        updatedUser.setPassword(checkMD5(user.getPassword()));
        entityManager.getTransaction().commit();
    }

    public static String checkMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] enc = md.digest();
            String md5Sum = new sun.misc.BASE64Encoder().encode(enc);
            return md5Sum;
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println(nsae.getMessage());
            return null;
        }
    }
}
