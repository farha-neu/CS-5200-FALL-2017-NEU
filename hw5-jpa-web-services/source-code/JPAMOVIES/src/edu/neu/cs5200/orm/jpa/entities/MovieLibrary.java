package edu.neu.cs5200.orm.jpa.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * Entity implementation class for Entity: MovieLibrary
 *
 */
@Entity
@XmlRootElement
public class MovieLibrary implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy="library", cascade=CascadeType.ALL)
	private List<Movie> movies = new ArrayList();
	
	private static final long serialVersionUID = 1L;

	public MovieLibrary() {
		super();
	}   
	public MovieLibrary(String name) {
		this.name = name;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Movie> getMovies() {
		return movies;
	}

}
