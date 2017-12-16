package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Entity implementation class for Entity: Movie
 *
 */
@Entity
@XmlRootElement
public class Movie implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	
	@ManyToOne
	private MovieLibrary library;
	
	@ManyToMany
	@JoinTable(name="Movie2Actor")
	private List<Actor> actors = null;
	
	@ManyToMany
	@JoinTable(name="Movie2Director")
	private List<Director> directors = null;
	
	private static final long serialVersionUID = 1L;

	public Movie() {
		super();
	}   
	public Movie(String title) {
		super();
		this.title = title;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
   
	public MovieLibrary getLibrary() {
		return library;
	}
	public void setLibrary(MovieLibrary library) {
		this.library = library;
	}
	
	public List<Actor> getActors() {
		return actors;
	}

	public List<Director> getDirectors() {
		return directors;
	}
	

	
}
