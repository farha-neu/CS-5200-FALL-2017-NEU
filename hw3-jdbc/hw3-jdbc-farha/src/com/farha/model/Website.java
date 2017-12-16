package com.farha.model;

public class Website {
	
	private int id;
	private String name;
	private String description;
	private String created;
	private String updated;
	private int visits;
	private int developerId;
	
	
	public Website() {
		super();
	}
	
	public Website(String name, String description, int visits,int developerId) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
		this.developerId = developerId;
	}
	
	
	public Website(String name, String description, int visits) {
		super();
		this.name = name;
		this.description = description;
		this.visits = visits;
	}
	
	
	public Website(String name, String description, String created, String updated, int visits) {
		super();
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
	}
	
	public Website(int id, String name, String description, String created, String updated, int visits,
			int developerId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.created = created;
		this.updated = updated;
		this.visits = visits;
		this.developerId = developerId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public int getVisits() {
		return visits;
	}
	public void setVisits(int visits) {
		this.visits = visits;
	}
	
	public int getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(int developerId) {
		this.developerId = developerId;
	}
	
	@Override
	public String toString() {
		return "Website [id=" + id + ", name=" + name + ", description=" + description + ", created=" + created
				+ ", updated=" + updated + ", visits=" + visits + ", developerId=" + developerId + "]";
	}
	
	
	
	
	
}
