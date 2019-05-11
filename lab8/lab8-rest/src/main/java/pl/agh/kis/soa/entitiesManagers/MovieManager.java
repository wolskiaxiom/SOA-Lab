package pl.agh.kis.soa.entitiesManagers;

import pl.agh.kis.soa.datasource.DatabaseService;
import pl.agh.kis.soa.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieManager {

    private static EntityManager entityManager = DatabaseService.getInstance().getEntityManager();

    public static void addMovie(Movie movie){
        entityManager.getTransaction().begin();
        entityManager.persist(movie);
        entityManager.getTransaction().commit();
    }

    public static Movie getMovie(long idMovie){
        return entityManager.find(Movie.class, idMovie);

    }

    public static List<Movie> readAllMovies(){
        TypedQuery<Movie> queryBooks = (TypedQuery<Movie>) entityManager.createQuery("select movie from Movie movie", Movie.class);
        return queryBooks.getResultList();
    }


    public static void deleteMovie(long idMovie){
        entityManager.getTransaction().begin();
        Movie movie= entityManager.find(Movie.class, idMovie);
        entityManager.remove(movie);
        entityManager.getTransaction().commit();
    }

    public static void updateBook(Movie movie){
        entityManager.getTransaction().begin();
        Movie edited = entityManager.find(Movie.class, movie.getMovieId());
        edited.setTitle(movie.getTitle());
        edited.setUsers(movie.getUsers());
        edited.setUri(movie.getUri());
        entityManager.getTransaction().commit();
    }

}
