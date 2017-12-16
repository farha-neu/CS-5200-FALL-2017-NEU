package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.farha.model.Page;



public class PageDao extends BaseDao {

	private final String CREATE_PAGE_FOR_WEBSITE="INSERT into page (title, description, visits, websiteId)"
												 + " VALUES (?,?,?,?)";
	private final String FIND_ALL_PAGES="SELECT * FROM page";
	
	private final String FIND_PAGE_BY_ID="SELECT * FROM page WHERE id=?";
	
	private final String FIND_PAGE_BY_TITLE="SELECT * FROM page WHERE `title`=? AND `websiteId`=?";
	
	private final String FIND_PAGES_FOR_WEBSITE="SELECT * FROM page WHERE websiteId=?";

	private final String UPDATE_PAGE="UPDATE page SET title= ? WHERE `id` = ?";
	
	private final String DELETE_PAGE = "DELETE FROM `page` where `id` = ?";
	
	private final String SELECT_LAST_UPDATED_PAGE_ID="SELECT `page`.`id` FROM `page` JOIN `website` " + 
			"ON `page`.`websiteId`=`website`.`id` " + 
			"WHERE `website`.`name`= ? " + 
			"AND `page`.`updated`= (SELECT `x`.`maxdate` " + 
			"FROM (SELECT max(`page`.`updated`) AS `maxdate` " + 
			"FROM `page`) AS `x`)";
	
	
	//	1.	int createPageForWebsite(int websiteId, Page page)
	//	inserts properties in page instance parameter into the Page table. 
	//	The page's websiteId foreign key refer to Website table primary key id whose 
	//	value is equal to the websiteId parameter
	public int createPageForWebsite(int websiteId, Page page) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_PAGE_FOR_WEBSITE);
			statement.setString(1, page.getTitle());
			statement.setString(2, page.getDescription());
		    statement.setInt(3, page.getVisits());
	    	statement.setInt(4, websiteId);
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
	
	//	2.	Collection<Page> findAllPages()
	//	returns all records from Page table as a Collection of Page instances
	public ArrayList<Page> findAllPages(){
		ArrayList<Page>pages = new ArrayList<Page>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_ALL_PAGES);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int websiteId = result.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,websiteId);
				pages.add(page);
			}
			display_pages(pages);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pages;
	}
	
	//	3.	Page findPageById(int pageId)
	//	returns a record from Page table whose id field is equal to the pageId parameter
	public Page findPageById(int pageId){
		ArrayList<Page>pages = new ArrayList<Page>();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_PAGE_BY_ID);
			statement.setInt(1, pageId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int websiteId = result.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,websiteId);
				pages.add(page);
			}
			display_pages(pages);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pages.get(0);
	}
	
	
	//	4.	Collection<Page> findPagesForWebsite(int websiteId)
	//	returns all records from Page table as a Collection of Page instances whose 
	//	websiteId is equal to the websiteId parameter
	public  ArrayList<Page> findPagesForWebsite(int websiteId){
		
		ArrayList<Page>pages = new ArrayList<Page>();
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_PAGES_FOR_WEBSITE);
			statement.setInt(1, websiteId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String title = result.getString("title");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int webId = result.getInt("websiteId");
				Page page = new Page(id,title,description,created,updated,visits,webId);
				pages.add(page);
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pages;
	}
	
	
	//	5.	int updatePage(int pageId, Page page)
	//	updates record in Page table whose id field is equal to pageId parameter. 
	//	New record field values are set to the values in the page instance parameter
	public int updatePage(int pageId, Page page) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(UPDATE_PAGE);
			statement.setString(1, page.getTitle());
	    	statement.setInt(2, pageId);
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
	
	
	//	6.	int deletePage(int pageId)
	//	deletes record from Page table whose id field is equal to pageId parameter
	public int deletePage(int pageId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_PAGE);
			statement.setInt(1,pageId);
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
	
	// finds the page id of last updated page. This id will be used to remove
	// last updated page of a website
	public int findLastUpdatedPageId(String websiteName){
		
		int id = -1;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(SELECT_LAST_UPDATED_PAGE_ID);
			statement.setString(1, websiteName);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				id=result.getInt("id");	
			}
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
	
	// finds a page by its title and websiteId. 
	public Page findPageByTitle(String title, int websiteId){
		
		Page page = null;
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_PAGE_BY_TITLE);
			statement.setString(1, title);
			statement.setInt(2, websiteId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String t = result.getString("title");
				String description = result.getString("description");
				String created = result.getString("created");
				String updated = result.getString("updated");
				int visits = result.getInt("visits");
				int wId = result.getInt("websiteId");
				page = new Page(id,t,description,created,updated,visits,wId);
				
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	// displays all pages inserted
	public void display_pages(ArrayList<Page>pages) {
		for(Page p: pages) {
			System.out.println(p);
		}	
	}
	
	
	// instance of PageDao
	private static PageDao instance = null;
	
	private PageDao() { }
	
	public static PageDao getInstance() {
	    if(instance == null) {
	        instance = new PageDao();
	    }
	    return instance;
	}
	

}
