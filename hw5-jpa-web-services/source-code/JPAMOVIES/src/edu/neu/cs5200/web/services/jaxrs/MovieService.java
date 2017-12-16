package edu.neu.cs5200.web.services.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.orm.jpa.daos.MovieDao;
import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Movie;

@Path("movie")
public class MovieService {
	      
   MovieDao mdao = new MovieDao();
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getMovies() {
	   List<Movie>movies = new ArrayList<Movie>();
	   movies = mdao.findAllMovies();
	   return movies;  
	}
   
   @Path("{mid}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public Movie getMovieById(@PathParam("mid") int mid) {
	
	   Movie movie = mdao.findMovieById(mid);
	   return movie;  
	}
   	
   @Path("{mid}/actor")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getMoviesByActorId(@PathParam("mid") int mid) {
	  
	   Movie movie = mdao.findMovieById(mid);
	   List<Actor>actors = movie.getActors();
	   return actors;
	}
}
