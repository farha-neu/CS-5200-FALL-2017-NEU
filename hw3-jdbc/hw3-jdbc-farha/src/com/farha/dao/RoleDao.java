package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.farha.model.Role;

public class RoleDao extends BaseDao{
	
	private final String ASSIGN_WEBSITE_ROLE = "INSERT INTO `websiterole` (`developerId`, `websiteId`,`roleId`) VALUES (?,?,?)";
	private final String ASSIGN_PAGE_ROLE = "INSERT INTO `pagerole` (`developerId`, `pageId`,`roleId`) VALUES (?,?,?)";
	private final String DELETE_WEBSITE_ROLE = "DELETE FROM `websiterole` WHERE `developerId`=? AND `websiteId`=? AND `roleId`=?";
	private final String DELETE_PAGE_ROLE = "DELETE FROM `pagerole` WHERE `developerId`=? AND `pageId`=? AND `roleId`=?";
	private final String FIND_ROLE_AND_ID = "SELECT `pagerole`.`id`,`pagerole`.`roleId`"
			+ " FROM `pagerole`,`developer`,`page`,`person` " + 
			" WHERE `pagerole`.`developerId`=`developer`.`id`" + 
			" AND `pagerole`.`pageId`=`page`.`id`" + 
			" AND `developer`.`id`=`person`.`id`" +
			" AND `developer`.`id`= ?" + 
			" AND `page`.`id`= ?";
	
	private final String SWAP_ROLES = "UPDATE `pagerole` AS `p1`, `pagerole` AS `p2` " + 
			"SET `p1`.`roleId` = `p2`.`roleId`,`p2`.`roleId`= ? " + 
			"WHERE (`p1`.`id`,`p2`.`id`)=(?,?)";

	




	//1. assignWebsiteRole(int developerId, int websiteId, int roleId)
	//inserts into table Role a record that assigns a developer whose id is
	//developerId, the role with roleId, to the website with websiteId
	public int assignWebsiteRole(int developerId, int websiteId, int roleId) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(ASSIGN_WEBSITE_ROLE);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, roleId);
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
	
	
	//2.	assignPageRole(int developerId, int pageId, int roleId)
	//inserts into table Role a record that assigns a developer whose 
	//id is developerId, the role with roleId, to the page with pageId
	public int assignPageRole(int developerId, int pageId, int roleId) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(ASSIGN_PAGE_ROLE);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, roleId);
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
	
	
	//3.	deleteWebsiteRole(int developerId, int websiteId, int roleId)
	//deletes from table Role a record that removes roleId from developerId, on websiteId
	public int deleteWebsiteRole(int developerId, int websiteId, int roleId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_WEBSITE_ROLE);
			statement.setInt(1,developerId);
			statement.setInt(2, websiteId);
		    statement.setInt(3, roleId);
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
	
	
	//	4.	deletePageRole(int developerId, int pageId, int roleId)
	//	deletes from table Role a record that removes roleId from developerId, on pageId
	public int deletePageRole(int developerId, int pageId, int roleId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_PAGE_ROLE);
			statement.setInt(1,developerId);
			statement.setInt(2, pageId);
		    statement.setInt(3, roleId);
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
	
	
	// swaps roles between 2 developers
	public int swapRoles(int developerId1, int developerId2, int pageId) {
		
		int result = -1;
		int role1 = findRoleAndId(developerId1,pageId).getRoleId();
		int id1 = findRoleAndId(developerId1,pageId).getId();
		int id2 = findRoleAndId(developerId2,pageId).getId();
	
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(SWAP_ROLES);
	    	statement.setInt(1, role1);
	    	statement.setInt(2, id1);
	    	statement.setInt(3, id2);
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
	
	// helper function of swapRoles()
	public Role findRoleAndId(int developerId, int pageId){
		Role role = new Role();
		try {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
		PreparedStatement statement = null;
		statement = connection.prepareStatement(FIND_ROLE_AND_ID);
		statement.setInt(1, developerId);
		statement.setInt(2, pageId);
		ResultSet result = statement.executeQuery(); 
		while(result.next()) {
			int id=result.getInt("id");
			int roleId = result.getInt("roleId");
			role = new Role(id,roleId);
		}
		statement.close();
		connection.close();	
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	
		return role;
	}
	
	private static RoleDao instance = null;
	
	private RoleDao() { }
	
	public static RoleDao getInstance() {
	    if(instance == null) {
	        instance = new RoleDao();
	    }
	    return instance;
	}
	
}
