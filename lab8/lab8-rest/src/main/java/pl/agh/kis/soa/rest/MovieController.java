package pl.agh.kis.soa.rest;


import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import org.json.JSONObject;
import pl.agh.kis.soa.entity.Movie;
import pl.agh.kis.soa.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class MovieController {

    @GET
    @Path("filmy")
    public Response redirectToMovies(){
        URI uri = UriBuilder.fromUri("/movies").build();
        return Response.status(302).location(uri).build();
    }

    @AddLinks
    @LinkResource(value = Movie.class)
    @GET
    @Path("movies")
    public Response getMovies() {
        User sampleUser = new User();
        sampleUser.setUserId(1);
        sampleUser.setName("Andrzej");
        sampleUser.setAge(123);

        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setUri("/home/movie");
        movie.setTitle("Ksiaze kaspian");
        Set users = movie.getUsers();
        users.add(sampleUser);
        movie.setUsers(users);

        Movie movie1 = new Movie(2, "sample title", "sample uri", null);

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        movies.add(movie1);
        return Response.status(200).entity(movies).build();
    }

    @AddLinks
    @LinkResource
    @GET
    @Path("movie/{id}")
    public Response getMovie(@PathParam("id") String id) {

        User user = new User();
        user.setUserId(1);
        user.setName("asd");
        user.setAge(123);

        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setUri("fd");
        movie.setTitle("sfd");
        Set users = movie.getUsers();
        users.add(user);
        movie.setUsers(users);
        return Response.status(200).entity(movie).build();
    }

    @LinkResource
    @POST
    @Path("movies")
    public void addMovie(Movie movie){

    }

    @LinkResource
    @PUT
    @Path("movie/{id}")
    public void updateMovie(@PathParam("id") long id, Movie movie){

    }

    @LinkResource(value = Movie.class)
    @DELETE
    @Path("movie/{id}")
    public void deleteMovie(@PathParam("id") String id){

    }




}

