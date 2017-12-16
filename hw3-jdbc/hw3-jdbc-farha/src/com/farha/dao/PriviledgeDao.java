package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PriviledgeDao extends BaseDao {
	private final String ASSIGN_WEBSITE_PRIVILEDGE = "INSERT INTO `websitepriviledge` (`developerId`, `websiteId`,`priviledgeId`) VALUES (?,?,?)";
	private final String ASSIGN_PAGE_PRIVILEDGE = "INSERT INTO `pagepriviledge` (`developerId`, `pageId`,`priviledgeId`) VALUES (?,?,?)";
	private final String DELETE_WEBSITE_PRIVILEDGE = "DELETE FROM `websitepriviledge` WHERE `developerId`=? AND `websiteId`=? AND `priviledgeId`=?";
	private final String DELETE_PAGE_PRIVILEDGE = "DELETE FROM `pagepriviledge` WHERE `developerId`=? AND `pageId`=? AND `priviledgeId`=?";

	
//	1.	assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId)
//	inserts into table Priviledge a record that assigns 
//	a developer whose id is developerId, the priviledge with priviledgeId, 
//	to the website with websiteId
	public int assignWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(ASSIGN_WEBSITE_PRIVILEDGE);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, priviledgeId);
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
	
	
	
//	2.	assignPagePriviledge(int developerId, int pageId, int priviledgeId)
//	inserts into table Priviledge a record that assigns a developer whose 
//  id is developerId, the priviledge with priviledgeId, to the page with pageId
	public int assignPagePriviledge(int developerId, int pageId, int priviledgeId) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(ASSIGN_PAGE_PRIVILEDGE);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, priviledgeId);
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
	
	
//	3.	deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId)
//	deletes from table Priviledge a record that removes priviledgeId from developerId, on websiteId
	public int deleteWebsitePriviledge(int developerId, int websiteId, int priviledgeId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_WEBSITE_PRIVILEDGE);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, priviledgeId);
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
	
//	4.	deletePagePriviledge(int developerId, int pageId, int priviledgeId)
//	deletes from table priviledge a record that removes priviledgeId from developerId, on pageId
	public int deletePagePriviledge(int developerId, int pageId, int priviledgeId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_PAGE_PRIVILEDGE);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, priviledgeId);
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
	
	// instance of PriviledgeDao
	private static PriviledgeDao instance = null;
	
	private PriviledgeDao() { }
	
	public static PriviledgeDao getInstance() {
	    if(instance == null) {
	        instance = new PriviledgeDao();
	    }
	    return instance;
	}
		
}
