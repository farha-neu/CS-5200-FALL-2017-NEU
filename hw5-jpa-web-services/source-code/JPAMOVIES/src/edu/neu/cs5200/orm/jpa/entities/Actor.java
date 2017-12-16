package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entity implementation class for Entity: Actor
 *
 */
@Entity
@XmlRootElement
public class Actor extends Person implements Serializable {

	private int oscarNominations;
	
	
	@ManyToMany(mappedBy="actors", cascade=CascadeType.ALL)
	private List<Movie> moviesActed;
	
	private static final long serialVersionUID = 1L;

	public Actor() {
		super();
	}   
	
	public Actor(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	public int getOscarNominations() {
		return this.oscarNominations;
	}

	public void setOscarNominations(int oscarNominations) {
		this.oscarNominations = oscarNominations;
	}

	public List<Movie> getMoviesActed() {
		return moviesActed;
	}
	
	@XmlElement(name="movie")
	public void setMoviesActed(List<Movie> movies) {
		this.moviesActed = movies;
		for(Movie movie: movies) {
			movie.getActors().add(this);
		}
	}

	
}

   

