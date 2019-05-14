package pl.agh.kis.soa.beans;

import pl.agh.kis.soa.entity.Movie;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class MovieBean implements Serializable {
    private Client client;

    private String title="";
    private String uri="";
    private List<Movie> movies= new ArrayList<>();

    private Boolean isEdited=false;
    private long idEdited;

    public List<Movie> getMovies(){
        client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/MyRESTfulExample/movies").
                request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus()!= 200){
            throw new RuntimeException("Error");
        }
        movies = response.readEntity(new GenericType<List<Movie>>(){});
        return movies;
    }

    public void addMovie(){
        client = ClientBuilder.newClient();
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setUri(uri);
        Entity entity = Entity.entity(movie, MediaType.APPLICATION_JSON_TYPE);
        System.out.println(movie);
        client.target("http://localhost:8080/MyRESTfulExample/movies").request().post(entity).close();
    }

    public void deleteMovie(long idMovie){
        client = ClientBuilder.newClient();
        URI uri = UriBuilder.fromUri("http://localhost:8080/MyRESTfulExample/movies/"+idMovie).build();
        System.out.println(uri);
        Response response = client.target(uri).request().delete();
        response.close();
    }

    public void startEdit(long idMovie, String title, String uri){
        this.title = title;
        this.uri = uri;
        this.idEdited = idMovie;
        isEdited = true;
    }
    public void editMovie(){
        client = ClientBuilder.newClient();
        URI uri = UriBuilder.fromUri("http://localhost:8080/MyRESTfulExample/movies/"+idEdited).build();
        Movie movie = new Movie();
        movie.setUri(this.uri);
        movie.setTitle(this.title);
        movie.setMovieId(idEdited);
        Entity entity = Entity.entity(movie, MediaType.APPLICATION_JSON_TYPE);
        client.target(uri).request().put(entity).close();
        isEdited=false;
        this.uri = "";
        this.title = "";
    }


    public MovieBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Boolean getEdited() {
        return isEdited;
    }

    public void setEdited(Boolean edited) {
        isEdited = edited;
    }
}
