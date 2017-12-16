package edu.neu.cs5200.orm.jpa.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.orm.jpa.entities.Actor;




public class ActorDao {
	private static final String UNIT = "JPAMOVIES";
	private static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory(UNIT);
	
	public int createActor(Actor actor) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(actor);
		em.flush();
		
		em.getTransaction().commit();
		em.close();
		return actor.getId();
	}
	
	public int deleteActor() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		int deletedCount = em.createQuery("DELETE FROM Actor").executeUpdate();
		
		em.getTransaction().commit();
		em.close();
		
		return deletedCount;
	}
	
	public void deleteActorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("DELETE FROM Actor a WHERE "
				+ "a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
	
	public Actor findActorById(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Actor actor = em.find(Actor.class, id);
		
		em.getTransaction().commit();
		em.close();
		return actor;
	}
	
	public void renameActor(int id, String newFirstName, String newLastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Actor actor = em.find(Actor.class, id);
		actor.setFirstName(newFirstName);
		actor.setLastName(newLastName);
		em.getTransaction().commit();
	}
	
	public List<Actor> findAllActors() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a");
		List<Actor> actors = (List<Actor>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return actors;
	}
	
	public Actor findFirstActor() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a");
		List<Actor> actors = (List<Actor>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return actors.get(0);
	}
	
	public Actor findActorByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a WHERE a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Actor actor = (Actor)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		return actor;
	}
	
	public int findActorIdByName(String firstName, String lastName) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createQuery("select a from Actor a WHERE a.firstName =:firstName AND a.lastName =:lastName");
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		Actor actor = (Actor)query.getSingleResult();
		
		em.getTransaction().commit();
		em.close();
		return actor.getId();
	}
	
	public void displayAllActors() {
		List<Actor>actors= findAllActors();
		for(Actor a:actors) {
			System.out.println(a.getFirstName()+" "+a.getLastName());
		}
	}
	
	public void test() {
		
		deleteActor();
		
		Actor actor = new Actor("Sigorney","Weaver");
		createActor(actor);
		
		actor = new Actor("Dan","Creg");
		createActor(actor);
		
		actor = new Actor("Jim","Carrey");
		createActor(actor);
		
		actor = findFirstActor();
		System.out.println(actor.getFirstName()+" "+actor.getLastName());
		
		displayAllActors();
		
		renameActor(findActorIdByName("Dan","Creg"),"Daniel","Craig");
		
		deleteActorByName("Jim","Carrey");
		
		displayAllActors();
		
		
	}
	
}
