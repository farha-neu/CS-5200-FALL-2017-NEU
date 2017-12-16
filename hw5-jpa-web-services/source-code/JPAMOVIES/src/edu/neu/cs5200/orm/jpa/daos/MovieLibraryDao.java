package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.orm.jpa.entities.Actor;
import edu.neu.cs5200.orm.jpa.entities.Director;
import edu.neu.cs5200.orm.jpa.entities.Movie;
import edu.neu.cs5200.orm.jpa.entities.MovieLibrary;

public class MovieLibraryDao {
	
	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);
	
	public int createMovieLibrary(MovieLibrary library) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(library);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		return library.getId();
	}

	public void addMovieToLibrary(int mId, int lId) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Movie movie = em.find(Movie.class, mId);
		MovieLibrary library = em.find(MovieLibrary.class, lId);
		library.getMovies().add(movie);
		movie.setLibrary(library);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public int deleteMovieLibrary() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		int deletedCount = em.createQuery("DELETE FROM MovieLibrary").executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		return deletedCount;
	}
	
	public MovieLibrary findMovieLibraryByName(String name) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select m from MovieLibrary m where m.name=:name");
		query.setParameter("name", name);
		MovieLibrary movieLib = (MovieLibrary)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		
		return movieLib;
	}
	
	public void displayMovieLibrary(MovieLibrary mlib) {
		System.out.println(mlib.getName());
		List<Movie>movies = mlib.getMovies();
		for(Movie m:movies) {
			System.out.println(m.getTitle());
			List<Actor>actors = m.getActors();
			for(Actor a:actors) {
				System.out.println(a.getFirstName()+" "+a.getLastName());
			}
			List<Director>directors = m.getDirectors();
			for(Director d:directors) {
				System.out.println(d.getFirstName()+" "+d.getLastName());
			}
		}
	}
	
	public void test() {
		MovieDao mdao = new MovieDao();
		ActorDao adao = new ActorDao();
		DirectorDao ddao = new DirectorDao();
		
		int mid = mdao.createMovie(new Movie("Star Wars A New Hope"));
		mdao.addActorToMovie(adao.createActor(new Actor("Mark","Hamill")),mid);
    	mdao.addActorToMovie(adao.createActor(new Actor("Carrie","Fisher")),mid);
		mdao.addDirectorToMovie(ddao.createDirector(new Director("George","Lucas")),mid);
		MovieLibrary mlib = new MovieLibrary("Favorite Movies");
		int mlibId = createMovieLibrary(mlib);
		addMovieToLibrary(mid,mlibId);
		
		mid = mdao.createMovie(new Movie("The Revanant"));
		mdao.addActorToMovie(adao.createActor(new Actor("Leonardo","DiCaprio")),mid);
    	mdao.addActorToMovie(adao.createActor(new Actor("Tom","Hardy")),mid);
		mdao.addDirectorToMovie(ddao.createDirector(new Director("Alejandro","Inarritu")),mid);
		addMovieToLibrary(mid,mlibId);
		displayMovieLibrary(findMovieLibraryByName("Favorite Movies"));
	}
	

}
