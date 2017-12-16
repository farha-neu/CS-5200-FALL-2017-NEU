package com.farha.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.farha.model.HeadingWidget;
import com.farha.model.HtmlWidget;
import com.farha.model.ImageWidget;
import com.farha.model.Widget;
import com.farha.model.YoutubeWidget;

public class WidgetDao extends BaseDao{
	
	private final String CREATE_HEADING_WIDGET_FOR_PAGE = "INSERT into `widget`"
			+ " (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`size`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	
	private final String CREATE_HTML_WIDGET_FOR_PAGE = "INSERT into `widget`"
			+ " (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`html`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private final String CREATE_IMAGE_WIDGET_FOR_PAGE = "INSERT into `widget`"
			+ " (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,`order`,`src`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private final String CREATE_YOUTUBE_WIDGET_FOR_PAGE = "INSERT into `widget`"
			+ " (`name`,`type`,`width`,`height`,`cssClass`,`cssStyle`,`text`,"
			+ "`order`,`url`,`shareable`,`expandable`,`pageId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private final String FIND_ALL_WIDGETS = "SELECT * FROM `widget`";
	
	private final String FIND_WIDGET_BY_ID = "SELECT * FROM `widget` WHERE `id`=?";
	
	private final String FIND_WIDGET_BY_NAME = "SELECT * FROM `widget` WHERE `name`=?";
	
	private final String FIND_WIDGETS_FOR_PAGE = "SELECT * FROM `widget` WHERE `pageId`=?";
	
	
	private final String UPDATE_WIDGET_ORDER = "UPDATE `widget`"
			+ " SET `widget`.`order`= "
			+ "CASE "
			+ "WHEN `widget`.`id`= ? THEN ?  "
			+ "WHEN (? < ? AND `widget`.`id` <> ? AND "
			+ "`widget`.`order`> ? AND `widget`.`order`<= ?) "
			+ "THEN `widget`.`order`- 1  "
			+ "WHEN (? > ? AND `widget`.`id` <> ? AND `widget`.`order`<= ? AND `widget`.`order`>= ? )  "
			+ "THEN `widget`.`order`+ 1  "
			+ "ELSE `widget`.`order` "
			+ "END "
			+ "WHERE `widget`.`pageId`= ?";
	
	private final String DELETE_WIDGET = "DELETE FROM `widget` WHERE `id`=?";
	
	private final String SELECT_LAST_WIDGET_ID = "SELECT * FROM `widget` JOIN `page` " + 
			"ON `widget`.`pageId`=`page`.`id` " + 
			"WHERE `page`.`title`= ? " + 
			"AND `widget`.`order`= (SELECT `x`.`value` FROM "+
			"(SELECT max(`widget`.`order`) AS `value` FROM `widget`) AS `x`)";
		
	
//	1.	int createWidgetForPage(int pageId, Widget widget)
//	inserts properties in widget instance parameter into the Widget table.
//	The widget's pageId foreign key refer to Page table primary key id whose value is equal to the pageId parameter
	public int createWidgetForPage(int pageId,Widget widget) {
		int result = -1;
		if(widget instanceof HeadingWidget) {
			result = createHeadingWidgetForPage(pageId,(HeadingWidget)widget);
		}
		else if(widget instanceof HtmlWidget) {
			result = createHtmlWidgetForPage(pageId,(HtmlWidget)widget);
		}
		else if(widget instanceof ImageWidget) {
			result = createImageWidgetForPage(pageId,(ImageWidget)widget);
		}
		else if(widget instanceof YoutubeWidget) {
			result = createYoutubeWidgetForPage(pageId,(YoutubeWidget)widget);
		}
		return result;	
	}
	
