package edu.neu.cs5200.orm.jpa.daos;

public class TestDao {

	
	

	public static void main(String[] args) {
		
		ActorDao adao = new ActorDao();
		MovieDao mdao = new MovieDao();
		MovieLibraryDao mldao = new MovieLibraryDao();
		DirectorDao ddao = new DirectorDao();
		
		adao.test();
		ddao.test();
		mdao.test();
		mldao.test();
		
	}
	
	
}
