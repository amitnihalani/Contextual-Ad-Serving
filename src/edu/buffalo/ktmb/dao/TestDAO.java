package edu.buffalo.ktmb.dao;

import java.sql.Connection;
import java.sql.Statement;

import edu.buffalo.ktmb.db.DBManager;

public class TestDAO {

	
	public boolean addEmployee(int id, String name, int salary) {
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = DBManager.getInstance().getConnection();
			stmt = con.createStatement();
			String query = "INSERT INTO emp VALUES(" + id + ",'" + name + "'," + salary +")";
			boolean flag = stmt.execute(query);
			if(flag) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
}
