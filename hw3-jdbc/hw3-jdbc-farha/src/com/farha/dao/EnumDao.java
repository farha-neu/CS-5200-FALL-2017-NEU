package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EnumDao extends BaseDao {
	
	private final String INSERT_ROLE = "INSERT INTO `role` (`name`) VALUES (?)";
	private final String INSERT_PRIVILEDGE = "INSERT INTO `priviledge` (`name`) VALUES (?)";
	private final String FIND_ROLE_ID = "SELECT `id` FROM `role` WHERE `name`=?";
	
	
	// inserts role name in Role table
	public int insertRole(String role) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(INSERT_ROLE);
			statement.setString(1,role);
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
	
	// inserts priviledge name in Priviledge table
	public int insertPriviledge(String priviledge) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(INSERT_PRIVILEDGE);
			statement.setString(1,priviledge);
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
	
	// finds role id by role name
	public int findRoleId(String name){
		int id = -1;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_ROLE_ID);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
			id=result.getInt("id");}
			statement.close();
			connection.close();	
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	return id;
	}
	
	// instance of EnumDao
	private static EnumDao instance = null;
	
	private EnumDao() { }
	
	public static EnumDao getInstance() {
	    if(instance == null) {
	        instance = new EnumDao();
	    }
	    return instance;
	}
	
	
}
