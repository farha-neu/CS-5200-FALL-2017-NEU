package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.orm.jpa.entities.Director;


public class DirectorDao {
	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);

	public int createDirector(Director director) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		em.persist(director);
		em.flush();

		em.getTransaction().commit();
		em.close();
		return director.getId();
	}

	public int deleteDirector() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		int deletedCount = em.createQuery("DELETE FROM Director").executeUpdate();

		em.getTransaction().commit();
		em.close();

		return deletedCount;
	}

	public void deleteDirectorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("DELETE FROM Director a WHERE " + "a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	public Director findDirectorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Director director = em.find(Director.class, id);

		em.getTransaction().commit();
		em.close();
		return director;
	}

	public void renameDirector(int id, String newFirstName, String newLastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Director director = em.find(Director.class, id);
		director.setFirstName(newFirstName);
		director.setLastName(newLastName);
		em.getTransaction().commit();
	}

	public List<Director> findAllDirectors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select a from Director a");
		List<Director> directors = (List<Director>) query.getResultList();

		em.getTransaction().commit();
		em.close();

		return directors;
	}

	public Director findFirstDirector() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select a from Director a");
		List<Director> directors = (List<Director>) query.getResultList();

		em.getTransaction().commit();
		em.close();
		return directors.get(0);
	}

	public int findDirectorIdByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select a from Director a WHERE a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Director director = (Director) query.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return director.getId();
	}
	
	public Director findDirectorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select a from Director a WHERE a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Director director = (Director) query.getSingleResult();

		em.getTransaction().commit();
		em.close();
		return director;
	}

	public void displayAllDirectors() {
		List<Director> directors = findAllDirectors();
		for (Director a : directors) {
			System.out.println(a.getFirstName() + " " + a.getLastName());
		}
	}

	public void test() {

		deleteDirector();

		Director director = new Director("Jimmy", "Camaron");
		createDirector(director);

		director = new Director("Steven","Spielberg");
		createDirector(director);

		director = new Director("Ron", "Howard");
		createDirector(director);

		director = findFirstDirector();
		System.out.println(director.getFirstName() + " " + director.getLastName());

		displayAllDirectors();

		renameDirector(findDirectorIdByName("Jimmy", "Camaron"), "James", "Cameron");

		deleteDirectorByName("Ron","Howard");

		displayAllDirectors();

	}

}
