package pl.agh.kis.soa.rest;


import org.jboss.resteasy.links.AddLinks;
import org.jboss.resteasy.links.LinkResource;
import pl.agh.kis.soa.entitiesManagers.MovieManager;
import pl.agh.kis.soa.entity.Movie;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

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
        List<Movie> movies = MovieManager.readAllMovies();
        return Response.status(200).entity(movies).build();
    }

    @AddLinks
    @LinkResource
    @GET
    @Path("movies/{id}")
    public Response getMovie(@PathParam("id") long id) {

        Movie movie = MovieManager.getMovie(id);
        if(movie==null){
            return Response.status(405).build();
        }

        return Response.status(200).entity(movie).build();
    }

    @LinkResource
    @POST
    @Path("movies")
    public Response addMovie(Movie movie){
        MovieManager.addMovie(movie);
        return Response.status(201).build();
    }

    @LinkResource
    @PUT
    @Path("movies/{id}")
    public Response updateMovie(@PathParam("id") long id, Movie movie){
        movie.setMovieId(id);
        try{
            MovieManager.updateMovie(movie);
        }catch (RuntimeException e){
            return Response.status(405).build();
        }
        return Response.status(201).build();
    }

    @LinkResource(value = Movie.class)
    @DELETE
    @Path("movies/{id}")
    public Response deleteMovie(@PathParam("id") long id){
        try{
            MovieManager.deleteMovie(id);
        }catch (Exception e){
            return Response.status(405).build();
        }
        return Response.status(200).build();
    }
}

