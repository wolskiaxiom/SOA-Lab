package pl.agh.kis.soa.beans;

import pl.agh.kis.soa.entity.Movie;
import pl.agh.kis.soa.entity.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class UserMovieBean implements Serializable {

    private Client client;

    private Long idUser;
    private Long idMovie;

    public void addUserMovie(){
        client = ClientBuilder.newClient();
        System.out.println("aaaa" +idMovie + idUser);
        if(idMovie!=null && idUser !=null){
            Movie movie = getMovie(idMovie);
            User user = getUser(idUser);
            user.getMovies().add(movie);
            Entity entity = Entity.entity(user, MediaType.APPLICATION_JSON_TYPE);
            System.out.println(user);
            client.target("http://localhost:8080/MyRESTfulExample/users/" + idUser)
                    .request().put(entity).close();
        }
    }

    public void deleteUserMovie(){
        client = ClientBuilder.newClient();
        if(idMovie==null && idUser ==null){
            client.target("http://localhost:8080/MyRESTfulExample/users/"+idUser+"/movies/"+idMovie)
            .request().delete().close();
        }
    }

    public User getUser(long idUser){
        client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/MyRESTfulExample/users/" + idUser).request().get();
        return response.readEntity(User.class);
    }
    public Movie getMovie(long idMovie){
        client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/MyRESTfulExample/movies/" + idMovie).request().get();
        return response.readEntity(Movie.class);

    }

    public UserMovieBean() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }
}
