package com.kenesis.api;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {
	private static final String DRIVER="com.mysql.cj.jdbc.Driver";
	private static final String URL="jdbc:mysql://luyin.iptime.org:8463/kenesis?";
	private static final String USER="luyin";
	private static final String PW="lima9361";
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
