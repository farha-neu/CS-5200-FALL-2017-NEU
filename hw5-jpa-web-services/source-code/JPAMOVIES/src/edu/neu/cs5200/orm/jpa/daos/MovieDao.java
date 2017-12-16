package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Director;
import edu.neu.cs5200.orm.jpa.entities.Movie;

public class MovieDao {
	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);
	
	public void addActorToMovie(int aId, int mId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, aId);
		Movie movie = em.find(Movie.class, mId);
		movie.getActors().add(actor);
		actor.getMoviesActed().add(movie);
		em.persist(movie);
		em.persist(actor);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public void addDirectorToMovie(int dId, int mId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Director director = em.find(Director.class, dId);
		Movie movie = em.find(Movie.class, mId);
		movie.getDirectors().add(director);
		director.getMoviesDirected().add(movie);
		em.persist(movie);
		em.persist(director);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public int createMovie(Movie movie) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(movie);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		
		return movie.getId();
	}
	
	public Movie findMovieById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void deleteMovie(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, id);
		em.remove(movie);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Movie> findAllMovies() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m");
		List<Movie> movies = (List<Movie>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return movies;
	}

	
	public Movie findMovieByTitle(String title) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from Movie m where m.title=:title");
		query.setParameter("title", title);
		Movie movie = (Movie)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return movie;
	}
	
	public void renameMovie(int id, String newTitle) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Movie movie = em.find(Movie.class, id);
		movie.setTitle(newTitle);
		em.getTransaction().commit();
	}
	
	
	
	public void displayAllMovies() {
		List<Movie>movies= findAllMovies();
		for(Movie m:movies) {
			System.out.println(m.getTitle());
			List<Actor>actors = m.getActors();
			List<Director>directors = m.getDirectors();
			for(Actor a:actors) {
				System.out.println(a.getFirstName()+" "+a.getLastName());
			}
			for(Director d:directors) {
				System.out.println(d.getFirstName()+" "+d.getLastName());
			}		
		}
	}
	
	public void displayAllMoviesOfActor(Actor actor) {
		List<Movie>movies= actor.getMoviesActed();
		System.out.println(actor.getFirstName()+" "+actor.getLastName());
		for(Movie m:movies) {
			System.out.println(m.getTitle());
		}
	}
	
	public void displayAllMoviesOfDirector(Director director) {
		List<Movie>movies= director.getMoviesDirected();
		System.out.println(director.getFirstName()+" "+director.getLastName());
		for(Movie m:movies) {
			System.out.println(m.getTitle());
		}
	}

	public void test() {

		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		
		int mid = createMovie(new Movie("Blade Runner"));
		int aid = adao.createActor(new Actor("Harrison","Ford"));
		addActorToMovie(aid,mid);
    	addActorToMovie(adao.createActor(new Actor("Rutger","Hauer")),mid);
		int did= ddao.createDirector(new Director("Ridley","Scott"));
		addDirectorToMovie(did,mid);
		
		
		mid = createMovie(new Movie("Raiders of The Lost Ark"));
		addActorToMovie(aid,mid);
		aid= adao.createActor(new Actor("Karen","Allen"));
    	addActorToMovie(aid,mid);
    	did=ddao.findDirectorIdByName("Steven","Spielberg");
		addDirectorToMovie(did,mid);
		
		mid = createMovie(new Movie("Close Encounters of the Third Kind"));
		addDirectorToMovie(did,mid);
		aid=adao.createActor(new Actor("Richard","Dreyfus"));
		addActorToMovie(aid,mid);
		aid=adao.createActor(new Actor("Melinda","Dillon"));
		addActorToMovie(aid,mid);
		
		displayAllMovies();
		displayAllMoviesOfActor(adao.findActorByName("Harrison","Ford"));
		displayAllMoviesOfDirector(ddao.findDirectorByName("Steven", "Spielberg"));

		
	}
	
	
}
