package com.farha.dao;

import java.sql.Connection;

public class BaseDao {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost/hw3_jawed_farha_fall_2017?autoReconnect=true&useSSL=false";
    final String USER = "cs5200";
    final String PASS = "cs5200";
    Connection connection = null;
}
