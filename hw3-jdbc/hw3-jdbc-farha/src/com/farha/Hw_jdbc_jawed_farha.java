package com.farha;

import java.util.ArrayList;

import com.farha.dao.DeveloperDao;
import com.farha.dao.EnumDao;
import com.farha.dao.PageDao;
import com.farha.dao.RoleDao;
import com.farha.dao.WebsiteDao;
import com.farha.dao.WidgetDao;
import com.farha.model.Developer;
import com.farha.model.HeadingWidget;
import com.farha.model.HtmlWidget;
import com.farha.model.ImageWidget;
import com.farha.model.Page;
import com.farha.model.Website;
import com.farha.model.Widget;
import com.farha.model.YoutubeWidget;

public class Hw_jdbc_jawed_farha {
	private static DeveloperDao developerDao = DeveloperDao.getInstance();
	private static WebsiteDao websiteDao = WebsiteDao.getInstance();
	private static EnumDao enumDao = EnumDao.getInstance();
	private static RoleDao roleDao = RoleDao.getInstance();
	private static PageDao pdao = PageDao.getInstance();
	private static WidgetDao widgetDao = WidgetDao.getInstance();
	
	
	public static void main(String[] args) {
		//1. Create the following developers and users. Insert into the correct tables depending on the type
		Developer alice = new Developer("Alice","Wonder","alice","alice","alice@wonder.com","4321rewq");
		developerDao.createDeveloper(alice);
		Developer bob = new Developer("Bob","Marley","bob","bob","bob@marley.com","5432trew");
		developerDao.createDeveloper(bob);
		Developer charlie = new Developer("Charles","Garcia","charlie","charlie","charliechuch@garcia.com","6543ytre");
		developerDao.createDeveloper(charlie);
		Developer dan = new Developer("Dan", "Martin", "dan", "dan", "dan@martin.com","7654fda");
		developerDao.createDeveloper(dan);
		Developer ed = new Developer("Ed","Karaz", "ed", "ed", "ed@kar.com","5678dfgh");
		developerDao.createDeveloper(ed);
		
		System.out.println("List of inserted developers:");
		developerDao.findAllDevelopers();
		
		// inserting roles
		enumDao.insertRole("owner");
		enumDao.insertRole("admin");
		enumDao.insertRole("writer");
		enumDao.insertRole("editor");
		enumDao.insertRole("reviewer");
		
		// inserting priviledge
		enumDao.insertPriviledge("create");
		enumDao.insertPriviledge("read");
		enumDao.insertPriviledge("update");
		enumDao.insertPriviledge("delete");
		
		// retrieving ids of developers by username
		int aliceId = developerDao.findDeveloperByUsername("alice").getId();
		int bobId = developerDao.findDeveloperByUsername("bob").getId();
		int charlieId = developerDao.findDeveloperByUsername("charlie").getId();
		
		//2. Create the following web sites for the developers above.
		//For both the created field and updated field, use the date your 
		//assignment will be graded, e.g., do not hardcode 
		Website facebook = new Website("Facebook","an online social media and social networking service",1234234);
		websiteDao.createWebsiteForDeveloper(aliceId, facebook);	
		Website twitter = new Website("Twitter","an online news and social networking service",4321543);
		websiteDao.createWebsiteForDeveloper(bobId, twitter);	
		Website wikipedia = new Website("Wikipedia","a free online encyclopedia",3456654);
		websiteDao.createWebsiteForDeveloper(charlieId, wikipedia);	
		Website cnn = new Website("CNN","an American basic cable and satellite television news channel",6543345);
		websiteDao.createWebsiteForDeveloper(aliceId, cnn);	
		Website cnet = new Website("CNET",
				"an American media website that publishes reviews,"
				+ " news, articles, blogs, podcasts and videos on"
				+ " technology and consumer electronics",5433455);
		websiteDao.createWebsiteForDeveloper(bobId, cnet);	
		Website gizmodo = new Website("Gizmodo","a design, technology, science and science fiction"
				+ " website that also writes articles on politics",4322345);
		websiteDao.createWebsiteForDeveloper(charlieId, gizmodo);
		
		System.out.println("*************\nList of inserted websites:");
		websiteDao.findAllWebsites();
		
		
		// retrieving website ids by name
		int facebookId = websiteDao.findWebsiteByName("Facebook").getId();
		int twitterId = websiteDao.findWebsiteByName("Twitter").getId();
		int wikipediaId = websiteDao.findWebsiteByName("Wikipedia").getId();
		int cnnId = websiteDao.findWebsiteByName("CNN").getId();
		int cnetId = websiteDao.findWebsiteByName("CNET").getId();
		int gizmodoId = websiteDao.findWebsiteByName("Gizmodo").getId();
		
		// retrieving role ids by name
		int ownerRoleId = enumDao.findRoleId("owner");
		int editorRoleId = enumDao.findRoleId("editor");
		int adminRoleId = enumDao.findRoleId("admin");	
		int reviewerRoleId = enumDao.findRoleId("reviewer");	
		int writerRoleId = enumDao.findRoleId("writer");	
		
		//owners
		roleDao.assignWebsiteRole(aliceId, facebookId, ownerRoleId);
		roleDao.assignWebsiteRole(bobId, twitterId, ownerRoleId);
		roleDao.assignWebsiteRole(charlieId, wikipediaId, ownerRoleId);
		roleDao.assignWebsiteRole(aliceId, cnnId, ownerRoleId);
		roleDao.assignWebsiteRole(bobId, cnetId, ownerRoleId);
		roleDao.assignWebsiteRole(charlieId, gizmodoId, ownerRoleId);	
		
		
		//editors
		roleDao.assignWebsiteRole(bobId, facebookId, editorRoleId);
		roleDao.assignWebsiteRole(charlieId, twitterId, editorRoleId);
		roleDao.assignWebsiteRole(aliceId, wikipediaId, editorRoleId);
		roleDao.assignWebsiteRole(bobId, cnnId, editorRoleId);
		roleDao.assignWebsiteRole(charlieId, cnetId, editorRoleId);
		roleDao.assignWebsiteRole(aliceId, gizmodoId, editorRoleId);	
		
		//admins
		roleDao.assignWebsiteRole(charlieId, facebookId, adminRoleId);
		roleDao.assignWebsiteRole(aliceId, twitterId, adminRoleId);
		roleDao.assignWebsiteRole(bobId, adminRoleId, adminRoleId);
		roleDao.assignWebsiteRole(charlieId, cnnId, adminRoleId);
		roleDao.assignWebsiteRole(aliceId, cnetId, adminRoleId);
		roleDao.assignWebsiteRole(bobId, gizmodoId, adminRoleId);	
		
		
		//3. Create the following pages for the web sites above. 
		// Use the semester's start date for the created field.
		// Use the assignment's due date for the updated field.
		Page cnetHome = new Page("Home","Landing page",123434);
		pdao.createPageForWebsite(cnetId,cnetHome);	
		Page gizmodoAbout = new Page("About","Website description",234545);
		pdao.createPageForWebsite(gizmodoId,gizmodoAbout);		
		Page wikiContact = new Page("Contact","Addresses, phones, and contact info",345656);
		pdao.createPageForWebsite(wikipediaId,wikiContact);		
		Page cnnPreferences = new Page("Preferences","Where users can configure their preferences",456776);
		pdao.createPageForWebsite(cnnId,cnnPreferences);		
		Page cnetProfile = new Page("Profile","Users can configure their personal information",567878);
		pdao.createPageForWebsite(cnetId,cnetProfile);		
		
		System.out.println("**************\nList of inserted pages:");
		pdao.findAllPages();
		
		// retrieving page ids of websites by website id and page title
		int cnetHomeId = pdao.findPageByTitle("Home", cnetId).getId();
		int gizmodoAboutId = pdao.findPageByTitle("About", gizmodoId).getId();
		int wikiContactId = pdao.findPageByTitle("Contact", wikipediaId).getId();
		int cnnPrefId = pdao.findPageByTitle("Preferences", cnnId).getId();
		int cnetProfileId = pdao.findPageByTitle("Profile", cnetId).getId();
		
		
		//editors
		roleDao.assignPageRole(aliceId, cnetHomeId, editorRoleId);
		roleDao.assignPageRole(bobId, gizmodoAboutId, editorRoleId);
		roleDao.assignPageRole(charlieId, wikiContactId, editorRoleId);
		roleDao.assignPageRole(aliceId, cnnPrefId, editorRoleId);
		roleDao.assignPageRole(bobId, cnetProfileId, editorRoleId);
		
		//reviewers
		roleDao.assignPageRole(bobId, cnetHomeId, reviewerRoleId);
		roleDao.assignPageRole(charlieId, gizmodoAboutId, reviewerRoleId);
		roleDao.assignPageRole(aliceId, wikiContactId, reviewerRoleId);
		roleDao.assignPageRole(bobId, cnnPrefId, reviewerRoleId);
		roleDao.assignPageRole(charlieId, cnetProfileId, reviewerRoleId);
		
		//writers
		roleDao.assignPageRole(charlieId, cnetHomeId, writerRoleId);
		roleDao.assignPageRole(aliceId, gizmodoAboutId, writerRoleId);
		roleDao.assignPageRole(bobId, wikiContactId, writerRoleId);
		roleDao.assignPageRole(charlieId, cnnPrefId, writerRoleId);
		roleDao.assignPageRole(aliceId, cnetProfileId,writerRoleId);	
		
		//4. Create the following widgets for the pages shown
		HeadingWidget headingWidget = new HeadingWidget("head123","heading","Welcome",0,2); //name, type, text, order, size
		widgetDao.createWidgetForPage(cnetHomeId, headingWidget);		
		HtmlWidget htmlWidget = new HtmlWidget("post234","html","<p>Lorem</p>",0); //name, type, text, order
		widgetDao.createWidgetForPage(gizmodoAboutId, htmlWidget);		
		HeadingWidget headingWidget2 = new HeadingWidget("head345","heading","Hi",1,2); //name, type, text, order, size
		widgetDao.createWidgetForPage(wikiContactId, headingWidget2);		
		HtmlWidget htmlWidget2 = new HtmlWidget("intro456","html","<h1>Hi</h1>",2); //name, type, text, order
		widgetDao.createWidgetForPage(wikiContactId, htmlWidget2);		
		ImageWidget imageWidget = new ImageWidget("image345","image",50,100,3,"/img/567.png"); // name,type,width,height,order,src
		widgetDao.createWidgetForPage(wikiContactId, imageWidget);		
		YoutubeWidget youtubeWidget = new YoutubeWidget("video456","youtube",
				400,300,0,"https://youtu.be/h67VX51QXiQ"); // name,type,width,height,order,url
		widgetDao.createWidgetForPage(cnnPrefId, youtubeWidget);
		System.out.println("**************\nList of inserted widgets:");
		widgetDao.findAllWidgets();
		
		//1. Update widget - Update the relative order of widget head345
		//on the page so that it's new order is 3. 
		//Note that the other widget's order needs to update as well
		Widget widgetHead345 = new Widget("head345",2);
		widgetDao.updateWidget(widgetDao.findWidgetByName("head345").getId(),widgetHead345);
		
		//2. Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		ArrayList<Page> pages = pdao.findPagesForWebsite(cnetId);
		for(Page p: pages) {
			pdao.updatePage(p.getId(), new Page("CNET - "+p.getTitle()));
		}
		
		//3. Update roles - Swap Charlie's and Bob's role in CNET's Home page
		roleDao.swapRoles(bobId, charlieId, cnetHomeId);
		
		//1. Delete widget - Remove the last widget in the Contact page.
		// The last widget is the one with the highest value in the order field
		widgetDao.deleteWidget(widgetDao.findLastWidgetId("Contact"));
		
		
		//2. Delete page - Remove the last updated page in Wikipedia
		pdao.deletePage(pdao.findLastUpdatedPageId("Wikipedia"));
		
		//3. Delete website - Remove the CNET web site, as well as all 
		// related roles and privileges relating developers to the Website and Pages
		websiteDao.deleteWebsite(cnetId);
}
	
	
}