	public int createHeadingWidgetForPage(int pageId, HeadingWidget widget) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_HEADING_WIDGET_FOR_PAGE);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setInt(9,widget.getSize());
	    	statement.setInt(10,pageId);
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
	
	public int createHtmlWidgetForPage(int pageId, HtmlWidget widget) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_HTML_WIDGET_FOR_PAGE);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getHtml());
	    	statement.setInt(10,pageId);
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
	
	public int createImageWidgetForPage(int pageId, ImageWidget widget) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_IMAGE_WIDGET_FOR_PAGE);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getSrc());
	    	statement.setInt(10,pageId);
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
	

	public int createYoutubeWidgetForPage(int pageId, YoutubeWidget widget) {
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(CREATE_YOUTUBE_WIDGET_FOR_PAGE);
			statement.setString(1, widget.getName());
			statement.setString(2, widget.getType());
			statement.setInt(3, widget.getWidth());
			statement.setInt(4, widget.getHeight());
			statement.setString(5, widget.getCssClass());
			statement.setString(6, widget.getCssStyle());
			statement.setString(7, widget.getText());
			statement.setInt(8,widget.getOrder());
			statement.setString(9,widget.getUrl());
			statement.setBoolean(10,widget.isShareable());
			statement.setBoolean(11,widget.isExpandable());
	    	statement.setInt(12,pageId);
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

	
//	2.	Collection<Widget> findAllWidgets()
//	returns all records from Widget table as a Collection of Widget instances
	public ArrayList<Widget> findAllWidgets(){
		
		ArrayList<Widget>widgets = new ArrayList<Widget>();
		Widget widget = new Widget();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_ALL_WIDGETS);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String type = result.getString("type");
				int width = result.getInt("width");
				int height = result.getInt("height");
				String cssClass = result.getString("cssClass");
				String cssStyle = result.getString("cssStyle");
				String text = result.getString("text");
				int order = result.getInt("order");
				int size = result.getInt("size");
				String html = result.getString("html");
				String url = result.getString("url");
				boolean shareable = result.getBoolean("shareable");
				boolean expandable = result.getBoolean("expandable");
				String src = result.getString("src");
				int pageId = result.getInt("pageId");
				
				if(type.equals("heading")) {
					widget = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,size);
				}
				else if(type.equals("html")) {
					widget = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,html);
				}
				else if(type.equals("image")) {
					widget = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,src);
				}
				else if(type.equals("youtube")) {
					widget = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,
							pageId,url,shareable,expandable);
				}
				
				widgets.add(widget);
			}
			display_widgets(widgets);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return widgets;
	}
	
	
//	3.	Widget findWidgetById(int widgetId)
//	returns a record from Widget table whose id field is equal to the widgetId parameter
	public Widget findWidgetById(int widgetId){
		
		Widget widget = new Widget();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WIDGET_BY_ID);
			statement.setInt(1, widgetId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String type = result.getString("type");
				int width = result.getInt("width");
				int height = result.getInt("height");
				String cssClass = result.getString("cssClass");
				String cssStyle = result.getString("cssStyle");
				String text = result.getString("text");
				int order = result.getInt("order");
				int size = result.getInt("size");
				String html = result.getString("html");
				String url = result.getString("url");
				boolean shareable = result.getBoolean("shareable");
				boolean expandable = result.getBoolean("expandable");
				String src = result.getString("src");
				int pageId = result.getInt("pageId");
				
				if(type.equals("heading")) {
					widget = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,size);
				}
				else if(type.equals("html")) {
					widget = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,html);
				}
				else if(type.equals("image")) {
					widget = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,src);
				}
				else if(type.equals("youtube")) {
					widget = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pageId,
							url,shareable,expandable);
				}
			}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return widget;
	}
	
	
	
	
