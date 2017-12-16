package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.farha.model.Website;

public class WebsiteDao extends BaseDao {

	private final String CREATE_WEBSITE_FOR_DEVELOPER = "INSERT INTO website (name,description,"+
														"created,updated,visits,developerId) "+
													   "VALUES (?,?,curdate(),curdate(),?,?)";
	
	private final String FIND_ALL_WEBSITES = "SELECT * from website";
	
	private final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * from website WHERE developerId = ?"; 
	
	private final String FIND_WEBSITE_BY_ID = "SELECT * from website WHERE id = ?"; 
	
	private final String FIND_WEBSITE_BY_NAME = "SELECT * from website WHERE name = ?"; 
	
	private final String UPDATE_WEBSITE = "UPDATE website SET name=?, description=?,created=?,updated=?,visits=? WHERE id=?"; 
	
	private final String DELETE_WEBSITE = "DELETE from website WHERE id=?";
	
	
	
//	1.	int createWebsiteForDeveloper(int developerId, Website website)
//	inserts properties in website instance parameter into the Website table.
//	The website's developerId foreign key refer to Developer table primary key id 
//	whose value is equal to the developerId parameter
	public int createWebsiteForDeveloper(int developerId, Website website) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_WEBSITE_FOR_DEVELOPER);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
		    statement.setInt(3, website.getVisits());
	    	statement.setInt(4,developerId);
			result = statement.executeUpdate(); 
			
			statement.close();
			connection.close();
			return result;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}
	
//	2.	Collection<Website> findAllWebsites()
//	returns all records from Website table as a Collection of Website instances
	public ArrayList<Website>findAllWebsites(){
		
		ArrayList<Website>websites = new ArrayList<Website>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_ALL_WEBSITES);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int developerId = result.getInt("developerId");
				Website website = new Website(id,name,description,created,updated,visits,developerId);
				websites.add(website);
			}
			display_websites(websites);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return websites;
	}
	
//	3.	Collection<Website> findWebsitesForDeveloper(int developerId)
//	returns all records from Website table as a Collection of Website 
//  instances whose developerId is equal to the developerId parameter	
	public ArrayList<Website>findWebsitesForDeveloper(int developerId){
		
		ArrayList<Website>websites = new ArrayList<Website>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);
			statement.setInt(1, developerId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int devId = result.getInt("developerId");
				Website website = new Website(id,name,description,created,updated,visits,devId);
				websites.add(website);
			}
			display_websites(websites);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return websites;
	}
	
//	4.	Website findWebsiteById(int websiteId)
//	returns a record from Website table whose id field is equal to the websiteId parameter	
	public Website findWebsiteById(int websiteId){
		
		Website website = new Website();	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WEBSITE_BY_ID);
			statement.setInt(1, websiteId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int devId = result.getInt("developerId");
				website = new Website(id,name,description,created,updated,visits,devId);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return website;
	}
	
	
	
	
//	5.	int updateWebsite(int websiteId, Website website)
//	updates record in Website table whose id field is equal to websiteId parameter.
//	New record field values are set to the values in the website instance parameter
	public int updateWebsite(int websiteId, Website website) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(UPDATE_WEBSITE);
			statement.setString(1, website.getName());
			statement.setString(2, website.getDescription());
			statement.setString(3, website.getCreated());
			statement.setString(4, website.getUpdated());
		    statement.setInt(5, website.getVisits());
			statement.setInt(6,websiteId);
			result = statement.executeUpdate(); 
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}
	
	// returns a record from Website table whose name field is equal to websiteName parameter
	public Website findWebsiteByName(String websiteName){
		
		Website website = new Website();	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WEBSITE_BY_NAME);
			statement.setString(1, websiteName);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int devId = result.getInt("developerId");
				website = new Website(id,name,description,created,updated,visits,devId);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return website;
	}
	
//	6.	int deleteWebsite(int websiteId)
//	deletes record from Website table whose id field is equal to websiteId parameter
	public int deleteWebsite(int websiteId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_WEBSITE);
			statement.setInt(1,websiteId);
			result = statement.executeUpdate(); 
			statement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return result;
	}
	
	public void display_websites(ArrayList<Website>websites) {
		
		for(Website w: websites) {
			System.out.println(w);
		}	
	}
	
	private static WebsiteDao instance = null;
	
	private WebsiteDao() { }
	
	public static WebsiteDao getInstance() {
	    if(instance == null) {
	        instance = new WebsiteDao();
	    }
	    return instance;
	}
	
}
