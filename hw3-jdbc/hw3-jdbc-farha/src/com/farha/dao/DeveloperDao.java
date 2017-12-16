package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.farha.model.Developer;
import com.mysql.jdbc.Statement;


public class DeveloperDao extends BaseDao{
	
	private final String CREATE_PERSON = "INSERT INTO person (firstname,lastname,"
										+ "username,password,email,dob) VALUES (?,?,?,?,?,?)";
	
	private final String CREATE_DEVELOPER = "INSERT INTO developer (id,developerKey) VALUES (?,?)";
	
	private final String FIND_ALL_DEVELOPERS = "SELECT * "
												+"FROM person JOIN developer "
												+"ON person.id = developer.id";
	
	private final String FIND_DEVELOPER_BY_ID = "SELECT * FROM person JOIN developer "
												+"ON person.id = developer.id "
												+"WHERE developer.id=?";
	
	private final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM person JOIN developer "
													+"ON person.id = developer.id "
													+"WHERE person.username=?";
	
	private final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM person JOIN developer "
			+"ON person.id = developer.id "
			+"WHERE person.username=? AND person.password=?";
	
	private final String UPDATE_DEVELOPER = "UPDATE person join developer "+
											"ON person.id = developer.id "+
											"SET firstname=?,lastname=?,username=?,"+
											"password=?,email=?,dob=?,developerKey=? "+
											"WHERE developer.id=?";
	
	private final String DELETE_DEVELOPER = "DELETE from person WHERE id=?";
	
	
	// 1.int createDeveloper(Developer developer)
	// inserts properties in developer instance parameter in tables Developer and Person
	public int createDeveloper(Developer developer) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement1 = null;
			statement1 = connection.prepareStatement(CREATE_PERSON,Statement.RETURN_GENERATED_KEYS);
			statement1.setString(1, developer.getFirstname());
			statement1.setString(2, developer.getLastname());
			statement1.setString(3, developer.getUsername());
			statement1.setString(4, developer.getPassword());
			statement1.setString(5, developer.getEmail());
			statement1.setString(6, developer.getDob());
			result = statement1.executeUpdate(); 
			ResultSet keys = statement1.getGeneratedKeys();
			int developerId;
		    if(keys.next()) {
		      developerId = keys.getInt(1);
		    } else {
	            throw new SQLException("No generated developer ID returned");
	        }
			statement1.close();	
			PreparedStatement statement2 = null;
			statement2 = connection.prepareStatement(CREATE_DEVELOPER);
			statement2.setInt(1, developerId);
			statement2.setString(2, developer.getDeveloperKey());
			result = statement2.executeUpdate(); 
			statement2.close();
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
	
	
	// 2.Collection<Developer> findAllDevelopers()
	// returns all joined records from Developer and Person tables as a Collection of Developer instances
	public ArrayList<Developer>findAllDevelopers(){
		
		ArrayList<Developer>developers = new ArrayList<Developer>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_ALL_DEVELOPERS);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String dob = result.getString("dob");
				String developerKey = result.getString("developerKey");
				Developer developer = new Developer(id, firstname,lastname,username,password,email,dob,developerKey);
				developers.add(developer);
			}
			display_developers(developers);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}		
		return developers;
	}
	
	
	//	3.	Developer findDeveloperById(int developerId)
	//	returns a joined record from Developer and Person tables whose id field 
	//  is equal to the developerId parameter
	public Developer findDeveloperById(int developerId) {
		
		Developer developer = new Developer();
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_DEVELOPER_BY_ID);
			statement.setInt(1, developerId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String username = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String dob = result.getString("dob");
				String developerKey = result.getString("developerKey");
				developer = new Developer(id, firstname,lastname,username,password,email,dob,developerKey);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
		return developer;
	}
	
	//4.Developer findDeveloperByUsername(String username)
	// returns a joined record from Developer and Person tables whose username field matches the parameter
	public Developer findDeveloperByUsername(String username) {
		
		Developer developer = new Developer();
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
			statement.setString(1, username);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String user = result.getString("username");
				String password = result.getString("password");
				String email = result.getString("email");
				String dob = result.getString("dob");
				String developerKey = result.getString("developerKey");
				developer = new Developer(id, firstname,lastname,user,password,email,dob,developerKey);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return developer;
	}
	
	//5. Developer findDeveloperByCredentials(String username, String password)
	// returns a joined record from Developer and Person tables whose username 
	// and password fields match the parameters
	public Developer findDeveloperByCredentials(String username,String password) {
		
		Developer developer = new Developer();
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				String user = result.getString("username");
				String pass = result.getString("password");
				String email = result.getString("email");
				String dob = result.getString("dob");
				String developerKey = result.getString("developerKey");
				developer = new Developer(id, firstname,lastname,user,pass,email,dob,developerKey);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return developer;
	}
	
	// 6. int updateDeveloper(int developerId, Developer developer)
	// updates records in Developer and Person tables whose id field is 
	// equal to developerId parameter. New record field values are set to
	// the values in the developer instance parameter
	public int updateDeveloper(int developerId, Developer developer) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(UPDATE_DEVELOPER);
			statement.setString(1,developer.getFirstname());
			statement.setString(2,developer.getLastname());
			statement.setString(3,developer.getUsername());
			statement.setString(4,developer.getPassword());
			statement.setString(5,developer.getEmail());
			statement.setString(6,developer.getDob());
			statement.setString(7,developer.getDeveloperKey());
			statement.setInt(8,developerId);
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
	
	//7. int deleteDeveloper(int developerId)
	//deletes records from Developer and Person tables whose 
	// id field is equal to developerId parameter
	public int deleteDeveloper(int developerId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_DEVELOPER);
			statement.setInt(1,developerId);
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
	
	// displays developer list
	public void display_developers(ArrayList<Developer>developers) {
		
		for(Developer d: developers) {
			System.out.println(d);
		}
	}
	
	// instance of DeveloperDao
	private static DeveloperDao instance = null;
	
	private DeveloperDao() { }
	
	public static DeveloperDao getInstance() {
	    if(instance == null) {
	        instance = new DeveloperDao();
	    }
	    return instance;
	}
	
}