//	4.	Collection<Widget> findWidgetsForPage(int pageId)
//	returns all records from Widget table as a Collection of Widget 
//	instances whose pageId is equal to the pageId parameter
	public ArrayList<Widget> findWidgetsForPage(int pageId){
		
		ArrayList<Widget>widgets = new ArrayList<Widget>();
		Widget widget = new Widget();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WIDGETS_FOR_PAGE);
			statement.setInt(1, pageId);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String type = result.getString("type");
				int width = result.getInt("width");
				int height = result.getInt("height");
				String cssClass = result.getString("cssClass");
				String cssStyle = result.getString("cssStyle");
				String text = result.getString("text");
				int order = result.getInt("order");
				int size = result.getInt("size");
				String html = result.getString("html");
				String url = result.getString("url");
				boolean shareable = result.getBoolean("shareable");
				boolean expandable = result.getBoolean("expandable");
				String src = result.getString("src");
				int pId = result.getInt("pageId");
				
				if(type.equals("heading")) {
					widget = new HeadingWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,size);
				}
				else if(type.equals("html")) {
					widget = new HtmlWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,html);
				}
				else if(type.equals("image")) {
					widget = new ImageWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,src);
				}
				else if(type.equals("youtube")) {
					widget = new YoutubeWidget(id,name,type,width,height,cssClass,cssStyle,text,order,pId,url,shareable,expandable);
				}
				
				widgets.add(widget);
			}
			display_widgets(widgets);
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return widgets;
	}

//	5.	int updateWidget(int widgetId, Widget widget)
//	updates record in Widget table whose id field is equal to widgetId parameter. 
//	New record field values are set to the values in the widget instance parameter
	public int updateWidget(int widgetId,Widget widget) {
		
		int result = -1;
		int pageId = findWidgetById(widgetId).getPageId();
		int preOrder = findWidgetById(widgetId).getOrder();
		int updatedOrder = widget.getOrder();
				
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(UPDATE_WIDGET_ORDER);
			statement.setInt(1, widgetId);
			statement.setInt(2, updatedOrder);
			statement.setInt(3, preOrder);
		    statement.setInt(4, updatedOrder);
		    statement.setInt(5, widgetId);
		    statement.setInt(6, preOrder);
		    statement.setInt(7, updatedOrder);
		    statement.setInt(8, preOrder);
		    statement.setInt(9, updatedOrder);
		    statement.setInt(10, widgetId);
		    statement.setInt(11, preOrder);
		    statement.setInt(12, updatedOrder);
	    	statement.setInt(13, pageId);
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
	
	// returns a record from Widget table whose name field is equal to parameter widgetName
	public Widget findWidgetByName(String widgetName){
		
		Widget widget = new Widget();
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(FIND_WIDGET_BY_NAME);
			statement.setString(1, widgetName);
			ResultSet result = statement.executeQuery(); 
			while(result.next()) {
				int id=result.getInt("id");
				String name = result.getString("name");
				String type = result.getString("type");
				int order = result.getInt("order");
				int pageId = result.getInt("pageId");
				widget = new Widget(id,name,type,order,pageId);
				}
			statement.close();
			connection.close();	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return widget;
	}
	
	
//	6.	int deleteWidget(int widgetId)
//	deletes record from Widget table whose id field is equal to widgetId parameter
	public int deleteWidget(int widgetId) {
		
		int result = -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(DELETE_WIDGET);
			statement.setInt(1,widgetId);
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
	
	// It return the id of last widget. Last widget is the one with the highest value in the order field.
	// This function will be used to delete the last widget of a page
	public int findLastWidgetId(String pageTitle){
		
		int id= -1;
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			PreparedStatement statement = null;
			statement = connection.prepareStatement(SELECT_LAST_WIDGET_ID);
			statement.setString(1, pageTitle);
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
	
	
	
	
	// displays all inserted widgets
	public void display_widgets(ArrayList<Widget>widgets) {
		for(Widget w : widgets) {
			System.out.println(w);
		}
	}
	
	
	
	// instance of WidgetDao
	private static WidgetDao instance = null;
	
	private WidgetDao() { }
	
	public static WidgetDao getInstance() {
	    if(instance == null) {
	        instance = new WidgetDao();
	    }
	    return instance;
	}

}
