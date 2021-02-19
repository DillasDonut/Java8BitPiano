package org.alb.eightbitpiano;

import java.sql.*;
public class Database {
	static private Database instance;
	Connection cnx;
	// PreparedStatement  for player insert
	private PreparedStatement insertStatement;
	private PreparedStatement updateStatement;
	private PreparedStatement deleteStatement;
	
	
	private Database() {
		String url = "jdbc:mysql://localhost/songproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String user = "root", pswd = "root";
		try {
			cnx = DriverManager.getConnection(url,user,pswd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	static Database getInstance() {
		if(instance!=null) {
			return instance;
		}
		instance = new Database();
		return instance;
	}

/**
 * Execute an SQL query against the MySQL database
 * 
 * Example, execute an SQL query and iterate over the ResultSet :
 * 	var db = Database.getinstance();
 * 	var res =  db.query("SELECT * FROM MyTable");
 * 	while(res.next()){
 * 		sysout ....
 * 	}
 * 
 * @param queryString the sql query to execute
 * @return result of the given sql query
 */
	public ResultSet query(String queryString) {
		Statement stm;
		ResultSet res = null;
		try {
			stm = cnx.createStatement();
			res = stm.executeQuery(queryString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}

}
