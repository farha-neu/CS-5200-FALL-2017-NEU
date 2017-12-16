package edu.neu.cs5200.web.services.jaxrs;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.orm.jpa.daos.ActorDao;
import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Movie;

@Path("actor")
public class ActorService {
	      
   ActorDao adao = new ActorDao();
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getActors() {
	   List<Actor>actors = new ArrayList<Actor>();
	   actors = adao.findAllActors();
	   return actors;  
	}
   
   @Path("{aid}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
	public Actor getActorById(@PathParam("aid") int aid) {
	
	   Actor actor = adao.findActorById(aid);
	   return actor;  
	}
   	
    @Path("{aid}/movie")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getMoviesByActorId(@PathParam("aid") int aid) {
	  
	   Actor actor = adao.findActorById(aid);
	   
	   return actor.getMoviesActed();  
	}
}
